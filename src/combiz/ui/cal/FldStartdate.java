package combiz.ui.cal;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDatebox;

import java.util.Date;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldStartdate 
extends FieldAdapter 
{

	@Override
	public void action(Component component)
	throws Exception 
	{

	}

	@Override
	public void init(Component component) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component component)
	throws Exception 
	{
		Date startdate = (Date) this.getValueByColname("startdate");
		Date enddate = (Date) this.getValueByColname("enddate");
		if(enddate!=null && startdate!=null)
		{
			if(enddate.before(startdate))
			{
				IDatebox idatebox= ((IDatebox)component);
				Messagebox.show("����ʱ�����ڿ�ʼʱ�䣬������ѡ��");
				idatebox.focus(); //Ϊ��ǿ��������ȷ��ֵ����ܽ�����������
			}
		}
	}

}
