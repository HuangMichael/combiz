package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;

public class PremaintList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：ljh 日期：2008-03-10
	 */
	public PremaintList()
	{
		super();
	}

	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Equipment parent = (Equipment)this.ownerWnd.getMainObject();
		
		Premaint newobj = new Premaint();
		newobj.setPmcounter(0L);
		newobj.setHaschild("否");
		newobj.setAdjnextdue("否");
		newobj.setAutowf("否");
		newobj.setFrequency(0L);
		newobj.setFrequnit("天");
		newobj.setUsefrequency("是");
		newobj.setEqdown("否");
		newobj.setUsejpseq("否");
		
		newobj.setEqnum(parent.getEqnum());
		

		this.mainObject = newobj;
		return true;
	}
	
	///////////////////////////////////////////////////////////////////
}
