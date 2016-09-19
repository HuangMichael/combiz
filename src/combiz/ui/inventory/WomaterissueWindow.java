package combiz.ui.inventory;


import java.util.List;

import combiz.business.inventory.MatreqSrv;
import combiz.business.workorder.WorkorderSrv;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

public class WomaterissueWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public WomaterissueWindow() {
		super();
	}

	public void save() throws Exception {
		// TODO �Զ����ɷ������
		Workorder wo = (Workorder) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("invusertrans");
		List invl = (List) listwnd.getModifiedObjectList();
		if (invl.size() > 0) {

			for (int j = 0; j < invl.size(); j++) {
				Invusetrans invusetrans = (Invusetrans) invl.get(j);
				Long id = invusetrans.getId();
				String itemnum = invusetrans.getItemnum();
				List itemlist = this.getMainSrv().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
				if(itemlist.size()>0)
				{
					Item item = (Item) itemlist.get(0);
					String lottype = item.getLottype();
					if(lottype.equals("���ι���") && Util.isNull(invusetrans.getLotnum()))
					{
						Messagebox.show("�����Ŀ'"+itemnum+"'Ϊ���ι�������ʣ����ڷ�������ѡ�����ȷ�����ŵ����Σ�");
						return;
					}
				}
				
				String issuetype = invusetrans.getIssuetype();
				Double quantity = invusetrans.getQuantity();
				Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.wonum = '"
								+ invusetrans.getWonum()
								+ "' and t.itemnum = '"
								+ invusetrans.getItemnum() + "'and t.id <> "
								+ id + "");
				if (num == null)
					num = 0d;
				
				Double issueqty = (Double) this.mainSrv.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where wonum = '"+wo.getWonum()+"'and t.issuetype = '����' and t.state = '�����'");
				   if(issueqty == null)
					   issueqty =0d;
				   
				List wpmaterlist = this.mainSrv.getBaseDao().findWithQuery(
						Wpmaterial.class,
						"wonum = '" + invusetrans.getWonum()
								+ "' and itemnum = '"
								+ invusetrans.getItemnum() + "'");
				
				if (wpmaterlist.size() > 0) {
					Wpmaterial wpmaterial = (Wpmaterial) wpmaterlist.get(0);
					Double orderqty = wpmaterial.getItemqty();
					if (issuetype.equals("����")) {
						if (quantity <= 0 || (quantity + num) > orderqty) {
							Messagebox.show("���Ϊ��" + itemnum
									+ "�Ŀ����Ŀ�����������ڸ�Ԥ����\n�����߷�������Ϊ�㣬���ʵ��");
							return;
						}
					}
					else
					{
						quantity = -quantity;
						if(quantity>=0 || (quantity+issueqty)<0) 
						{
							Messagebox.show("�˿�������Ϊ�㣬�Ҳ������ѷ������������ʵ��");
							return;
						}
						
					}
						
				}
			}
		}
		super.save();
	}

	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�������з��Ų���ǰ�����ȱ����¼��");
			return;
		}
		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (wo.getStatus().equals("����׼")) {
			

			int count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invreserve t where t.wonum = '"
							+ wo.getWonum() + "'");
			if (count > 0) {
				Window listWnd = (Window) this.popupDialog(
						this.getMainObject(),
						"/inventory/woinvreservepopup.xul", "wonum='"
								+ wo.getWonum() + "'");
			} else {
				Messagebox.show("����'" + wo.getWonum()
						+ "'û�д����ŵ����ʣ��豸������ȷ�ϣ�");
				return;
			}
		
			
			
			/*CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
					.getMainObject(), "/inventory/invreservepopup.xul",
					"wonum='" + wo.getWonum() + "'");
			if (listWnd == null)
				return;

			// �ж��Ƿ�����ȷ����ť������ȡ����ť
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			System.out.println("retlist.size()="+retList.size());
			if (retList.size() < 1)
				throw new Exception("��ѡ��һ����¼��");
			((WorkorderSrv) this.getMainSrv()).geninvuse(retList, wo);
			this.refreshData();
			Messagebox.show("���ݳɹ����棬ȷ�Ϸ������������У����ɷ���.�������ȫ�����ţ����ڷ��������޸ķ�������!");*/
			
			
		} else
			// Messagebox.show("�ù���û����׼�����ܽ��з��Ų���");
			throw new Exception("�ù���û����׼�����ܽ��з��Ų���");
	}
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ�����Ԥ��
	 * ���ڣ�Oct 29, 2008 4:29:01 PM
	 *
	 */
	public void verify() throws Exception {

		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"wonum = '" + wo.getWonum() + "'and issuetype = '����' and state ='��ȷ��'");
		if (retList.size() == 0) {
			Messagebox.show("û�д�ȷ�ϵĽ��ռ�¼!");
		} else {
			((WorkorderSrv) this.getMainSrv()).verify(retList, wo);
			this.refreshData();
			Messagebox.show("��ɷ���!");

		}
	}

	public void returnissue() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ����˿����������");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���������˿����ǰ�����ȱ����¼��");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusertrans");
		List retList = listWnd.getSelectObjects();
		int size = retList.size();
		if (retList.size() > 0) {
			
			int sum = 0;
			for(int i = 0;i <retList.size();i++)
			{
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				if(invusetrans.getState().equals("�����") && invusetrans.getIssuetype().equals("����"))
				{
					sum++;
				}
			}
			if(retList.size() - sum !=0)
			{
				throw new Exception("ֻ���ڷ�������ѡ���Ѿ�������ȷ�ϵļ�¼�����˿����");
			}
			
			

			Workorder wo = (Workorder) this.mainObject;
			if (wo.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.wonum ='"
							+ wo.getWonum() + "'and issuetype = '����' and t.state = '�����'");

			if (count > 0) {
				/*
				 * CommonListWindow listWnd = (CommonListWindow)
				 * this.popupDialog(this .getMainObject(),
				 * "/inventory/invreservepopup.xul", "wonum='" + wo.getWonum() +
				 * "'");
				 */
				((WorkorderSrv) this.getMainSrv()).returnissue(retList, wo);
				listWnd.mulitpleSelect();
				this.refreshData();
				Messagebox
						.show("���ݳɹ����棬ȷ���˿���������󣬵���˿�У��.�������ȫ���˿⣬���ڷ��������޸��˿�����!");
			} else
				// Messagebox.show("�ù���û����׼�����ܽ��з��Ų���");
				throw new Exception("�ù�����δ���з��ϣ������˿�");
		} else
			throw new Exception("��ѡ���˿��¼");

	}
	
	public void verifyreturn() throws Exception {

		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"wonum = '" + wo.getWonum() + "' and issuetype = '�˻�' and state ='��ȷ��'");
		if (retList.size() == 0) {
			Messagebox.show("û�д�ȷ�ϵ��˿��¼!");
		} else {
			((WorkorderSrv) this.getMainSrv()).verify(retList, wo);
			this.refreshData();
			Messagebox.show("����˿�ȷ��!");

		}
	}
	

}
