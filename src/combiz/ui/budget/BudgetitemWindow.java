package combiz.ui.budget;

import java.util.HashMap;
import java.util.List;

import combiz.business.budget.BudgetitemSrv;
import combiz.domain.budget.Budgetitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class BudgetitemWindow extends TMainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public BudgetitemWindow()
	{
		super();
	}

	
	/**
	 * 添加记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Budgetitem newobj = new Budgetitem();
		String version="";
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) this.getFellow("budgetItemVersion");
		if(budgetItemVersion.getSelectedItem()!=null)
			version = (String) budgetItemVersion.getSelectedItem().getValue();
		if(Util.isNull(version))
		{
			Messagebox.show("请选择一个预算版本！");
			return false;
		}
		//请在下面添加对象的初始化值
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Budgetitem parent = (Budgetitem) ti.getValue();
			newobj.setParent(parent.getBuditem());
			
			Long maxorder = 1L;
			List sameList = this.getMainSrv().findWithQuery("parent='"+parent.getBuditem()+"' and version='"+parent.getVersion()+"'", "buditem desc");
			if(sameList.size()>0)
			{
				Budgetitem same = (Budgetitem) sameList.get(0);
				String bitem = same.getBuditem();
				String bitemend = bitem.substring(bitem.lastIndexOf(".") + 1);
				if(bitemend!=null)
					maxorder = Long.parseLong(bitemend) + 1;
			}
			newobj.setVersion(parent.getVersion());
			newobj.setBuditem(parent.getBuditem() + "." + maxorder);
			newobj.setOrderby(maxorder);
			newobj.setMeaunit(parent.getMeaunit());
			newobj.setBudtype(parent.getBudtype());
			newobj.setBudclass(parent.getBudclass());
			newobj.setChildclass(parent.getChildclass());
			newobj.setBudperiod(parent.getBudperiod());
		}
		else
		{
			Long maxorder = 1L;
			List sameList = this.getMainSrv().findWithQuery("parent is null and version='"+version+"'", "buditem desc");
			if(sameList.size()>0)
			{
				Budgetitem same = (Budgetitem) sameList.get(0);
				String bitem = same.getBuditem();
				String bitemend = bitem.substring(bitem.lastIndexOf(".") + 1);
				if(bitemend!=null)
					maxorder = Long.parseLong(bitemend) + 1;
			}

			newobj.setVersion(version);
			newobj.setBuditem(String.valueOf(maxorder));
			newobj.setOrderby(maxorder);
			newobj.setMeaunit("元");
			newobj.setBudtype("公司核算");
			newobj.setBudperiod("年度");
			newobj.setChildclass("支出");
		}

		
		newobj.setHaschild("否");
		newobj.setEnabled("是");
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * 
	 * brianhong  2008-11-24
	 * @throws Exception 
	 */
	public void deleteall() throws Exception
	{
		int rtn = Messagebox.show("确定删除该版本的所有记录吗？","删除确认！",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
		if(rtn==Messagebox.YES)
		{
			Budgetitem budgetitem = (Budgetitem) this.getMainObject();
			((BudgetitemSrv)this.getMainSrv()).deleteall(budgetitem);
			this.getMainTree().createRoot();
		}
	}
	
	/**
	 * 复制当前版本
	 * brianhong  2008-11-23
	 * @throws Exception
	 */
	public void copyitem()
	throws Exception
	{
		Budgetitem mainobj = (Budgetitem) mainObject;
		if(mainobj!=null && mainobj.getId()!=null)
		{
			Budgetitem newobj = new Budgetitem();
			newobj.setHaschild("否");
			newobj.setEnabled("是");
			this.popupDialog(newobj, "/budget/copyverpopup.xul");
		}
		else
			Messagebox.show("没有选择预算项目！");
		
	}
	
	/**
	 * 创建新版本
	 * brianhong  2008-11-23
	 * @throws Exception 
	 */
	public void newver() 
	throws Exception
	{
		Budgetitem mainobj = (Budgetitem) mainObject;
		Budgetitem newobj = new Budgetitem();
		newobj.setOrderby(1L);
		newobj.setMeaunit("元");
		newobj.setBudtype("公司核算");
		newobj.setBudperiod("年度");
		newobj.setChildclass("支出");
		newobj.setHaschild("否");
		newobj.setEnabled("是");
		this.popupDialog(newobj, "/budget/newverpopup.xul");
	}
}
