package combiz.ui.corp;
 
import combiz.domain.corp.Labor;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;


public class LaborWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public LaborWindow()
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
		Labor newobj = new Labor();
		newobj.setPayrate(0.0D);
		newobj.setOtscale(0.0D);
		newobj.setEnabled("是");
		newobj.setOutside("是");
		newobj.setEmployeetype("正式员工");
		newobj.setNavmenutype("树形");
		newobj.setCorpnum(this.getLaborInfo().getCorpnum());
		mainObject = newobj;
		return true;
	}
	
	
	@Override
	public void delete() throws Exception
	{
		Labor obj = (Labor) this.getMainObject();
		if(obj.getLabornum().equals("ADMIN"))
		{
			Messagebox.show("ADMIN是默认系统管理员不能删除！");
			return;
		}
		super.delete();
	}
}
