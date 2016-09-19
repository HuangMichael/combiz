package combiz.ui.po;


import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Item;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldWarehouse extends FieldAdapter
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
		
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception
	{

		//�õ���������
		Poline pl = (Poline)this.mainObject;
		String itemnum = pl.getItemnum();

		//�õ��ؼ�
		
		//ͨ���ؼ���valueֵ����ѯ���
		List Itmlist = this.mainSrv.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
		
		//���������Ĵ�С>=1
		if(Itmlist.size()>0){
		Item it = (Item)Itmlist.get(0);
		//�������һ������ֵ�󶨸����������һ������
		String orderunit = it.getOrderunit();
		
		this.setValueByColname("orderunit", orderunit);
		}else{
			this.setValueByColname("orderunit", "");
			
		}
//		//ֵ�󶨣�������󶨣���֮ǰ�ĸ�ֵ���׷ѹ���
//		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
//		try {
//			recWnd.bandData();
//		} catch (Exception e) {
//			// TODO �Զ����� catch ��
//			e.printStackTrace();
//		}
	
		
	}

}
