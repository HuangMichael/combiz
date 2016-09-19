package combiz.business.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class InventoryImpl extends IBOBaseImpl
implements InventorySrv
{
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Inventory))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
	
	public void adjustbinnum(Object obj) throws Exception {
		if (!(obj instanceof Invrectrans))
			throw new Exception("对象传递不正确!");
		Invrectrans irt = (Invrectrans) obj;
		irt.setStatus("已检验");
		super.save(irt);
		if(!irt.getFromwarehouse().equals(irt.getTowarehouse()))
		{
			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+irt.getItemnum()+"'");
			if(itemlist.size() >0)
			{
				Item item = (Item) itemlist.get(0);
				
				Inventory inventory = new Inventory();
	        	inventory.setItemnum(irt.getItemnum());
	        	inventory.setItemdesc(irt.getDescription());
	        	inventory.setWarehouse(irt.getTowarehouse());
	        	inventory.setMinlevel(0d);
	        	inventory.setMaxlevel(0d);
	        	inventory.setStocktype("非常备库存");
	        	inventory.setOrderqty(0d);
	        	inventory.setOrderunit(item.getOrderunit());
	        	inventory.setIssueunit(item.getIssueunit());
	        	inventory.setConversion(1d);
	        	inventory.setAvgcost(irt.getUnitcost());// 第一接收时候，库存成本就为最后一次接收成本。
	         	inventory.setLastcost(irt.getUnitcost());
	       	    inventory.setStdcost(irt.getUnitcost());	
	          	inventory.setIssueytd(0d);
	          	//inventory.setSitenum(irt.getSitenum());
	          	//inventory.setCorpnum(irt.getCorpnum());
	        	super.save(inventory);
			}
			
			
		}


//		List invtocklist =  this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"'");
			//转出库存项目原位置INVSTOCK表中LIST
		    List frominvstocklist = null;
		    String binnum = irt.getFrombin();
		    String lotnum = irt.getFromlot();
		    String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
		    if(Util.isNull(binnum))
	        {
		    	if(DBProduct.equals("SQLSERVER"))
				{
		    		if(Util.isNull(lotnum))
		    		{
		    			frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and (binnum is null or DATALENGTH(binnum) = 0)");
		    		}
		    		else
		    		{
		    			frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and lotnum = '"+lotnum+"'and warehouse ='"+irt.getFromwarehouse()+"' and (binnum is null or DATALENGTH(binnum) = 0)");
		    		}
		    		
				}
				else if(DBProduct.equals("ORACLE"))
				{
					if(Util.isNull(lotnum))
		    		{
						frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and binnum is null");
		    		}
					else
					{
						frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and binnum is null and lotnum='"+lotnum+"'");
					}
					
				}

	        }
	        else
	        {
	        	frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and binnum ='"+binnum+"'");
	        }
	        
			List toinvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getTowarehouse()+"' and binnum ='"+irt.getTobin()+"'");
			if(frominvstocklist.size() >0)
			{   
				Invstock frominvstock = (Invstock) frominvstocklist.get(0);
				if(frominvstock.getCurbal() - irt.getQuantity() == 0)//是否将原位置的所有库存项目转移出来
				{
					super.delete(frominvstock);
					//同时更新inventory表的平均价格为0(原仓库物料)
					Inventory oldinventory = null;
					Inventory newinventory = null;
					List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+irt.getItemnum()+"' and warehouse='"+irt.getFromwarehouse()+"'");
					if (inventorylist.size()>0) {
						oldinventory = (Inventory) inventorylist.get(0);
						oldinventory.setAvgcost(0D);
						this.getBaseDao().updateObject(oldinventory);
					}
					
					if(toinvstocklist.size() >0)//如果在INVSTOCK表中存在转移目标的记录。
					{
						Invstock toinvstock = (Invstock) toinvstocklist.get(0);
//						更新目标仓库库存余量
						toinvstock.setCurbal(toinvstock.getCurbal() + irt.getQuantity());
						this.getBaseDao().updateObject(toinvstock);
						//同时更新目标仓库的平均价格
						List invenlist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+irt.getItemnum()+"' and warehouse='"+irt.getTowarehouse()+"'");
						if (invenlist.size()>0) {
							newinventory = (Inventory) inventorylist.get(0);
							Double oldavgcost = 0D;
							Double newavgcost = 0D;
							if (oldinventory.getAvgcost() != null) {
								oldavgcost = oldinventory.getAvgcost();
							}
							if (newinventory.getAvgcost() != null) {
								newavgcost = newinventory.getAvgcost();
							}
							Double oldcurbal = 0D;
							Double newcurbal = 0D;
							if (toinvstock.getCurbal() != null) {
								oldcurbal = toinvstock.getCurbal();
							}
							if (irt.getQuantity() != null) {
								newcurbal = irt.getQuantity();
							}
							Double avgcost1 = oldavgcost*oldcurbal+newavgcost*newcurbal;
							Double curbal = oldcurbal + newcurbal;
							Double avgcost = (avgcost1/curbal);
							newinventory.setAvgcost(avgcost);
							this.getBaseDao().updateObject(newinventory);
						}
//						super.save(toinvstock);
					}
					else//在INVSTOCK表中新建一条记录
					{
						Invstock newobj = new Invstock();
						newobj.setItemnum(irt.getItemnum());
						newobj.setWarehouse(irt.getTowarehouse());
						newobj.setBinnum(irt.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						newobj.setPhyscnt(irt.getQuantity());
						newobj.setPhyscntdate(new Date());
						newobj.setCurbal(irt.getQuantity());
						newobj.setLotnum(irt.getFromlot());
						newobj.setBinnum(irt.getTobin());
						newobj.setReconciled("否");
						//newobj.setSitenum(irt.getSitenum());
						//newobj.setCorpnum(irt.getCorpnum());
						super.save(newobj);
					}
				}
				else//将原位置的库存项目部分转出
				{
					frominvstock.setCurbal(frominvstock.getCurbal() - irt.getQuantity());
					if(toinvstocklist.size() >0)//如果在INVSTOCK表中存在转移目标的记录。
					{
						Invstock toinvstock = (Invstock) toinvstocklist.get(0);
//						更新目标仓库库存余量
						toinvstock.setCurbal(toinvstock.getCurbal() + irt.getQuantity());
//						super.save(toinvstock);
						this.getBaseDao().updateObject(toinvstock);
					}
					else//在INVSTOCK表中新建一条记录
					{
						Invstock newobj = new Invstock();
						newobj.setItemnum(irt.getItemnum());
						newobj.setWarehouse(irt.getTowarehouse());
						newobj.setBinnum(irt.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						newobj.setPhyscnt(irt.getQuantity());
						newobj.setPhyscntdate(new Date());
						newobj.setLotnum(irt.getFromlot());
						newobj.setCurbal(irt.getQuantity());
						newobj.setBinnum(irt.getTobin());
						//newobj.setCorpnum(irt.getCorpnum());
						//newobj.setSitenum(irt.getSitenum());
						newobj.setReconciled("否");
						super.save(newobj);
					}
					
				}
			
			}
			Messagebox.show("完成库存项目调整");
			
		/*if (!(obj instanceof Invrectrans))
			throw new Exception("对象传递不正确!");
		Invrectrans irt = (Invrectrans) obj;
		irt.setStatus("已检验");
		super.save(irt);
		if(!irt.getFromwarehouse().equals(irt.getTowarehouse()))
		{
			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+irt.getItemnum()+"'");
			if(itemlist.size() >0)
			{
				Item item = (Item) itemlist.get(0);
				
				Inventory inventory = new Inventory();
	        	inventory.setItemnum(irt.getItemnum());
	        	inventory.setItemdesc(irt.getDescription());
	        	inventory.setWarehouse(irt.getTowarehouse());
	        	inventory.setMinlevel(0d);
	        	inventory.setMaxlevel(0d);
	        	inventory.setStocktype(item.getStocktype());
	        	inventory.setOrderqty(0d);
	        	inventory.setOrderunit(item.getOrderunit());
	        	inventory.setIssueunit(item.getIssueunit());
	        	inventory.setConversion(1d);
	        	inventory.setAvgcost(irt.getUnitcost());// 第一接收时候，库存成本就为最后一次接收成本。
	         	inventory.setLastcost(irt.getUnitcost());
	       	    inventory.setStdcost(irt.getUnitcost());	
	          	inventory.setIssueytd(0d);
	        	super.save(inventory);
			}
			
			
		}


//		List invtocklist =  this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse = '"+warehouse+"'");
			//转出库存项目原位置INVSTOCK表中LIST
		    List frominvstocklist = null;
		    String binnum = irt.getFrombin();
		    if(binnum == null || binnum.length() == 0)
	        {
		    	frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and (binnum is null or DATALENGTH(binnum) = 0)");
	        }
	        else
	        {
	        	frominvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getFromwarehouse()+"' and binnum ='"+binnum+"'");
	        //}
			List toinvstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+irt.getItemnum()+"' and warehouse ='"+irt.getTowarehouse()+"' and binnum ='"+irt.getTobin()+"'");
			if(frominvstocklist.size() >0)
			{   
				Invstock frominvstock = (Invstock) frominvstocklist.get(0);
				if(frominvstock.getCurbal() - irt.getQuantity() == 0)//是否将原位置的所有库存项目转移出来
				{
					super.delete(frominvstock);
					if(toinvstocklist.size() >0)//如果在INVSTOCK表中存在转移目标的记录。
					{
						Invstock toinvstock = (Invstock) toinvstocklist.get(0);
//						更新目标仓库库存余量
						toinvstock.setCurbal(toinvstock.getCurbal() + irt.getQuantity());
						this.getBaseDao().updateObject(toinvstock);
//						super.save(toinvstock);
					}
					else//在INVSTOCK表中新建一条记录
					{
						Invstock newobj = new Invstock();
						newobj.setItemnum(irt.getItemnum());
						newobj.setWarehouse(irt.getTowarehouse());
						newobj.setBinnum(irt.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						newobj.setPhyscnt(irt.getQuantity());
						newobj.setPhyscntdate(new Date());
						newobj.setCurbal(irt.getQuantity());
						newobj.setBinnum(irt.getTobin());
						newobj.setReconciled("否");
						super.save(newobj);
					}
				}
				else//将原位置的库存项目部分转出
				{
					frominvstock.setCurbal(frominvstock.getCurbal() - irt.getQuantity());
					if(toinvstocklist.size() >0)//如果在INVSTOCK表中存在转移目标的记录。
					{
						Invstock toinvstock = (Invstock) toinvstocklist.get(0);
//						更新目标仓库库存余量
						toinvstock.setCurbal(toinvstock.getCurbal() + irt.getQuantity());
//						super.save(toinvstock);
						this.getBaseDao().updateObject(toinvstock);
					}
					else//在INVSTOCK表中新建一条记录
					{
						Invstock newobj = new Invstock();
						newobj.setItemnum(irt.getItemnum());
						newobj.setWarehouse(irt.getTowarehouse());
						newobj.setBinnum(irt.getTobin());
						// 需要调整，库存项目不在一个箱柜的接收处理。
						newobj.setPhyscnt(irt.getQuantity());
						newobj.setPhyscntdate(new Date());
						newobj.setCurbal(irt.getQuantity());
						newobj.setBinnum(irt.getTobin());
						newobj.setCorpnum(irt.getCorpnum());
						newobj.setSitenum(irt.getSitenum());
						newobj.setReconciled("否");
						super.save(newobj);
					}
				}
			
			}
			Messagebox.show("完成库存项目调整");*/	
	}
/////////////////////业务方法-库存中发放物资//////////////////////////////////
	public void issue(Object obj) throws Exception {
		if (!(obj instanceof Invusetrans))
			throw new Exception("对象传递不正确!");
		Invusetrans invu = (Invusetrans) obj;
//		super.save(invuse);
		List avgcostlist = this.getBaseDao().findWithQuery(
				Inventory.class,
				"itemnum = '" + invu.getItemnum()
						+ "' and warehouse = '" + invu.getWarehouse()
						+ "'");
		Inventory avgcost = (Inventory) avgcostlist.get(0);
		invu.setUnitcost(avgcost.getAvgcost());
		Double linecost = invu.getQuantity()* avgcost.getAvgcost();
		invu.setLinecost(linecost);
		invu.setUnitcost(avgcost.getAvgcost());
		invu.setActualcost(avgcost.getAvgcost());
		Double issueqty = invu.getQuantity();
		// if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
		if (invu.getBinnum() != null) {
			Integer length = invu.getItemnum().trim().length();
			if (length > 0) {
				List invstocklist = this.getBaseDao().findWithQuery(
						Invstock.class,
						"itemnum ='" + invu.getItemnum()
								+ "' and warehouse ='" + invu.getWarehouse()
								+ "' and binnum = '" + invu.getBinnum()
								+ "'");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal = invstock.getCurbal();
				if (curbal - issueqty > 0) {
					invstock.setCurbal(curbal - issueqty);
					super.save(invstock);
					invu.setState("已完成");
					super.save(invu);

				} else {
					if (curbal - issueqty == 0) {
						invstock.setCurbal(0d);
						super.save(invstock);
						super.delete(invstock);
						invu.setState("已完成");
						super.save(invu);
					} else
						throw new Exception("库存项目编号为："
								+ invu.getItemnum() + "库存余量小于发放数量,不能进行发放");
				}
			} else
				throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");

		} else {
			List invstocklist = this.getBaseDao().findWithQuery(
					Invstock.class,
					"itemnum ='" + invu.getItemnum() + "' and warehouse ='"
							+ invu.getWarehouse() + "'and  (binnum is null or DATALENGTH(binnum) = 0) ");
			if(invstocklist.size()>0)
			{
			Invstock invstock = (Invstock) invstocklist.get(0);
			Double curbal1 = invstock.getCurbal();
			if (curbal1 - issueqty > 0) {
				invstock.setCurbal(curbal1 - issueqty);
				super.save(invstock);
				invu.setState("已完成");
				super.save(invu);

			} else {
				if (curbal1 - issueqty == 0) {
					invstock.setCurbal(0d);
					super.save(invstock);
					invu.setState("已完成");
					super.save(invu);
				} else
				{
					invu.setQuantity(curbal1);
					super.save(invu);
					throw new Exception("库存项目编号为：" + invu.getItemnum()
							+ "库存余量小于发放数量,不能进行发放");
				}

			}

		}
			else
			{
				throw new Exception("库存项目编号为：" + invu.getItemnum()
						+ "该箱柜余量为空，不能进行发放，请修正箱柜信息！");
			}
	}
}

	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：调整库存物料余量
	 * 日期：Jun 17, 2008 3:05:41 PM
	 *
	 */
	public void editcurbal(Object obj) throws Exception {
		if (!(obj instanceof Invtrans))
			throw new Exception("对象传递不正确!");
		Invtrans invtrans = (Invtrans) obj;
		Double qty = invtrans.getQuantity();
		invtrans.setQuantity(qty-invtrans.getCurbal());
		invtrans.setLinecost(invtrans.getNewcost()*qty);
		invtrans.setTransdate(new Date());
		this.getBaseDao().saveObject(invtrans);
		
		Invstock invstock = null;
		
	    List invstocklist = null;
	    String binnum = invtrans.getBinnum();
	    
	    
	    if(Util.isNull(binnum))
        {
	    	String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
	    	if(DBProduct.equals("SQLSERVER"))
			{
	    		invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invtrans.getWarehouse()+"' and itemnum='"+invtrans.getItemnum()+"'and (binnum is null or DATALENGTH(binnum) = 0)");
			}
			else if(DBProduct.equals("ORACLE"))
			{
				invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invtrans.getWarehouse()+"' and itemnum='"+invtrans.getItemnum()+"' and binnum is null");
			}
	    	
        }
        else
        {
        	invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "warehouse='"+invtrans.getWarehouse()+"' and itemnum='"+invtrans.getItemnum()+"' and binnum ='"+invtrans.getBinnum()+"'");
        }
		
		if (invstocklist.size()>0){
			invstock = (Invstock) invstocklist.get(0);
			invstock.setCurbal(qty);
			if (invtrans.getPhyscnt() != null){
			invstock.setPhyscnt(invtrans.getPhyscnt());
			}
			invstock.setPhyscntdate(new Date());
			Double cub = qty;
			Double phycnt = invtrans.getPhyscnt();
			if (cub == null) {
				cub = 0D;
			}
			if (phycnt == null) {
				phycnt = 0D;
			}
			if (cub - phycnt == 0) {
				invstock.setReconciled("是");
			}else{
				invstock.setReconciled("否");
			}
			this.getBaseDao().updateObject(invstock);
		}else{
			throw new Exception("箱柜编号传递不正确!");
		}
			Messagebox.show("完成库存物料余量调整");
			
	}

	
	/* 
	 * 功能：重订购库存项目
	 * 作者：李阳
	 * 日期：Oct 24, 2008  12:00:27 PM
	 */
	public int autogenerate(List list) throws Exception {
		// TODO Auto-generated method stub
		int hasgen = 0;
		Pr pr = null;
		String prnum = this.genInskey("prnum");
		for(int j=0;j<list.size();j++)
		{
			Inventory inv = (Inventory) list.get(j);
			Double minlevel = inv.getMinlevel();
			String itemnum = inv.getItemnum();
			String warehouse = inv.getWarehouse();
//			库存余量+采购途中的数量-预留数量 < 重订购点
//			库存余量
			Double sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where  t.itemnum = '"+itemnum+"' and t.warehouse = '"+warehouse+"'");
//			采购途中的数量
			Double sumpoorderqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.orderqty) from Poline t where  t.ponum in(select p.ponum  from Po p where p.status not in ('取消','关闭')) and t.itemnum = '"+itemnum+"' and t.warehouse = '"+warehouse+"' and t.sitenum ='");
			
			Double sumporecqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.receivedqty) from Poline t where  t.ponum in(select p.ponum  from Po p where p.status not in ('取消','关闭')) and t.itemnum = '"+itemnum+"' and t.warehouse = '"+warehouse+"'");
//			预留数量
			Double sumresqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.reservedqty) from Invreserve t where  t.itemnum = '"+itemnum+"' and t.warehouse = '"+warehouse+"'");
			
			if(sumcurbal == null)
			{
				sumcurbal = 0d;
			}
			if(sumporecqty == null)
			{
				sumporecqty = 0d;
			}
			if(sumpoorderqty == null)
			{
				sumpoorderqty = 0d;
			}
			if(sumresqty == null)
			{
				sumresqty = 0d;
			}
			
			if (sumcurbal + sumpoorderqty - sumporecqty - sumresqty - minlevel < 0)
			{
				hasgen++;
				Prline prline = new Prline();
				long maxID = this.getBaseDao().selectLongMaxByHql("select max(pr.prlinenum) from Prline pr where pr.prnum='" + prnum + "'") + 1;
				prline.setPrnum(prnum);
				prline.setPrlinenum(maxID);
				prline.setItemnum(inv.getItemnum());
				List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum = '"+inv.getItemnum()+"'");
				if(itemlist.size()>0)
				{
					Item item = (Item) itemlist.get(0);
					prline.setDescription(item.getDescription());
				}
				prline.setWarehouse(inv.getWarehouse());
				prline.setOrderqty(inv.getOrderqty());
				prline.setOrderunit(inv.getOrderunit());
				prline.setConversion(1d);
				prline.setUnitcost(inv.getAvgcost());
				prline.setLinecost(inv.getAvgcost() * inv.getOrderqty());
				prline.setEnterby(this.getLaborInfo().getLaborname());
				prline.setEnterdate(new Date());
				prline.setProrateservice("否");
				prline.setIsservice("否");
				prline.setLoadedcost(0d);
				prline.setInspection("否");
				//prline.setCorpnum(inv.getCorpnum());
				//prline.setSitenum(inv.getSitenum());
				this.getBaseDao().saveObject(prline);
			}
			
		}
		
		if(hasgen > 0)
		{
			pr = new Pr();
			pr.setPrnum(prnum);
			Date date=new Date(); 
			SimpleDateFormat sp=new SimpleDateFormat("yyyy年MM月dd日hh时mm分"); 
			String nowdate=sp.format(date); 
			
			String des = nowdate + "由重订购自动生成";
			pr.setDescription(des);
			pr.setRequestedby(this.getUserInfo().getLabornum());
			pr.setChangeby(this.getUserInfo().getLabornum());
			pr.setRequestdate(new Date());
			pr.setChangedate(new Date());
			pr.setStatus("流程未启动");
			//pr.setCorpnum(corpnum);
			//pr.setSitenum(sitenum);
			pr.setPrtype("采购申请");
			pr.setStatusdate(new Date());
			this.getBaseDao().saveObject(pr);
		}
		return hasgen;
	}
	
	
	public void rsetavgcost(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if (!(obj instanceof Invtrans))
			throw new Exception("对象传递不正确!");
		Invtrans invtrans = (Invtrans) obj;
		Double curbalsum = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '"+invtrans.getItemnum()+"' and t.warehouse='"+invtrans.getWarehouse()+"'");
		if(curbalsum == null)
		{
			curbalsum=0d;
		}
		invtrans.setQuantity(curbalsum);
		invtrans.setLinecost(invtrans.getNewcost()*curbalsum);
		
		this.getBaseDao().saveObject(invtrans);
		
		Inventory inventory = null;
		List invinventorylist = null;
		 String binnum = invtrans.getBinnum();
		    if(binnum == null || binnum.length() == 0)
	        {
		    	invinventorylist = this.getBaseDao().findWithQuery(Inventory.class, "warehouse='"+invtrans.getWarehouse()+"' and itemnum='"+invtrans.getItemnum()+"'");
	        }
	        else
	        {
	        	invinventorylist = this.getBaseDao().findWithQuery(Inventory.class, "warehouse='"+invtrans.getWarehouse()+"' and itemnum='"+invtrans.getItemnum()+"'");
	        }
		
		
		if (invinventorylist.size()>0){
			inventory = (Inventory) invinventorylist.get(0);
			inventory.setAvgcost(invtrans.getNewcost());
			this.getBaseDao().updateObject(inventory);
		}else{
			throw new Exception("箱柜编号传递不正确!");
		}
			Messagebox.show("完成库存物料平均成本调整");
	}

}
