package combiz.ui.assetscard;

import combiz.domain.assetscard.Allocation;
import combiz.domain.assetscard.Allocationline;
import combiz.system.ui.ListWindow;
import com.inbasis.zul.Listitem;

public class Allocation_AllocationlineTableList extends ListWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public Allocation_AllocationlineTableList()
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
		Allocation parent = (Allocation) this.getOwnerWnd().getMainObject();
		
		Allocationline newobj= new Allocationline();
		//newobj.setXXXX(parent.getXXXX()); //必须添加关联字段值
		newobj.setAllocationnum(parent.getAllocationnum());

		this.mainObject = newobj;
		return true;
	}
	
	
	/**
	 * 自定义接口
	 * 设置自定义默认第一次的查询条件，打开界面时执行一次，以后的查询都不会执行该条件
	 * brianhong  2009-6-16
	 * @return
	 * @throws Exception 
	 */
	public String getDefaultQueryString()
	throws Exception
	{
		return null;
	}
	

	/**
	 * 初始化一行数据行时候的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param listitem  数据行控件
	 * @param objtmp  数据行对应的主对象值
	 * @throws Exception
	 */
	public void initRowData(Listitem listitem, Object objtmp) throws Exception
	{
		//添加自定义代码
	}
	
	/**
	 * 列表上选中了某条记录后会调用该方法
	 * brianhong  2007-11-2
	 */
	public void onRowSelected()
	{
		//添加自定义代码
	}


	/**
	 * 在保存按钮点击后，在保存动作执行之后的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterSave() throws Exception
	{
		//添加自定义代码
	}

	/**
	 * 在保存按钮点击后，在保存动作执行前的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave() throws Exception
	{
		return true;
	}



	/**
	 * 在删除行按钮点击后，在删除动作执行前的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeRemove() throws Exception
	{
		return true;
	}
	
	/**
	 * 在删除行按钮点击后，在删除标记动作执行之后的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterRemove() throws Exception
	{
		//添加自定义代码
	}
	
}
