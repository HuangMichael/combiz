package ${uipackage};

import ${mainObjectClass};
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import com.inbasis.zul.Tabpanel;

public class ${uiclassname} extends TMainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 */
	public ${uiclassname}()
	{
		super();
	}

	
	/**
	 * 添加记录
	 * @throws Exception
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		${ftablename} newobj = new ${ftablename}();
		//请在下面添加对象的初始化值
		
		mainObject = newobj;
		return true;
	}
	

	
	/**
	 * 自定义接口
	 * 设置自定义默认第一次的查询条件，打开界面时执行一次，以后的查询都不会执行该条件
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
	 * @throws Exception
	 */
	public void initData() throws Exception
	{
		//添加用户自己的代码
	}

	
	/**
	 * 事件接口类：用户点击tab页时调用
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
	 * @throws Exception
	 */
	public void afterSave()
	throws Exception
	{
		//添加自己的代码
	}
	
	
	/**
	 * 删除数据之前的自定义接口  20091103
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
