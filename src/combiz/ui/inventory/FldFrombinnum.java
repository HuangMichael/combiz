package combiz.ui.inventory;


import combiz.domain.inventory.Invrectrans;
import combiz.system.FieldAdapter;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
public class FldFrombinnum extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	public void validate(Component component) 
	throws Exception 
	{
		
	}
	@Override
	public String getListWhere(Component ibandbox) 
	{
		//从编辑窗口获取对应的LIST窗口
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invrectrans inv =(Invrectrans) this.getMainObject();
        String itemnum = inv.getItemnum();
        String warehouse = inv.getFromwarehouse();
        if(Util.isNotNull(warehouse)&&Util.isNotNull(itemnum))
        {
        	String whereStr = "itemnum = '" + itemnum +"' and warehouse = '" + warehouse +"'";
        	return whereStr;
        }
        else
 
        return "1=2";
       
		
	}
	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
