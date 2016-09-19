package combiz.business.contract;

import combiz.domain.contract.Contline;
import combiz.domain.contract.Contract;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.IBOBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContractImpl extends IBOBaseImpl implements ContractSrv {

	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Contract))
			throw new Exception("要删除的对象传递不正确！");

		this.deleteAllChild(obj, "cntline");
		this.deleteAllChild(obj, "cntpay");
		this.deleteAllChild(obj, "cntchange");
		this.deleteAllChild(obj, "cntterms");
		super.delete(obj);

	}
	
	/**
	 * 方法：createpo
	 * 作者：李建红
	 * 功能：通过合同，生成采购订单
	 * 日期：Apr 13, 2009  2:40:11 PM
	 */
	public String createpo(Object obj) throws Exception {

		if (!(obj instanceof Contract)) {
			throw new Exception("对象传递不正确！");
		}
		Contract contract = (Contract) obj;
        
        Double totalcost = (Double) this.getBaseDao().selectSumByHql("select sum(t.taxlinecost) from Contline t where t.cntnum='"+contract.getCntnum()+"'");
		if(totalcost == null)
		{
			totalcost = 0d;
		}
		

		String cntnum = contract.getCntnum(); //获取PR的prnum
		String ponum = this.genInskey("PONUM");//从自动编号中获取RFQNUM
		
		ArrayList nopolist = new ArrayList();//建立ArrayList
		
		List list = this.getBaseDao().findWithQuery(Contline.class, "cntnum='"+cntnum+"'");
		//通过Prline.class对数据库Prline进行操作，执行prnum = PR.prnum
		
		if(list.size() == 0)
		{
			throw new Exception("您还没有填写合同采购行明细，不能创建采购单！");
		}
		
		for(int i = 0;i<list.size();i++) //对List执行查询，将prline中rfqnum为空的记录放到 norfqlist中；
		{
			Contline contline =(Contline) list.get(i);
			if (contline.getPonum() == null)
				{
				nopolist.add(list.get(i));				
				contline.setPonum(ponum);//对prline里的rfqnum进行赋值，便于prline和rfq进行关联。
				}
				
		}
		
		if (nopolist.size() == 0)
		{
			throw new Exception("该采购申请已经全部创建为采购单完毕，不需创建！");
		}
		//

		/**采购单*/
        Po po = new Po();
		po.setPonum(ponum);
		//状态、日期
		po.setStatus(contract.getStatus());
		po.setVendor(contract.getVendor());
		po.setStatusdate(contract.getStatusdate());
		//描述
		po.setDescription("由合同 "+contract.getDescription() +" 生成！");
		//定购日期,需求日期
		po.setOrderdate(new Date());
		//供应商,联系人
		po.setContact(contract.getVendorcontact());
		//总价
		po.setTotalcost(totalcost);
		//收货人、地址
		po.setShiptolabor("请填写收货人");
		po.setShiptoaddr("请填写收货地址");
		//收票人、地址
		//po.setBilltolabor(pr.getBilltolabor());
		//po.setBilltoaddr(pr.getBilltoaddr());
		//更改人、时间
		//po.setChangeby(contract.getChangeby());
		//po.setChangedate(contract.getChangedate());
		//汇率,汇率日期,税总额
		//po.setExchangerate(contract.getExchangerate());
		//po.setExchangedate(contract.getExchangedate());
		//po.setTotalcost(contract.getTotalcost());
		//setTotaltax(contract.getTotaltax());
		//po.setCorpnum(contract.getCorpnum());
		//po.setSitenum(contract.getSitenum());

		//保存对象
		super.save(po);

		/***生成采购单行**/

		//取得主对象的所有的采购申请行
		for (int i = 0; i < nopolist.size(); i++) {
			Contline pl = (Contline) nopolist.get(i);
			Poline ol = new Poline();
			pl.setLinenum(Long.parseLong(Integer.toString(i + 1)));
			//	int polinenum = this.getRowCountByWhere(ol, "ponum='"+po.getPonum()+"'")+1;
			ol.setPonum(ponum);
			ol.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			//				库存项目、描述、仓库,库存类型
			ol.setItemnum(pl.getItemnum());
			ol.setDescription(pl.getDescription());
			ol.setWarehouse(pl.getWarehouse());
			ol.setStocktype(pl.getStocktype());
			//				订购数量、订购单位、转换系数
			Double ordqty = pl.getOrderqty();
			if (ordqty==null) {
				ordqty= 0d;
			}
			ol.setOrderqty(ordqty);
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

			Double recqty = ol.getReceivedqty();
			if (recqty == null) {
				recqty = 0D;
			}
			if (ol.getOrderqty() != null ) {
				ol.setRejectedqty(ol.getOrderqty() - recqty);
			}
			
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
			ol.setService(pl.getService());
			ol.setRemark(pl.getRemark());
			//记帐位置,完成接收,需要检查,载入的成本,已分摊
			ol.setLocation(pl.getLocation());
			ol.setReceiptscomplete("否");
			ol.setInspection(pl.getInspection());
			ol.setLoadedcost(pl.getLoadedcost());
			String prorated = pl.getProrated();
			if (prorated == null || prorated.equals("")) {
				prorated = "否";
			}
			ol.setProrated(prorated);

			//工单,工单任务
			//ol.setWonum(pl.getWonum());
			//ol.setTasknum(pl.getTasknum());
			//ol.setCorpnum(pl.getCorpnum());
			//ol.setSitenum(pl.getSitenum());
			super.save(ol);
		}
		//return Integer.toString(ponum);
		return ponum;
	}
}
