package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.InvusetransSrv;
import combiz.business.inventory.MatreqSrv;
import combiz.business.location.LocationsSrv;
import combiz.domain.inventory.Invusetrans;

import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainListWindow;
import combiz.system.ui.MainWindow;

public class EqreturnWindow extends MainListWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EqreturnWindow()
	{
		super();
	}

	
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ѡ���豸��¼���й黹��
	 * ���ڣ�1:47:11 PM  Feb 18, 2009 
	 *
	 */
	public void returneq() throws Exception
	{
		//InvusetransSrv invusetranssrv = (InvusetransSrv)IBOSrvUtil.getSrv("invusetrans");
		List invuselist = this.getSelectObjects();
		if(invuselist.size()>0)
		{
			int rtn = Messagebox.show("ȷ���黹ѡ�е��ʲ���","ȷ���黹�ʲ���",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
			if(rtn == Messagebox.NO)
				return ;
			else if(rtn == Messagebox.YES)
			{
				((InvusetransSrv)(this.getMainSrv())).returneq(invuselist);
				//invusetranssrv.returneq(invuselist);
				this.clear();
				Messagebox.show("�Ѿ���ɹ黹��");
			}
			
			
		}
		else
		{
			throw new Exception("��ѡ��Ҫ�黹���ʲ���¼��Ȼ�������黹����!");
		}
		
	}


	
	
}
