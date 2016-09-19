package combiz.ui.equipment;

import java.util.List;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.system.ui.ListWindow;

public class EquipdepList extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipdepList()
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
		Depreciation parent = (Depreciation) this.getOwnerWnd().getMainObject();
		Equipdep newobj= new Equipdep();
		int linenum = this.mainSrv.getRowCountByWhere(newobj, "depnum = '"+parent.getDepnum()+"'");
		List newprlinelist = this.getAddedObjectList();
		linenum = linenum+newprlinelist.size();
		newobj.setLinenum((long)linenum+1);//��� 
		newobj.setDepnum(parent.getDepnum()); //������ӹ����ֶ�ֵ
		newobj.setNowcost(0.0);
		newobj.setDepcost(0.0);
		newobj.setPlanyears((long)0);
		newobj.setUsedyears((long)0);
		newobj.setStatus("δ");
		this.mainObject = newobj;
		return true;
	}
}
