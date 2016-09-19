package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;
import combiz.business.inventory.MatreqSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueMrWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IssueMrWindow() {
		super();
	}

	public void save() throws Exception {
		// TODO �Զ����ɷ������
		Matreq matreq = (Matreq) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("invusetrans");
		List invl = (List) listwnd.getModifiedObjectList();
		if (invl.size() > 0) {

			for (int j = 0; j < invl.size(); j++) {
				Invusetrans invusetrans = (Invusetrans) invl.get(j);
				Long id = invusetrans.getId();
				String itemnum = invusetrans.getItemnum();
				Double quantity = invusetrans.getQuantity();
				Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.matreqnum = '"
								+ invusetrans.getMatreqnum()
								+ "' and t.itemnum = '"
								+ invusetrans.getItemnum() + "' and t.id <> "
								+ id + "");
				if (num == null)
					num = 0d;
				List wpmaterlist = this.mainSrv.getBaseDao().findWithQuery(
						Wpmaterial.class,
						"matreqnum = '" + invusetrans.getMatreqnum()
								+ "' and itemnum = '"
								+ invusetrans.getItemnum() + "'");
				if (wpmaterlist.size() > 0) {
					Wpmaterial wpmaterial = (Wpmaterial) wpmaterlist.get(0);
					Double orderqty = wpmaterial.getItemqty();
					if ((quantity <= 0 || (quantity + num) > orderqty)
							&& (invusetrans.getIssuetype().equals("����"))) {
						throw new Exception("���Ϊ��" + itemnum
								+ "�Ŀ����Ŀ��������������׼����\n���߷�������Ϊ�㣬���ʵ��");

					}
					if ((quantity >= 0)
							&& (invusetrans.getIssuetype().equals("�˻�"))) {
						throw new Exception("���Ϊ��" + itemnum
								+ "�Ŀ����Ŀ�˿�����ӦΪ��ֵ�����ʵ��");
					}
					if ((invusetrans.getIssuetype().equals("�˻�"))
							&& (quantity + num) < 0) {
						throw new Exception("���Ϊ��" + itemnum
								+ "�Ŀ����Ŀ�˿�������Ӧ���ڷ������������ʵ��");
					}
				}
			}
		}
		/*
		 * else throw new Exception("û���޸ģ�����Ҫ����");
		 */
		super.save();
	}

	// ����
	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ���Ԥ����������Ϣ��������(invusetrans)�� ���ڣ�Nov 4, 2008 11:37:04 AM
	 * 
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�����������Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		String status = matreq.getStatus();
		if (Util.isNotNull(status) && status.equals("����׼")) {
			int count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invreserve t where t.reqnum = '"
							+ matreq.getMatreqnum() + "'");
			if (count > 0) {
				CommonListWindow listWnd = (CommonListWindow) this.popupDialog(
						this.getMainObject(),
						"/inventory/mrinvreservepopup.xul", "reqnum='"
								+ matreq.getMatreqnum() + "'");
				if (listWnd == null)
					return;

				// �ж��Ƿ�����ȷ����ť������ȡ����ť
				if (!listWnd.isConfirm())
					return;

				List retList = listWnd.getSelectObjects();
				((MatreqSrv) this.getMainSrv()).geninvuse(retList, matreq);
				this.refreshData();
				Messagebox
						.show("���ݳɹ����棬ȷ�Ϸ������������У����ɷ���.�������ȫ�����ţ����ڷ��������޸ķ�������!");
			} else
				Messagebox.show("û�и������Ԥ�������Ŀ�����Ƚ���Ԥ������");
			return;

		} else
			Messagebox.show("����������û����׼�����ܽ��з��Ų���");
		return;

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ�ȷ�Ϸ��� ���ڣ�Nov 4, 2008 11:37:41 AM
	 * 
	 */
	public void verify() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ���ȷ�ϲ�����");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ڽ���ȷ�ϲ���ǰ�����ȱ����¼��");
			return;
		}

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		List retList = null;
		List selectList = null;
		Boolean toverify = null;
		if (ismultiple == true)// �û��Ƿ���ѡ��
		{
			if (Messagebox.show("�Ƿ�ȷ��ѡ�еķ����У�", "��ȷ�ϣ�", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				selectList = listWnd.getSelectObjects();
				for (int i = 0; i < selectList.size(); i++) {
					Invusetrans invu = (Invusetrans) selectList.get(i);
					if (invu.getState().equals("��ȷ��")) {
						retList.add(invu);
					}

				}
				if (retList == null)
					;
				{
					throw new Exception("û�д�ȷ�ϵĽ��ռ�¼!");
				}
			} else {
				return;
			}

		} else {
			if (Messagebox.show("�Ƿ�ȷ�ϸ���������������δȷ�ϵķ����У�", "��ȷ�ϣ�", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				retList = this.getMainSrv().getBaseDao().findWithQuery(
						Invusetrans.class,
						"matreqnum = '" + matreq.getMatreqnum()
								+ "' and state ='��ȷ��'");
			} else {
				return;
			}

		}
		if (retList.size() == 0) {
			Messagebox.show("û�д�ȷ�ϵĽ��ռ�¼!");
		} else {
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("��ɷ���!");
		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ��˿���� ���ڣ�Nov 4, 2008 11:38:04 AM
	 * 
	 */
	public void returnissue() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ����˿����������");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���������˿����ǰ�����ȱ����¼��");
			return;
		}

		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();

		if (retList.size() > 0) {

			int sum = 0;
			for (int i = 0; i < retList.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				if (invusetrans.getState().equals("�����")
						&& invusetrans.getIssuetype().equals("����")) {
					sum++;

					Double issueqty = (Double) this
							.getMainSrv()
							.getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.state = '�����' and t.issuetype = '����'");
					Double returnqty = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "'and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.issuetype = '�˻�'");
					if (issueqty == null) {
						issueqty = 0d;
					}
					if (returnqty == null) {
						returnqty = 0d;
					}
					if (issueqty + returnqty <= 0) {
						throw new Exception("���������˿��У���ѡ�еĿ����Ϊ'"
								+ invusetrans.getItemnum()
								+ "'�����У��˿��������ڷ�����������");
					}
				}
			}
			if (retList.size() - sum > 0) {
				throw new Exception("ֻ���ڷ�������ѡ���Ѿ���������ɵķ��ż�¼�����˿����");
			}

			Matreq matreq = (Matreq) this.mainObject;
			if (matreq.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.matreqnum ='"
							+ matreq.getMatreqnum()
							+ "'and issuetype = '����' and t.state = '�����'");

			if (count > 0) {
				((MatreqSrv) this.getMainSrv()).returnissue(retList, matreq);
				listWnd.mulitpleSelect();
				this.refreshData();
				Messagebox
						.show("���ݳɹ����棬ȷ���˿���������󣬵���˿�У��.�������ȫ���˿⣬���ڷ��������޸��˿�����!");
			} else
				throw new Exception("�����뵥��δ���з��ϣ������˿�");
		} else
			throw new Exception("��ѡ���˿��¼");

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ��˿�У�� ���ڣ�Nov 4, 2008 11:38:14 AM
	 * 
	 */
	public void verifyreturn() throws Exception {

		Matreq matreq = (Matreq) this.mainObject;

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"matreqnum = '" + matreq.getMatreqnum()
						+ "' and issuetype = '�˻�' and state ='��ȷ��'");
		if (retList.size() == 0) {
			Messagebox.show("û�д�ȷ�ϵ��˿��¼!");
		} else {

			for (int i = 0; i < retList.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				Double issueqty = (Double) this
						.getMainSrv()
						.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.state = '�����' and t.issuetype = '����'");
				Double returnqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "'and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.issuetype = '�˻�'");
				if (issueqty == null) {
					issueqty = 0d;
				}
				if (returnqty == null) {
					returnqty = 0d;
				}
				if (issueqty + returnqty <= 0) {
					throw new Exception("��������˿⣬��ѡ�еĿ����Ϊ'"
							+ invusetrans.getItemnum() + "'�����У��˿��������ڷ�����������");
				}
			}
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("����˿�У��!");

		}
	}

	/*
	 * @Override @������ɲ�������ֻ�� ljh
	 */
	/*
	 * public void initData() throws Exception { // TODO Auto-generated method
	 * stub Matreq matreq = (Matreq) this.mainObject;
	 * 
	 * ListWindow issuemrWnd = (ListWindow) this.getFellow("invusetrans"); if
	 * (matreq.getStatus()!=null && matreq.getStatus().equals("����׼")) {
	 * issuemrWnd.setReadonlyList(true); } super.initData(); }
	 */

}
