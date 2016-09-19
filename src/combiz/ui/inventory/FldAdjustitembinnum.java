package combiz.ui.inventory;


import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.invoice.Invoice;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import java.util.List;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Textbox;

public class FldAdjustitembinnum extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将供应商的联系人赋值给另一个文本框
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		List invstocklist = null;
		Invrectrans irt =(Invrectrans) this.getMainObject();
		Component arg = this.getFellow("invrectrans.curbal");
        String itemnum = irt.getItemnum();
        String warehouse = irt.getFromwarehouse();
        String binnum = (String) this.getValueByColname("frombin");
        /*if(binnum == null || binnum.length() == 0)
        {
        	invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum = '"+itemnum+"' and (binnum is null or DATALENGTH(binnum) = 0)and warehouse = '"+warehouse+"'");
        }
        else
        {*/
        	invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum = '"+itemnum+"' and warehouse = '"+warehouse+"' and binnum ='"+binnum+"'");
       /* }*/
           
        for(int i=0;i<invstocklist.size();i++)
        {
        	Invstock invstock = (Invstock) invstocklist.get(0);
        	Double curbal = invstock.getCurbal();
        	this.setValueByColname("curbal",curbal );
        	
        }
        

	}

	@Override
	public String getListWhere(Component ibandbox) 
	{
		//从编辑窗口获取对应的LIST窗口
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invrectrans irt =(Invrectrans) this.getMainObject();
        String itemnum = irt.getItemnum();
        String warehouse = irt.getFromwarehouse();
        if(warehouse.trim()!= null)
        {
        	if (itemnum != null  && warehouse != null)
        		//在应用程序PDXL下面才生效
        		{
        			String whereStr = "itemnum = '" + itemnum +"' and warehouse = '" + warehouse +"'";
        			return whereStr;
        		}
        		else
        			return "1=2";
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
