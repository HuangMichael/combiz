package combiz.ui.inventory;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.inventory.RejectSrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Reject;
import combiz.domain.inventory.Rejectitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
public class RejectWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public RejectWindow()
	{
		super();
	}

	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Reject newobj = new Reject();
		//����������Ӷ���ĳ�ʼ��ֵ
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String rejectnum = sitenum + "_" +this.genAutokey("rejectnum");
		newobj.setRejectnum(rejectnum);
		newobj.setRejectdate(new Date());
		newobj.setRequestby(labornum);
		newobj.setStatusdate(new Date());
		newobj.setStatus("����δ����");
		mainObject = newobj;
		return true;
	}
	
	/**
	 * ������ѡ������Ҫ���ϵĿ����Ŀ�����ɱ�����ϸ�У�
	 * 
	 * ���ߣ����� 
	 * ���ܣ�
	 * ���ڣ�Oct 20, 2008 6:16:10 PM
	 *
	 */
	public void createdel() throws Exception
	{
		Reject reject = (Reject)this.mainObject;
		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("�������ɱ�����ϸ��ǰ�������ݣ�");
			return;
		}
		this.popupDialog(this.getMainObject(), "/inventory/rejinvstockpopup.xul","itemnum in(select t.itemnum from Item t where t.rotating='��') and curbal > 0 and warehouse not like '%���Ͽ�%'");
		
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����б�����׼�����������еı�����ϸת�Ƶ����Ͽ��
	 * ���ڣ�Oct 21, 2008 11:52:54 AM
	 *
	 */
	public void verify() throws Exception {

		Reject reject = (Reject) this.mainObject;

		if (reject.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Rejectitem.class,
				"rejectnum = '" + reject.getRejectnum() + "'");
		if (retList.size() == 0) {
			Messagebox.show("û��Ҫ��׼�ı��ϼ�¼!");
		} else {
			((RejectSrv) this.getMainSrv()).verify(retList);
			this.refreshData();
			Messagebox.show("�Ѿ���ɱ���!");

		}
	}


	
	
	
}
