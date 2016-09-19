package combiz.ui.pm;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.domain.classattr.Classification;
import combiz.system.ui.EditWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;

public class PmClassWindow extends TMainWindow implements InfWindow {
	public PmClassWindow() {
		super();
	}

	public boolean addNew() throws Exception {
		Classification newobj = new Classification();
		newobj.setClasstype("预防性维护");
		newobj.setHaschild("否");
		Treeitem ti = mainTree.getSelectedItem();
		
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			String classid = classification.getClassid();
			newobj.setParent(classification.getClassid());
		}

		mainObject = newobj;

		return true;
	}

	@Override
	public void save() throws Exception {
	// TODO 自动生成方法存根
		ListWindow listwnd = (ListWindow) this.getFellow("pmclass");
		EditWindow editwnd = listwnd.getDialogWnd();
		if(!(editwnd == null))
		{
			ITextbox arg =  (ITextbox) editwnd.getFellow("premaint.pmnum");
			String pmnum = arg.getValue();
//		if(pmnum.trim().length()>0)
			if(Util.isNotNull(pmnum))
		{
			IBandbox arg0 =  (IBandbox) editwnd.getFellow("premaint.location");
			IBandbox arg1 = (IBandbox) editwnd.getFellow("premaint.eqnum");
			ICheckbox arg2 = (ICheckbox) editwnd.getFellow("premaint.usejpseq");
			IBandbox arg3 =  (IBandbox) editwnd.getFellow("premaint.jpnum");
			String eqnum = arg1.getValue();
			String location = arg0.getValue();
			String jpnum = arg3.getValue();
			String usejpseq = arg2.getValue();
//			if((eqnum.trim().length() + location.trim().length()) == 0 )
			if(!Util.isNotNull(eqnum) && !Util.isNotNull(location))
			{
				throw new Exception("位置和设备编号不能同时为空，请补充信息");
				
			}
			if(!Util.isNotNull(jpnum) && usejpseq.equals("是"))
			{
				throw new Exception("您选择使用标准作业计划，请完善“标准作业计划”字段！");
			}
			
			
		}
		
	}
	super.save();
}
	
	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			String classid = classification.getClassid();
			String sql = "select count(p.id) from Premaint p where p.classid = '"+classid+"'";
			
			int getpm = this.getMainSrv().getBaseDao().selectCountByHql(sql);
			if (getpm >0) {
				Messagebox.show("该分类已关联数据，不能删除该分类！");
				return;
			}
			// 调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}
}
