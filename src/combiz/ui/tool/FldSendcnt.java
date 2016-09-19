package combiz.ui.tool;

import combiz.business.tool.TooltransSrv;
import combiz.domain.tool.Tooltrans;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Longbox;
import com.inbasis.zul.Messagebox;

/**
 *  @author ljh Ӧ�ó���
 */

public class FldSendcnt extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������
		Tooltrans tooltrans = (Tooltrans) this.mainObject;
		Long id = tooltrans.getId();
		Longbox ilbox = null;
		ilbox = (Longbox) com;
		
		/******************��ѯ�Ѿ��黹����ʷ��¼************************************/
		TooltransSrv worksrv = (TooltransSrv)IBOSrvUtil.getSrv("tooltrans");
		Long getcont =(Long) worksrv.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where sendnum = '"+tooltrans.getId()+"'");
		
		if (getcont == null)
		{
			getcont = 0L;
		}
		
		if (ilbox.getValue()!=null) {
			Long count = ilbox.getValue();
			Long coun = tooltrans.getToolqty();
			if((coun-getcont)==0) 
			{
				Messagebox.show("�ù����Ѿ��黹�꣡");
				this.setValueByColname("sendcount", 0L);
				return;
			}else if (count <= 0)
			{
				Messagebox.show("������������Ϊ����0��������");
				if((coun-getcont)>0) 
				{
					this.setValueByColname("sendcount", (coun-getcont));
				}else{
					this.setValueByColname("sendcount", 0L);
				}
			}else if ((count+getcont) > tooltrans.getToolqty())
			{
				Messagebox.show("�黹�Ĺ����������ڽ��õĹ���������");
				this.setValueByColname("sendcount", 0L);
				return;
				
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}

	@Override
	public void validate(Component arg0) throws Exception {
		// TODO �Զ����ɷ������
		
	}
	
}
