package combiz.ui.inventory;


import combiz.domain.inventory.Invrectrans;
import combiz.system.FieldAdapter;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
public class FldFrombinnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
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
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
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
