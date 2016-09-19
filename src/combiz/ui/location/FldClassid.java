package combiz.ui.location;

import java.util.List;

import combiz.business.location.LocationsSrv;
import combiz.domain.location.Locations;
import combiz.system.FieldAdapter;
import combiz.system.ui.MainWindow;
import combiz.system.ui.RecWindow;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldClassid extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		List locspecList;
		Locations locations = (Locations)this.mainObject;
		try {
			locspecList = this.mainSrv.findByRelation(mainObject, "locspec");
			if(locations.getClassid()!=null && locspecList.size()>0)
			{
				this.setReadonly(component);
			}
			else
			{
				this.setNoReadonly(component);
			}	
		} catch (Exception e) {
			e.printStackTrace();
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

}
