package combiz.business.inventory;

import combiz.domain.ibs.Ibswhauth;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class WarehouseImpl extends IBOBaseImpl
implements WarehouseSrv {
	
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		Warehouse wh = (Warehouse)obj;
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		this.deleteAllChild(wh, "invbin");
		this.deleteAllChild(wh, "invusetrans");
		this.deleteAllChild(wh, "inventoryTable");
		
		this.getBaseDao().deleteBatch(this.getBaseDao().findWithQuery(Ibswhauth.class, "warehouse='"+wh.getWarehouse()+"'"));
	}
	/*********************发放检验***********************************/
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：发放选择的行
	 * 日期：Oct 7, 2008 2:30:39 PM
	 *
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Warehouse))
			throw new Exception("对象传递不正确!");
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			
			// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			super.save(inv);
			Double issueqty = inv.getQuantity();
			Double reqqty = inv.getReqqty();
			if (reqqty == null) {
				reqqty = 0D;
			}
			if (issueqty == null) {
				issueqty = 0D;
			}
//			if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			if(inv.getBinnum() != null)
			{
				Integer length = inv.getItemnum().trim().length();
				if(inv.getItemnum().length() >0 && length == 0)
				{
					throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");
					
				}
				else
				{
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						super.save(invstock);
						inv.setState("已完成");
						super.save(inv);
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							super.save(invstock);
							super.delete(invstock);
							inv.setState("已完成");
							super.save(inv);
						}
						else
//						Messagebox.show("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
							throw new Exception("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
					}
				}
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				if(invstocklist.size() >0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal1 = invstock.getCurbal();
					if(curbal1 - issueqty > 0)
					{
						invstock.setCurbal(curbal1-issueqty);
						super.save(invstock);
						inv.setState("已完成");
						super.save(inv);
						
					}
					else
					{
						if(curbal1 - issueqty == 0)
						{
							invstock.setCurbal(0d);
							super.save(invstock);
//							super.delete(invstock);
							inv.setState("已完成");
							super.save(inv);
						}
						else
//						Messagebox.show("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
							throw new Exception("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
					}
				}
				else
					throw new Exception("发放行中库存项目编号为："+inv.getItemnum()+"发放箱柜的库存余量为零，不能发放！请输入其他箱柜");
			}
			//更新invusetrans表
			if ((reqqty-issueqty)>0) {
				inv.setState("部分发放");
			}else{
				inv.setState("已完成");
			}
			inv.setIssuetype("发放");
			this.getBaseDao().updateObject(inv);
			
			//操作完成，设置为只读
			
		}
		
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：库存项目发放后，且检验过，可以进行退库操作
	 * 日期：Oct 7, 2008 2:34:20 PM
	 *
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		String matreqnum = matreq.getMatreqnum();
		
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans invusetrans = (Invusetrans) list.get(i);
			List invuselist = this.getBaseDao().findWithQuery(Invusetrans.class, "matreqnum = '" + matreqnum + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype = '发放' and state ='已完成'");
			if(invuselist.size() > 0)
			{
		    Invusetrans newinvuse = new Invusetrans();
		    newinvuse.setItemnum(invusetrans.getItemnum());
		    newinvuse.setWarehouse(invusetrans.getWarehouse());
		    newinvuse.setMatreqnum(invusetrans.getMatreqnum());
		    newinvuse.setLocation(invusetrans.getLocation());
		    newinvuse.setIssuetype("退回");
		    newinvuse.setTransdate(new Date());
		    newinvuse.setActualdate(new Date());
		    String binnum = invusetrans.getBinnum();
		    newinvuse.setBinnum(binnum);
		    Double issueqty = 0d;
		    if(Util.isNotNull(binnum))
		    {
//		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and issuetype ='发放' and state ='已完成'");
		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + invusetrans.getMatreqnum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and state ='已完成'");
		    	if(issueqty == null)
		    		issueqty = 0d;
		    	
		    }
		    else
		    {
//		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype ='发放' and binnum is null and state ='已完成'");
		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + invusetrans.getMatreqnum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum is null and state ='已完成'");
		    	if(issueqty == null)
		    		issueqty = 0d;
		    }
		    newinvuse.setQuantity(-issueqty);
			List invstock =this.getBaseDao().findWithQuery(Invstock.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse() +"'");
			Invstock curbal = (Invstock) invstock.get(0);
			newinvuse.setCurbal(curbal.getCurbal());
			newinvuse.setPhyscnt(curbal.getPhyscnt());
			List avgcostlist =this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse()+"'");
			Inventory avgcost = (Inventory)avgcostlist.get(0);
			newinvuse.setUnitcost(avgcost.getAvgcost());
			Double linecost = (-issueqty) * avgcost.getAvgcost();
			newinvuse.setLinecost(linecost);
			newinvuse.setActualcost(avgcost.getAvgcost());
			newinvuse.setConversion(1.0);
			newinvuse.setEnterby(this.getLaborInfo().getLabornum());
			newinvuse.setState("待确认");
			super.save(newinvuse);
			}
			else
				throw new Exception("没有可以退库的发放行！");
			
		}
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：在进行退库操作后，对退库的退库行记录进行校验。
	 * 日期：Oct 7, 2008 2:34:51 PM
	 *
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;
		

		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			/*******************************************************************
			 * ******************************************************************
			 * 
			 ******************************************************************/
			
			 // 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			super.save(inv);
			Double returnqty = inv.getQuantity();
//			if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			Double hasissueqty = 0d;
			List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse()+ "'");
			Inventory inventory = (Inventory) inventorylist.get(0);
			Double avgcost = 0d;
			if(inv.getBinnum() != null)
			{
				Integer length = inv.getItemnum().trim().length();
				if(length > 0)
				{
	        	List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal = invstock.getCurbal();
//				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='发放' and state ='已完成'");	
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + inv.getMatreqnum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and state ='已完成'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal * inventory.getAvgcost())/(curbal-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("已完成");
					super.save(inv);
					
				}
				else
				{
					throw new Exception("库存项目编号为："+inv.getItemnum()+"且类型为退库的发放行中退库数量大于发放合计数量，不能退库");
				}
				}
				else
					throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");
					
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal1 = invstock.getCurbal();
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + inv.getMatreqnum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='发放' and state ='已完成'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal1 - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal1 * inventory.getAvgcost())/(curbal1-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("已完成");
					super.save(inv);
					
					
				}
				else
				{
					throw new Exception("库存项目编号为："+inv.getItemnum()+"且类型为退库的发放行中退库数量大于发放合计数量，不能退库");
						
				}
				
			}
		}
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：对所有未校样的退库的退库行记录进行撤销退库。
	 * 日期：Sep 8, 2008 9:56:26 AM
	 *
	 */
	public void verifyreturnall(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("对象传递不正确!");
		Matreq matreq = (Matreq) obj;

		if(list.size()<0) {
			throw new Exception("没有退回未确认的记录！");
		}
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			
			// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			super.save(inv);
			Double returnqty = inv.getQuantity();
			// if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			if(inv.getState().equals("待确认") && inv.getIssuetype().equals("退回")) {
				inv.setState("取消退回");
				this.getBaseDao().updateObject(inv);
			}else{
				throw new Exception("必需选择的是退回待确认的记录！");
			}
			
		}
	}
	
/////////////////////业务方法//////////////////////////////////
}
