package combiz.business.po;

import combiz.domain.classattr.Classspec;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Conversion;
import combiz.domain.po.Invvendor;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workflow.Wfassignment;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;
import combiz.domain.inventory.Invstock;
import combiz.domain.location.Locations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.inbasis.zk.ui.Sessions;
import com.inbasis.zul.Messagebox;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */

public class PoImpl extends IBOBaseImpl implements PoSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		// 删除子表记录--采购单行

		this.deleteAllChild(obj, "poline");
	}
	
	
	
	@Override
	public void wfReassign(Object arg0, Wfassignment arg1, String arg2)
			throws Exception {
		// TODO Auto-generated method stub
		String a = arg2;
		Po po = (Po) arg0;
		if(po.getStatus().equals("等待审批"))
		{
			po.setChangeby(a);
			po.setContact(a);
			this.update(po);
		}
		super.wfReassign(arg0, arg1, arg2);
	}



	/**
	 * 在流程发送的时候判断所有采购行的仓库是否填写
	 * brianhong  2008-2-22
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 */
	@Override
	public boolean validData(Object arg0, Wfinstance arg1)
	throws Exception 
	{
		List polineList = this.getListByRelation(arg0, "poline");
		for(int i=0;i<polineList.size();i++)
		{
			Poline poline = (Poline) polineList.get(i);
			if(Util.isNull(poline.getWarehouse()))
			{
				Messagebox.show("采购订单行["+poline.getItemnum()+"]的项目所在仓库为空，请填写！");
				return false;
			}
		}
		return true;
	}

	
	/**
	 * 
	 * @TODO 拷贝采购单申请行。弹出一个窗体，用户可以选择其中的部分已有数据。
	 * @param list
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午05:53:29
	 */
	public void copyprline(List list, Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("对象传递不正确!");
		Po po = (Po) obj;
		// Prline prline = new Prline();
		
		for (int i = 0; i < list.size(); i++) {
			Prline pl = (Prline) list.get(i);
			Poline p = new Poline();
			long maxID = this.getBaseDao().selectLongMaxByHql("select max(po.polinenum) from Poline po where po.ponum='" + po.getPonum() + "'") + 1;
			p.setPonum(po.getPonum());
			p.setPolinenum(maxID);

			p.setItemnum(pl.getItemnum());
			p.setDescription(pl.getDescription());
			p.setWarehouse(pl.getWarehouse());
			p.setOrderqty(pl.getOrderqty());
			p.setOrderunit(pl.getOrderunit());
			if (pl.getConversion() == null) {
				p.setConversion(0.0);
			} else {
				p.setConversion(pl.getConversion());
			}
			p.setUnitcost(pl.getUnitcost());
			p.setTaxunitcost(0.0);
			p.setTaxlinecost(0.0);
			p.setWarehouse(pl.getWarehouse());
			p.setLinecost(pl.getLinecost());
			p.setReceivedqty(0.0);
			p.setReceivedunitcost(0.0);
			p.setReceivedtotalcost(0.0);
			p.setTaxrate(0.00);
			p.setTax(0.00);
			p.setRejectedqty(0.0);
			p.setVendeliverydate(pl.getVendeliverydate());
			p.setRequestedby(pl.getEnterby());
			p.setEnterdate(pl.getEnterdate());
			p.setEnterby(this.getLaborInfo().getLabornum());
			p.setRequestedby(pl.getRequestedby());
			p.setReqdeliverydate(pl.getReqdeliverydate());
			p.setManufacturer(pl.getManufacturer());
			p.setModelnum(pl.getModelnum());
			p.setService(pl.getIsservice());
			p.setStocktype(pl.getStocktype());
			p.setRemark(pl.getRemark());
			p.setLocation(pl.getLocation());
			p.setReceiptscomplete("否");
			p.setInspection(pl.getInspection());
			p.setLoadedcost(pl.getLoadedcost());
			p.setProrated(pl.getProrateservice());
			p.setWonum(pl.getWonum());
			p.setTasknum(pl.getTasknum());
			//p.setCorpnum(po.getCorpnum());
			//p.setSitenum(po.getSitenum());
			super.save(p);

			// 回写数据,将采购单号和采购单行号回写入采购申请行里

			pl.setPonum(po.getPonum());
			pl.setPolinenum(p.getPolinenum());
			super.save(pl);

		}

	}

	/**
	 * 
	 * @TODO 拷贝采购单申请行。弹出一个窗体，用户可以选择其中的部分已有数据。
	 * @param list
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午05:53:29
	 */
	public void addpoline(List list, Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("对象传递不正确!");
		Po po = (Po) obj;

		for (int i = 0; i < list.size(); i++) {

			Poline pl = (Poline) list.get(i);

			/*******************************************************************
			 *******************************************************************
			 1.先在Invrectrans里写数据   
			 ********************************************************************/

			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+pl.getItemnum()+"'");
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);

				/**********************************************************************************************************
				 * *********************关于订购单位和发放单位转化系数的处理******************************************************
				 * *********************************************************************************************************
				 */
				Double realqty = pl.getRealqty();
				Double convrealqty = 0d;
				String issueunit = null;
				Double conversion = 0d;
				Double unitcost = 0d;

				Double hasqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + pl.getPonum() + "' and t.itemnum = '" + pl.getItemnum() + "'");
				if (hasqty == null)
					hasqty = 0d;


				List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, " itemnum='"+pl.getItemnum()+"' and warehouse ='"+pl.getWarehouse()+"'");
				if(inventorylist.size()>0)//如果该库存项目在仓库中存在。
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					issueunit = inventory.getIssueunit();
				}
				else//如果该库存项目在仓库中不存在，在物资编码里存在
				{
					issueunit = item.getIssueunit();
				}
				if(pl.getOrderunit().equals(issueunit))
				{
					convrealqty = pl.getRealqty();
					conversion = 1d;
				}
				else
				{
					List convlist = this.getBaseDao().findWithQuery(Conversion.class, "orderunit = '"+pl.getOrderunit()+"' and issueunit = '"+issueunit+"'and itemnum = '"+pl.getItemnum()+"'");
					if(convlist.size()>0)
					{
						Conversion conv = (Conversion) convlist.get(0);
						conversion = conv.getConversion();
						convrealqty = pl.getRealqty()*conversion;
					}
					else
					{
						throw new Exception("请在物资编码里对物资编号为'"+pl.getItemnum()+"'的物资,添加订购单位为'"+pl.getOrderunit()+"',发放单位为'"+issueunit+"'之间的转化系数!");
					}
				}

				/**********************************************************************************************************
				 * *********************判断是否是周转件******************************************************
				 * *********************************************************************************************************
				 */

				if(item.getRotating().equals("是"))//周转件(item.getLottype().equals("批次管理"))
				{
					int lastrealqty = (int) (convrealqty+0);
					//得出当前日期YYYYMMDDHHMMSS，为生成批次号
					Date date=new Date(); 
					SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
					String nowdate=sp.format(date); 
					for(int j=0;j<lastrealqty;j++)
					{
						Invrectrans irt = new Invrectrans();
						irt.setQuantity(conversion);
						irt.setModelnum(pl.getModelnum());
						irt.setUnitcost(pl.getUnitcost()/conversion);
						irt.setConversion(conversion);
						irt.setRecunit(issueunit);
						irt.setTolot(nowdate);
						String buditem = pl.getBuditem();
						String budnum = pl.getBudnum();
						if(Util.isNotNull(buditem)){
							irt.setBuditem(buditem);
						}
						else{
							irt.setBuditem("");
						}
						if(Util.isNotNull(budnum)){
							irt.setBudnum(budnum);
						}else{
							irt.setBudnum("");
						}
						List avglist = (List) this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + pl.getItemnum() + "' and warehouse = '" + pl.getWarehouse() +"'" );
						irt.setPonum(pl.getPonum());
						irt.setPolinenum(pl.getPolinenum());
						irt.setItemnum(pl.getItemnum());
						irt.setTowarehouse(pl.getWarehouse());
						irt.setTransdate(new Date());
						irt.setActualdate(new Date());
						irt.setLinecost(pl.getUnitcost()/conversion);
						irt.setActualcost(pl.getReceivedunitcost());
						irt.setRejectqty(0d);
						irt.setDescription(pl.getDescription());
						//irt.setSitenum(pl.getSitenum());
						//irt.setCorpnum(pl.getCorpnum());
						//调整。。。。。。
						irt.setChangeby(pl.getEnterby());
						List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + pl.getItemnum() + "' and warehouse ='" + pl.getWarehouse() +"'");
						if(invstocklist.size()>0)
						{
							Invstock invstock = (Invstock) invstocklist.get(0);
							Double curbal = invstock.getCurbal();
							irt.setCurbal(curbal);
						}
						else
						{
							irt.setCurbal(0.0d);
						}


						irt.setRectype("接收"); //设置invtrans表中的类型为接收
						irt.setLoadedcost(pl.getLoadedcost());
						irt.setStatus("待编号");
						if(avglist.size() > 0)
						{
							Inventory inventory = (Inventory) avglist.get(0);
							irt.setOldavgcost(inventory.getAvgcost());
						}
						else
						{
							irt.setOldavgcost(0d);
						}
						irt.setOldavgcost(0d);
						this.getBaseDao().saveObject(irt);
					}
				}
				else//非周转件
				{
					Invrectrans irt = new Invrectrans();
					List avglist = (List) this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + pl.getItemnum() + "' and warehouse = '" + pl.getWarehouse() +"'" );
					Double waitqty = pl.getOrderqty() - hasqty;
					irt.setPonum(pl.getPonum());
					irt.setPolinenum(pl.getPolinenum());
					irt.setItemnum(pl.getItemnum());
					irt.setTowarehouse(pl.getWarehouse());
					irt.setTransdate(new Date());
					irt.setActualdate(new Date());
					irt.setPonum(pl.getPonum());
					irt.setPolinenum(pl.getPolinenum());
					irt.setUnitcost(pl.getUnitcost());
					irt.setLinecost(pl.getLinecost());
					irt.setActualcost(pl.getReceivedunitcost());
					irt.setRejectqty(0d);
					irt.setDescription(pl.getDescription());
					String buditem = pl.getBuditem();
					String budnum = pl.getBudnum();
					if(Util.isNotNull(buditem)){
						irt.setBuditem(buditem);
					}
					else{
						irt.setBuditem("");
					}
					if(Util.isNotNull(budnum)){
						irt.setBudnum(budnum);
					}else{
						irt.setBudnum("");
					}	
					//irt.setSitenum(pl.getSitenum());
					//irt.setCorpnum(pl.getCorpnum());
					//调整。。。。。。
					irt.setConversion(pl.getConversion());
					irt.setChangeby(pl.getEnterby());
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + pl.getItemnum() + "' and warehouse ='" + pl.getWarehouse() +"'");
					if(invstocklist.size()>0)
					{
						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal = invstock.getCurbal();
						irt.setCurbal(curbal);
					}
					else
					{
						irt.setCurbal(0.0d);
					}

					/**********************************************************************************************************
					 * *********************判断是否是批次管理******************************************************
					 * *********************************************************************************************************
					 */

					if(item.getLottype().equals("批次管理"))
					{
						Date date=new Date(); 
						SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
						String nowdate=sp.format(date); 
						irt.setTolot(nowdate);

					}

					irt.setRecunit(issueunit);
					irt.setConversion(conversion);
					irt.setQuantity(convrealqty);
					irt.setUnitcost(pl.getUnitcost()/conversion);
					irt.setRectype("接收"); //设置invtrans表中的类型为接收
					irt.setLoadedcost(pl.getLoadedcost());
					irt.setStatus("待检验");
					if(avglist.size() > 0)
					{
						Inventory inventory = (Inventory) avglist.get(0);
						irt.setOldavgcost(inventory.getAvgcost());
					}
					else
						irt.setOldavgcost(0d);
					this.getBaseDao().saveObject(irt);
				}

			}

		}


	}
	/* 
	 * 功能：拷贝采购单
	 * 作者：李阳
	 * 日期：Nov 2, 2008  1:11:20 PM
	 */
	public Po copypo(Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("对象传递不正确!");
		Po po = (Po) obj;
		Po newpo = new Po();
		String sitenum = null;
		String corpnum=null;
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class, "labornum='"+labornum+"'");
		if(laborlist.size()>0)
		{
			Labor labor = (Labor) laborlist.get(0);
			sitenum = labor.getSitenum();
			corpnum = labor.getCorpnum();
		}
		
		String ponum = this.genInskey("PONUM");
		newpo.setPonum(ponum);
		newpo.setStatus("流程未启动");
		newpo.setDescription(po.getDescription());
		newpo.setStatusdate(new Date());
		newpo.setChangeby(this.getLaborInfo().getLaborname());
		newpo.setVendor(po.getVendor());
		newpo.setTotalcost(po.getTotalcost());
		//newpo.setCorpnum(corpnum);
		//newpo.setSitenum(sitenum);
		
		this.save(newpo);
		
		List copypol = this.getBaseDao().findWithQuery(Poline.class, "ponum='" + po.getPonum() + "'");
		for (int i = 0; i < copypol.size(); i++) {
			Poline poline = new Poline();
			Poline pl = (Poline) copypol.get(i);
			Long maxID = this.getBaseDao().selectLongMaxByHql("select max(po.polinenum) from Poline po where po.ponum='" + newpo.getPonum() + "'") + 1;
			poline.setPonum(newpo.getPonum());
			poline.setPolinenum(maxID);
			poline.setItemnum(pl.getItemnum());
			poline.setDescription(pl.getDescription());
			poline.setWarehouse(pl.getWarehouse());
			poline.setStocktype(pl.getStocktype());
			poline.setTaxunitcost(0d);
			poline.setTaxlinecost(pl.getTaxlinecost());
			poline.setProrated("否");
			poline.setReceivedunitcost(0d);
			poline.setReceivedqty(0d);
			poline.setRejectedqty(0d);
			poline.setReceivedtotalcost(0d);
			poline.setReceiptscomplete("未接收");
			poline.setOrderqty(pl.getOrderqty());
			poline.setOrderunit(pl.getOrderunit());
			poline.setConversion(pl.getConversion());
			poline.setUnitcost(pl.getUnitcost());
			poline.setLinecost(pl.getLinecost());
			poline.setEnterby(this.getLaborInfo().getLaborname());
			poline.setEnterdate(new Date());
			poline.setService("否");
			poline.setInspection("否");
			poline.setLoadedcost(0d);
			//poline.setCorpnum(corpnum);
			//poline.setSitenum(sitenum);
			super.save(poline);
		
		}
		return newpo;


	}
	/* (non-Javadoc)
	 * @see combiz.business.po.PoSrv#verify(java.util.List, java.lang.Object)
	 */
	public void verify(List list, Object obj,List polinelist) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("对象传递不正确!");
		Po po = (Po) obj;
		
		
	/*	for (int m = 0; m< list.size(); m++) 
		{
			Invrectrans invrectrans = (Invrectrans) list.get(m);
			String itemnum = invrectrans.getItemnum();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			if(item.getRotating().equals("是"))
			{
				if(Util.isNull(invrectrans.getEqnum()))
				{
					throw new Exception("编号为:'"+itemnum+"'的库存项目，资产编号不能为空，请确认！");
				}
			}
			int count = this.getBaseDao().selectCountByHql("select count(*) from Invrectrans t where t.eqnum = '"+invrectrans.getEqnum()+"'");
			if(count>1)
			{
				throw new Exception("库存编号为:'"+itemnum+"'的周转件，存在相同的资产编号，请确认！");
			}
			
		}*/
		

		for (int i = 0; i < list.size(); i++) {
			Invrectrans invrectrans = (Invrectrans) list.get(i);
			String ponum = po.getPonum();
			String itemnum = invrectrans.getItemnum();
			String warehouse = invrectrans.getTowarehouse();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			String orderunit = item.getOrderunit();
			String issueunit = item.getIssueunit();
			Long polinenum = invrectrans.getPolinenum();
			
			
			/***********************************************************************
			 * ******************************************************************
			 * 3.判断该库存项目是否在在库存中有，如果没有发生过，将其信息添加到INVENTORY中
			 **********************************************************************/

			//先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			
			List unitcostlist = this.getBaseDao().findWithQuery(Poline.class, "ponum = '" + ponum +"' and polinenum = '" + polinenum + "'");
			Poline poline = (Poline) unitcostlist.get(0);
			Double totalrecqty = 0d;
			totalrecqty  = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + ponum +"' and polinenum = '" + polinenum + "'" );
			if (totalrecqty == null)
				totalrecqty = 0d;
			poline.setReceivedqty(totalrecqty);
			//查询在接收记录里所有采购单等于 ponum 的接收记录；
			if((poline.getOrderqty() - totalrecqty) > 0) 
				poline.setReceiptscomplete("部分接收");
			else
				poline.setReceiptscomplete("全部接收");

			poline.setReceivedunitcost((poline.getReceivedunitcost()==null?0:invrectrans.getUnitcost()) );
			poline.setReceivedtotalcost((poline.getReceivedtotalcost()==null?0:poline.getReceivedtotalcost()) + invrectrans.getLinecost());

			super.update(poline);
			Double unitcost = poline.getUnitcost();
			String binnum = invrectrans.getTobin();
			List noitem = this.getBaseDao().findWithQuery(Inventory.class,"itemnum = '"+itemnum+"' and warehouse='"+ warehouse +"'");

			if(noitem.size() == 0){

				Inventory inventory = new Inventory();
				inventory.setItemnum(invrectrans.getItemnum());
				inventory.setItemdesc(invrectrans.getDescription());
				inventory.setWarehouse(invrectrans.getTowarehouse());
				inventory.setMinlevel(0d);
				inventory.setMaxlevel(0d);
				inventory.setStocktype("非常备库存");
				inventory.setOrderqty(0d);
				inventory.setModelnum(invrectrans.getModelnum());
				inventory.setOrderunit(orderunit);
				inventory.setIssueunit(issueunit);
				inventory.setConversion(1d);
				inventory.setAvgcost(unitcost);// 第一接收时候，库存成本就为最后一次接收成本。
				inventory.setLastcost(unitcost);
				inventory.setStdcost(unitcost);	
				inventory.setIssueytd(0d);
				//inventory.setSitenum(invrectrans.getSitenum());
				//inventory.setCorpnum(invrectrans.getCorpnum());
				super.save(inventory);

			}
			else
			{
				Inventory inventory = (Inventory) noitem.get(0);

				//List calavgcost = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'空') = '" + binnum);
//				List calavgcost = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"'");
				Double sumcurbal =  0d;

				sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '" + itemnum + "' and t.warehouse = '"+ warehouse +"'");
				if(sumcurbal == null)
					sumcurbal = 0d;
//				for (int j = 0;j < calavgcost.size();j++)
//				{
//				Invstock invstock = (Invstock) calavgcost.get(j);
//				sumcurbal = sumcurbal + invstock.getCurbal();

//				}
				Double avgcost = (sumcurbal * inventory.getAvgcost() + unitcost * invrectrans.getQuantity()) / (sumcurbal + invrectrans.getQuantity());
				inventory.setAvgcost(avgcost);
				super.update(inventory);

			}

			/***************************************************
			 * ******************************************************************
			 * 3.如果是非批次管理，判断该库存项目是否在库存余量表中有信息，如果没有发生过，赋值当前余量为0；
			 * 如果是批次管理的话，就在INVSTOCK、INVLOT表中插入数据。 
			 **************************************************/

			Double rqty = invrectrans.getQuantity();
			String rotating = item.getRotating();
			if(item.getLottype().equals("非批次管理"))//非批次管理
			{
				int size = 0;
				if(binnum == null)
					//List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'空') = '" + binnum +"'");
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
					size = curbal.size();
					if (size == 0) {
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(rqty);
						invstock.setReconciled("否");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						super.save(invstock);


					} else {
						Invstock invstock = (Invstock) curbal.get(0);
						invrectrans.setCurbal(invstock.getCurbal());// 在Invrectrans表里的curbal字段写回当前的库存余量；
						Double nowcurbal = invstock.getCurbal() + rqty; // 将接收的库存数量增加，更新库存余量；
						invstock.setCurbal(nowcurbal);	
						super.getBaseDao().updateObject(invrectrans);
						super.save(invstock);// 注意顺序，一定想写回库存接收表的余量信息，因为这是接收前库存余量，再更新invstock的库存余量，否则会读脏数据。
					}
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}
				else
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum = '" + binnum +"'");
					size = curbal.size();
					if (size == 0) {
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());

						invstock.setCurbal(rqty);
						invstock.setReconciled("否");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						super.save(invstock);
						invrectrans.setStatus("已检验");//接收完成，不可编辑状态
						Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
						String labornum = labor.getLabornum();
						invrectrans.setStatuschangeby(labornum);
						invrectrans.setStatusdate(new Date());
						super.getBaseDao().updateObject(invrectrans);

					} else {
						Invstock invstock = (Invstock) curbal.get(0);

						invrectrans.setCurbal(invstock.getCurbal());// 在Invrectrans表里的curbal字段写回当前的库存余量；
						Double nowcurbal = invstock.getCurbal() + rqty; // 将接收的库存数量增加，更新库存余量；
						invstock.setCurbal(nowcurbal);	
						super.save(invrectrans);
						super.save(invstock);// 注意顺序，一定想写回库存接收表的余量信息，因为这是接收前库存余量，再更新invstock的库存余量，否则会读脏数据。
					}
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态
					super.getBaseDao().updateObject(invrectrans);
				}

			}
			else//批次管理的设备
			{
				
				if(rotating.equals("是"))//周转件
				{
					//在资产表equipment里插入数据。
					int totallength = 10;//资产编号的总位数；
					Equipment equip = new Equipment();
					equip.setInvcost(0.0D);
					equip.setBudgetcost(0.0D);
					equip.setPriority(0L);
					equip.setPurprice(0.0D);
					//equip.setReplacecost(0.0D);
					equip.setTotalcost(invrectrans.getUnitcost());
					equip.setYtdcost(0.0D);
					equip.setTotdowntime(0.0d);
					equip.setIsrunning("是");
					equip.setLotnum(invrectrans.getTolot());
					String eq = invrectrans.getEqnum();
					equip.setItemnum(invrectrans.getItemnum());
					equip.setLocation(invrectrans.getTowarehouse());
					equip.setDescription(item.getDescription());
					equip.setChangeby(this.getUserInfo().getLabornum());
					equip.setChangedate(new Date());
					equip.setInstalldate(new Date());
					//equip.setCorpnum(invrectrans.getCorpnum());
					//equip.setSitenum(invrectrans.getSitenum());
					equip.setClassid(item.getClassid());
					equip.setPlanyears(0L);
					equip.setModel(invrectrans.getModelnum());
					//equip.setPonum(invrectrans.getPonum());
					//equip.setPolinenum(invrectrans.getPolinenum());
					Double maxeq = 0d;
					long largeid = 0l;
					//对资产进行编号
					//int count = this.getBaseDao().selectCountByHql("select count(*) from Equipment  t where t.classid = '"+item.getClassid()+"' and sitenum='"+invrectrans.getSitenum()+"' and corpnum='"+invrectrans.getCorpnum()+"'");
					int count = this.getBaseDao().selectCountByHql("select count(*) from Equipment  t where t.classid = '"+item.getClassid()+"'");
					List maxidlist = null;
					if(count>0)
					{
						if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER")){//判断使用的数据库是sqlserver还是oracle数据库。
							/*	maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substring(t.eqnum,len(t.classid)+1,len(t.eqnum)-len(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "' and sitenum='"
											+ invrectrans.getSitenum()
											+ "' and corpnum='"
											+ invrectrans.getCorpnum()
											+ "'");*/
							maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substring(t.eqnum,len(t.classid)+1,len(t.eqnum)-len(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "'");
							if (maxidlist.size() > 0) {
								Map map = (Map) maxidlist.get(0);
								if (map.size() > 0) {
									Double maxvalue = (Double) map
											.get("LARGEID");
									maxeq = maxvalue.doubleValue() + 1;
									largeid = maxeq.longValue();
								}
							}
						}else{
							/*maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substr(t.eqnum,length(t.classid)+1,length(t.eqnum)-length(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "' and sitenum='"
											+ invrectrans.getSitenum()
											+ "' and corpnum='"
											+ invrectrans.getCorpnum()
											+ "'");*/
							maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substr(t.eqnum,length(t.classid)+1,length(t.eqnum)-length(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "'");
							if (maxidlist.size() > 0) {
								Map map = (Map) maxidlist.get(0);
								if (map.size() > 0) {
									BigDecimal maxvalue = (BigDecimal) map
											.get("LARGEID");
									maxeq = maxvalue.doubleValue() + 1;
									largeid = maxeq.longValue();
								}
							}
						}
						
					}
					
					String s = String.valueOf(largeid);
					String neweqnum = item.getClassid();
					int len1 = s.length();
					int len2 = item.getClassid().length();
					for(int j=0;j<totallength-len1-len2;j++)
					{
						neweqnum = neweqnum + "0";
					}
					neweqnum = neweqnum + s;
					
					equip.setEqnum(neweqnum);
					
//					equip.setEqnum(item.getClassid()+this.genInskey("EQNUM"));
					equip.setStatusdate(new Date());
					equip.setAssetnum(this.genInskey("CARDNUM"));
					equip.setStatus("未启用");
					this.getBaseDao().saveObject(equip);
					
					//判断分类的技术参数表是否存在，如果存在的话，就插入数据。
					List classpeclist = this.getBaseDao().findWithQuery(Classspec.class, "classid='"+item.getClassid()+"'");
					if(classpeclist.size()>0)
					{
						for(int j=0;j<classpeclist.size();j++)
						{
							Classspec classpec = (Classspec) classpeclist.get(j);
							Eqspec eqspec = new Eqspec();
							eqspec.setEqnum(neweqnum);
							eqspec.setClassid(item.getClassid());
							eqspec.setClassattr(classpec.getClassattr());
							eqspec.setStrvalue(classpec.getDefaultstrvalue());
							eqspec.setUnitid(classpec.getUnitid());
							eqspec.setChangedate(new Date());
							eqspec.setChangeby(this.getUserInfo().getLabornum());
							eqspec.setIsmustbe(classpec.getIsmustbe());
							eqspec.setRemark(classpec.getRemark());
							this.getBaseDao().saveObject(eqspec);
						}
						
						
					}
					
					//修改接收行中的库存余量为当前库存余量。
					Double sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '"+itemnum+"' and t.warehouse = '"+ warehouse +"' ");
					if(sumcurbal==null)
					{
						sumcurbal=0d;
					}
					invrectrans.setCurbal(sumcurbal);
					invrectrans.setEqnum(equip.getEqnum());
					
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='"+itemnum+"' and warehouse ='"+warehouse+"' and lotnum = '"+invrectrans.getTolot()+"' ");
					if(invstocklist.size()>0)
					{
						Invstock invstock = (Invstock) invstocklist.get(0);
						invstock.setCurbal(invstock.getCurbal()+invrectrans.getQuantity());
						this.getBaseDao().updateObject(invstock);
					}
					else
					{
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(rqty);
						invstock.setReconciled("否");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						invstock.setLotnum(invrectrans.getTolot());
						this.getBaseDao().saveObject(invstock);
					}
					
					int invlotcount = this.getBaseDao().selectCountByHql("select count(*) from Invlot t where t.itemnum ='"+itemnum+"' and t.warehouse ='"+warehouse+"' and t.lotnum = '"+invrectrans.getTolot()+"'");
					if(invlotcount == 0)
					{
						Invlot invlot = new Invlot();
						invlot.setItemnum(invrectrans.getItemnum());
						//invlot.setCorpnum(invrectrans.getCorpnum());
						//invlot.setSitenum(invrectrans.getSitenum());
						invlot.setWarehouse(invrectrans.getTowarehouse());
						invlot.setLotnum(invrectrans.getTolot());
						invlot.setLotcost(invrectrans.getUnitcost());
						invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
						this.getBaseDao().saveObject(invlot);
					}
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
					
				}
				else//非周转件
				{
					Invstock invstock = new Invstock();
					invstock.setItemnum(invrectrans.getItemnum());
					invstock.setWarehouse(invrectrans.getTowarehouse());
					invstock.setBinnum(invrectrans.getTobin());
					// 需要调整，库存项目不在一个箱柜的接收处理。
					invstock.setPhyscnt(invrectrans.getQuantity());
					invstock.setPhyscntdate(new Date());
					invstock.setCurbal(rqty);
					invstock.setReconciled("否");
					//invstock.setSitenum(invrectrans.getSitenum());
					//invstock.setCorpnum(invrectrans.getCorpnum());
					invstock.setLotnum(invrectrans.getTolot());
					super.save(invstock);

					Invlot invlot = new Invlot();
					invlot.setItemnum(invrectrans.getItemnum());
					//invlot.setCorpnum(invrectrans.getCorpnum());
					//invlot.setSitenum(invrectrans.getSitenum());
					invlot.setWarehouse(invrectrans.getTowarehouse());
					invlot.setVendor(po.getVendor());
					invlot.setLotnum(invrectrans.getTolot());
					invlot.setLotcost(invrectrans.getUnitcost());
					invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
					super.getBaseDao().saveObject(invlot);
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}
				
			}
			
		/*	//资产编号（2008年11月10日备份）
			String lastitemnum = null;
			List invrectlist = this.getBaseDao().findWithQuery(Invrectrans.class, "eqnum is null and tolot is not null","itemnum");
			for(int m = 0; m < invrectlist.size(); m++)
			{
				Invrectrans invrec = (Invrectrans)invrectlist.get(m);
				if(invrec.getItemnum().equals(lastitemnum))//如果编号相同
				{
					
				}
				else//编号不相同
				{
					
				}
			}
*/

            /*************修改invvendor表里的数据*************/
			
			//List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and sitenum='"+invrectrans.getSitenum()+"' and vendor='"+po.getVendor()+"'");
			List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and vendor='"+po.getVendor()+"'");
			if(invendorlist.size()>0)
			{
				Invvendor invendor = (Invvendor) invendorlist.get(0);
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setLastdate(invrectrans.getActualdate());
				this.getBaseDao().updateObject(invendor);
			}
			else
			{
				Invvendor invendor = new Invvendor();
				invendor.setItemnum(invrectrans.getItemnum());
				invendor.setVendor(po.getVendor());
				invendor.setPromtime(po.getRequireddate());
				invendor.setLastdate(invrectrans.getTransdate());
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setOrderunit(invrectrans.getRecunit());
				//invendor.setCorpnum(invrectrans.getCorpnum());
				//invendor.setSitenum(invrectrans.getSitenum());
				invendor.setIsdefault("否");
				this.getBaseDao().saveObject(invendor);
				
			}
			
			/*************修改invvendor表里的数据(结束)*************/

			List invstatus = this.getBaseDao().findWithQuery(Poline.class, "ponum = '" + ponum + "'");
			for(int k=0;k<invstatus.size();k++){
				Poline p = (Poline) invstatus.get(k);
				if(!p.getReceiptscomplete().equals("全部接收"))
				{
					po.setReceipts("部分接收");
					this.getBaseDao().updateObject(po);
					break;
				}						
				else
				{
					po.setReceipts("接收完成");
					this.getBaseDao().updateObject(po);
				}
			}
		}
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************如果该接收行对应的有领用申请行，通知可以领料*************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/


		if (polinelist != null && polinelist.size() > 0) 
		{
			String ponum = null;
			String polinenum = null;
			String body = null;
			String matreqnum = null;
			String requestby = null;
			for(int i=0;i<polinelist.size();i++)
			{
				Map polineobj = (Map) polinelist.get(i);
				if (polineobj.size() > 0) 
				{
					if (polineobj.get("PONUM") != null) 
					{
						ponum = (polineobj.get("PONUM")).toString();
					}
					if (polineobj.get("POLINENUM") != null) 
					{
						Object obj1 = polineobj.get("POLINENUM");
						polinenum = (polineobj.get("POLINENUM")).toString();
					}

					//1.采购单由领用申请直接生成。
					List wpmatList = this.getBaseDao().selectListBySql("select matreqnum,requestby from Wpmaterial   where prnum='"+ponum+"' and prlinenum ='"+polinenum+"'  group by matreqnum,requestby");
					if (wpmatList != null && wpmatList.size() > 0) 
					{
						for(int w=0;w<wpmatList.size();w++)
						{
							Map wpmatobj = (Map) wpmatList.get(w);
							if (wpmatobj.size() > 0) 
							{
								if (wpmatobj.get("MATREQNUM") != null) 
								{
									matreqnum = (wpmatobj.get("MATREQNUM")).toString();
								}
								if (wpmatobj.get("REQUESTBY") != null) 
								{
									requestby = (wpmatobj.get("REQUESTBY")).toString();
								}



								List sendwpmatList = this.getBaseDao().findWithQuery(Wpmaterial.class, "prnum = '"+ponum+"' and prlinenum ='"+polinenum+"' and matreqnum = '"+matreqnum+"' and requestby='"+requestby+"'");
								if(sendwpmatList.size()>0)
								{
									for(int n =0;n<sendwpmatList.size();n++)
									{
										Wpmaterial wpmat = (Wpmaterial) sendwpmatList.get(n);
										String matreqstr = wpmat.getMatreqnum();
										String itemnumstr = wpmat.getItemnum();
										String description = wpmat.getDescription();
										if(Util.isNull(body))
										{
											body = itemnumstr +"描述为["+description+"]";;
										}
										else
										{
											body = body + ","+itemnumstr +"描述为["+description+"]";;
										}
									}


								}

							}
						}



					}

					//2.采购单由采购申请生成。
					List prlinelist = this.getBaseDao().findWithQuery(Prline.class, "ponum='"+ponum+"' and polinenum ='"+polinenum+"'");
					if(prlinelist.size()>0)
					{
						for(int j=0;j<prlinelist.size();j++)
						{
							Prline prline  = (Prline) prlinelist.get(j);
							Long prlinenum = prline.getPrlinenum();
							String prnum = prline.getPrnum();
							List wpmatList2 = this.getBaseDao().selectListBySql("select matreqnum,requestby from Wpmaterial   where prnum='"+prnum+"' and prlinenum ='"+prlinenum+"' group by matreqnum,requestby");
							if (wpmatList2 != null && wpmatList2.size() > 0) 
							{
								for(int w=0;w<wpmatList2.size();w++)
								{
									Map wpobj = (Map) wpmatList2.get(w);
									if (wpobj.size() > 0) 
									{
										if (wpobj.get("MATREQNUM") != null) 
										{
											matreqnum = (wpobj.get("MATREQNUM")).toString();
										}
										if (wpobj.get("REQUESTBY") != null) 
										{
											requestby = (wpobj.get("REQUESTBY")).toString();
										}



										List sendwpmatList = this.getBaseDao().findWithQuery(Wpmaterial.class, "prnum = '"+prnum+"' and prlinenum ='"+prlinenum+"' and matreqnum = '"+matreqnum+"' and requestby='"+requestby+"'");
										if(sendwpmatList.size()>0)
										{
											for(int n =0;n<sendwpmatList.size();n++)
											{
												Wpmaterial wpmat = (Wpmaterial) sendwpmatList.get(n);
												String matreqstr = wpmat.getMatreqnum();
												String itemnumstr = wpmat.getItemnum();
												String description = wpmat.getDescription();
												if(Util.isNull(body))
												{
													body = itemnumstr +"描述为["+description+"]";
												}
												else
												{
													body = body + ","+itemnumstr +"描述为["+description+"]";
												}
											}

										}

									}

								}


							}
						}

					}



				}
			}

			if(Util.isNotNull(body))
			{
				Msgreceive msg = new Msgreceive();
				body = "您'"+matreqnum+"'申请单中申请的"+body+"已经到货，请尽快办理领用！";
				String title = "资产领用提示！";
				msg.setTitle(title);
				msg.setBody(body);
				msg.setSddate(new Date());
				msg.setSender("Admin");
				msg.setReceiver(requestby);
				msg.setSendtype("私有");
				msg.setRectype("个人");
				msg.setHasread("否");
				this.getBaseDao().saveObject(msg);
				Labor labor = (Labor) this.getBaseDao().findUniqueBy(Labor.class, "labornum", requestby);
				if(Util.isNotNull(labor.getEmail()))
				{
					Util.sendMail(labor.getEmail(), "领用提示！", body);
				}

			}
		}


	}

	// ///////////////////业务方法//////////////////////////////////
}
