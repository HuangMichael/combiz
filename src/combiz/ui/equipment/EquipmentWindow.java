package combiz.ui.equipment;

import combiz.business.equipment.EquipmentSrv;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class EquipmentWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipmentWindow() {
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
	
	
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		
		int a = this.ADDED;
		Equipment equipment = (Equipment) this.getMainObject();
		String args[]={"equipment.parent"};
		String argsnull[]={};
		if(this.getObjStatus() == a)
		{
			this.setReadonly(argsnull);
		}
		else
		{
			this.setReadonlyFields(args);
		}
		super.initData();
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����������豸
	 * ���ڣ�10:07:46 AM  Jun 10, 2010 
	 *
	 */
	public void equipchg()
	throws Exception
	{
		Equipment equipment = (Equipment) this.getMainObject();
		CommonDialog cp = (CommonDialog) this.popupDialog(equipment, "/equipment/equipchg.xul"); //
		if(cp.isConfirm())
		{
			this.clear();
			Messagebox.show("�����豸�����Ѿ���ɣ�");
		}
	}
	

	/**
	 * @throws Exception
	 * @author:��Ⱥ��
	 * @description:�豸�ƶ� @ 2007-8-7 ����11:41:49
	 */
	public void eqmove() throws Exception {
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

	/**
	 * @author ���� ���ܣ���Ӧ����Ӧ�ó����²˵��еĽ��ռ��飻����ʱ��ͨ�����ռ����޸Ľ�������Ϣ��
	 * @throws Exception
	 *             2008-1-22����01:11:24
	 */
	public void fixed() throws Exception {
	
		Equipment equipment = (Equipment) this.mainObject;
		
		List list = this.getSelectObjects();
	
		if (equipment.getId()==null ) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		if (equipment.getAssetnum()!=null ) {
			Messagebox.show("�����������ɣ�������ѡ��һ����¼��");
			return;
		}
	
	
		else {
			((EquipmentSrv) this.getMainSrv()).copyfixed(list);
			this.clear();
			Messagebox.show("�Ѿ����ɹ̶��ʲ�!");
		}
			
	
		}
	}

