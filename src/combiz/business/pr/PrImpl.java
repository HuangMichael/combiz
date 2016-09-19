package combiz.business.pr;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
/**
 * @author 李阳
 *
 */
public class PrImpl extends IBOBaseImpl implements PrSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Pr))
			throw new Exception("要删除的对象传递不正确！");

		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//删除采购申请行
		this.deleteAllChild(obj, "prline");
	}

	/* save()
	 * 保存方法
	 */
	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if (!(obj instanceof Pr)){
			throw new Exception("对象传递不正确！");
		}
		Pr pr = (Pr) obj;
		/*if (pr.getId()!=null) {
			pr.setChangeby(this.getLaborInfo().getLaborname());
			pr.setChangedate(new Date());
			this.getBaseDao().updateObject(obj);
		}*/
		super.save(obj);
	}

	/***
	 * 
	 * @TODO 生成询价单及询价单行，将需求计划中采购申请及采购申请行的信息分别赋值给询价单、询价单行
	 * @param obj
	 * @蒋祖兵 2007-8-14 下午03:52:56
	 */
	public String ceaterfq(Object obj) throws Exception {
		if (!(obj instanceof Pr))
			throw new Exception("对象传递不正确！");
		//获得采购申请对象
		Pr pr = (Pr) obj;
		String prnum = pr.getPrnum(); //获取PR的prnum
		String rfqnum = this.genInskey("RFQNUM");//从自动编号中获取RFQNUM
		ArrayList norfqlist = new ArrayList();//建立ArrayList
		List list = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"'");
		//通过Prline.class对数据库Prline进行操作，执行prnum = PR.prnum
		if(list.size() == 0)
		{
			throw new Exception("采购申请行为空，不能创建询价单");
		}
		
		for(int i = 0;i<list.size();i++) //对List执行查询，将prline中rfqnum为空的记录放到 norfqlist中；
		{
			Prline prline =(Prline) list.get(i);
			if (prline.getRfqnum() == null)
				{
				norfqlist.add(prline);				
			    prline.setRfqnum(rfqnum);//对prline里的rfqnum进行赋值，便于prline和rfq进行关联。
			    this.getBaseDao().updateObject(prline);
				}
				
		}
		
		/***********如果该采购申请中的采购申请行都生成了询价单行，提示不需要生成*******/
		if (norfqlist.size() == 0)
		{
			throw new Exception("该采购申请已经全部创建完毕，不需创建！");
		}
		
	     /**在询价单rfq中插入主记录**********************************************************/
		
		Rfq rfq = new Rfq();
		rfq.setRfqnum(rfqnum);
		/**生成询价单**/
		//将采购申请信息赋值给询价单
		//描述
		rfq.setDescription(pr.getDescription());
		//状态信息
		rfq.setStatus(pr.getStatus());
		rfq.setStatusdate(pr.getStatusdate());
		//录入人、录入时间
		rfq.setEnterby(this.getLaborInfo().getLaborname());
		rfq.setEnterdate(new Date());
		//需求日期 
		rfq.setRequireddate(pr.getRequireddate());

		//收货人、地址
		rfq.setShiptolabor(pr.getShiptoaddr());
		rfq.setShiptoaddr(pr.getShiptoaddr());
		//收票人、地址
		rfq.setBilltolabor(pr.getBilltolabor());
		rfq.setBilltoaddr(pr.getBilltoaddr());
		//运输条款、方式、支付条款
		rfq.setFreightterms(pr.getFreightterms());
		rfq.setShipvia(pr.getShipvia());
		rfq.setPaymentterms(pr.getPaymentterms());
		//更改人、时间
		rfq.setChangeby(pr.getChangeby());
		rfq.setChangedate(pr.getChangedate());
		//rfq.setCorpnum(pr.getCorpnum());
		//rfq.setSitenum(pr.getSitenum());

		this.getBaseDao().saveObject(rfq);

		/**将采购申请中执行的没有生成询价单的采购申请行生成询价单行**********************************************************/
		//获取需要生成询价单的采购申请行集合norfqlist
		for (int i = 0; i < norfqlist.size(); i++) {
			Prline pl = (Prline) norfqlist.get(i);
			Rfqline rl = new Rfqline();
			rl.setRfqnum(rfq.getRfqnum());
			pl.setRfqlinenum(Long.parseLong(Integer.toString(i + 1)));//将采购申请行生成的询价单行写回采购申请行
	
			//获取询价单行id数

			//int rlnum = this.getRowCountByWhere(rl, "rfqnum='"+rfq.getRfqnum()+"'")+1;
			rl.setRfqlinenum(Long.parseLong(Integer.toString(i + 1)));
			//库存项目、描述、仓库
			rl.setItemnum(pl.getItemnum());
			rl.setDescription(pl.getDescription());
			rl.setWarehouse(pl.getWarehouse());
			//订购数量、订购单位、转换系数
			rl.setOrderqty(pl.getOrderqty());
			rl.setOrderunit(pl.getOrderunit());
			rl.setConversion(pl.getConversion());
			//交付日期 
			rl.setReqdeliverydate(pl.getReqdeliverydate());
			//录入人、日期
			rl.setEnterby(pl.getEnterby());
			rl.setEnterdate(pl.getEnterdate());
			//采购单、采购单行
			rl.setPonum(pl.getPonum());
			rl.setPolinenum(pl.getPolinenum());
			//类型、注释
			rl.setStocktype(pl.getStocktype());
			rl.setRemark(pl.getRemark());
			//服务、需要检查
			rl.setIsservice(pl.getIsservice());
			rl.setInspection(pl.getInspection());
			//制造商、供应商型号
			rl.setManufacturer(pl.getManufacturer());
			rl.setModelnum(pl.getModelnum());
			//行成本、单位成本
			rl.setLinecost(pl.getLinecost());
			rl.setUnitcost(pl.getUnitcost());
			//位置、设备
			rl.setLocation(pl.getLocation());
			rl.setEqnum(pl.getEqnum());
			//工单、工单任务
			rl.setWonum(pl.getWonum());
			rl.setTasknum(pl.getTasknum());
			//rl.setSitenum(pl.getSitenum());
			//rl.setCorpnum(pl.getCorpnum());

			//保存对象
			this.getBaseDao().saveObject(rl);
			System.out.println("生成询价单行成功.........");
		}

		return rfqnum;
	}

	/**
	 * 
	 * @TODO	自动生成采购单、采购单行，对应数据来源于采购申请、采购申请行s
	 * @param obj
	 * @return String
	 * @throws Exception
	 * @蒋祖兵 2007-8-15 下午12:19:50
	 */
	public String createpo(Object obj) throws Exception {

		if (!(obj instanceof Pr)) {
			throw new Exception("对象传递不正确！");
		}
        Pr pr = (Pr) obj;
        
        Double totalcost = (Double) this.getBaseDao().selectSumByHql("select sum(t.linecost) from Prline t where t.prnum='"+pr.getPrnum()+"'");
		if(totalcost == null)
		{
			totalcost = 0d;
		}
		

		String prnum = pr.getPrnum(); //获取PR的prnum
		String ponum = this.genInskey("PONUM");//从自动编号中获取RFQNUM
		
		
		List list = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"'");
		//通过Prline.class对数据库Prline进行操作，执行prnum = PR.prnum
		
		if(list.size() == 0)
		{
			throw new Exception("您还没有填写行明细，不能创建采购单");
		}
		
		List nopolist = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"' and ponum is null");
		
		if (nopolist.size() == 0)
		{
			throw new Exception("该采购申请已经全部创建为采购单完毕，不需创建！");
		}
		//

		/**采购单*/
        Po po = new Po();
		//	po.setPonum(Integer.toString(ponum));
		po.setPonum(ponum);
		//状态、日期
		//po.setStatus(pr.getStatus());
		po.setStatus("流程未启动");
		po.setVendor(pr.getVendor());
		po.setStatusdate(pr.getStatusdate());
		//描述
		po.setDescription(pr.getDescription());
		//定购日期,需求日期
		po.setOrderdate(pr.getRequestdate());
		po.setRequireddate(pr.getRequireddate());
		//供应商,联系人
//		po.setVendor(pr.getVendor());
		po.setContact(pr.getContact());
		//		运输条款、方式、支付条款
		po.setFreightterms(pr.getFreightterms());
		po.setShipvia(pr.getShipvia());
		po.setPaymentterms(pr.getPaymentterms());
		//总价
		po.setTotalcost(totalcost);
		//收货人、地址
		po.setShiptolabor(pr.getShiptoaddr());
		po.setShiptoaddr(pr.getShiptoaddr());
		//收票人、地址
		po.setBilltolabor(pr.getBilltolabor());
		po.setBilltoaddr(pr.getBilltoaddr());
		//更改人、时间
		po.setChangeby(pr.getChangeby());
		po.setChangedate(pr.getChangedate());
		//汇率,汇率日期,税总额
		po.setExchangerate(pr.getExchangerate());
		po.setExchangedate(pr.getExchangedate());
		po.setTotaltax(pr.getTotaltax());
		//po.setCorpnum(pr.getCorpnum());
		//po.setSitenum(pr.getSitenum());

		//保存对象
		super.save(po);

		/***生成采购单行**/

		//取得主对象的所有的采购申请行
		for (int i = 0; i < nopolist.size(); i++) {
			Prline pl = (Prline) nopolist.get(i);
			Poline ol = new Poline();
			pl.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			pl.setPonum(ponum);
			this.getBaseDao().updateObject(pl);
			//	int polinenum = this.getRowCountByWhere(ol, "ponum='"+po.getPonum()+"'")+1;
			ol.setPonum(ponum);
			String buditem = pl.getBuditem();
			String budnum = pl.getBudnum();
			if(Util.isNotNull(buditem)){
				ol.setBuditem(buditem);
			}
			else{
				ol.setBuditem("");
			}
			if(Util.isNotNull(budnum)){
				ol.setBudnum(budnum);
			}else{
				ol.setBudnum("");
			}
			ol.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			//				库存项目、描述、仓库,库存类型
			ol.setItemnum(pl.getItemnum());
			ol.setDescription(pl.getDescription());
			ol.setWarehouse(pl.getWarehouse());
			ol.setStocktype(pl.getStocktype());
			//				订购数量、订购单位、转换系数
			ol.setOrderqty(pl.getOrderqty());
			ol.setOrderunit(pl.getOrderunit());
			if (pl.getConversion() == null) {
				ol.setConversion(0.00);
			} else {
				ol.setConversion(pl.getConversion());
			}

			//不含税单价、总价
			ol.setUnitcost(pl.getUnitcost());
			ol.setLinecost(pl.getLinecost());
			//含税单价,含税总价,接收数量,已接收的单价,接收到的总价,税率,税额、拒收数量
			ol.setTaxunitcost(0.00);
			ol.setTaxlinecost(0.00);
			ol.setReceivedqty(0.00);
			ol.setReceivedunitcost(0.00);
			ol.setReceivedtotalcost(0.00);
			ol.setTaxrate(0.00);
			ol.setTax(0.00);

			ol.setRejectedqty(ol.getOrderqty() - ol.getReceivedqty());
			//供应商发货日期,录入日期,录入人,申请人
			ol.setVendeliverydate(pl.getVendeliverydate());
			ol.setEnterdate(pl.getEnterdate());
			ol.setEnterby(pl.getEnterby());
			ol.setRequestedby(pl.getRequestedby());
			//				交付日期 
			ol.setReqdeliverydate(pl.getReqdeliverydate());
			//				制造商、供应商型号
			ol.setManufacturer(pl.getManufacturer());
			ol.setModelnum(pl.getModelnum());
			//服务,注释
			ol.setService(pl.getIsservice());
			ol.setRemark(pl.getRemark());
			//记帐位置,完成接收,需要检查,载入的成本,已分摊
			ol.setLocation(pl.getLocation());
			ol.setReceiptscomplete("否");
			ol.setInspection(pl.getInspection());
			ol.setLoadedcost(pl.getLoadedcost());
			ol.setProrated(pl.getProrateservice());

			//工单,工单任务
			ol.setWonum(pl.getWonum());
			ol.setTasknum(pl.getTasknum());
			//ol.setCorpnum(pl.getCorpnum());
			//ol.setSitenum(pl.getSitenum());
			super.save(ol);
		}
		//return Integer.toString(ponum);
		return ponum;
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：将选中的需求计划行合并到采购申请行里
	 * 日期：Oct 22, 2008 3:44:16 PM
	 *
	 */
	public void uniteprline(List list,Object obj) throws Exception{
		Pr pr = (Pr) obj;
		
		String prnum = pr.getPrnum();
		for(int i=0;i<list.size();i++)
		{
			Prline popuprline = (Prline) list.get(i); //弹出窗口中选中的记录
			String itemnum = popuprline.getItemnum();
			List prlinelist = this.getBaseDao().findWithQuery(Prline.class, "prnum = '"+prnum+"' and itemnum = '"+itemnum+"'");
//			如果在当前采购申请下，有相同库存项目编号的采购申请行就合并，否则的话，新创建一条记录。
			if(prlinelist.size()>0)
			{
				Prline prl = (Prline) prlinelist.get(0);//已经加入到该采购申请的申请行下的记录；
				prl.setOrderqty(prl.getOrderqty() + popuprline.getOrderqty());
				
				this.getBaseDao().updateObject(prl);
				/****
				 *拷贝需求计划生成采购申请后，将新生成的采购申请编号和行号反写到对应的需求计划行下
				 */
				popuprline.setToprlinenum(prl.getToprlinenum());
				popuprline.setToprnum(prl.getPrnum());
				this.getBaseDao().updateObject(popuprline);
				
			}
			else
			{
				Prline prline = new Prline();
				long maxID = this.getBaseDao().selectLongMaxByHql("select max(pr.prlinenum) from Prline pr where pr.prnum='" + pr.getPrnum() + "'") + 1;
				prline.setPrnum(pr.getPrnum());
				prline.setPrlinenum(maxID);
				prline.setItemnum(popuprline.getItemnum());
				prline.setDescription(popuprline.getDescription());
				prline.setWarehouse(popuprline.getWarehouse());
				prline.setOrderqty(popuprline.getOrderqty());
				prline.setUnitcost(popuprline.getUnitcost());
				prline.setOrderunit(popuprline.getOrderunit());
				prline.setConversion(popuprline.getConversion());
				prline.setUnitcost(popuprline.getUnitcost());
				prline.setLinecost(popuprline.getLinecost());
				prline.setEnterby(this.getLaborInfo().getLaborname());
				prline.setEnterdate(new Date());
				prline.setProrateservice("否");
				prline.setIsservice("否");
				prline.setLoadedcost(0d);
				prline.setInspection("否");
				//prline.setCorpnum(pr.getCorpnum());
				//prline.setSitenum(pr.getSitenum());
				this.getBaseDao().saveObject(prline);
				
				/****
				 *拷贝需求计划生成采购申请后，将新生成的采购申请编号和行号反写到对应的需求计划行下
				 */
				popuprline.setToprlinenum(prline.getToprlinenum());
				popuprline.setToprnum(prline.getPrnum());
				this.getBaseDao().updateObject(popuprline);
				
			}
			
		}
		
	}
	
	
	
	 
		/**
		*@author 李阳
		*功能：
		*@param list
		*@param obj
		*@throws Exception 
		*2008-1-22上午10:14:34
		*/
		public String copypr(List list, Object obj) throws Exception {
			if (!(obj instanceof Pr))
				throw new Exception("对象传递不正确!");
			Pr pr = (Pr) obj;
			Pr newpr = new Pr();
			String prnum = this.genInskey("PRNUM");
			newpr.setPrnum(prnum);
			newpr.setStatus("WAPPR");
			newpr.setDescription(pr.getDescription());
			newpr.setStatusdate(new Date());
			newpr.setChangeby(this.getLaborInfo().getLaborname());
			newpr.setRequestdate(new Date());
			newpr.setRequestedby(this.getLaborInfo().getLaborname());
			newpr.setRequestdept(this.getLaborInfo().getDeptnum());
			newpr.setChangedate(new Date());
			//newpr.setCorpnum(pr.getCorpnum());
			//newpr.setSitenum(pr.getSitenum());
			this.save(newpr);
			List copypl = this.getBaseDao().findWithQuery(Prline.class, "prnum='" + pr.getPrnum() + "'");
			for (int i = 0; i < copypl.size(); i++) {
				Prline prline = new Prline();
				Prline pl = (Prline) copypl.get(i);
//				pl.setId(this.get)
				long maxID = this.getBaseDao().selectLongMaxByHql("select max(pr.prlinenum) from Prline pr where pr.prnum='" + newpr.getPrnum() + "'") + 1;
				prline.setPrnum(newpr.getPrnum());
				prline.setPrlinenum(maxID);
				prline.setItemnum(pl.getItemnum());
				prline.setDescription(pl.getDescription());
				prline.setWarehouse(pl.getWarehouse());
				prline.setOrderqty(pl.getOrderqty());
				prline.setOrderunit(pl.getOrderunit());
				prline.setConversion(pl.getConversion());
				prline.setUnitcost(pl.getUnitcost());
				prline.setLinecost(pl.getLinecost());
				prline.setEnterby(this.getLaborInfo().getLaborname());
				prline.setEnterdate(new Date());
				prline.setProrateservice("否");
				prline.setIsservice("否");
				prline.setLoadedcost(0d);
				prline.setInspection("否");
				//prline.setCorpnum(pl.getCorpnum());
				//prline.setSitenum(pl.getSitenum());
			
				super.save(prline);
			
			}
			return prnum;


		}
	/////////////////////业务方法//////////////////////////////////

}
