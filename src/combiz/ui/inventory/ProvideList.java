package combiz.ui.inventory;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.system.ui.ListWindow;

import java.util.Date;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

public class ProvideList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ProvideList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Matreq parent = (Matreq)ownerWnd.getMainObject();
		if(this.getOwnerWnd().getApp().equals("ISSUEEQ")||this.getOwnerWnd().getApp().equals("PROVIDE"))
		{
			throw new Exception("�����½���¼�����ѡ������˵��µ�ѡ�񷢷��н��в�����");
		}
		Invusetrans newobj = new Invusetrans();
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setEnterby(this.getLaborInfo().getLabornum());
		newobj.setActualdate(new Date());
		newobj.setIssuetype("δ�ύ");
		newobj.setRequestdate(parent.getRequestdate());
		newobj.setUsedate(parent.getUsedate());
		newobj.setTransdate(new Date());
		newobj.setConversion(1D);
		
		this.mainObject = newobj;
		return true;
	}
	
	
	
}
