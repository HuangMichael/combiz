package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.ICheckbox;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 6:49:52 PM  Jul 22, 2008 
 * ���ܣ��ڿⷿ�̵��У����ѡ���̵����пⷿ�����ֶΡ��ⷿ���롱��Ϊ���ɱ༭״̬��ͬʱ��տⷿ�����ֶ������ݣ�������Ϊ�ɱ༭״̬��
 * �����hgequstocktake
 * �����ֶΣ�isall
 */
public class FldIssall extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		
	}

	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
	 */
	public void validate(Component component) 
	throws Exception 
	{

	
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		ICheckbox arg = (ICheckbox) component;
		Bandbox arg1 = (Bandbox) component.getFellow("checkqty.chkwarehouse");
		Textbox arg2 = (Textbox) component.getFellow("checkqty.checkbinnum");
		Textbox arg3 = (Textbox) component.getFellow("checkqty.classid");
		Textbox arg4 = (Textbox) component.getFellow("checkqty.itemnum");
		if(arg.getValue().equals("��"))
		{
			this.setReadonly(arg1);
			this.setReadonly(arg2);
			this.setReadonly(arg3);
			this.setReadonly(arg4);
			this.setValueByColname("chkwarehouse", "");
			this.setValueByColname("checkbinnum", "");
			this.setValueByColname("classid", "");
			this.setValueByColname("itemnum", "");
		}
		else
		{
			this.setNoReadonly(arg1);
			this.setNoReadonly(arg2);
			this.setNoReadonly(arg3);
			this.setNoReadonly(arg4);
		}	
		
	}

}
