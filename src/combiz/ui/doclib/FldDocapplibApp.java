package combiz.ui.doclib;

import combiz.domain.ibs.Ibsapps;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;

import com.inbasis.zk.ui.Component;

public class FldDocapplibApp extends FieldAdapter
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
		if(this.getRecWnd()!=null)
		{
			if(((EditWindow)this.getRecWnd()).getListWnd() instanceof DocapplibList)
			{
				String app = (String) this.getValueByColname("app");
				Ibsapps ibsapp = (Ibsapps) this.getMainSrv().getBaseDao().findUniqueBy(Ibsapps.class, "app", app);
				if(ibsapp!=null)
					this.setValueByColname("ownertable", ibsapp.getMaintbname());
			}
		}
	}
}
