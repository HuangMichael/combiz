package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.RejectSrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
public class RejectWindow extends MainWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public RejectWindow()
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
		Reject newobj = new Reject();
		//请在下面添加对象的初始化值
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String rejectnum = sitenum + "_" +this.genAutokey("rejectnum");
		newobj.setRejectnum(rejectnum);
		newobj.setRejectdate(new Date());
		newobj.setRequestby(labornum);
		newobj.setStatusdate(new Date());
		newobj.setStatus("流程未启动");
		mainObject = newobj;
		return true;
	}
	
	/**
	 * 方法：选择库存中要报废的库存项目，生成报废明细行！
	 * 
	 * 作者：李阳 
	 * 功能：
	 * 日期：Oct 20, 2008 6:16:10 PM
	 *
	 */
	public void createdel() throws Exception
	{
		Reject reject = (Reject)this.mainObject;
		if (reject.getId()==null) {
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
			Messagebox.show("请在生成报废明细行前保存数据！");
			return;
		}
		this.popupDialog(this.getMainObject(), "/inventory/rejinvstockpopup.xul","itemnum in(select t.itemnum from Item t where t.rotating='否') and curbal > 0 and warehouse not like '%报废库%'");
		
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：进行报废批准，将报废行中的报废明细转移到报废库里。
	 * 日期：Oct 21, 2008 11:52:54 AM
	 *
	 */
	public void verify() throws Exception {

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum = '" + reject.getRejectnum() + "'");
		if (retList.size() == 0) {
			Messagebox.show("没有要批准的报废记录!");
		} else {
			((RejectSrv) this.getMainSrv()).verify(retList);
			this.refreshData();
			Messagebox.show("已经完成报废!");

		}
	}


	
	
	
}
