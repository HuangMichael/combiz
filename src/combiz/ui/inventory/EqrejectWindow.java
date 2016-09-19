package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.RejectSrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

public class EqrejectWindow extends MainWindow implements InfWindow {
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqrejectWindow() {
		super();
	}

	/**
	 * ��Ӽ�¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Reject newobj = new Reject();
		// ����������Ӷ���ĳ�ʼ��ֵ
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String rejectnum = this.genAutokey("rejectnum");
		newobj.setRejectnum(rejectnum);
		newobj.setReqdept(labor.getDeptnum());
		newobj.setRejectdate(new Date());
		newobj.setRequestby(labornum);
		newobj.setStatusdate(new Date());
		newobj.setRejecttype("�ʲ�����");
		newobj.setStatus("�ݸ�");
		mainObject = newobj;
		return true;
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ���̨����ѡ�������������ʲ���Ϣ�� ���ڣ�1:53:43 PM Feb 13, 2009
	 * 
	 */
	public void listeq() throws Exception {
		Reject reject = (Reject) this.mainObject;
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("����ѡ��������ʲ���ϸ��ǰ�������ݣ�");
			return;
		}
		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (reject.getStatus().equals("�ݸ�")
				|| reject.getStatus().equals("������ͨ��")) {
			this.popupDialog(this.getMainObject(),
					"/inventory/eqrejectpopup.xul");
		} else {
			throw new Exception("��״̬Ϊ:'" + reject.getStatus() + "'�£�������ӱ��������У�");
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ��쵼����ʱ��ѡ��ĳЩ�ʲ���ͨ���������ϡ� ���ڣ�4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void noapprove() throws Exception {
		Reject reject = (Reject) this.mainObject;
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("����ѡ�񱨷�������ͨ��ǰ�������ݣ�");
			return;
		}

		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (reject.getStatus().equals("�����쵼����")
				|| reject.getStatus().equals("�豸�����쵼����")
				|| reject.getStatus().equals("�����쵼����")) {
			ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
			List noappeqlist = listWnd.getSelectObjects();
			if (noappeqlist.size() > 0) {
				int rtn = Messagebox.show("ȷ��ѡ�е��ʲ���ͨ������������", "����������ͨ����",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
				if (rtn == Messagebox.NO)
					return;
				else if (rtn == Messagebox.YES) {
					((RejectSrv) this.getMainSrv()).noapproveeq(noappeqlist);
					this.refreshData();
					Messagebox.show("�Ѿ���ѡ�е��ʲ���¼����Ϊ����������ͨ��!");
				}
			} else {
				throw new Exception("���ڱ���������ϸ���У�ѡ�񱾴β����ϵ��ʲ���¼��");
			}
		} else {
			throw new Exception("��״̬Ϊ:'" + reject.getStatus()
					+ "'�£�����ִ�иò��������ʵ��");
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ��쵼����ʱ�򣬶�����Ϊ���β����ϵ��豸���±��ϡ� ���ڣ�4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void noyesapprove() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("����ѡ���޸�Ϊ������ǰ�������ݣ�");
			return;
		}

		Reject reject = (Reject) this.mainObject;
		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (reject.getStatus().equals("�����쵼����")
				|| reject.getStatus().equals("�豸�����쵼����")
				|| reject.getStatus().equals("�����쵼����")) {
			ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
			List noappeqlist = listWnd.getSelectObjects();
			if (noappeqlist.size() > 0) {
				int rtn = Messagebox.show("ȷ��ѡ�е��ʲ��޸�Ϊ����״̬��", "�޸�Ϊ���ϣ�",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
				if (rtn == Messagebox.NO)
					return;
				else if (rtn == Messagebox.YES) {
					((RejectSrv) this.getMainSrv()).noyesapproveeq(noappeqlist);
					this.refreshData();
					Messagebox.show("�Ѿ���ѡ�е��ʲ���¼���Ƿ񱨷��޸�Ϊ���ǡ�!");
				}
			} else {
				throw new Exception("���ڱ���������ϸ���У�ѡ�񱾴��޸�Ϊ���ϵļ�¼��");
			}

		} else {
			throw new Exception("��״̬Ϊ:'" + reject.getStatus()
					+ "'�£�����ִ�иò��������ʵ��");
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ��쵼����ʱ����׼�ֶα�ʶ���Ƿ񱨷ϣ�Ϊ'��'�������ʲ��� ���ڣ�4:43:32 PM Feb 14, 2009
	 * 
	 */
	public void approverej() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("����ѡ��ͬ�ⱨ��ǰ�������ݣ�");
			return;
		}

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (reject.getStatus().equals("�ݸ�")
				|| reject.getStatus().equals("�������")) {
			throw new Exception("���ϵ���״̬Ϊ'�ݸ�'��'�������'ʱ�����ܱ��ϣ�");
		}
		// ListWindow listWnd = (ListWindow) this.getFellow("rejectitem");
		List appeqlist = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum='" + reject.getRejectnum() + "' and isreject ='��'");
		if (appeqlist.size() > 0) {
			int rtn = Messagebox.show("ȷ��������Ҫ���ϵ��ʲ����б��ϣ�", "ͬ�ⱨ�ϣ�",
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
			if (rtn == Messagebox.NO)
				return;
			else if (rtn == Messagebox.YES) {
				((RejectSrv) this.getMainSrv()).approverej(appeqlist, reject);
				this.refreshData();
				Messagebox.show("�Ѿ�������Ҫ���ϵ��ʲ����б���!");
			}
		} else {
			throw new Exception("����������ϸ���У�û��Ҫ���ϵ��ʲ���¼����ȷ�ϣ�");
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ����б�����׼�����������еı�����ϸת�Ƶ����Ͽ�� ���ڣ�Oct 21, 2008 11:52:54 AM
	 * 
	 */
	public void verify() throws Exception {

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum = '" + reject.getRejectnum() + "' ");
		if (retList.size() == 0) {
			Messagebox.show("û��Ҫ��׼�ı��ϼ�¼!");
		} else {
			((RejectSrv) this.getMainSrv()).verify(retList);
			this.refreshData();
			Messagebox.show("�Ѿ���ɱ���!");

		}
	}

}
