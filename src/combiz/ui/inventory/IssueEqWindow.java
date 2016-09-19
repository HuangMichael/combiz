package combiz.ui.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

import combiz.business.inventory.MatreqSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueEqWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IssueEqWindow() {
		super();
	}

	/**
	 * 
	 * brianhong 2007-11-13
	 */
	public void selectMRLine() {

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
						// throw new Exception("��������Ӧ��Ϊ0���Ҳ����ڶ������������ʵ��");
						// Messagebox.show("���Ϊ��" + itemnum +
						// "�Ŀ����Ŀ��������������׼����\n���߷�������Ϊ�㣬���ʵ��");
						// return;
						throw new Exception("���Ϊ��" + itemnum
								+ "�Ŀ����Ŀ��������������׼����\n���߷�������Ϊ�㣬���ʵ��");

					}
					if ((quantity >= 0)
							&& (invusetrans.getIssuetype().equals("�˻�"))) {
						// Messagebox.show("���Ϊ��" + itemnum +
						// "�Ŀ����Ŀ�˿�����ӦΪ��ֵ�����ʵ��");
						// return;
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

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ����ɷ����� ���ڣ�Nov 4, 2008 10:38:45 AM
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
				Window listWnd = (Window) this.popupDialog(
						this.getMainObject(),
						"/inventory/eqinvreservepopup.xul", "reqnum='"
								+ matreq.getMatreqnum() + "'");
			} else {
				Messagebox.show("��������'" + matreq.getMatreqnum()
						+ "'û�д����ŵ��豸����ȷ�ϣ�");
				return;
			}
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ����ʺ���ת����ȷ�Ϸ��� ���ڣ�Nov 4, 2008 10:38:59 AM
	 * 
	 */
	public void verify() throws Exception {

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		ArrayList retList = new ArrayList();
		ArrayList selectList = new ArrayList();
		Boolean toverify = null;
		if (ismultiple == true)// �û��Ƿ���ѡ��
		{
			if (Messagebox.show("�Ƿ�У��ѡ�еķ����У�", "��ȷ�ϣ�", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				selectList = (ArrayList) listWnd.getSelectObjects();
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
				retList = (ArrayList) this.getMainSrv().getBaseDao()
						.findWithQuery(
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

	// �˿�
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
											+ "' and t.eqnum = '"
											+ invusetrans.getEqnum()
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.state = '�����' and t.issuetype = '����'");
					Double returnqty = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "' and t.eqnum = '"
											+ invusetrans.getEqnum()
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.issuetype = '�˻�'");
					if (issueqty == null) {
						issueqty = 0d;
					}
					if (returnqty == null) {
						returnqty = 0d;
					}
					if (issueqty + returnqty <= 0) {
						Messagebox.show("���������˿��У���ѡ�е��ʲ����Ϊ'"
								+ invusetrans.getEqnum() + "'�����У��˿��������ڷ�����������");
						break;
					}
				}
			}
			if (retList.size() - sum != 0) {
				throw new Exception("ֻ���ڷ�������ѡ���Ѿ�������ȷ�Ϲ��ļ�¼�����˿����");
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
				this.refreshData();
				Messagebox.show("���ݳɹ����棬ȷ���˿���������󣬵���˿�ȷ��!");
			} else
				throw new Exception("������������δ���з��ϣ������˿�");
		} else
			throw new Exception("��ѡ���˿��¼");

	}

	// �˿�У��
	/**
	 * ������verifyreturn ���ߣ���� ���ܣ� ���ڣ�Apr 16, 2009 6:26:36 PM
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
										+ "' and t.eqnum = '"
										+ invusetrans.getEqnum()
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.state = '�����' and t.issuetype = '����'");
				Double returnqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "' and t.eqnum = '"
										+ invusetrans.getEqnum()
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.issuetype = '�˻�'");
				if (issueqty == null) {
					issueqty = 0d;
				}
				if (returnqty == null) {
					returnqty = 0d;
				}
				if (issueqty + returnqty < 0) {
					Messagebox.show("��������˿⣬�ʲ����Ϊ'"
							+ invusetrans.getEqnum() + "'�˿��У��˿��������ڷ�����������");
					break;
				}
			}

			((MatreqSrv) this.getMainSrv()).verifyreturn(retList, matreq);
			this.refreshData();
			Messagebox.show("����˿�ȷ��!");

		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ���ӡ�ʲ���Ƭ ���ڣ�11:49:14 AM Mar 11, 2009
	 * 
	 */
	public void printEuipcard() throws Exception {

		String isrotating = null;
		Matreq mat = (Matreq) this.getMainObject();
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		boolean toprint = false;
		if (!ismultiple) {
			if (Messagebox.show("�Ƿ��ӡ�÷��ŵ������е��豸��ǩ��", "��ʾ������", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toprint = true;
			else
				toprint = false;
		} else {
			if (Messagebox.show("�Ƿ��ӡѡ�еķ��ż�¼�е��豸��ǩ��", "��ʾ������", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toprint = true;
			else
				toprint = false;

		}

		if (toprint) {
			List invuselist = listWnd.getSelectObjects();
			if (invuselist.size() == 0) {
				throw new Exception("��ѡ��һ�����ż�¼�ٴ�ӡ��");
			} else {
				Map param = new HashMap();
				for (int j = 0; j < invuselist.size(); j++) {
					Invusetrans invu = (Invusetrans) invuselist.get(j);
					String itemnum = invu.getItemnum();
					Item item = (Item) this.getMainSrv().getBaseDao()
							.findUniqueBy(Item.class, "itemnum", itemnum);
					isrotating = item.getRotating();

					param.put("printType", "equip");
					List objList = new ArrayList();
					objList.add(invu);
					param.put("objList", objList);
				}

				if (Util.getBoolean(isrotating))// �ж��Ƿ���ת����
				{

					Window equipcardWnd = (Window) Executions.createComponents(
							"/common/printeqcard.xul", null, param);
					equipcardWnd.doModal();

				}
			}
		} else {
			return;
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
