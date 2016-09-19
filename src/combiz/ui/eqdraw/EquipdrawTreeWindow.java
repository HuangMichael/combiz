package combiz.ui.eqdraw;

import combiz.domain.doclib.Docversion;
import combiz.domain.eqdraw.Equipdraw;
import combiz.system.eqdraw.DocversionList;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Iframe;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;
import com.inbasis.zul.Window;

public class EquipdrawTreeWindow extends TMainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EquipdrawTreeWindow()
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
		Equipdraw newobj = new Equipdraw();
		//请在下面添加对象的初始化值
		newobj.setDrawnum(this.genAutokey("drawnum"));
		newobj.setCreateby(this.getLaborInfo().getLabornum());
		newobj.setCreatedate(new Date());
		newobj.setPosition(0L);
		
		mainObject = newobj;
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
		/*if(this.objStatus == this.ADDED)
		{*/
			Equipdraw eqdraw = (Equipdraw) this.getMainObject();
			//String doclibroot = combiz.system.IBSServer.getIBSServer().getDoclibroot();
			String fileName = java.io.File.separator + "eqdraw" + 
				java.io.File.separator + "project" + java.io.File.separator + eqdraw.getDrawnum() + ".ibx";
			eqdraw.setDrawpath(fileName);
		//}
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
	 * 打开图形编辑器
	 * brianhong  2007-11-1
	 * @throws Exception
	 */
	public void redraw()
	throws Exception
	{
		String userid = this.getUserInfo().getUserid();
		String labornum = this.getUserInfo().getLabornum();
		Equipdraw eqdraw = (Equipdraw) this.getMainObject();
		
		String doclibroot = combiz.system.IBSServer.getIBSServer().getDoclibroot();
		String fileName = doclibroot + java.io.File.separator + "eqdraw" + 
			java.io.File.separator + "project" + java.io.File.separator + eqdraw.getDrawnum() + ".ibx";
		if(fileName==null)
			fileName = "";
		else
			fileName = java.net.URLEncoder.encode(fileName);

		Iframe hiframe = (Iframe) this.getFellow("hiddenIframe");
		hiframe.setSrc("/EqdrawWebStart?fileName="+fileName);
		hiframe.invalidate();
	}
	
	/**
	 * 删除图元库
	 * brianhong  2007-11-6
	 */
	public void deldrawlib()
	{
		Map param= new HashMap();
		Window cp = (Window)Executions.createComponents("/eqdraw/drawlibpopup.xul", null, param);
		try {
			cp.doModal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 下载JRE安装文件
	 * brianhong  2007-11-1
	 */
	public void downloadJRE()
	{
		Iframe hiframe = (Iframe) this.getFellow("hiddenIframe");
		hiframe.setSrc("/system/jre-1_5_0-windows-i586.exe");
		hiframe.invalidate();
	}
}
