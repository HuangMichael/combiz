package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldInvuseqty extends FieldAdapter {

	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。 如果
	 */
	public void init(Component component) {

	}

	/*
	 * 功能： 作者：李建红 日期：Sep 25, 20086:23:51 PM
	 */
	public void action(Component com) throws Exception {
		// 得到主窗体类
		Double quantity = (Double) this.getValueByColname("quantity");
		String issuetype = (String) this.getValueByColname("issuetype");
		if (quantity <= 0 && issuetype.equals("发放") ) {
			this.setValueByColname("quantity", 0D);
			throw new Exception ("交易数量不能小于0 ！");
		}
		
		if(quantity > 0 && issuetype.equals("退回"))
		{
			this.setValueByColname("quantity", -quantity);
		}
			
		Invusetrans inv = (Invusetrans) this.mainObject;
		String itemnum = inv.getItemnum();
		String warehouse = inv.getWarehouse();
		Long id = inv.getId();
		String binnum = (String) this.getValueByColname("binnum");	
		String lotnum = (String) this.getValueByColname("lotnum");
		String lottype = null;
		double avgcost = 0d;
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		if(itemlist.size()>0)
		{
			Item item = (Item) itemlist.get(0);
			lottype = item.getLottype();
		}
		if(lottype.equals("批次管理") && Util.isNotNull(inv.getLotnum()))
		{
			List invlotlist = this.getMainSrv().getBaseDao().findWithQuery(Invlot.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and lotnum = '"+inv.getLotnum()+"'");
			if(invlotlist.size() >0)
			{
				Invlot invlot = (Invlot) invlotlist.get(0);
				avgcost = invlot.getLotcost();
				double linecost = quantity * avgcost;
				this.setValueByColname("linecost", linecost);
				this.setValueByColname("unitcost", avgcost);
			}
		}
		else
		{
			List avgcostlist = this.getMainSrv().getBaseDao().findWithQuery(
					Inventory.class,
					"itemnum = '" + itemnum + "' and warehouse = '" + warehouse
							+ "'");
			if (avgcostlist.size() > 0) {
				Inventory inventory = (Inventory) avgcostlist.get(0);
				avgcost = inventory.getAvgcost();
				// 从invusetrans表中读出除了该发放行外，物料发放行中的物料发放总数；
				Double issueqty = null;// 已经发放且检验数量
				Double num = null;// 除了本行发放记录，总发放数量
				Double reqty = null;// 申请领用物料数量
				Double curbal = null;// 库存总余量
				curbal = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(i.curbal) from Invtrans i where i.warehouse='"
								+ warehouse + "' and i.itemnum='" + itemnum + "'");
				if (curbal == null) {
					curbal = 0D;
				}
				if (Util.isNotNull(inv.getWonum())) {
					reqty = (Double) this.mainSrv.getBaseDao().selectSumByHql(
							"select sum(t.itemqty) from Wpmaterial t where t.wonum = '"
									+ inv.getWonum() + "' and t.itemnum = '"
									+ inv.getItemnum() + "'");
					issueqty = (Double) this.mainSrv
							.getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where wonum = '"
											+ inv.getWonum()
											+ "'and t.issuetype = '发放' and t.state = '已完成'");
					num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
							"select sum(t.quantity) from Invusetrans t where t.wonum = '"
									+ inv.getWonum() + "' and t.itemnum = '"
									+ inv.getItemnum() + "' and id <> " + id + "");
					
					// 从wpmaterila中读出该发放行对应的工单物料申请行中的物料申请数量
					if (num == null)
						num = 0d;
					if (reqty == null)
						reqty = 0d;
					if (issueqty == null)
						issueqty = 0d;
					if (inv.getIssuetype().equals("发放")) {
						if ((quantity + num - reqty > 0 || quantity <= 0)) {
							IDoublebox target = (IDoublebox) com
									.getFellow("invusetrans.quantity");
							// Messagebox.show("发放收数量应不为0，且不大于物料申请数量，请核实！");
							// this.setValueByComponent(target, "0.0");
							this.setValueByColname("quantity", 0.0);
							throw new Exception("发放收数量应不为0，且不大于物料申请数量，请核实！");

						} else {
							double linecost = quantity * avgcost;
							// Component target = com.getFellow("invrectrans.linecost");
							// Component avgtarget =
							// com.getFellow("invusetrans.unitcost");
							// this.setValueByComponent(target, linecost);
							this.setValueByColname("linecost", linecost);
							// this.setValueByComponent(avgtarget, avgcost);
							this.setValueByColname("unitcost", avgcost);
						}

					} 
				} else {
					//得到库存余量总数
					Double curbaltwo = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.curbal) from Invstock t where t.warehouse='"
											+ warehouse + "' and t.itemnum='" + itemnum
											+ "'");
					//现在申请的余量数
					Double quanty = inv.getQuantity();
					if (curbaltwo == null) {
						curbaltwo = 0D;
					}
					if (quanty == null) {
						quanty = 0D;
					}
					if ((curbaltwo-quanty)<0) {
						this.setValueByColname("quantity", 0D);
						this.setValueByColname("linecost", 0D);
						throw new Exception("库存余量不足，需采购！");
					}
					if (inv.getUnitcost() != null) {
						Double linecost = inv.getUnitcost()*quanty;
						this.setValueByColname("linecost", linecost);
					}else
						this.setValueByColname("linecost", 0D);
				}
				
			} else{
				this.setValueByColname("quantity", 0D);
				throw new Exception("没有库存的成本信息！");
			}
			
		}
		
		
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if (reqqty != null) {
			if ((reqqty-quantity)<0) {
				throw new Exception("交易数量不能大于申请数量，请核实!");
			}
		}
	}


	/*
	 * 功能： 作者：李建红 日期：Sep 25, 20086:22:54 PM
	 */
	public void validate(Component component) {

	}

}
