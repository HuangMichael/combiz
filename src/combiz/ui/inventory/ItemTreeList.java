package combiz.ui.inventory;

import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Item;
import combiz.system.ui.ListWindow;


public class ItemTreeList extends ListWindow 
{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ItemTreeList()
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
		//获取父级主窗体对象
		//ItemClassWindow parentWnd = (ItemClassWindow)ownerWnd;
		Classification parent = (Classification)this.getOwnerWnd().getMainObject();
		
		Item newobj = new Item();
		//物资编码自动生成
		//newobj.setItemnum(parent.getClassid()+"-");
		String getkey = this.genAutokey("itemnum");
		int a = getkey.length();
		if (a<4) {
			for(int i = 0;i<(4-a);i++) {
				getkey = "0"+getkey;
			}
		}
		newobj.setItemnum(parent.getClassid()+getkey);
		newobj.setClassid(parent.getClassid());
		if(parent.getClasstype().equals("资产"))
		{
			newobj.setRotating("是");
			newobj.setLottype("批次");
		}
		else
		{
			newobj.setRotating("否");
			newobj.setLottype("非批次");
		}
		
		newobj.setOutside("否");
		newobj.setSpareautoadd("否");
		newobj.setInspectreq("否");

		this.mainObject = newobj;
		return true;
	}
}
