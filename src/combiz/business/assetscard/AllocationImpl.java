package combiz.business.assetscard;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Sessions;

import combiz.domain.assetscard.*;
import combiz.domain.classattr.Classspec;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Rejectitem;
import combiz.domain.po.Invvendor;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;

public class AllocationImpl extends IBOBaseImpl
implements AllocationSrv
{

	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave(Object obj) 
	throws Exception
	{
		return true;
	}

	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		return true;
	}

	/* 
	 * 功能：从资产明细表里拷贝数据到调拨明细表
	 * 作者：李阳
	 * 日期：May 13, 2010  4:25:59 PM
	 */
	public void insertline(List list,Object obj) throws Exception
	{
		Allocation allocation = (Allocation) obj;
		for(int i=0;i<list.size();i++)
		{
			Equipment equip = (Equipment) list.get(i);
			Allocationline newobj = new Allocationline();
			Long linenum = (Long) this.getBaseDao().selectMaxByHql("select max(t.linenum) from Allocationline t where t.allocationnum='"+allocation.getAllocationnum()+"'");
			if(linenum == null)
			{
				linenum = 0l;
			}
			newobj.setAllocationnum(allocation.getAllocationnum());
			newobj.setLinenum(linenum+1);
			newobj.setEqnum(equip.getEqnum());
			newobj.setItemnum(equip.getItemnum());
			newobj.setFromwarehouse(equip.getLocation());
			newobj.setModelnum(equip.getModel());
			newobj.setDescription(equip.getDescription());
			newobj.setQuantity(1d);
			newobj.setTotalcost(equip.getTotalcost());	
			newobj.setSitenum(allocation.getSitenum());
			newobj.setCorpnum(allocation.getCorpnum());
			this.getBaseDao().saveObject(newobj);

		}

	}

	/* 
	 * 功能：往调拨接收行里插入数据
	 * 作者：李阳
	 * 日期：May 18, 2010  11:24:38 AM
	 */
	public void addallocline(List list,Object obj) throws Exception
	{
		Allocation allocation = (Allocation) obj;
		for(int i=0;i<list.size();i++)
		{
			Allocationline allocationline = (Allocationline) list.get(i);
			Invrectrans irt = new Invrectrans();
			Date date=new Date(); 
			SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
			String nowdate=sp.format(date); 
			irt.setTolot(nowdate);
			irt.setQuantity(1d);
			irt.setEqnum(allocationline.getEqnum());
			irt.setModelnum(allocationline.getModelnum());
			irt.setUnitcost(allocationline.getTotalcost());
			irt.setConversion(1d);
			Equipment equip = (Equipment) this.getBaseDao().findUniqueBy(Equipment.class, "eqnum",allocationline.getEqnum());
			if(equip!=null)
			{
				irt.setFromlot(equip.getLotnum());
				irt.setItemnum(equip.getItemnum());
				irt.setManufacturer(equip.getManufacturer());

				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + equip.getItemnum() + "' and warehouse ='" + allocationline.getTowarehouse() +"'");
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
			}
			Item item = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum", equip.getItemnum());
			if(item!=null)
			{
				irt.setRecunit(item.getOrderunit());
			}

			irt.setPonum(allocationline.getAllocationnum());
			irt.setPolinenum(allocationline.getLinenum());
			irt.setChangeby(this.getUserInfo().getLabornum());
			irt.setTransdate(new Date());
			irt.setActualdate(new Date());
			irt.setLinecost(allocationline.getTotalcost());
			irt.setActualcost(allocationline.getTotalcost());
			irt.setRejectqty(0d);
			irt.setDescription(allocationline.getDescription());
			//irt.setSitenum(pl.getSitenum());
			//irt.setCorpnum(pl.getCorpnum());
			//调整。。。。。。
			irt.setFromwarehouse(allocationline.getFromwarehouse());
			irt.setTowarehouse(allocationline.getTowarehouse());


			irt.setRectype("接收"); //设置invtrans表中的类型为接收
			irt.setLoadedcost(allocationline.getTotalcost());
			irt.setStatus("待检验");
			irt.setOldavgcost(0d);
			this.getBaseDao().saveObject(irt);


		}

	}



	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：校验调拨明细行里的数据，修改接收仓库的库存。
	 * 日期：1:43:08 PM  May 18, 2010 
	 *
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Allocation))
			throw new Exception("对象传递不正确!");
		Allocation allocation = (Allocation) obj;
		for (int i = 0; i < list.size(); i++)
		{
			Invrectrans invrectrans = (Invrectrans) list.get(i);
			String ponum = invrectrans.getPonum();
			String itemnum = invrectrans.getItemnum();
			String warehouse = invrectrans.getTowarehouse();
			String fromwarehouse = invrectrans.getFromwarehouse();
			Double unitcost = invrectrans.getUnitcost();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			String orderunit = item.getOrderunit();
			String issueunit = item.getIssueunit();
			Long linenum = invrectrans.getPolinenum();


			/***********************************************************************
			 * **********************************************************************
			 * ************************修改源仓库库存余量*******************************
			 **********************************************************************/
			String frombinnum = invrectrans.getFrombin();
			Double rqty = invrectrans.getQuantity();
			String rotating = item.getRotating();
			if(item.getLottype().equals("非批次"))//非批次管理
			{
				int size = 0;
				if(Util.isNull(frombinnum))
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ fromwarehouse +"' and frombinnum is null");
					if (curbal.size() > 0) 
					{
						Invstock invstock = (Invstock) curbal.get(0);
						Double nowcurbal = invstock.getCurbal() - rqty; // 将接收的库存数量增加，更新库存余量；
						invstock.setCurbal(nowcurbal);	
						this.getBaseDao().saveObject(invstock);// 注意顺序，一定想写回库存接收表的余量信息，因为这是接收前库存余量，再更新invstock的库存余量，否则会读脏数据。
					}

					super.getBaseDao().updateObject(invrectrans);
				}
				else
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ fromwarehouse +"' and binnum = '" + frombinnum +"'");
					if (curbal.size()>0) {
						Invstock invstock = (Invstock) curbal.get(0);
						Double nowcurbal = invstock.getCurbal() - rqty; 
						invstock.setCurbal(nowcurbal);	
						this.getBaseDao().saveObject(invstock);
					}
				}

			}
			else//批次管理的设备
			{


				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='"+itemnum+"' and warehouse ='"+fromwarehouse+"' and lotnum = '"+invrectrans.getFromlot()+"' ");
				if(invstocklist.size()>0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					invstock.setCurbal(invstock.getCurbal()-invrectrans.getQuantity());
					this.getBaseDao().updateObject(invstock);
				}

			}


			/***********************************************************************
			 * ******************************************************************
			 * 判断该库存项目是否在在库存中有，如果没有发生过，将其信息添加到INVENTORY中
			 **********************************************************************/

			//先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；

			String binnum = invrectrans.getTobin();
			List noitem = this.getBaseDao().findWithQuery(Inventory.class,"itemnum = '"+itemnum+"' and warehouse='"+ warehouse +"'");

			if(noitem.size() == 0)
			{

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

				Double sumcurbal =  0d;
				sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '" + itemnum + "' and t.warehouse = '"+ warehouse +"'");
				if(sumcurbal == null)
					sumcurbal = 0d;
				Double avgcost = (sumcurbal * inventory.getAvgcost() + unitcost * invrectrans.getQuantity()) / (sumcurbal + invrectrans.getQuantity());
				inventory.setAvgcost(avgcost);
				super.update(inventory);

			}

			/***************************************************
			 * ******************************************************************
			 * 3.如果是非批次管理，判断该库存项目是否在库存余量表中有信息，如果没有发生过，赋值当前余量为0；
			 * 如果是批次管理的话，就在INVSTOCK、INVLOT表中插入数据。 
			 **************************************************/

			if(item.getLottype().equals("非批次"))//非批次管理
			{
				int size = 0;
				if(Util.isNull(binnum))
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
					size = curbal.size();
					if (size == 0) 
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
						super.save(invstock);


					} 
					else 
					{
						Invstock invstock = (Invstock) curbal.get(0);
						invrectrans.setCurbal(invstock.getCurbal());// 在Invrectrans表里的curbal字段写回当前的库存余量；
						Double nowcurbal = invstock.getCurbal() + rqty; // 将接收的库存数量增加，更新库存余量；
						invstock.setCurbal(nowcurbal);	
						super.getBaseDao().updateObject(invrectrans);
						super.save(invstock);// 注意顺序，一定想写回库存接收表的余量信息，因为这是接收前库存余量，再更新invstock的库存余量，否则会读脏数据。
					}
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态

					invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
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
						super.save(invstock);
						invrectrans.setStatus("已检验");//接收完成，不可编辑状态
						invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
						invrectrans.setStatusdate(new Date());
						super.getBaseDao().updateObject(invrectrans);

					} else 
					{
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
					//修改资产表equipment里的数据。
					Equipment equip = (Equipment) this.findUniqueBy(Equipment.class, "eqnum",invrectrans.getEqnum());
					if(equip!=null)
					{
						equip.setLocation(invrectrans.getTowarehouse());
						equip.setStatus("未启用");
						equip.setDeptnum("");
						equip.setLabornum("");
						equip.setChangeby(this.getUserInfo().getLabornum());
						equip.setChangedate(new Date());
						equip.setLotnum(invrectrans.getTolot());
						this.getBaseDao().updateObject(equip);
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
						invstock.setLotnum(invrectrans.getTolot());
						this.getBaseDao().saveObject(invstock);
					}

					int invlotcount = this.getBaseDao().selectCountByHql("select count(*) from Invlot t where t.itemnum ='"+itemnum+"' and t.warehouse ='"+warehouse+"' and t.lotnum = '"+invrectrans.getTolot()+"'");
					if(invlotcount == 0)
					{
						Invlot invlot = new Invlot();
						invlot.setItemnum(invrectrans.getItemnum());
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
					invstock.setLotnum(invrectrans.getTolot());
					super.save(invstock);

					Invlot invlot = new Invlot();
					invlot.setItemnum(invrectrans.getItemnum());
					invlot.setWarehouse(invrectrans.getTowarehouse());
					invlot.setVendor(invrectrans.getManufacturer());
					invlot.setLotnum(invrectrans.getTolot());
					invlot.setLotcost(invrectrans.getUnitcost());
					invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
					super.getBaseDao().saveObject(invlot);
					invrectrans.setStatus("已检验");//接收完成，不可编辑状态
					invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}

			}


			/*************修改invvendor表里的数据*************/
			if(Util.isNotNull(invrectrans.getManufacturer()))
			{List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and vendor='"+invrectrans.getManufacturer()+"'");
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
				invendor.setVendor(invrectrans.getManufacturer());
				invendor.setPromtime(allocation.getFromdate());
				invendor.setLastdate(invrectrans.getTransdate());
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setOrderunit(invrectrans.getRecunit());
				invendor.setIsdefault("否");
				this.getBaseDao().saveObject(invendor);

			}
			}
		}


		/*************修改invvendor表里的数据(结束)*************/


	}


	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		return true;
	}



	/**
	 * 用户接口
	 * 保存新建对象后的用户接口方法
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterInsert(Object obj) 
	throws Exception
	{
	}

	/**
	 * 用户接口
	 * 保存更新对象后的用户接口方法
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterUpdate(Object obj) 
	throws Exception
	{
	}

	/**
	 * 删除对象之前的用户接口
	 * 返回false的话不会删除该对象
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public boolean beforeDelete(Object obj) throws Exception
	{
		return true;
	}

	/**
	 * 系统删除对象的方法
	 * 可以在该方法中手工指定删除哪些子表结果集（通过关系名）
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj 主对象
	 * @throws Exception
	 */
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");  //通过关系名删除字表结果集
		super.delete(obj);
	}


	/**
	 * 删除对象后的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public void afterDelete(Object obj) throws Exception
	{

	}


	/**
	 * 工作流操作中通过该方法更改主对象的状态
	 * 英贝思 Nov 14, 2009
	 * @param obj 主对象
	 * @param toStatus 状态
	 * @throws Exception
	 */
	public void changeStatus(Object obj, String toStatus) throws Exception
	{
		super.changeStatus(obj, toStatus);
	}

	/**
	 * 工作流发送时，调用此接口。
	 * 在发送工作流之前判断数据的完整性。
	 * 在发送流程之前调用该方法,效验工作流对应的主表数据是否有效
	 * 或者通过主对象获取子表记录集进行效验:List childs = this.getListByRelation(parentObj, relation)
	 * 如果是第一次启动工作流,那么参数wfinstance==null
	 * 如果有错误,可以弹出Messagebox消息,或者抛出异常 throw new Exception("异常消息");
	 * 返回true可以正常往下走，如果返回false，流程中断
	 * 英贝思  Nov 14, 2009
	 * @param mainObject 主对象
	 * @param wfinstance 工作流程实例
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance) throws Exception
	{
		return true;
	}


	/**
	 * 工作流发送时，弹出下一步操作选择窗口，选择完操作线后，调用此接口。
	 * 可以根据选择的下一步操作来判断数据的完整性，决定是否执行下一步工作流。
	 * 在发送流程选择操作后调用该方法,效验工作流对应的主表数据是否有效
	 * 或者通过主对象获取子表记录集进行效验:List childs = this.getListByRelation(parentObj, relation)
	 * 如果是第一次启动工作流,那么参数wfinstance==null
	 * 如果有错误,可以弹出Messagebox消息,或者抛出异常 throw new Exception("异常消息");
	 * 返回true可以正常往下走，如果返回false，流程中断
	 * 英贝思  Nov 14, 2009
	 * @param mainObject 主对象
	 * @param wfinstance 工作流程实例
	 * @param wfaction  下一步的操作
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance, Wfaction wfaction) throws Exception
	{
		return true;
	}


	/**
	 * 工作流任务用户重新分配
	 * 用户接口
	 * 洪小林  Dec 21, 2009
	 */
	public void wfReassign()
	throws Exception
	{
		//用户自定义接口方法
	}

}