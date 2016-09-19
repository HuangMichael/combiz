package combiz.ui.equipment;


import combiz.business.equipment.EquipmentSrv;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;
import java.util.Date;

public class AppertainList extends ListWindow  //�����豸����
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AppertainList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	*/ 
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Equipment parent = (Equipment) this.getOwnerWnd().getMainObject();
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		newobj.setLocation(parent.getLocation());
		newobj.setParent(parent.getEqnum());
		newobj.setChangedate(new Date());
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setInvcost(0.0D);
		newobj.setIsrunning("��");
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		this.mainObject = newobj;
		return true;
	}
	
}
