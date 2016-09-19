package combiz.ui.assetscard;

import java.util.List;

import combiz.business.assetscard.AllocationSrv;
import combiz.business.po.PoSrv;
import combiz.domain.assetscard.Allocation;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class AllocationrecWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AllocationrecWindow()
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
		Allocation newobj = new Allocation();
		//请在下面添加对象的初始化值

		mainObject = newobj;
		return true;
	}


	/**
	 * 当在过滤器上回车后，执行过滤器搜索时调用
	 * 可以添加自己的自定义搜索条件
	 * @throws Exception
	 * @洪小林  2007-8-4  下午11:55:49
	 */
	public void search()
	throws Exception
	{
		this.getUISearchString();

		//在这里加入自己的搜索方法
		//if(this.searchString.length()>0)
		//	this.searchString = searchString + " and 自己的搜索条件";
		//else
		//	this.searchString = " and 自己的搜索条件";

		//重新获取列表结果集
		this.getResultList(true);
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
	 * 由子类继承，应用程序接口
	 * 在屏幕数据显示完后，在做屏幕字段授权之前
	 * 
	 * 英贝思  Nov 14, 2009
	 * @throws Exception
	 */
	public void initData() throws Exception
	{
		//添加用户自己的代码
	}


	/**
	 * 事件接口类：用户点击tab页时调用
	 * brianhong  2008-10-10
	 * @param selectedTabid
	 */
	public void onSelectedTab(String selectedTabid)
	throws Exception
	{
		//添加用户自己的代码
	}

	/**
	 * 当点击Tab标签时，初始化Tabpanel数据时调用
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param tabpanel
	 * @throws Exception
	 */
	public void onInitTabpanel(Tabpanel tabpanel) throws Exception
	{
		//添加用户自己的代码
	}


	/**
	 * 用户接口
	 * 在保存方法之前被调用
	 * 返回true-执行保存动作，返回false-不保存
	 * 洪小林  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave()
	throws Exception
	{
		return true;
	}



	/**
	 * 用户接口
	 * 保存方法执行后调用用户接口 
	 * 洪小林  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterSave()
	throws Exception
	{
		//添加自己的代码
	}


	/**
	 * 删除数据之前的自定义接口  20091103
	 * 洪小林  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeDelete()
	throws Exception
	{
		return true;
	}



	/**
	 * 删除数据之前的自定义接口  20091103
	 * 洪小林  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public void afterDelete()
	throws Exception
	{
		//添加自己的代码
	}


	/**
	 * 自定义接口
	 * 根据流程终止时的状态返回true和false标示子表是否只读--只有流程已经关闭的情况下才可以使用该方法
	 * 返回true-设置所有的子表为只读
	 * 返回false-不设置所有子表为只读
	 * 洪小林  Nov 14, 2009
	 * @return
	 */
	public boolean isWFStopStatus()
	{
		//示例：
		/**
		 * Workorder workorder = (Workorder)this.getMainObject();
		 * String status = workorder.getStatus();
		 * if(status!=null && (status.equal("已关闭") || status.equal("已取消"))
		 * 	return true;
		 * else
		 *  return false;
		 */
		return super.isWFStopStatus();
	}


	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：从调拨申请行里拷贝数据到调拨接收行里，完成接收。
	 * 日期：11:10:40 AM  May 18, 2010 
	 *
	 */
	public void addallocline() throws Exception {

		Allocation allocation = (Allocation) this.mainObject;

		if (allocation.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (allocation.getStatus().equals("已批准")) {
			CommonListWindow listWnd = (CommonListWindow) this
			.popupDialog(
					this.getMainObject(),
					"/assetscard/allocationlinelist.xul",
					"allocationnum='"
					+ allocation.getAllocationnum()
					+ "' and eqnum not in(select i.eqnum from Invrectrans i where i.ponum = '"+allocation.getAllocationnum()+"')");
			if (listWnd == null)
				return;

			// 判断是否点击了确定按钮，还是取消按钮
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((AllocationSrv) this.getMainSrv()).addallocline(retList, allocation);
			this.refreshData();
			Messagebox.show("数据成功保存!");
		} else
			Messagebox.show("该调拨申请单没有批准，不能进行接收");

		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

	}


	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：对调拨接收行进行校验
	 * 日期：2:32:55 PM  May 18, 2010 
	 *
	 */
	public void verify() throws Exception {

		Allocation allocation = (Allocation) this.mainObject;

		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invrectrans.class,
				"ponum = '" + allocation.getAllocationnum() + "' and status ='待检验'");
		if (retList.size() == 0)
		{
			Messagebox.show("没有需要检验的调拨接收记录!");
		} 
		else 
		{

			Boolean besure = null;
			if(Messagebox.show("检验后,不能修改记录!您确认'接收仓库'无误?","提示!!!",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
				besure = true;
			else
				besure = false;
			if(besure)
			{
				((AllocationSrv) this.getMainSrv()).verify(retList, allocation);
				this.refreshData();
				Messagebox.show("已经完成检验!");
			}
			else
			{
				return;
			}
		}
	}
}
