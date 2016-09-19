package combiz.ui.inventory;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.system.ui.ListWindow;

import java.util.Date;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

public class IssueMrInvuseList extends ListWindow
{

	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public IssueMrInvuseList()
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
		if(this.getOwnerWnd().getApp().equals("ISSUEEQ"))
		{
			throw new Exception("�����½���¼�����ѡ������˵��µ�ѡ�񷢷��н��в�����");
		}
		Invusetrans newobj = new Invusetrans();
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setEnterby(this.getLaborInfo().getLabornum());
		newobj.setActualdate(new Date());
		newobj.setIssuetype("δ�ύ");
		newobj.setTransdate(new Date());
		newobj.setConversion(1D);
		
		this.mainObject = newobj;
		//Messagebox.show("����������������У���ͨ�������˵���ѡ������Ŀ��ӣ�");
		return true;
	}


	/* @Override
	 * ��������ɵ���Ϊֻ��
	 */
	
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		Invusetrans invusetrans = (Invusetrans) arg1;
		if (invusetrans.getState() != null && invusetrans.getState().equals("�����")) {
			this.setReadonlyObject(arg0,true);
		}else{
			this.setReadonlyObject(arg0, false);
		}
		super.initRowData(arg0, arg1);
	}
}
