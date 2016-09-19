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
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO �ֶ�������ƿ�����ø÷������������������ǽ���Ӧ�̵���ϵ�˸�ֵ����һ���ı���
	 * @param component
	 * @throws Exception
	 * @����� 2007-8-7 ����03:04:39
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
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invrectrans irt =(Invrectrans) this.getMainObject();
        String itemnum = irt.getItemnum();
        String warehouse = irt.getFromwarehouse();
        if(warehouse.trim()!= null)
        {
        	if (itemnum != null  && warehouse != null)
        		//��Ӧ�ó���PDXL�������Ч
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
