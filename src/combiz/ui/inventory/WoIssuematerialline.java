package combiz.ui.inventory;

import java.util.Date;

import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;
import combiz.ui.workorder.WorkorderWindow;

public class WoIssuematerialline extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WoIssuematerialline()
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
//		//获取父级主窗体对象
//		WorkorderWindow parentWnd = (WorkorderWindow)ownerWnd;
//		Workorder parent = (Workorder) parentWnd.getMainObject();
//		
//		Invusetrans newobj = new Invusetrans();
//		newobj.setWonum(parent.getWonum());
//		newobj.setQuantity(0.0);
//		newobj.setUnitcost(0.0);
//		newobj.setLinecost(0.0);
//		newobj.setEnterby(this.getLaborInfo().getLabornum());
//		newobj.setConversion(1.0);
//		newobj.setTransdate(new Date());
//		newobj.setActualdate(new Date());
//		newobj.setState("待检验");
//		newobj.setCurbal(0d);
//		newobj.setActualcost(0d);
//		newobj.setPhyscnt(0d);
//		newobj.setConversion(1.0);
//		newobj.setIssuetype("发放");
//		this.mainObject = newobj;
		Messagebox.show("不能添加，请点击发放预留库存项目进行发放");
		return false;
	}
	public void onRowSelected() {
		// TODO 自动生成方法存根

		ListWindow listWnd = (ListWindow) this.getFellow("invusertrans");
		Invusetrans invusetrans = (Invusetrans) this.getMainObject();
		if(invusetrans.getState().equals("已完成"))
		{
			listWnd.setReadonlyList(true);
		}
		else
		{
			listWnd.setReadonlyList(false);
		}
//		super.onRowSelected();
	}
	
	

	
}
