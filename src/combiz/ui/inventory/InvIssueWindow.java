package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.MatreqSrv;
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


public class InvIssueWindow extends MainWindow
implements InfWindow
{		
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InvIssueWindow()
	{
		super();
	}


	/**
	 * 
	 * brianhong  2007-11-13
	 */
	public void selectWMatLine()
	{

	}

	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�����Ŀǰѡ�е�����ISSUSESEL
	 * ���ڣ�Oct 6, 2008 2:35:42 PM
	 *
	 */
	public void issusesel() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�����������Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();
		Double qtycount = 0D;//����ѡ����н�������

		if (retList.size() > 0) {
			Invusetrans invusetrans = null;
			int a = retList.size();
			int sum = 0;
			for(int i = 0;i < a;i++)
			{
				invusetrans = (Invusetrans) retList.get(i);
				if (invusetrans.getIssuetype().equals("δ�ύ")) {
					qtycount = qtycount + invusetrans.getQuantity();
					sum++;
				}else{
					throw new Exception("ֻ��ѡ��δ�ύ�������У�");
				}

			}
			if( retList.size() - sum<0)
			{
				throw new Exception("ֻ���ڷ�������ѡ��״̬Ϊδ�ύ���н��з��Ų���");
			}

			Warehouse warehouse = (Warehouse) this.mainObject;
			//��ѯ����ǰ�������
			Integer curbal = this.getMainSrv().getBaseDao().selectCountByHql("select count(t.curbal) from Invstock t where t.warehouse='"+invusetrans.getWarehouse()+"' and t.itemnum='"+invusetrans.getItemnum()+"'");

			if ((curbal-qtycount)<0) {
				throw new Exception("����������㣬���Ȳɹ���");
			}
			//((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			((WarehouseSrv) this.getMainSrv()).verify(retList, warehouse);
			Messagebox.show("������ɣ�");

		} else
			throw new Exception("��ѡ��Ҫ���ŵ��У�");

	}


	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�����Ŀǰ����δ�ύ��ISSUEALL
	 * ���ڣ�Oct 6, 2008 2:36:45 PM
	 *
	 */
	public void issueall() throws Exception {
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ϲ������Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		Warehouse warehouse = (Warehouse) this.mainObject;
		int rtn = Messagebox.show("�Ƿ�ȷ������Ŀǰ����δ�ύ�У�","���ţ�",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			if (warehouse.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			String labornum = this.getLaborInfo().getLabornum();
			Double curbal = 0D;//�������
			Double reqcount = 0D;//��������
			// ��֤�Ƿ���δ�ύ����,����ֻ�ܷ����Լ���ӵ���,״̬Ϊδ�ύ
			Integer selectall = this.getMainSrv().getBaseDao().selectCountByHql("select count(t.quantity) from Invusetrans t" +
					" where t.warehouse='"+warehouse.getWarehouse()+"' "
					+"and t.issuetype='����' and t.state='��ȷ��' "
					+"and t.enterby='"+labornum+"'");
			if (selectall <=0) {
				throw new Exception("û����Ҫ���ŵĴ�ȷ�ϼ�¼�����ʵ��");
			}
			List retlist = null;
			retlist = this.getMainSrv().getBaseDao().findWithQuery(Invusetrans.class, " issuetype='����' and state='��ȷ��' and warehouse ='"+
					warehouse.getWarehouse()+"' and enterby='"+labornum+"'");
			if (retlist.size()>0) {
				for (int n=0;n<retlist.size();n++) {
					Invusetrans invusetrans = (Invusetrans) retlist.get(n);
					//�õ��������з�����Ҫ�������������жϿ�������Ƿ����������ж�����Ϊ����ͬһ�ֿ�ͬһ�������Ʒ����
					String binnum = invusetrans.getBinnum();
					if(Util.isNotNull(binnum))
					{
						curbal = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum='"+invusetrans.getItemnum()+"' and t.warehouse='"+invusetrans.getWarehouse()+"' and binnum = '"+invusetrans.getBinnum()+"'");
					}
					else
					{
						curbal = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum='"+invusetrans.getItemnum()+"' and t.warehouse='"+invusetrans.getWarehouse()+"' and binnum is null");
					}

					if(curbal-invusetrans.getQuantity()<0)
					{
						throw new Exception("���'"+invusetrans.getItemnum()+"'�������㣬��������ɹ��������档");
					}

				}
			}else{
				throw new Exception("��ǰû�������Է��ŵ��У����ʵ��");
			}
			
			MatreqSrv matreqSrv = (MatreqSrv)IBOSrvUtil.getSrv("matreq");
			matreqSrv.verify(retlist, warehouse);
			this.refreshData();
			this.refreshChildData();
			Messagebox
			.show("��ɷ���!");}
	}

	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ��˿�Ŀǰѡ�е�����RETURNSEL
	 * ���ڣ�Oct 6, 2008 2:37:48 PM
	 *
	 */
	public void returnsel() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ϲ������Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();

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
			if( retList.size() - sum<0)
			{
				throw new Exception("ֻ���ڷ�������ѡ���Ѿ���������ȷ�Ϲ��ļ�¼�����˿����");
			}


			Matreq matreq = (Matreq) this.mainObject;
			if (matreq.getId()==null) {
				Messagebox.show("������ѡ��һ����¼��");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.matreqnum ='"
					+ matreq.getMatreqnum() + "'and t.issuetype = '����' and t.state = '�����'");

			if (count > 0) {
				/*
				 * CommonListWindow listWnd = (CommonListWindow)
				 * this.popupDialog(this .getMainObject(),
				 * "/inventory/invreservepopup.xul", "wonum='" + wo.getWonum() +
				 * "'");
				 */
				((MatreqSrv) this.getMainSrv()).returnissue(retList, matreq);
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


}
