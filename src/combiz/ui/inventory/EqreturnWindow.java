package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InvusetransSrv;
import combiz.business.inventory.MatreqSrv;
import combiz.business.location.LocationsSrv;
import combiz.domain.inventory.Invusetrans;

import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainListWindow;
import combiz.system.ui.MainWindow;

public class EqreturnWindow extends MainListWindow
implements InfWindow
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EqreturnWindow()
	{
		super();
	}

	
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：对选中设备记录进行归还。
	 * 日期：1:47:11 PM  Feb 18, 2009 
	 *
	 */
	public void returneq() throws Exception
	{
		//InvusetransSrv invusetranssrv = (InvusetransSrv)IBOSrvUtil.getSrv("invusetrans");
		List invuselist = this.getSelectObjects();
		if(invuselist.size()>0)
		{
			int rtn = Messagebox.show("确定归还选中的资产？","确定归还资产！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
			if(rtn == Messagebox.NO)
				return ;
			else if(rtn == Messagebox.YES)
			{
				((InvusetransSrv)(this.getMainSrv())).returneq(invuselist);
				//invusetranssrv.returneq(invuselist);
				this.clear();
				Messagebox.show("已经完成归还！");
			}
			
			
		}
		else
		{
			throw new Exception("请选择要归还的资产记录，然后再做归还操作!");
		}
		
	}


	
	
}
