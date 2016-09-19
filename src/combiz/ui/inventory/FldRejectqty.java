package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Rejectitem;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDoublebox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
/**
 *  @author lijh Ӧ�ó���
 */
public class FldRejectqty extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������

		Rejectitem rej = (Rejectitem) this.mainObject;
		Double rejectqty = (Double) this.getValueByColname("rejectqty");
		String itemnum = (String) this.getValueByColname("itemnum");
		String warehouse = (String) this.getValueByColname("warehouse");
		String binnum = (String) this.getValueByColname("binnum");
		String lotnum = (String) this.getValueByColname("lotnum");
		
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
		Item item = (Item) itemlist.get(0);
		String lottype =  item.getLottype();
		
		List stocklist = null;
		if(lottype.equals("�����ι���"))
		{
			if(Util.isNull(rej.getBinnum()))//������Ϊ��
			{
				stocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
						+"' and binnum is null ");
			}
			else
			{
				stocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
						+"' and binnum ='"+rej.getBinnum()+"' ");
			}
				
		}
		else//���ι���
		{
			stocklist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse ='"+rej.getWarehouse()
					+"' and lotnum ='"+rej.getLotnum()+"' ");
			
		}
		
		if(stocklist.size()>0)
		{
			Invstock invstock = (Invstock) stocklist.get(0);
			Double curbal = invstock.getCurbal();
			if(rejectqty - curbal >0)
			{
				this.setValueByColname("rejectqty", 0d);
				throw new Exception("�����Ϊ:'"+itemnum+"'�Ŀ�汨���������ڿ�����������޸ı���������");
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
