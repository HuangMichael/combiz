package combiz.ui.equipment;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.corp.DepartmentSrv;
import combiz.business.equipment.DepreciationSrv;
import combiz.business.equipment.EquipdepSrv;
import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;

import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

public class DepreciationWindow extends MainWindow implements InfWindow {
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public DepreciationWindow() {
		super();
	}

	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ 
		 **********************************************************************/
		Depreciation newobj = new Depreciation();
		// ����������Ӷ���ĳ�ʼ��ֵ
		newobj.setDepnum(this.genAutokey("depnum"));
		newobj.setLabornum(this.getLaborInfo().getLabornum());
		newobj.setDepdate(new Date());
		mainObject = newobj;
		return true;
	}

	/**
	 * �����۾ɵ���ϸ ����:������ ����:Mar 31, 2009
	 * 
	 * @throws Exception
	 */
	public void creatline() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��������۾ɵ���ϸ������");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("equipdep");
		List list = listWnd.getSelectObjects();// �Ӵ������Ѿ���ӵ�
		EquipdepSrv equipdepSrv = (EquipdepSrv) IBOSrvUtil.getSrv("equipdep");
		equipdepSrv.creatList(list, this.mainObject);
		this.refreshData();
	}

	/**
	 * ���ʲ��۾� ����:������ ����:Mar 31, 2009
	 * 
	 * @throws Exception
	 */
	public void depasset() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��ж��ʲ��۾ɲ�����");
			return;
		}
		Equipdep equipdep = new Equipdep();
		Depreciation dep = (Depreciation)this.getMainObject();
		equipdep.setDepcount(dep.getDepfaction());
		this.popupDialog(equipdep, "/equipment/depasset.xul");
		this.refreshData();
	}
}
