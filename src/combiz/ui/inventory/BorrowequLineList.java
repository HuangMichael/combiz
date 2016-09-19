package combiz.ui.inventory;

import combiz.domain.inventory.Matreq;
import combiz.domain.po.Po;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

import java.util.Date;

import com.inbasis.zul.Listitem;

public class BorrowequLineList extends ListWindow
{


	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public BorrowequLineList()
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
		//��ȡ�������������
		Matreq parent = (Matreq) ownerWnd.getMainObject();
		String defwarehouse = this.getLaborInfo().getDefaultstoreloc();
		Wpmaterial newobj = new Wpmaterial();
		newobj.setWarehouse(defwarehouse);
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setWonum(parent.getWonum());
		newobj.setItemqty(0.0);
		newobj.setUnitcost(0.0);
		newobj.setLinecost(0.0);
		newobj.setRequestby(parent.getRequestby());
		newobj.setRequiredate(new Date());

		this.mainObject = newobj;
		return true;
	}	
	
	/* 
	 * ���ܣ������״̬��"�����쵼����"��״̬������Ϊ��ֻ����
	 * ���ߣ�����
	 * ���ڣ�Nov 28, 2008  11:13:25 AM
	 */
	@Override
	public void initRowData(Listitem listitem, Object obj) throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		ListWindow listwnd = (ListWindow) this.getOwnerWnd().getFellow(
				"wpmaterial");
		boolean onlyread = false;
		if (listwnd != null) {
			
			if (matreq.getStatus().equals("�����쵼����")||matreq.getStatus().equals("�豸�����쵼����")||matreq.getStatus().equals("�����쵼����"))
			{
				onlyread = true;
			} 
			else
			{
				onlyread = false;
			}

			// �Ƿ���Ȩ�ޱ༭
			if (onlyread) {
				this.setReadonlyObject(listitem, true);// ������Ϊ���ɱ༭״̬
			} else {
				this.setReadonlyObject(listitem, false);// ������Ϊ�ɱ༭״̬
			}
		}

	}

	
	
}
