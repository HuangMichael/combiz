package combiz.business.inventory;

import java.util.Date;
import java.util.List;

import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.*;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

public class RejectImpl extends IBOBaseImpl
implements RejectSrv
{
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");
		super.delete(obj);
	}

	/* 
	 * 功能：从库存表中复制到报废明细行里
	 * 作者：李阳
	 * 日期：Oct 21, 2008  12:00:45 PM
	 */
	public void createdel(List list,String s) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Invstock invstock = (Invstock) list.get(i);
			Item item = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum", invstock.getItemnum());
			if(item != null)
			{
				int count = this.getBaseDao().selectCountByHql("select count(*) from Classification t where t.classtype = '资产' and t.classid = '"+item.getClassid()+"'");
				if(count>0)//资产
				{
					Double qty = invstock.getCurbal();
					int iqty = qty.intValue();
					int eqlistsize = 0;
					if(iqty>=1)
					{
						List eqlist = this.getBaseDao().findWithQuery(Equipment.class, "itemnum='"+invstock.getItemnum()+"' and lotnum='"+invstock.getLotnum()+"'");
						if(eqlist.size()>0)
						{
							eqlistsize = eqlist.size();

						}
						for(int j=0;j<iqty;j++)
						{
							if(j<=eqlistsize-1)
							{
								Equipment equipment = (Equipment) eqlist.get(j);
								Long linenum = (Long) this.getBaseDao().selectMaxByHql("select max(t.rejlinenum) from Rejectitem t where t.rejectnum='"+s+"'");
								if(linenum == null)
								{
									linenum = 0l;
								}
								Rejectitem rej = new Rejectitem();
								rej.setRejectnum(s);
								rej.setEqnum(equipment.getEqnum());
								rej.setRejlinenum(linenum+1);
								rej.setItemnum(invstock.getItemnum());
								rej.setLotnum(invstock.getLotnum());
								rej.setRejectqty(1d);
								rej.setWarehouse(invstock.getWarehouse());
								rej.setInvstockid(invstock.getId());
								rej.setBinnum(invstock.getBinnum());
								this.getBaseDao().saveObject(rej);

							}


						}

					}

				}
				else//物资
				{
					Rejectitem rej = new Rejectitem();
					Long linenum = (Long) this.getBaseDao().selectMaxByHql("select max(t.rejlinenum) from Rejectitem t where t.rejectnum='"+s+"'");
					rej.setRejectnum(s);
					if(linenum==null){
						linenum = 0L;
					}
					rej.setRejlinenum(linenum+1);
					rej.setItemnum(invstock.getItemnum());
					rej.setLotnum(invstock.getLotnum());
					rej.setRejectqty(invstock.getCurbal());
					rej.setWarehouse(invstock.getWarehouse());
					rej.setInvstockid(invstock.getId());
					rej.setBinnum(invstock.getBinnum());
					this.getBaseDao().saveObject(rej);

				}
			}
		}

	}


	/* 
	 * 功能：对报废单下的报废明细行进行批准报废操作
	 * 作者：李阳
	 * 日期：Oct 21, 2008  12:00:45 PM
	 */
	public void verify(List list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Rejectitem rej = (Rejectitem) list.get(i);
			String itemnum = rej.getItemnum();
			List itemlist = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemlist.get(0);
			String lottype =  item.getLottype();

			/*************************
			 * 修改相关表的数据
			 */
			List stocklist = null;
			if(lottype.equals("非批次管理"))
			{
				if(Util.isNull(rej.getBinnum()))//如果箱柜为空
				{
					stocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
							+"' and binnum is null");
				}
				else
				{
					stocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
							+"' and binnum ='"+rej.getBinnum()+"' ");
				}

			}
			else
			{
				stocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
						+"' and lotnum ='"+rej.getLotnum()+"' ");

			}

			if(stocklist.size()>0)
			{
				Invstock invstock = (Invstock) stocklist.get(0);
				Double curbal = invstock.getCurbal();
				if(rej.getRejectqty() - curbal >0)
				{
					throw new Exception("库存编号为:'"+itemnum+"'的库存报废数量大于库存余量，不能报废，请核实！");
				}
				else
				{
					if(rej.getRejectqty() - curbal == 0 && lottype.equals("批次管理"))
					{
						this.getBaseDao().deleteObject(invstock);
						List invlotlist = this.getBaseDao().findWithQuery(Invlot.class, "itemnum ='"+rej.getItemnum()+"' and warehouse ='"+rej.getWarehouse()+"'  and lotnum = '"+rej.getLotnum()+"'");
						if(invlotlist.size()>0)
						{
							Invlot invlot = (Invlot) invlotlist.get(0);
							this.getBaseDao().deleteObject(invlot);
						}

					}
					else
					{
						invstock.setCurbal(curbal - rej.getRejectqty());
						this.getBaseDao().updateObject(invstock);
					}

				}

			}




			/*************************
			 * 相关表里写数据
			 */
			Double avgcost = 0d;
			if(lottype.equals("非批次管理"))
			{
				List invlist = this.getBaseDao().findWithQuery(Inventory.class,"itemnum = '"+itemnum+"' and warehouse='"+ rej.getWarehouse() +"'");
				if(invlist.size()>0)
				{
					Inventory inventory = (Inventory) invlist.get(0);
					avgcost = inventory.getAvgcost();
				}
			}
			else//批次管理
			{
				List invlotlist = this.getBaseDao().findWithQuery(Invlot.class,"itemnum = '"+itemnum+"' and warehouse='"+ rej.getWarehouse() +"' and lotnum = '"+rej.getLotnum()+"'");
				Invlot invlot = (Invlot) invlotlist.get(0);
				avgcost = invlot.getLotcost();
			}
			/****************************
			 * 在库存的交易表(Invtrans)里插入一条数据
			 */

			Invtrans invt = new Invtrans();
			invt.setItemnum(itemnum);
			invt.setWarehouse(rej.getWarehouse());
			invt.setTransdate(new Date());
			invt.setTranstype("报废");
			invt.setQuantity(rej.getRejectqty());
			invt.setCurbal(rej.getRejectqty());
			invt.setPhyscnt(avgcost);
			invt.setOldcost(avgcost);
			invt.setNewcost(avgcost);
			invt.setLinecost(avgcost * rej.getRejectqty());
			invt.setConversion(1d);
			invt.setEnterby(this.getUserInfo().getLabornum());
			invt.setBinnum(rej.getBinnum());
			invt.setLotnum(rej.getLotnum());
			//invt.setSitenum(rej.getSitenum());
			//invt.setCorpnum(rej.getCorpnum());
			this.getBaseDao().saveObject(invt);
			/****************************
			 * 判断该地点是否是第一次报废，如果是第一次报废的话，在仓库表里插入一条“报废库”数据。
			 */
			List warelist = this.getBaseDao().findWithQuery(Warehouse.class, "warehouse like '%报废库%' ");
			if(warelist.size()<=0)//第一次做报废操作
			{
				Warehouse ware =  new Warehouse();
				ware.setWarehouse("报废库");
				ware.setDescription("报废库");
				ware.setWarehouseadmin("ADMIN");
				//ware.setSitenum(rej.getSitenum());
				//ware.setCorpnum(rej.getCorpnum());
				this.getBaseDao().saveObject(ware);

			}

			/****************************
			 * 在库存的接收表(Invrectrans)里插入一条数据
			 */
			Invrectrans invrec = new Invrectrans();
			invrec.setItemnum(itemnum);
			invrec.setDescription(item.getDescription());
			invrec.setTowarehouse("报废库");
			invrec.setTransdate(new Date());
			invrec.setActualdate(new Date());
			invrec.setQuantity(rej.getRejectqty());
			invrec.setRecunit(item.getIssueunit());
			invrec.setRectype("报废接收");
			invrec.setUnitcost(avgcost);
			invrec.setLinecost(rej.getRejectqty() * avgcost);
			invrec.setOldavgcost(avgcost);
			invrec.setActualcost(avgcost);
			invrec.setRejectqty(0d);
			invrec.setConversion(1d);
			invrec.setCurbal(rej.getRejectqty());
			invrec.setTobin(rej.getBinnum());
			invrec.setFromwarehouse(rej.getWarehouse());
			invrec.setFrombin(rej.getBinnum());
			invrec.setTolot(rej.getLotnum());
			invrec.setLoadedcost(rej.getRejectqty() * avgcost);
			invrec.setStatus("已检验");
			invrec.setStatusdate(new Date());
			invrec.setChangeby(this.getUserInfo().getLabornum());
			//invrec.setCorpnum(rej.getCorpnum());
			//invrec.setSitenum(rej.getSitenum());
			this.getBaseDao().saveObject(invrec);
			
			//如果是资产报废，修改资产卡片里的信息。
			if(Util.isNotNull(rej.getEqnum()))
			{
				Equipment equip = (Equipment) this.findUniqueBy(Equipment.class, "eqnum",rej.getEqnum());
				if(equip!=null)
				{
					equip.setLocation("报废位置");
					equip.setLabornum("");
					equip.setStatus("报废");
					this.getBaseDao().updateObject(equip);
				}
			}
			
			

		}
		
		
		
		

	}

	/* 
	 * 功能：在资产报损报废应用程序里将从台帐中选择的明细行生成报废明细
	 * 作者：李阳
	 * 日期：Feb 13, 2009  2:48:24 PM
	 */
	public void genrejectline(List list,String s) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Equipment equip = (Equipment) list.get(i);
			Rejectitem rej = new Rejectitem();

			int linenum = this.getRowCountByWhere(rej, "rejectnum='"+s+"'");	
			rej.setRejectnum(s);
			rej.setRejlinenum((long)linenum+1);
			//rej.setSitenum(equip.getSitenum());
			//rej.setCorpnum(equip.getCorpnum());
			rej.setItemnum(equip.getItemnum());

			String lotnum = null;
			List invreclist = this.getBaseDao().findWithQuery(Invrectrans.class, "rectype = '接收' and eqnum = '"+equip.getEqnum()+"'");
			if(invreclist.size()>0)
			{
				Invrectrans invrec = (Invrectrans) invreclist.get(0);
				lotnum = invrec.getTolot();
			}
			rej.setLotnum(lotnum);

			rej.setRejectqty(1d);
			rej.setEqnum(equip.getEqnum());

			rej.setLocation(equip.getLocation());
			rej.setLabornum(equip.getLabornum());
			rej.setDeptnum(equip.getDeptnum());
			rej.setInvstockid(equip.getId());
			rej.setItemnum(equip.getItemnum());
			rej.setIsreject("是");
			this.getBaseDao().saveObject(rej);
		}

	}


	/* 
	 * 功能：领导审批时候，从报废申请中选择不通过审批的资产。
	 * 作者：李阳
	 * 日期：Feb 14, 2009  4:59:21 PM
	 */
	public void noapproveeq(List list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Rejectitem rej = (Rejectitem) list.get(i);
			rej.setIsreject("否");
			this.getBaseDao().updateObject(rej);
		}

	}


	/* 
	 * 功能：领导审批时候，从报废申请中选择不通过审批的资产。
	 * 作者：李阳
	 * 日期：Feb 14, 2009  4:59:21 PM
	 */
	public void noyesapproveeq(List list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Rejectitem rej = (Rejectitem) list.get(i);
			rej.setIsreject("是");
			this.getBaseDao().updateObject(rej);
		}

	}


	/* 
	 * 功能：领导审批时候，同意报废资产。
	 * 作者：李阳
	 * 日期：Feb 14, 2009  4:59:21 PM
	 */
	public void approverej(List list,Object obj) throws Exception
	{
		Reject reject = (Reject) obj;
		for(int i=0;i<list.size();i++)
		{
			Rejectitem rej = (Rejectitem) list.get(i);
			String loc = rej.getLocation();
			Long id = rej.getInvstockid();
			Equipment equip = (Equipment) this.getBaseDao().findById(Equipment.class, id);
			if(loc.equals("仓库一"))//判断是否为仓库位置
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum='"+equip.getItemnum()+"' and lotnum='"+equip.getLotnum()+"' ");
				if(invstocklist.size()>0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal-rej.getRejectqty()>=0)
					{
						invstock.setCurbal(curbal-rej.getRejectqty());
						this.getBaseDao().updateObject(invstock);
					}
					else
					{
						throw new Exception("库存余量不足，无法完成报废，请核实！");
					}
				}


			}
			//修改资产台帐中相关信息。

			equip.setLocation("BFWZ");
			equip.setStatus("报废");
			this.getBaseDao().updateObject(equip);
			//在资产交易表里插入数据。
			Eqtrans eqtrans = new Eqtrans();
			eqtrans.setEqnum(equip.getEqnum());
			eqtrans.setFromloc(equip.getLocation());
			eqtrans.setToloc("BFWZ");
			eqtrans.setMoveby(reject.getRequestby());
			eqtrans.setDatemoved(new Date());
			eqtrans.setTransdate(new Date());
			//eqtrans.setSitenum(equip.getSitenum());
			//eqtrans.setCorpnum(equip.getCorpnum());
			this.getBaseDao().saveObject(eqtrans);
			/***********
			 * 将报废单主表的单据修改为‘报废完成’
			 * 
			 */
			reject.setStatus("执行报废");
			this.getBaseDao().updateObject(reject);

		}

	}
}