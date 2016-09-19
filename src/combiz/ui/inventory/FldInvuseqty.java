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
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ� ���
	 */
	public void init(Component component) {

	}

	/*
	 * ���ܣ� ���ߣ���� ���ڣ�Sep 25, 20086:23:51 PM
	 */
	public void action(Component com) throws Exception {
		// �õ���������
		Double quantity = (Double) this.getValueByColname("quantity");
		String issuetype = (String) this.getValueByColname("issuetype");
		if (quantity <= 0 && issuetype.equals("����") ) {
			this.setValueByColname("quantity", 0D);
			throw new Exception ("������������С��0 ��");
		}
		
		if(quantity > 0 && issuetype.equals("�˻�"))
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
		if(lottype.equals("���ι���") && Util.isNotNull(inv.getLotnum()))
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
				// ��invusetrans���ж������˸÷������⣬���Ϸ������е����Ϸ���������
				Double issueqty = null;// �Ѿ������Ҽ�������
				Double num = null;// ���˱��з��ż�¼���ܷ�������
				Double reqty = null;// ����������������
				Double curbal = null;// ���������
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
											+ "'and t.issuetype = '����' and t.state = '�����'");
					num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
							"select sum(t.quantity) from Invusetrans t where t.wonum = '"
									+ inv.getWonum() + "' and t.itemnum = '"
									+ inv.getItemnum() + "' and id <> " + id + "");
					
					// ��wpmaterila�ж����÷����ж�Ӧ�Ĺ��������������е�������������
					if (num == null)
						num = 0d;
					if (reqty == null)
						reqty = 0d;
					if (issueqty == null)
						issueqty = 0d;
					if (inv.getIssuetype().equals("����")) {
						if ((quantity + num - reqty > 0 || quantity <= 0)) {
							IDoublebox target = (IDoublebox) com
									.getFellow("invusetrans.quantity");
							// Messagebox.show("����������Ӧ��Ϊ0���Ҳ����������������������ʵ��");
							// this.setValueByComponent(target, "0.0");
							this.setValueByColname("quantity", 0.0);
							throw new Exception("����������Ӧ��Ϊ0���Ҳ����������������������ʵ��");

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
					//�õ������������
					Double curbaltwo = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.curbal) from Invstock t where t.warehouse='"
											+ warehouse + "' and t.itemnum='" + itemnum
											+ "'");
					//���������������
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
						throw new Exception("����������㣬��ɹ���");
					}
					if (inv.getUnitcost() != null) {
						Double linecost = inv.getUnitcost()*quanty;
						this.setValueByColname("linecost", linecost);
					}else
						this.setValueByColname("linecost", 0D);
				}
				
			} else{
				this.setValueByColname("quantity", 0D);
				throw new Exception("û�п��ĳɱ���Ϣ��");
			}
			
		}
		
		
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if (reqqty != null) {
			if ((reqqty-quantity)<0) {
				throw new Exception("�����������ܴ����������������ʵ!");
			}
		}
	}


	/*
	 * ���ܣ� ���ߣ���� ���ڣ�Sep 25, 20086:22:54 PM
	 */
	public void validate(Component component) {

	}

}
