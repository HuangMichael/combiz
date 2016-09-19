package combiz.ui.equipment;

import combiz.domain.classattr.Classification;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.ListWindow;

import java.util.Date;

public class EqClassList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqClassList()
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
		EqClassWindow parentWnd = (EqClassWindow)ownerWnd;
		Classification parent = (Classification) parentWnd.getMainObject();
		
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
		newobj.setClassid(parent.getClassid());
		//newobj.setIsmustbe("��");

		this.mainObject = newobj;
		return true;
	}
}
