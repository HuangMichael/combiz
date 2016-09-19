package combiz.ui.equipment;

import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainTreeListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class EquipmentTreeWindow extends MainTreeListWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipmentTreeWindow() {
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
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		Equipment parentEq = (Equipment) this.getSelectObject();
		if(parentEq!=null)
		{
			newobj.setParent(parentEq.getEqnum());
			newobj.setLocation(parentEq.getLocation());
		}
		newobj.setInvcost(0.0D);
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		newobj.setIsrunning("��");

		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());

		mainObject = newobj;
		return true;
	}

	/**
	 * @throws Exception
	 * @author:��Ⱥ��
	 * @description:�豸�ƶ� @ 2007-8-7 ����11:41:49
	 */
	public void eqmove() throws Exception 
	{
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ����豸�ƶ�������");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�豸�ƶ�����ǰ�����ȱ������ݣ�");
			return;
		}

		Equipment mainObject = (Equipment) this.getMainObject();

		Eqtrans eqtrans = new Eqtrans();
		eqtrans.setEqnum(mainObject.getEqnum());
		eqtrans.setFromloc(mainObject.getLocation());

		this.popupDialog((Object) eqtrans, "/equipment/eqmove.xul");

		// ����ˢ������
		this.refreshData();
	}

	
	/**
	 * �豸ͣ��
	 * ����:������
	 * ����:Apr 2, 2009
	 * @throws Exception
	 */
	public void eqdowntime() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼!");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.objStatus == this.ADDED) {
			Messagebox.show("���ȱ�������");
			return;
		}
		Eqdowntime eqdowntime = new Eqdowntime();
		this.popupDialog(eqdowntime, "/equipment/eqdowntimelist.xul");
	}
}
