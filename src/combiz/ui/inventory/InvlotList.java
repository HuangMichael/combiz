package combiz.ui.inventory;

import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.system.ui.ListWindow;

public class InvlotList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvlotList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Inventory parent = (Inventory)ownerWnd.getMainObject();
		
		Invlot newobj = new Invlot();
		//newobj.setCorpnum(parent.getCorpnum());
		//newobj.setSitenum(parent.getSitenum());
		newobj.setItemnum(parent.getItemnum());
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setLotnum(this.genAutokey("lotnum"));
		this.mainObject = newobj;
		return true;
	}
}
