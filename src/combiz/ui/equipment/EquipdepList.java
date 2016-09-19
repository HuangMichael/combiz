package combiz.ui.equipment;

import java.util.List;

import combiz.domain.equipment.Depreciation;
import combiz.domain.equipment.Equipdep;
import combiz.system.ui.ListWindow;

public class EquipdepList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EquipdepList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Depreciation parent = (Depreciation) this.getOwnerWnd().getMainObject();
		Equipdep newobj= new Equipdep();
		int linenum = this.mainSrv.getRowCountByWhere(newobj, "depnum = '"+parent.getDepnum()+"'");
		List newprlinelist = this.getAddedObjectList();
		linenum = linenum+newprlinelist.size();
		newobj.setLinenum((long)linenum+1);//序号 
		newobj.setDepnum(parent.getDepnum()); //必须添加关联字段值
		newobj.setNowcost(0.0);
		newobj.setDepcost(0.0);
		newobj.setPlanyears((long)0);
		newobj.setUsedyears((long)0);
		newobj.setStatus("未");
		this.mainObject = newobj;
		return true;
	}
}
