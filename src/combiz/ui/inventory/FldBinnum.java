package combiz.ui.inventory;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invtrans;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;

import java.util.List;
import com.inbasis.zk.ui.Component;
public class FldBinnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) 
	{
		
	}

	/* 
	 * ���ܣ��õ���������ϵ�����
	 * ���ߣ����
	 * ���ڣ�Jun 17, 200811:09:00 PM
	 */
	public void validate(Component component) 
	throws Exception 
	{
		Invtrans inv =(Invtrans) this.getMainObject();
		Component arg = this.getFellow("invtrans.curbal");
		List invstocklist = null;
        String itemnum = inv.getItemnum();
        String warehouse = inv.getWarehouse();
        String binnum = (String) this.getValueByColname("binnum");
       /* if(binnum == null || binnum.length() == 0)
        {
        	invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum = '"+itemnum+"' and (binnum is null or DATALENGTH(binnum) = 0) and warehouse = '"+warehouse+"'");
        }
        else
        {*/
        	invstocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum = '"+itemnum+"' and warehouse = '"+warehouse+"' and binnum ='"+binnum+"'");
        /*}*/
           
        
        for(int i=0;i<invstocklist.size();i++)
        {
        	Invstock invstock = (Invstock) invstocklist.get(0);
        	Double curbal = invstock.getCurbal();
        	Double physcnt = invstock.getPhyscnt();
        	this.setValueByColname("physcnt",physcnt);
        	this.setValueByColname("curbal",curbal);
        	this.setValueByColname("oldcost",inv.getOldcost());
        	
        }
		
	}
	@Override
	public String getListWhere(Component ibandbox) 
	{
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invtrans inv =(Invtrans) this.getMainObject();
        String itemnum = inv.getItemnum();
        String warehouse = inv.getWarehouse();
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
