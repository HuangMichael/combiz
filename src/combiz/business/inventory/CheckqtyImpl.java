package combiz.business.inventory;

import java.util.Date;
import java.util.List;

import combiz.domain.inventory.*;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;

public class CheckqtyImpl extends IBOBaseImpl
implements CheckqtySrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}
	
/////////////////////业务方法//////////////////////////////////
	/* 
	 * 功能：在用户输入盘点日期后，选择需要盘点的库房，系统自动生成该盘点日期中，选定库存的实时库存余量信息。
	 * 作者：李阳
	 * 日期：Oct 20, 2008  10:21:06 AM
	 */
	public void generate(Object obj,String wherestr) throws Exception{
		
		Checkqty checkqty = (Checkqty) obj;
		if(Util.isNotNull(wherestr))
		{
			List realList = this.getBaseDao().findWithQuery(Invstock.class, "curbal > 0 and "+wherestr+"");
			if(realList.size()>0){
				for(int i=0;i<realList.size();i++){
					Invstock invstock = (Invstock)realList.get(i);
					Checkqtyitem checkqtyitem = new Checkqtyitem();
					String itemnum = invstock.getItemnum();
					checkqtyitem.setItemnum(itemnum);
					
					List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
					if(itemlist.size()>0)
					{
						Item item = (Item) itemlist.get(0);
						checkqtyitem.setItemdesc(item.getDescription());
					}
					checkqtyitem.setBinnum(invstock.getBinnum());
					checkqtyitem.setWarehouse(invstock.getWarehouse());
					checkqtyitem.setAccountqty(invstock.getCurbal());
					checkqtyitem.setActualqty(invstock.getCurbal());
					checkqtyitem.setCheckqtynum(checkqty.getCheckqtynum());
					checkqtyitem.setLotnum(invstock.getLotnum());
					//checkqtyitem.setSitenum(checkqty.getSitenum());
					//checkqtyitem.setCorpnum(checkqty.getCorpnum());
					this.getBaseDao().saveObject(checkqtyitem);
				}
			}
			else
			{
				throw new Exception("该过滤条件下，没有盘点明细表，请确认！");
			}
		
		}
		
		
	}
	

	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：生成盘点日期错误的时候，清除所有的生成的盘点明细。
	 * 日期：Oct 20, 2008 4:19:35 PM
	 *
	 */
	public void cleardata(List list) throws Exception{

		this.getBaseDao().deleteBatch(list);
	}
	
	/* 
	 * 功能：根据盘点明细修改库存存货余量
	 * 作者：李阳
	 * 日期：Dec 29, 2008  3:41:37 PM
	 */
	public void modifycurbal(List list) throws Exception{
		for(int i =0;i<list.size();i++)
		{
			Checkqtyitem checkitem = (Checkqtyitem) list.get(i);
			Double avgcost= 0d;
			Double curbal = checkitem.getAccountqty();
			Double newcurbal = checkitem.getActualqty();
			List invstocklist = null;
			String itemnum = checkitem.getItemnum();
			String warehouse = checkitem.getWarehouse();
			String binnum = checkitem.getBinnum();
			String lotnum = checkitem.getLotnum();
			String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
			if(curbal-newcurbal!=0)
			{
				List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+checkitem.getItemnum()+"'and warehouse='"+checkitem.getWarehouse()+"'");
				if(inventorylist.size()>0)
				{
					Inventory inven = (Inventory) inventorylist.get(0);
					avgcost = inven.getAvgcost();
				}
				if(Util.isNull(binnum))
				{
					
					if(Util.isNull(lotnum))
					{
						if(DBProduct.equals("SQLSERVER"))
						{
				    		invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and (binnum is null or DATALENGTH(binnum) = 0) and (lotnum is null or DATALENGTH(lotnum) = 0)");
						}
						else if(DBProduct.equals("ORACLE"))
						{
							invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and binnum is null and lotnum is null");
						}
						
					}
					else
					{
						if(DBProduct.equals("SQLSERVER"))
						{
				    		invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and (binnum is null or DATALENGTH(binnum) = 0) and lotnum ='"+checkitem.getLotnum()+"'");
						}
						else if(DBProduct.equals("ORACLE"))
						{
							invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and binnum is null and lotnum='"+checkitem.getLotnum()+"'");
						}
					}
			    	
				}
				else
				{
					if(Util.isNull(lotnum))
					{
						if(DBProduct.equals("SQLSERVER"))
						{
							invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and binnum ='"+binnum+"' and (lotnum is null or DATALENGTH(lotnum) = 0)");
						}
						else if(DBProduct.equals("ORACLE"))
						{
							invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and binnum ='"+binnum+"' and lotnum is null ");
						}
					}
					else
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + itemnum + "' and warehouse ='" + warehouse + "' and binnum ='"+binnum+"' and lotnum='"+lotnum+"'");
					}
					
					
					
				}
				if(invstocklist.size()>0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					invstock.setCurbal(newcurbal);
					this.getBaseDao().updateObject(invstock);
				}
				//在库存交易表里插入数据，记录库存盘点的操作记录
				Invtrans invtrans = new Invtrans();
				invtrans.setQuantity(checkitem.getActualqty());
				invtrans.setLinecost(avgcost*checkitem.getActualqty());
				invtrans.setCurbal(checkitem.getAccountqty());
				invtrans.setPhyscnt(checkitem.getActualqty());
				invtrans.setItemnum(checkitem.getItemnum());
				invtrans.setTranstype("库存盘点");
				invtrans.setWarehouse(checkitem.getWarehouse());
				invtrans.setLotnum(checkitem.getLotnum());
				invtrans.setOldcost(avgcost);
				invtrans.setNewcost(avgcost);
				invtrans.setConversion(1d);
				invtrans.setEnterby(this.getUserInfo().getLabornum());
				invtrans.setTransdate(new Date());
				//invtrans.setCorpnum(checkitem.getCorpnum());
				//invtrans.setSitenum(checkitem.getSitenum());
				this.getBaseDao().saveObject(invtrans);
			}
			
			
			
			
			
		}

	}
}