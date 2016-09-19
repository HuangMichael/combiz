package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zk.ui.Component;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Warehouse;
import combiz.system.FieldAdapter;

public class FldItemnum extends FieldAdapter {

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component arg0) throws Exception {
		// ���������
		Invusetrans invusetrans = (Invusetrans) this.mainObject;
		if (invusetrans != null) {
			String warehouse = invusetrans.getWarehouse();
			String itemnum = invusetrans.getItemnum();
			//�õ������������	
			Double curbal = (Double) this.getMainSrv().getBaseDao()
					.selectSumByHql(
							"select sum(t.curbal) from Invstock t where t.warehouse='"
									+ warehouse + "' and t.itemnum='" + itemnum
									+ "'");
			//�õ�������ʵ�ʵ���̵�������
			Double phycnt = (Double) this.getMainSrv().getBaseDao()
					.selectSumByHql(
							"select sum(t.physcnt) from Invstock t where t.warehouse='"
									+ warehouse + "' and t.itemnum='" + itemnum
									+ "'");
			//�õ����������Ϣ
			Double quanty = invusetrans.getQuantity();
			Double avgcost = 0D;
			List invlist = this.getMainSrv().getBaseDao().findWithQuery(Inventory.class, "warehouse='"+warehouse+"' and itemnum='"+itemnum+"'");
			if (invlist.size()>0) {
				Inventory inventory =(Inventory) invlist.get(0);
				avgcost = inventory.getAvgcost();
			}
			
			if (curbal == null) {
				curbal = 0D;
			}
			if (quanty == null) {
				quanty = 0D;
			}
			if (phycnt == null) {
				phycnt = 0D;
			}
			if (avgcost == null) {
				avgcost = 0D;
			}
			Double linecost = avgcost*quanty;
			if (linecost == null) {
				linecost = 0D;
			}
			
			//�жϣ�������Ӧ�ֵ
			this.setValueByColname("curbal", curbal);
			this.setValueByColname("physcnt", phycnt);
			this.setValueByColname("unitcost", avgcost);
			if (invusetrans.getQuantity() != null) {
				this.setValueByColname("linecost", linecost);
			}else{
				this.setValueByColname("linecost", 0D);
			}
			this.setValueByColname("actualcost", avgcost);
		}

	}
}
