package combiz.ui.inventory;

import java.util.Date;

import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.ListWindow;
import combiz.ui.workorder.WorkorderWindow;

public class WoIssuematerialline extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WoIssuematerialline()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
//		//��ȡ�������������
//		WorkorderWindow parentWnd = (WorkorderWindow)ownerWnd;
//		Workorder parent = (Workorder) parentWnd.getMainObject();
//		
//		Invusetrans newobj = new Invusetrans();
//		newobj.setWonum(parent.getWonum());
//		newobj.setQuantity(0.0);
//		newobj.setUnitcost(0.0);
//		newobj.setLinecost(0.0);
//		newobj.setEnterby(this.getLaborInfo().getLabornum());
//		newobj.setConversion(1.0);
//		newobj.setTransdate(new Date());
//		newobj.setActualdate(new Date());
//		newobj.setState("������");
//		newobj.setCurbal(0d);
//		newobj.setActualcost(0d);
//		newobj.setPhyscnt(0d);
//		newobj.setConversion(1.0);
//		newobj.setIssuetype("����");
//		this.mainObject = newobj;
		Messagebox.show("������ӣ���������Ԥ�������Ŀ���з���");
		return false;
	}
	public void onRowSelected() {
		// TODO �Զ����ɷ������

		ListWindow listWnd = (ListWindow) this.getFellow("invusertrans");
		Invusetrans invusetrans = (Invusetrans) this.getMainObject();
		if(invusetrans.getState().equals("�����"))
		{
			listWnd.setReadonlyList(true);
		}
		else
		{
			listWnd.setReadonlyList(false);
		}
//		super.onRowSelected();
	}
	
	

	
}
