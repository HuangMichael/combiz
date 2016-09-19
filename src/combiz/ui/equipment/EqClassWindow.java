package combiz.ui.equipment;
 

import java.util.List;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.equipment.EquipmentSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class EqClassWindow extends TMainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqClassWindow()
	{
		super();
	}
	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Classification newobj = new Classification();
		newobj.setClasstype("资产");
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		newobj.setHaschild("否");
		mainObject = newobj;
		
		return true;
	}
	

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null)
		{
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) 
		{
			if ("物资".equals(classification.getClasstype())) {
				if ("物资分类".equals(classification.getClassid())){
					Messagebox.show("顶级分类，不能删除！");
					return;
				}
			}
			if ("资产".equals(classification.getClasstype())) {
				if ("资产分类".equals(classification.getClassid())){
					Messagebox.show("顶级分类，不能删除！");
					return;
				}
			}
			if ("工具".equals(classification.getClasstype())) {
				if ("工具".equals(classification.getClassid())){
					Messagebox.show("顶级分类，不能删除！");
					return;
				}
			}
			//调用业务对象的删除方法
			super.delete();
			// 重新构建树
			mainTree.afterDeleteItem();
		}
	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：classance中插入关联关系
	 * 日期：Sep 1, 2008 3:36:07 PM
	 *
	 */
	public void cretetree() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("生成父类操作前，请先保存当前的记录！");
			return;
		}
		int rtn = Messagebox.show("是否确定重新创建数据吗？","确认创建！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			EquipmentSrv equipmentsrv = (EquipmentSrv)IBOSrvUtil.getSrv("equipment");
			List classlist = this.getMainSrv().getBaseDao().findWithQuery(Classification.class, "classtype = '物资' and  parent is null");
			
			if (classlist.size()>0) {
				List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, " classid='"+((Classification)classlist.get(0)).getClassid()+"'");
				if (listance.size()>0) {
					Messagebox.show("数据已经存在，不能再次创建!");
					return;
				}
			}
			//List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, "");
			//this.getMainSrv().getBaseDao().executeSql("delete Classance where ");
			
			for (int i=0;i<classlist.size();i++) {
				Classification classification = (Classification) classlist.get(i);
				equipmentsrv.cretetree(classlist);
				this.refreshData();
				Messagebox.show("数据创建完毕!");
			}
		setMainData();

		}
	}
}
