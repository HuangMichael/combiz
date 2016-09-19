package combiz.ui.workorder;

import java.util.Date;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IDatebox;
import combiz.system.ui.common.IDatetimebox;

import com.inbasis.zk.ui.Component;

/**
 * @author ��� E-mail:superyang4208731@yahoo.com.cn
 * 2008-5-8����08:21:51
 * ���ܣ������ѡ��ʼʱ�䣬�����ʱ�䲻��ѡ��Ŀ�ļ���ʱ��13753311706
 * �����workorder
 * �����ֶΣ�actstart
 */
public class FldActstart extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������

	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component com)
	throws Exception
	{

		IDatetimebox datebox = null;
		datebox = (IDatetimebox) com;
		
		Date startdate = null;
		Date enddate = (Date) this.getValueByColname("actfinish");
		if (datebox.getValue() != null)
		{
			
			this.setNoReadonly("workorder.actfinish");
		}
		startdate=datebox.getValue();
		if (startdate !=null && enddate != null)
		{
			if (startdate.after(enddate)) {
				this.setValueByColname("schedstart", null);
				this.throwException(com, "��ʼʱ��["+startdate+"]Ӧ�ڽ���ʱ��ǰ�����ʵ��");
			}
			
			Long getmu = this.getTime(startdate, enddate);
			this.setValueByColname("remdur", getmu);
		}
			return;
		
	}
	public Long getTime(Date date1,Date date2) 
	{
		Long d = 0L;
		d= (date2.getTime() - date1.getTime())/1000/60;
		return d;
	}
}
