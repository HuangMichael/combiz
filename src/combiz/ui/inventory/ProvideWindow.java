package combiz.ui.inventory;
 
import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

import combiz.business.inventory.MatreqSrv;
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


public class ProvideWindow extends MainWindow
implements InfWindow
{	

	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ProvideWindow()
	{
		super();
	}
	
	
	/**
	 * 
	 * brianhong  2007-11-13
	 */
	public void selectMRLine()
	{
		
	}

	public void save() throws Exception {
		// TODO 自动生成方法存根
		super.save();
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：生成借用发放行
	 * 日期：Nov 4, 2008 10:38:45 AM
	 *
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("借用发放操作前，请先保存已经发放的记录！");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();
		
		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		String status = matreq.getStatus();
		if (Util.isNotNull(status) && status.equals("已批准")) {
			int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Invreserve t where t.reqnum = '" + matreq.getMatreqnum() + "'");
			if(count > 0)
			{
			Window listWnd = (Window) this.popupDialog(this
					.getMainObject(), "/inventory/eqinvreservepopup.xul",
					"reqnum='" + matreq.getMatreqnum() + "'");
			}
			else
			{
				throw new Exception("没有预留记录，请核实！");
			}
		}
		else
		{
			throw new Exception("借用申请'"+matreq.getMatreqnum()+"'没有批准，不能进行发放");
		}
		
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：物资和周转件（资产）的确认发放
	 * 日期：Nov 4, 2008 10:38:59 AM
	 *
	 */
	public void verify() throws Exception {

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		
		ListWindow listWnd = (ListWindow) this.getFellow("eqbrowissue");
		boolean ismultiple = listWnd.isMultiple();
		List retList = null;
		List selectList = null;
		Boolean toverify = null;
		if(ismultiple == true)//用户是否点击选择
		{
	    	if(Messagebox.show("是否校验选中的发放行？","请确认！",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
	    		toverify = true;
			else
				toverify = false;
	    	if(toverify)
			{
	    		selectList = listWnd.getSelectObjects();
				for(int i=0;i<selectList.size();i++)
				{
					Invusetrans invu = (Invusetrans) selectList.get(i);
					if(invu.getState().equals("待确认"))
					{
						retList.add(invu);
					}
					
				}
				if(retList==null);
				{
					throw new Exception("没有待确认的接收记录!");
				}
			}
	    	else
	    	{
	    		return;
	    	}
			
		}
		else
		{
			if(Messagebox.show("是否确认该借用申请下所有未确认的发放行？","请确认！",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
	    		toverify = true;
			else
				toverify = false;
	    	if(toverify)
			{
	    		retList = this.getMainSrv().getBaseDao().findWithQuery(Invusetrans.class,"matreqnum = '" + matreq.getMatreqnum() + "' and state ='待确认'");
			}
	    	else
	    	{
	    		return;
	    	}
			
			
		}
		if (retList.size() == 0) 
		{
			Messagebox.show("没有待确认的发放记录!");
		} 
		else 
		{
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("完成发放!");
		}
	}
	
	
	

	
	
}
