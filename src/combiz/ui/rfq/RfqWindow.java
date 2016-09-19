package combiz.ui.rfq;
 
import combiz.business.rfq.RfqSrv;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.TableList;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;


public class RfqWindow extends MainWindow
implements InfWindow
{

	public RfqWindow()
	{
		super();
	}

	
	/**
	 * 
	 * @TODO �½�һ��ѯ�۵�
	 * @throws Exception
	 * @����� 2007-8-13 ����11:50:22
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Rfq rfq = new Rfq();
		//int count = this.mainSrv.getRowCountByWhere(rfq, "");
		rfq.setRfqnum(this.genAutokey("rfqnum"));
		rfq.setStatus("����׼");
		rfq.setStatusdate(new Date());

		mainObject = rfq;
		return true;
	}

	/**
	 * 
	 * @TODO ͨ��ѯ�۹�Ӧ�̺�ѯ�۵������ɱ�����
	 * @throws Exception
	 * @����� 2007-8-20 ����10:52:12
	 */
	public void createquot() throws Exception{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"'");
		
		if(count >0)
		{
			throw new Exception("�Ѿ���ѯ�۵�'"+rfq.getRfqnum()+"'���ɱ����У������ظ����ɱ����У�");
		}
		((RfqSrv)this.mainSrv).createquot(this.getMainObject());
		this.setMainData();
		this.refreshData();
		Messagebox.show("�ɹ����ɱ�����");
	}
	/* 
	 * ���ܣ�ͨ��ѯ�۹�Ӧ�̼���Ӧ�ı��������ɲɹ����Ͳɹ�����
	 * ���ߣ����
	 * ���ڣ�Sep 17, 2008 4:33:54 PM
	 */
	public void createpo()
	throws Exception
	{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"'");
		if(count == 0)
		{
			throw new Exception("ѯ�۵�'"+rfq.getRfqnum()+"'��û�б����У��������ɲɹ�����");
		}
		//ȷ��
		int rtn = Messagebox.show("�Ƿ�ȷ������ѡ�еĹ�Ӧ�����ɶ�����","�Ƿ�ȷ����",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		/*ListWindow listWnd = (ListWindow) this.getFellow("rfqvendor");
		List retList = listWnd.getSelectObjects();
		*/
		
		List retList = this.getMainSrv().getBaseDao().findWithQuery(Rfqvendor.class, "rfqnum='"+rfq.getRfqnum()+"'");
		if(retList.size() == 0)
		{
			Messagebox.show("��ѡ��Ҫ���ɶ����Ĺ�Ӧ�̣�");
			return;
		}
		((RfqSrv)this.mainSrv).CreatePo(retList,rfq);
		this.setMainData();
		Messagebox.show("�ɹ����ɲɹ����Ͳɹ���!");
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���������ı��������ɺ�ͬ��
	 * ���ڣ�Oct 24, 2008 3:55:49 PM
	 *
	 */
	public void createcont()
	throws Exception
	{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"' and t.isawarded ='��'");
		if(count == 0)
		{
			throw new Exception("ѯ�۵�'"+rfq.getRfqnum()+"'��û������ı����У��������ɺ�ͬ��");
		}
		//ȷ��
		int rtn = Messagebox.show("�Ƿ�������ı��������ɺ�ͬ��","�Ƿ�ȷ����",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		List rvlist = this.getMainSrv().getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rfq.getRfqnum()+"' and isawarded ='��'");
		List vendorlist = this.getMainSrv().getBaseDao().selectListBySql("select t.vendor from Rfqquoteline t  where t.rfqnum = '"+rfq.getRfqnum()+"' and t.isawarded ='��' group by t.vendor");
		if(rvlist.size()>0 && vendorlist.size()>0)
		{
			((RfqSrv)this.mainSrv).createcont(rvlist,vendorlist,rfq);
			this.setMainData();
			Messagebox.show("�ɹ����ɺ�ͬ�ͺ�ͬ��!");
		}
		
	}
	
	
	/**
	 * 
	 * ͨ����Ӧ�̼���Ӧ�̶�Ӧ�ı��������ɲɹ����Ͳɹ�����
	 * @throws Exception
	 * @����� 2007-8-20 ����11:02:20
	 */
/*	public void CreatePo()
	throws Exception
	{
		//ȷ��
		int rtn = Messagebox.show("�Ƿ�ȷ������ѡ�еĹ�Ӧ�����ɶ�����","�Ƿ�ȷ����",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		Rfq rfq = (Rfq)this.getMainObject();
		//ͨ����ϵ����ȡlistWindwo
		ListWindow listWnd = (ListWindow) this.getFellow("rfqvendor1");
		//�õ�ѡ�е�ĳһ����¼;
		Rfqvendor rv = (Rfqvendor)listWnd.getMainObject();
		
		String ponum = ((RfqSrv)this.mainSrv).CreatePo(rv,rfq);
		this.setMainData();
		Messagebox.show("�ɹ����ɲɹ����Ͳɹ���--��Ӧ���"+ponum);
	}*/
	/**
	 * 
	 * @TODO �����ɹ������У�����һ�����壬����Ϊ���еĲɹ������С��������û�ѡ�����е�һ��������
	 * @throws Exception
	 * @����� 2007-8-24 ����03:16:24
	 */
	public void copypr() throws Exception{
		Rfq rfq = (Rfq)this.getMainObject();
//		����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this.getMainObject(), "/pr/prlineList.xul","rfqnum is null and rfqlinenum is null");
		
		if(listWnd==null)
			return;
		
		//�ж��Ƿ�����ȷ����ť������ȡ����ť
		if(!listWnd.isConfirm())
			return;
		
		//TableList tabblelist = (TableList) listWnd.getFellow(listWnd.getId()+"List");
		
		List retList = listWnd.getSelectObjects();
		((RfqSrv)this.getMainSrv()).CopyPrline( rfq,retList);
		this.refreshData();
		Messagebox.show("�ɹ������ɹ�������!");
	}
	
}
