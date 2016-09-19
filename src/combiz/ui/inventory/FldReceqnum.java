package combiz.ui.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 1:37:20 PM  Nov 2, 2008 
 * 功能：在接收周转件的时候，字段设置为必填项，并且验证输入的资产编号是否在资产中存在，如果存在，提示重新输入资产编号。
 * 捆绑表：invrectrans
 * 捆绑字段：eqnum
 */
public class FldReceqnum extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
/*	public void init(Component component)
	throws Exception 
	{
		String itemnum = (String) this.getValueByColname("itemnum");
		if(Util.isNotNull(itemnum))
		{
			List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);
				String rotating = item.getRotating();
				if(Util.isNotNull(rotating) && rotating.equals("是"))
				{
					this.setRequired(component);
					this.setNoReadonly(component);
				}
				else
				{
					this.setNoRequired(component);
					this.setReadonly(component);
				}
			}
			
		}
		


	}
*/
	
	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 计算行总价
	 */
	public void action(Component component)
	throws Exception 
	{
		String eqnum = (String) this.getValueByColname("eqnum");
		if(Util.isNotNull(eqnum))
		{
			Invrectrans invrec = (Invrectrans) this.getMainObject();
			int count =0;
			if(invrec.getId()== null)
			{
				count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment t where t.eqnum = '"+eqnum+"'");
			}
			else
			{
				count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment t where t.eqnum = '"+eqnum+"' and id<>'"+invrec.getId()+"'");
			}
			
			if(count >0)
			{
				this.setValueByColname("eqnum" ,"");
				Messagebox.show("已经存在资产编号为：'"+eqnum+"'的资产，请对资产重新进行编号！");
			}
		}
		
//		EditWindow editwind = (EditWindow) this.getEditWnd(component);
//		ListWindow listWnd = editwind.getListWnd();
//		List modfifylist = listWnd.getModifiedObjectList();
//		String str = null;
//		String[] s = null; 
//		Set set = new HashSet();
//
//		for(int j=0;j<modfifylist.size();j++)
//		{
//			Invrectrans invrec = (Invrectrans) modfifylist.get(j);
//			String neweqnum = invrec.getEqnum();
//			if(Util.isNull(str))
//			{
//				str = neweqnum;
//			}
//			else
//			{
//				str = str + "," + neweqnum;
//			}
//		}
//		if(Util.isNotNull(str))
//		{
//			s=str.split(","); 
//			for(int i = 0;i<s.length;i++){ 
//				set.add(s[i]); 
//			} 
//			if(set.size() < s.length) //有重复   //		if(set.size() == s.length) //无重复 
//			{
//				throw new Exception("有重复的编号！");
//			}
//		}
		
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
