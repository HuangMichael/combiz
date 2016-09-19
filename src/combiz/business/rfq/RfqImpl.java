package combiz.business.rfq;

import combiz.domain.contract.Contline;
import combiz.domain.contract.Contract;
import combiz.domain.inventory.Item;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zul.Messagebox;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */

public class RfqImpl extends IBOBaseImpl
implements RfqSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfq))
			throw new Exception("要删除的对象传递不正确！");
		Rfq rfq = (Rfq)obj;
		//得到所有的与主记录想关的报价行
		
		List rfqquoteline = this.getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rfq.getRfqnum()+"'");
		//删除这些记录
		this.getBaseDao().deleteBatch(rfqquoteline);
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		
		//询价单行
		this.deleteAllChild(obj, "rfqline");
		//询价供应商
		this.deleteAllChild(obj, "rfqvendor");
		//报价行
		this.deleteAllChild(obj,"");
	}
	
	/***
	 * 
	 * 通过询价单行和询价供应商自动生成报价行，其实数据来源于询价单和询价供应商
	 * @param obj
	 * @throws Exception
	 * @蒋祖兵 2007-8-17 上午10:02:53
	 */
	public void createquot(Object obj) throws Exception{
		if(!(obj instanceof Rfq))
			throw new Exception("对像传递不正确!");
		Rfq rfq = (Rfq)obj;
		
		List rlList = this.getBaseDao().findWithQuery(Rfqline.class, "rfqnum='"+rfq.getRfqnum()+"'");
		List rvList = this.getBaseDao().findWithQuery(Rfqvendor.class, "rfqnum='"+rfq.getRfqnum()+"'");
	
		for(int i = 0;i<rlList.size();i++){
			Rfqline rl = (Rfqline)rlList.get(i);
			for(int j = 0;j<rvList.size();j++){
				Rfqquoteline  rql = new Rfqquoteline();
				Rfqvendor rv = (Rfqvendor)rvList.get(j);
//				询价单
				rql.setRfqnum(rfq.getRfqnum());
				//询价单行
				rql.setRfqlinenum(rl.getRfqlinenum());
//				库存项目,描述,
				rql.setItemnum(rl.getItemnum());
				rql.setDescription(rl.getDescription());
				//制造商,供应商型号
				rql.setManufacturer(rl.getManufacturer());
				rql.setModelnum(rl.getModelnum());
				//订购数量,订购单位,单价,行总价
				rql.setOrderqty(rl.getOrderqty());
				rql.setOrderunit(rl.getOrderunit());
				//rql.setUnitcost(rl.getUnitcost());
				//rql.setLinecost(rl.getOrderqty()*rl.getUnitcost());
				//转换系数,输入日期,录入人
				rql.setConversion(rl.getConversion());
				rql.setEnterdate(rl.getEnterdate());
				rql.setEnterby(rl.getEnterby());
				//授予,税额
				rql.setIsawarded("否");
				rql.setTax(0.00);
				//服务,备忘
				rql.setIsservice(rl.getIsservice());
				rql.setRemark(rl.getRemark());
				//供应 商
				rql.setVendor(rv.getVendor());
				//rql.setSitenum(rl.getSitenum());
				//rql.setCorpnum(rl.getCorpnum());
				super.save(rql);
				
				
			}
		}
		
	}
	/* 
	 * 功能：通过询价供应商及对应的报价行生成采购单和采购单行
	 * 作者：李建红
	 * 日期：Sep 17, 20084:33:54 PM
	 */
	public void CreatePo(List rvlist,Rfq rfq) throws Exception {
		int count = 0;
		for (int a=0;a<rvlist.size();a++) {
			Rfqvendor rv =(Rfqvendor) rvlist.get(a);

			if(!(rv instanceof Rfqvendor))
				throw new Exception("对像传递错误：非对象询价供应商!");	
		
	
		
		//循环报价行
			List rl = this.getBaseDao().findWithQuery(Rfqquoteline.class, 
					"rfqnum='"+rv.getRfqnum()+"' and vendor='"+rv.getVendor()+"' and isawarded='是'");
			int vendorcount = 0;
			String ponum = null;
			Double totalcost = 0d;
			Po po = null;
			/*if (rl.size() <=0)
				throw new Exception("无法生成订单，请先对该供应商'"+rv.getVendor()+"'的报价行进行授权操作！");*/

		
			for(int i=0;i<rl.size();i++)
			{
				Rfqquoteline rol = (Rfqquoteline)rl.get(i);
				List rfqlinelist = this.getBaseDao().findWithQuery(Rfqline.class, 
						"rfqnum='"+rol.getRfqnum()+"' and rfqlinenum="+rol.getRfqlinenum()+" and itemnum='"+rol.getItemnum()+"' and ponum is null");
				if(rfqlinelist.size()<=0)
				{
					continue;
				}
				Rfqline rfqline = (Rfqline) rfqlinelist.get(0);
				vendorcount++;
				if(vendorcount == 1)//该供应商第一次生成采购单！
				{
					po = new Po();
					count ++;
					ponum = this.genInskey("PONUM");
					po.setDescription(rfq.getDescription());
					po.setStatus("询价单生成");
					po.setReceipts("未接收");
					po.setStatusdate(new Date());
					po.setChangeby(this.getUserInfo().getLabornum());
					po.setChangedate(new Date());
					po.setPonum(ponum);
					po.setVendor(rv.getVendor());//供应商
					po.setContact(rv.getContact());//联系人
					po.setFreightterms(rv.getFreightterms());//运输条款
					po.setPaymentterms(rv.getPaymentterms());//支付条款
					po.setShipvia(rv.getShipvia());//运输方式
					po.setTotalcost(totalcost);
					//po.setSitenum(this.getLaborInfo().getSitenum());
					//po.setCorpnum(this.getLaborInfo().getCorpnum());
					this.getBaseDao().saveObject(po);
					
				}
				//新建采购单行
				Poline pl = new Poline();
				Long polinenum = Long.parseLong(Integer.toString(i+1));
				pl.setPolinenum(polinenum);
				pl.setPonum(ponum);
				pl.setItemnum(rol.getItemnum());
				pl.setDescription(rol.getDescription());
				if(Util.isNull(rfqline.getWarehouse()))
					throw new Exception("询价单行["+rol.getItemnum()+"]的仓库字段为空,无法生成订单!");
				pl.setWarehouse(rfqline.getWarehouse());
				pl.setOrderunit(rol.getOrderunit());
				if(rol.getConversion()==null)
				{
					pl.setConversion(0.00);
				}
				else
				{
					pl.setConversion(rol.getConversion());
				}
				//
				Double orderqty = (rol.getOrderqty()==null?0.0D:rol.getOrderqty());
				Double unitcost = (rol.getUnitcost()==null?0.0D:rol.getUnitcost());
				pl.setOrderqty(orderqty);
				pl.setUnitcost(unitcost);
				Double linecost = orderqty * unitcost;
				totalcost = totalcost + linecost;
				pl.setLinecost(linecost);
				pl.setTaxunitcost(unitcost);
				pl.setTaxlinecost(linecost);
				pl.setReceivedqty(0.0);
				pl.setReceivedunitcost(0.0);
				pl.setReceivedtotalcost(0.0);
				pl.setTaxcode(rol.getTaxcode());
				pl.setTaxrate(0.0);
				pl.setTax(rol.getTax());
				pl.setRejectedqty(0.0);
				pl.setEnterdate(rol.getEnterdate());
				pl.setEnterby(this.getUserInfo().getLabornum());
				pl.setManufacturer(rol.getManufacturer());
				pl.setModelnum(rol.getModelnum());
				pl.setService(rol.getIsservice());
				pl.setRemark(rol.getRemark());
				pl.setReceiptscomplete("否");
				pl.setInspection("是");
				pl.setLoadedcost(0.0);
				pl.setProrated("否");
				//pl.setCorpnum(this.getLaborInfo().getCorpnum());
				//pl.setSitenum(this.getLaborInfo().getSitenum());
				this.getBaseDao().saveObject(pl);
				
				//同时更新询价单行
				rfqline.setPonum(ponum);
				rfqline.setPolinenum(polinenum);
				super.update(rfqline);
				
				
				//同时更新采购申请行
				List prlines = this.getBaseDao().findWithQuery(Prline.class, 
						"rfqnum='"+rol.getRfqnum()+"' and rfqlinenum="+rol.getRfqlinenum()+" and itemnum='"+rol.getItemnum()+"'");
				for(int j=0;j<prlines.size();j++)
				{
					Prline prline = (Prline) prlines.get(j);
					prline.setPonum(ponum);
					prline.setPolinenum(polinenum);
					super.update(prline);
				}
				
				po.setTotalcost(totalcost);//总价
				this.getBaseDao().updateObject(po);
				


			}
		}
		if(count == 0)
		{
			throw new Exception("您选中的供应商已经全部生成过订单，此次操作没有生成任何订单！");
		}
		
		
	}
	
	/* 
	 * 功能：根据授予的询价单行生成合同行
	 * 作者：李阳
	 * 日期：Oct 24, 2008  4:07:57 PM
	 */
	public void createcont(List rvlist,List vendorlist,Rfq rfq) throws Exception {
		
		if(vendorlist.size() >0)
		{
			//按照供应商进行分组后，取出供应商的信息，分别生成合同行
			for(int j=0;j<vendorlist.size();j++)
			{
				Map obj = (Map) vendorlist.get(j);
				String vendor = null;
				//生成合同主表
				if (obj.size() > 0)
				{
					if (obj.get("VENDOR") != null) 
					{
						vendor = (obj.get("VENDOR")).toString();
						System.out.println(vendor);
					}
					Contract cont = new Contract();
					String cntnum = this.genInskey("CONTRACTNUM");
					cont.setCntnum(cntnum);
					cont.setStatus("流程未启动");
					cont.setStatusdate(new Date());
					cont.setVendor(vendor);
					cont.setCntversion("V1.0");
					//cont.setSitenum(rfq.getSitenum());
					//cont.setCorpnum(rfq.getCorpnum());
					this.getBaseDao().saveObject(cont);
					
					for (int a=0;a<rvlist.size();a++)
					{
						Rfqquoteline rfqline =(Rfqquoteline) rvlist.get(a);
						if(rfqline.getVendor().equals(vendor))
						{
							Contline contline = new Contline();
							contline.setCntnum(cntnum);
							long maxID = this.getBaseDao().selectLongMaxByHql("select  max(co.linenum) from Contline co where co.cntnum='"+cont.getCntnum()+"'")+1;
							contline.setLinenum(maxID);
							contline.setItemnum(rfqline.getItemnum());
							contline.setConversion(1d);
							//contline.setSitenum(rfq.getSitenum());
							//contline.setCorpnum(rfq.getCorpnum());
							List list = this.getBaseDao().findWithQuery(Item.class,"itemnum='" +rfqline.getItemnum()+ "'");
							if (list.size() >= 0) 
							{
								Item item = (Item) list.get(0);
								contline.setDescription(item.getDescription());
								contline.setOrderunit(item.getOrderunit());
								contline.setTaxcode(item.getTaxcode());
							}
							
							contline.setTaxunitcost(rfqline.getUnitcost());
							contline.setOrderqty(rfqline.getOrderqty());
							contline.setTaxlinecost(rfqline.getUnitcost() * rfqline.getOrderqty());
							contline.setLoadedcost(rfqline.getUnitcost() * rfqline.getOrderqty());
							contline.setRejectedqty(0d);
							contline.setEnterdate(new Date());
							contline.setEnterby(this.getUserInfo().getLabornum());
							contline.setRequestedby(rfqline.getEnterby());
							contline.setService("否");
							contline.setInspection("是");
							this.getBaseDao().saveObject(contline);
						}

					}
				}
			}
		}
		
		
	}
	/**
	 * 
	 * 拷贝采购申请行，弹出一个窗体，数据为所有的采购申请行。可以由用户选择其中的一部分数据
	 * @param obj
	 * @param list
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午06:00:05
	 */

	public void CopyPrline(Object obj, List list) throws Exception {
		if(!(obj instanceof Rfq)) 
			throw new Exception("对象传递不正确!");
		Rfq rfq = (Rfq)obj;
		for(int i = 0;i<list.size();i++)
		{
			Prline pl = (Prline)list.get(i);
			Rfqline rl = new Rfqline();
			rl.setRfqnum(rfq.getRfqnum());
			long n = this.getBaseDao().selectLongMaxByHql("select max(rl.rfqlinenum) from Rfqline rl where rl.rfqnum='"+rfq.getRfqnum()+"'")+1;
			//System.out.println(n);
			rl.setRfqlinenum(n);
			rl.setItemnum(pl.getItemnum());rl.setWarehouse(pl.getWarehouse());
			rl.setDescription(pl.getDescription());
			rl.setOrderqty(pl.getOrderqty());rl.setOrderunit(pl.getOrderunit());
			rl.setConversion(pl.getConversion());
			rl.setReqdeliverydate(pl.getReqdeliverydate());
			rl.setEnterby(pl.getEnterby());rl.setEnterdate(pl.getEnterdate());
			rl.setPonum(pl.getPonum());rl.setPolinenum(pl.getPolinenum());
			rl.setStocktype(pl.getStocktype());rl.setRemark(pl.getRemark());
			rl.setIsservice(pl.getIsservice());
			rl.setManufacturer(pl.getManufacturer());rl.setModelnum(pl.getModelnum());
			rl.setUnitcost(pl.getUnitcost());
			rl.setLinecost(pl.getLinecost());
			rl.setInspection(pl.getInspection());
			rl.setEqnum(pl.getEqnum());rl.setLocation(pl.getLocation());
			rl.setWonum(pl.getWonum());rl.setTasknum(pl.getTasknum());
			//rl.setSitenum(pl.getSitenum());
			//rl.setCorpnum(pl.getCorpnum());
			super.save(rl);
			
			//回写数据 ，将rfqnum、rfqlinenum写回拷贝过的采购单行
			pl.setRfqnum(rfq.getRfqnum());
			pl.setRfqlinenum(rl.getRfqlinenum());
			super.save(pl);
			
		}
		
	}

	
	
}
