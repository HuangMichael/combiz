package combiz.ui.po;

import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class PoWindow extends MainWindow implements InfWindow {

	public PoWindow() {
		super();
	}

	/**
	 * 
	 * @TODO ����һ����¼
	 * @throws Exception
	 * @����� 2007-8-7 ����01:17:05
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Po po = new Po();
		// int count = this.mainSrv.getRowCountByWhere(po, "");
		po.setPonum(this.genAutokey("ponum"));
		po.setStatus("����δ����");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		po.setReceipts("δ����");
		mainObject = po;
		return true;
	}

	/*
	 * ���ܣ�����֮�����Ƿ�Ϊֻ�� ���ߣ���� ���ڣ�2008-11-7����03:47:32
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po) this.mainObject;
		ListWindow polineWnd = (ListWindow) this.getFellow("poline");
		if (po.getStatus() != null && po.getStatus().equals("����׼")) {
			polineWnd.setReadonlyList(true);
		} else {
			polineWnd.setReadonlyList(false);
		}
		super.initData();
	}

	/**
	 * 
	 * @TODO �����ɹ��������С�����һ�����壬�û�����ѡ�����еĲ����������ݡ�
	 * @throws Exception
	 * @����� 2007-8-24 ����04:20:44
	 */
	public void copypr() throws Exception {
		Po po = (Po) this.mainObject;
		if (po.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��и��Ʋɹ������в�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�����ɹ������в���ǰ�����ȱ������ݣ�");
			return;
		}
		if (po.getStatus().equals("����׼"))
			Messagebox.show("�òɹ����Ѿ���׼�����ܽ��п����ɹ������в���");
		else {

			CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
					.getMainObject(), "/pr/copyprlinepopup.xul");
			if (listWnd == null)
				return;

			// �ж��Ƿ�����ȷ����ť������ȡ����ť
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((PoSrv) this.getMainSrv()).copyprline(retList, po);
			Messagebox.show("�ɹ������ɹ�������");
			this.refreshData();
		}
	}

	/**
	 * @author ���� ���ܣ����Ʋɹ�������ѡ�еĲɹ������и��ƣ�
	 * @throws Exception
	 *             2008-1-22����01:10:31
	 */
	public void copypo() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��и��Ʋ�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ɹ������Ʋ���ǰ�����ȱ������ݣ�");
			return;
		}
		Po po = (Po) this.getMainObject();
		if (po.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Prline.class, "prnum = '" + po.getPonum() + "'");
		Po newpo = ((PoSrv) this.getMainSrv()).copypo(po);
		this.setMainObject(newpo);
		Messagebox.show("�ѳɹ����ɹ���:" + po.getPonum() + "���Ƶ��ɹ���"
				+ newpo.getPonum());
		this.refreshData();
	}

	/**
	 * ������ �����豸ѡ�񱸼� 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		// Po po = (Po) this.getMainObject();
		// if (!po.getStatus().equals("����׼")) {
		Workorder workorder = new Workorder();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
				"/common/pofindpartbyequip.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		this.refreshData();
		// } else {
		// Messagebox.show("��������׼�޷���Ӳɹ���");
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
				"/common/pofindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("poline");
		listWnd.refreshData();// ˢ�²���
		// }else{
		// Messagebox.show("��������׼�޷���ӱ���");
		// }
	}

	/* workflow
	 * ���͹�������ʱ��У��ֿ⣬������������Ϣ
	 * ���
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po)this.getMainObject();
		String ponum = po.getPonum();
		boolean flasg = false;
		List polinelist = this.getMainSrv().getBaseDao().findWithQuery(Poline.class, "ponum = '"+ponum+"'");
		if (polinelist.size()>0) {
			for (int i=0;i<polinelist.size();i++) {
				Poline poline = (Poline) polinelist.get(i);
				String warehouse = poline.getWarehouse();
				String itemnum = poline.getItemnum();
				Double qty = poline.getOrderqty();
				Double unitcost = poline.getUnitcost();
				if (warehouse == null || itemnum == null || qty == null || unitcost==null || qty<=0 || unitcost<=0){
					flasg = true;	
				}
			}
			if (flasg) {
				Messagebox.show("�ɹ������ж�����������˰���ۣ��ֿ���Ϣ�����ƣ�\n���ܷ��͹����������ʵ��");
				return;
			}
		}else{
			Messagebox.show("�òɹ�����δ�κβɹ����У����ܷ��͹����������ʵ��");
			return;
		}
		super.workflow();
	}

}
