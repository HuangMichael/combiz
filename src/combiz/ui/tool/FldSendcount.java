package combiz.ui.tool;

import combiz.business.tool.TooltransSrv;
import combiz.domain.tool.Toolreserve;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Longbox;
import com.inbasis.zul.Messagebox;

/**
 *  @author ljh Ӧ�ó���
 */

public class FldSendcount extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO �Զ����ɷ������
		Toolreserve toolreserve = (Toolreserve) this.mainObject;
		Longbox ilbox = null;
		ilbox = (Longbox) com;
		
		TooltransSrv worksrv = (TooltransSrv)IBOSrvUtil.getSrv("tooltrans");
		Long getcont =(Long) worksrv.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where sendnum = '"+toolreserve.getId()+"' and transtype = '����'");
		
		if (getcont == null)
		{
			getcont = 0L;
		}
		
		
		if (ilbox.getValue()!=null) {
			Long count = ilbox.getValue();
			if (count <= 0)
			{
				Messagebox.show("������������Ϊ����0��������");
				this.setValueByColname("sendcount", toolreserve.getReservedqty());
				return;
			}else if (count > toolreserve.getReservedqty())
			{
				Messagebox.show("���ŵĹ���������������Ĺ���������");
				this.setValueByColname("sendcount", toolreserve.getReservedqty());
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
