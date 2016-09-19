package combiz.ui.assetscard;

import java.util.Date;

import combiz.domain.assetscard.Allocation;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Reject;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class AllocationWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AllocationWindow()
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
		newobj.setAllocationnum(this.genAutokey("allocationnum"));
		String labornum = this.getUserInfo().getLabornum();
		Labor labor = (Labor) this.getMainSrv().getBaseDao().findUniqueBy(Labor.class, "labornum", labornum);
		if(labor != null)
		{
			newobj.setFromsite(labor.getDeptnum());
			newobj.setSitenum(labor.getDeptnum());
		}
		newobj.setEnterby(labornum);
		newobj.setEnterdate(new Date());
		newobj.setStatus("草稿");
		newobj.setStatusdate(new Date());
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：从调入地点的仓库里选择报废资产明细。
	 * 日期：4:11:57 PM  May 13, 2010 
	 *
	 */
	public void addallocationline() throws Exception
	{
		Allocation allocation = (Allocation)this.mainObject;
		if (allocation.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("请在生成调拨明细行前保存数据！");
			return;
		}
		
		this.popupDialog(this.getMainObject(), "/assetscard/equiplinepopup.xul", "location in(select t.warehouse from Warehouse t where t.sitenum='"+allocation.getFromsite()+"')");
		
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
}
