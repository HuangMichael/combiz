package combiz.ui.assetscard;

import combiz.business.assetscard.CarryoverSrv;
import combiz.domain.assetscard.Carryover;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class CarryoverWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CarryoverWindow()
	{
		super();
	}

	
	/**
	 * @author 王伟 功能：反结转
	 * @throws Exception
	 *             2010-05-06上午17:22
	 */
	public void uncarryover() throws Exception {
				
		Carryover carryover = (Carryover) this.getSelectObject();
	
		List eqlist = this.getSelectObjects();
		if(eqlist.size()==0 )
		{
			Messagebox.show("请选择至少一条记录");
			return;
		}
		if(!carryover.getStatus().equals("未结转"))
		{
			Messagebox.show("该月份已经结转，请选择一条未结转的记录");
			return;
		}
		
		else
		{
			((CarryoverSrv) this.getMainSrv()).copyuncarryover(carryover);
			this.clear();
			Messagebox.show("反结转成功！");
		}
	  	
	
	
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
	
	}


	/**
	 * @author 王伟 功能：将设备台账的部分信息拷贝到固定资产中去
	 * @throws Exception
	 *             2010-05-06上午17:22
	 */
	public void carryover() throws Exception {
				
		Carryover carryover = (Carryover) this.mainObject;
		List eqlist = this.getSelectObjects();
		if(eqlist.size()==0 )
		{
			Messagebox.show("请选择至少一条记录");
			return;
		}
		if(eqlist.size()!=0 && carryover.getStatus()=="已结转")
		{
			Messagebox.show("该月份已结转");
			return;
		}
		
		else
		{
			((CarryoverSrv) this.getMainSrv()).copycarryover();
			this.clear();
			Messagebox.show("结转成功！");
		}
	  	
	
	
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
	
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
		Carryover newobj = new Carryover();
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
	 * 设置自定义查询条件
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
