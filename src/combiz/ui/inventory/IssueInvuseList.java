package combiz.ui.inventory;

import java.util.Date;

import com.inbasis.zul.Listitem;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.ListWindow;

public class IssueInvuseList extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IssueInvuseList() {
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * 
	 * @throws Exception
	 *             ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew() throws Exception {
		// ��ȡ�������������
		Warehouse parent = (Warehouse) ownerWnd.getMainObject();

		Invusetrans newobj = new Invusetrans();
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setEnterby(this.getLaborInfo().getLabornum());
		newobj.setActualdate(new Date());
		newobj.setIssuetype("����");
		newobj.setState("��ȷ��");
		newobj.setTransdate(new Date());
		newobj.setConversion(1D);

		this.mainObject = newobj;
		return true;
	}

	/*
	 * ���ܣ��жϺ������м�¼Ϊֻ�� ���ߣ���� ���ڣ�Oct 8, 20083:53:43 PM
	 */
	@Override
	public void initRowData(Listitem listitem, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String labornum = this.getLaborInfo().getLabornum();
		ListWindow listwnd = (ListWindow) this.getOwnerWnd().getFellow(
				"invusetrans");
		boolean onlyread = false;
		if (listwnd != null) {
			Invusetrans invusetrans = (Invusetrans) obj;
			// �ж��Ƿ�Ϊ�༭�˱���
			if (invusetrans.getEnterby().equals(labornum)) {
				if (invusetrans.getState().equals("�����")
						|| invusetrans.getState().equals("���ַ���")
						|| invusetrans.getState().equals("�����")) {
					onlyread = true;
				} else {
					onlyread = false;
				}

			} else {
				onlyread = true;
			}
			// �Ƿ���Ȩ�ޱ༭
			if (onlyread) {
				this.setReadonlyObject(listitem, true);// ������Ϊ���ɱ༭״̬
			} else {
				this.setReadonlyObject(listitem, false);// ������Ϊ�ɱ༭״̬
			}
		}

	}
}
