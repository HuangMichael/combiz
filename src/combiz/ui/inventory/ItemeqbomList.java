package combiz.ui.inventory;

import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemeqbom;
import combiz.system.ui.ListWindow;

public class ItemeqbomList extends ListWindow
{

	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：李建红 日期：2009-2-24
	 */
	public ItemeqbomList()
	{
		super();
	}

	
	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：李建红 日期：2009-2-24
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Item parent = (Item)this.getOwnerWnd().getMainObject();
		
		Itemeqbom newobj = new Itemeqbom();
		newobj.setParent(parent.getItemnum());
		
		this.mainObject = newobj;
		return true;
	}
}
