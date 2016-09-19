package combiz.ui.inventory;

import combiz.domain.inventory.Invrectrans;
import combiz.system.ui.ListWindow;

import com.inbasis.zul.Listitem;

public class InvrectransList2 extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvrectransList2() {
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * 
	 * @throws Exception
	 *             ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew() throws Exception {
		// ��ȡ�������������
//		PoWindow parentWnd = (PoWindow) ownerWnd;
//		Po po = (Po)parentWnd.getMainObject();
		//Inventory parent = (Inventory) parentWnd.getMainObject();
//		Listwindow invrectrans = this.getFellow("invrectrans");

		Invrectrans newobj = new Invrectrans();
//		newobj.setPonum()
//		newobj.setItemnum(parent.getItemnum());
//		newobj.setTowarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}

	

	@Override
	public void save() throws Exception {
		// TODO �Զ����ɷ������
		super.save();
	}

	@Override
//	���ݽ����еĽ��ռ�¼��״̬������ֶ����ж��Ƿ�Ϊ�ɱ༭״̬��
	public void onRowSelected() {
		// TODO �Զ����ɷ������
		
		ListWindow listWnd = (ListWindow) this.getFellow("invrectrans");
		Invrectrans invrectrans = (Invrectrans) this.getMainObject();
		if(invrectrans.getStatus().equals("�Ѽ���") ){
			listWnd.setReadonlyList(true);
		}
		else
		{
			listWnd.setReadonlyList(false);
		}
		super.onRowSelected();
	}

	@Override
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		Invrectrans invrectrans = (Invrectrans) arg1;
		
		if (invrectrans.getStatus().equals("������") || invrectrans.getStatus().equals("�����")) {
			this.setReadonlyObject(arg0, false);
		}else{
			this.setReadonlyObject(arg0, true);
		}
		super.initRowData(arg0, arg1);
	}
	

}
