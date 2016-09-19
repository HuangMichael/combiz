package combiz.ui.inventory;

import java.util.List;

import combiz.domain.inventory.Invstock;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;



/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:44:02 PM  Jul 13, 2008 
 * ���ܣ������û������װ�����룬��ѯװ��ʵʱ�����Ѹ�װ������ѡ�ֿ�ɴ洢�Ļ������г�����
 * �����
 * �����ֶΣ�
 */
public class FldrelationCabin extends FieldAdapter
{
	//�ֶ�����ֵ�󣬻س�������뿪���ø÷���
	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
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

	
	public String getListWhere(Component ibandbox) 
	{
		Textbox arg1 = (Textbox) ibandbox.getFellow("invrectrans.itemnum");
		Bandbox arg2 = (Bandbox) ibandbox.getFellow("invrectrans.towarehouse");
		String itemnum = arg1.getValue();
		String warehouse = arg2.getValue();
		String whereStr = null;
		List invstocklist = (List) this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+itemnum+"' and warehouse='"+warehouse+"'");
		if(invstocklist.size() >0)
		{
			for(int i=0;i<invstocklist.size();i++)
			{
				Invstock invstock = (Invstock) invstocklist.get(i);
				Long id = invstock.getId();
				if(whereStr==null)
					whereStr = "'"+id + "'";
				else
					whereStr = "'"+ id + "'," + whereStr;
			}
			if(whereStr!=null)
			{
				whereStr = "id in(" + whereStr + ")";
			}
			else
			{
				whereStr = "1=2";
			}
		}
		else
			whereStr = "1=2";
		return whereStr;

	}
	


}
