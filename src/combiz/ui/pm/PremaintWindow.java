package combiz.ui.pm;
 
import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.pm.PremaintSrv;
import combiz.domain.corp.Labor;
import combiz.domain.pm.Premaint;
import combiz.domain.user.Ibsgroupusers;
import combiz.domain.user.Ibsusers;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICheckbox;
import combiz.system.ui.common.ICombobox;
import combiz.system.ui.common.ITextbox;
import combiz.system.util.Util;


public class PremaintWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public PremaintWindow()
	{
		super();
	}
	
	/* 
	 * ���ܣ��ж�λ�ú��ʲ��Ƿ�ͬʱΪ��
	 * ���ߣ�����
	 * ���ڣ�2008-3-19����08:16:20
	 */
	public void save() throws Exception {
		// TODO �Զ����ɷ������
		Premaint premaint = (Premaint) this.getMainObject();
		IBandbox arg0 =  (IBandbox) this.getFellow("premaint.location");
		IBandbox arg1 = (IBandbox) this.getFellow("premaint.eqnum");
		ICheckbox arg2 = (ICheckbox) this.getFellow("premaint.usejpseq");
		IBandbox arg3 =  (IBandbox) this.getFellow("premaint.jpnum");
		String eqnum = arg1.getValue();
		String location = arg0.getValue();
		String jpnum = arg3.getValue();
		String usejpseq = arg2.getValue();
		if(!Util.isNotNull(eqnum) && !Util.isNotNull(location))
		{
			Messagebox.show("λ�ú��豸��Ų���ͬʱΪ�գ��벹����Ϣ");
			return;			
		}
		if(!Util.isNotNull(jpnum) && usejpseq.equals("��"))
		{
			Messagebox.show("��ѡ��ʹ�ñ�׼��ҵ�ƻ���������'��׼��ҵ�ƻ�'�ֶΣ�");
			return;
		}
		
		
		
	
		
		super.save();
	}
	

	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Premaint newobj = new Premaint();
		newobj.setPmcounter(0L);
		newobj.setHaschild("��");
		newobj.setAdjnextdue("��");
		newobj.setAutowf("��");
		newobj.setUsefrequency("��");
		newobj.setFrequnit("��");
		newobj.setEqdown("��");
		newobj.setAutowf("��");
		newobj.setUsejpseq("��");
		newobj.setWostatus("����δ����");
//		String labornum = this.getUserInfo().getLabornum();
//		List laborlist = this.getMainSrv().findWithQuery(Labor.class,"labornum ='" + labornum+ "'");
//		Ibsusers ibsusers = (Ibsusers) this.getDesktop().getSession().getAttribute("userinfo");//��ȡ��ǰ��¼�û���
		
		
		Labor labor =(Labor)this.getDesktop().getSession().getAttribute("laborinfo");//��ȡ��ǰ��¼��Ӧ����Ա��
		String  sitenum = labor.getSitenum();
		
		String pmnum = this.genAutokey("pmnum");
		if (Util.isNotNull(sitenum)) {
			pmnum = sitenum + " - " + pmnum;
		}
		else {
			sitenum = "ADMIN";
			pmnum = "admin-" + pmnum;
		}
		newobj.setPmnum(pmnum);
		mainObject = newobj;
		return true;
	}

	
	
	/**
	 * ���ɹ���
	 * @throws Exception
	 * hyf
	 */
	public void workorder() throws Exception {
		if (this.getObjStatus() == this.ADDED|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��п���������");
			return;
		}
		Premaint premaint = (Premaint)this.getMainObject();
		String description = premaint.getDescription();
		//����ҵ����ķ���
		((PremaintSrv)this.getMainSrv()).createWorkorder(premaint,description);
		Messagebox.show("������ɣ������ƣ�");
		this.refreshData();
	}

}
