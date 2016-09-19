package combiz.ui.inventory;

import combiz.business.inventory.MatreqSrv;
import combiz.domain.budget.Budget;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class EqreqWindow extends MainWindow implements InfWindow {
	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqreqWindow() {
		super();
	}

	/**
	 * ������¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Matreq newobj = new Matreq();
		String matreqnum = this.genAutokey("matreqnum");
		newobj.setMatreqnum(matreqnum);
		newobj.setRequestdate(new Date());
		newobj.setStatus("����δ����");
		newobj.setStatusdate(new Date());
		String dept = this.getLaborInfo().getDeptnum();
		newobj.setReqdept(dept);
		String budnum = null;
		List list = this.getMainSrv().getBaseDao().findWithQuery(Budget.class, "buddept='"+dept+"'");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Budget budget= (Budget)list.get(0);
				budnum = budget.getBudnum();				
			}
		}
		newobj.setBudnum(budnum);
		newobj.setRequestby(this.getUserInfo().getLabornum());
		newobj.setRequireddate(new Date());
		newobj.setReqtype("�豸��������");
		newobj.setUsedept(this.getLaborInfo().getDeptnum());		
		mainObject = newobj;
		return true;
	}

	/** ****************����Ԥ����Ϣ*************ljh************** */
	public void createinvr() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("��������Ԥ��ǰ�������ݣ�");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		/**
		 * �ж��Ƿ����ظ�����Ԥ�����Ƿ���Ҫ����Ԥ�� һ������ֻ������һ��Ԥ�����ж�״̬����Ϊ��׼״̬�Ĳ�������Ԥ��
		 */
		String matreqnum = matreq.getMatreqnum();
		String status = matreq.getStatus();
		if (!status.equals("����׼")) {
			Messagebox.show("���뵥δ����׼����������Ԥ����");
			return;
		}
		List wpmateriallist = this.getMainSrv().getBaseDao().findWithQuery(
				Wpmaterial.class, "matreqnum = '" + matreqnum + "'");
		// List wptoollist =
		// this.getMainSrv().getBaseDao().findWithQuery(Wptool.class,
		// "wonum='"+matreqnum+"'");
		int wpmaterialcount = wpmateriallist.size();
		/*
		 * int wptoolcount = wptoollist.size(); if
		 * (!((wpmaterialcount+wptoolcount)>0)) { throw new Exception
		 * ("�ù���û�п����ɵ�Ԥ�������ʵ��"); }
		 */

		List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(
				Toolreserve.class, "reqnum = '" + matreqnum + "'");
		if (toolreservelist.size() > 0) {
			Messagebox.show("�����뵥�Ѿ�����Ԥ���������ظ����ɣ�");
			return;
		}

		List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(
				Invreserve.class, "reqnum = '" + matreqnum + "'");
		if (invreservelist.size() > 0) {
			Messagebox.show("�����뵥�Ѿ�����Ԥ���������ظ����ɣ�");
			return;
		}

		((MatreqSrv) this.getMainSrv()).createinvr(matreq);
		//Messagebox.show("Ԥ��������ϣ�");
		this.refreshData();
	}

	/*
	 * ���ܣ������Ӵ���ֻ�� ���ߣ���� ���ڣ�2008-11-28����10:56:15
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("wpmaterial");
		if (matreq.getStatus() != null && matreq.getStatus().equals("����׼")) {
			listwnd.setReadonlyList(true);
		} else {
			listwnd.setReadonlyList(false);
		}
		super.initData();
	}

	/**
	 * ������ �����豸ѡ�񱸼� 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		// Matreq matreq = (Matreq) this.getMainObject();
		// if (!matreq.getStatus().equals("����׼")) {
		Workorder workorder = new Workorder();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/matreqfindpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// }else{
		// Messagebox.show("��������׼�޷����������");
		// }
	}

	/**
	 * �������ʱ������BOM������Ϣ
	 * 
	 * ����:������ ����:Mar 2, 2009
	 * 
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		// Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("����׼")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,
				"/common/matreqfindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		listWnd.refreshData();// ˢ�²���
		// }else{
		// Messagebox.show("��������׼�޷���ӱ���");
		// }
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ����ʲ���������������ļ�¼����ITEM��¼�� ���ڣ�3:55:29 PM Dec 26, 2008
	 * 
	 */
	public void createitem() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("�����������������в�����");
			return;
		}
		if (this.objStatus == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ȱ������ݣ�");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		List selectlist = listWnd.getSelectObjects();
		if (selectlist.size() == 0) {
			throw new Exception("��ѡ��Ҫ�����ʲ�����ļ�¼��");
		}
		int count = ((MatreqSrv) this.getMainSrv()).createitem(selectlist);
		this.refreshData();
		Messagebox.show("�ɹ�����'" + count + "'���ʲ������¼��");
	}

	/*
	 * ���ܣ����͹�������ʱ�����mainsendΪ�գ����û�ȷ��һ�¡� ���ߣ����� ���ڣ�Jan 17, 2009 12:24:41 PM
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		String mainsend = matreq.getSupervisor();
		boolean flag = false;
		ListWindow listWnd = (ListWindow) this.getFellow("wpmaterial");
		List selectlist = listWnd.getSelectObjects();
		if (selectlist.size()>=0) {
			for (int i=0;i<selectlist.size();i++) {
				Wpmaterial wpmaterial = (Wpmaterial)selectlist.get(i);
				if (wpmaterial.getItemqty() == null || wpmaterial.getItemqty()<=0) {
					flag = true;
				}
			}
		}else{
			Messagebox.show("δ������������У����ܷ��͹����������ʵ��");
			return;
		}
		if (flag) {
			Messagebox.show("����������������������С��0�����ܷ��͹����������ʵ��");
			return;
		}
		if (matreq.getStatus().equals("�豸�����쵼����")) {
			if (Util.isNull(mainsend)) {
				Boolean tosend = null;
				if (Messagebox.show("������Ϊ�գ���ȷ�����͹�������", "��ʾ������", Messagebox.YES
						| Messagebox.NO, "") == Messagebox.YES)
					tosend = true;
				else
					tosend = false;
				if (!tosend) {
					return;
				} else {
					super.workflow();
				}
			} else {
				super.workflow();
			}

		} else {
			super.workflow();
		}

	}
}
