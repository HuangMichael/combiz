package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICheckbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;


/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 2:44:11 PM  Jan 12, 2009 
 * ���ܣ�������ʱ��벻Ϊ�գ�����Ϊֻ����
 * �����wpmaterial
 * �����ֶΣ�classid
 */
public class FldWpClassid extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component)
	throws Exception
	{
		IBandbox arg = (IBandbox) this.getFellow("wpmaterial.itemnum");
		if(Util.isNotNull(arg.getValue()))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
		}		
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
		
	}

	@Override
	public String getListWhere(Component arg0) {
		// TODO Auto-generated method stub
		String app = this.getRecWnd().getOwnerWnd().getApp();
		String classtype = "";
		if(app.equals("EQREQ") || app.equals("BORROWEQU")) {
			classtype = "classtype in ('�ʲ�')";
		}else{
			classtype = "classtype in ('����','����')";
		}
		return classtype;
	}

}
