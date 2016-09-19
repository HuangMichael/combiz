package combiz.ui.inventory;
 
import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

import combiz.business.inventory.MatreqSrv;
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


public class ProvideWindow extends MainWindow
implements InfWindow
{	

	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ProvideWindow()
	{
		super();
	}
	
	
	/**
	 * 
	 * brianhong  2007-11-13
	 */
	public void selectMRLine()
	{
		
	}

	public void save() throws Exception {
		// TODO �Զ����ɷ������
		super.save();
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����ɽ��÷�����
	 * ���ڣ�Nov 4, 2008 10:38:45 AM
	 *
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���÷��Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();
		
		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		String status = matreq.getStatus();
		if (Util.isNotNull(status) && status.equals("����׼")) {
			int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Invreserve t where t.reqnum = '" + matreq.getMatreqnum() + "'");
			if(count > 0)
			{
			Window listWnd = (Window) this.popupDialog(this
					.getMainObject(), "/inventory/eqinvreservepopup.xul",
					"reqnum='" + matreq.getMatreqnum() + "'");
			}
			else
			{
				throw new Exception("û��Ԥ����¼�����ʵ��");
			}
		}
		else
		{
			throw new Exception("��������'"+matreq.getMatreqnum()+"'û����׼�����ܽ��з���");
		}
		
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����ʺ���ת�����ʲ�����ȷ�Ϸ���
	 * ���ڣ�Nov 4, 2008 10:38:59 AM
	 *
	 */
	public void verify() throws Exception {

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		
		ListWindow listWnd = (ListWindow) this.getFellow("eqbrowissue");
		boolean ismultiple = listWnd.isMultiple();
		List retList = null;
		List selectList = null;
		Boolean toverify = null;
		if(ismultiple == true)//�û��Ƿ���ѡ��
		{
	    	if(Messagebox.show("�Ƿ�У��ѡ�еķ����У�","��ȷ�ϣ�",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
	    		toverify = true;
			else
				toverify = false;
	    	if(toverify)
			{
	    		selectList = listWnd.getSelectObjects();
				for(int i=0;i<selectList.size();i++)
				{
					Invusetrans invu = (Invusetrans) selectList.get(i);
					if(invu.getState().equals("��ȷ��"))
					{
						retList.add(invu);
					}
					
				}
				if(retList==null);
				{
					throw new Exception("û�д�ȷ�ϵĽ��ռ�¼!");
				}
			}
	    	else
	    	{
	    		return;
	    	}
			
		}
		else
		{
			if(Messagebox.show("�Ƿ�ȷ�ϸý�������������δȷ�ϵķ����У�","��ȷ�ϣ�",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
	    		toverify = true;
			else
				toverify = false;
	    	if(toverify)
			{
	    		retList = this.getMainSrv().getBaseDao().findWithQuery(Invusetrans.class,"matreqnum = '" + matreq.getMatreqnum() + "' and state ='��ȷ��'");
			}
	    	else
	    	{
	    		return;
	    	}
			
			
		}
		if (retList.size() == 0) 
		{
			Messagebox.show("û�д�ȷ�ϵķ��ż�¼!");
		} 
		else 
		{
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("��ɷ���!");
		}
	}
	
	
	

	
	
}
