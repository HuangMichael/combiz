package combiz.ui.pm;

import java.util.List;

import combiz.domain.pm.Premaint;
import combiz.system.FieldAdapter;
import com.inbasis.zk.ui.Component;
/**
 *  @author lijh Ӧ�ó���
 */
public class FldFirstdate extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
//		Premaint  premaint = (Premaint) this.mainObject;
		Long pmcounter = (Long) this.getValueByColname("pmcounter");
//		Component component = arg0.getFellow("premaint.firstdate");
		if(pmcounter > 0)
		{
			this.setReadonly(arg0);
		}
		else
		{
			this.setNoReadonly(arg0);
		}
			
		
	}

	@Override
	public void validate(Component arg0)
	throws Exception
	{
		
		
	}
	
}
