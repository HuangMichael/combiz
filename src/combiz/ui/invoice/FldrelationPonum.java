package combiz.ui.invoice;

import java.util.List;

import combiz.domain.contract.Contract;
import combiz.domain.invoice.Invoice;
import combiz.domain.location.Locations;
import combiz.domain.po.Po;
import combiz.system.FieldAdapter;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;


/**
 * ���ܣ��ڱ��Ʒ�Ʊ�ǣ����ݽ�����ѡ��Ĺ�Ӧ����Ϣ�����ù�Ӧ�̶�Ӧ��û�п���Ʊ�Ĳɹ������˳�����
 * �󶨱�invoice
 * ���ֶΣ�ponum
 *@author ����
 *2008-1-24����11:05:39
 */

public class FldrelationPonum extends FieldAdapter
{
	//�ֶ�����ֵ�󣬻س�������뿪���ø÷���
	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		Object obj = this.getMainObject();
		if(obj instanceof Invoice)
		{
			Invoice invoice  = (Invoice) obj;
		}
		String ponum = (String) this.getValueByColname("ponum");
		if (Util.isNotNull(ponum)) 
		{
			List polist = this.mainSrv.getBaseDao().findWithQuery(Po.class, "ponum = '"+ponum+"'");
			if (polist.size()>0) {
				Po po = (Po) polist.get(0);
				this.setValueByColname("vendor", po.getVendor());
				this.setReadonly("invoice.vendor");
			}
			this.setValueByColname("cntnum", "");
		}else{
			this.setNoReadonly("invoice.vendor");
			this.setRequired("invoice.vendor");
		}
		
	}
    //�ֶγ�ʼ�����ø÷���
	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
   //�����ƿ�Ч��
	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}


	/**
	*@author ����
	*���ܣ�
	*@param ibandbox
	*@return 
	*2008-1-24����11:41:43
	*/
	
	public String getListWhere(Component ibandbox) 
	{
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invoice invoice =(Invoice) this.getMainObject();
		String vendor = invoice.getVendor();
		if (vendor != null && vendor.trim().length() > 0)
		//��Ӧ�ó���PDXL�������Ч
		{
			String whereStr = "status not in('����δ����','�ر�','ȡ��') and vendor = '" + vendor +"'";
			return whereStr;
		}
		else
		{
			String whereStr = "status not in('����δ����','�ر�','ȡ��')";
			return whereStr;
		}
	}


}
