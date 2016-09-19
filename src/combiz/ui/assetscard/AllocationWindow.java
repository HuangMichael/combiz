package combiz.ui.assetscard;

import java.util.Date;

import combiz.domain.assetscard.Allocation;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Reject;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class AllocationWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AllocationWindow()
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
		Allocation newobj = new Allocation();
		//����������Ӷ���ĳ�ʼ��ֵ
		newobj.setAllocationnum(this.genAutokey("allocationnum"));
		String labornum = this.getUserInfo().getLabornum();
		Labor labor = (Labor) this.getMainSrv().getBaseDao().findUniqueBy(Labor.class, "labornum", labornum);
		if(labor != null)
		{
			newobj.setFromsite(labor.getDeptnum());
			newobj.setSitenum(labor.getDeptnum());
		}
		newobj.setEnterby(labornum);
		newobj.setEnterdate(new Date());
		newobj.setStatus("�ݸ�");
		newobj.setStatusdate(new Date());
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ��ӵ���ص�Ĳֿ���ѡ�񱨷��ʲ���ϸ��
	 * ���ڣ�4:11:57 PM  May 13, 2010 
	 *
	 */
	public void addallocationline() throws Exception
	{
		Allocation allocation = (Allocation)this.mainObject;
		if (allocation.getId()==null) {
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
			Messagebox.show("�������ɵ�����ϸ��ǰ�������ݣ�");
			return;
		}
		
		this.popupDialog(this.getMainObject(), "/assetscard/equiplinepopup.xul", "location in(select t.warehouse from Warehouse t where t.sitenum='"+allocation.getFromsite()+"')");
		
	}
	
	
	/**
	 * ���ڹ������ϻس���ִ�й���������ʱ����
	 * ��������Լ����Զ�����������
	 * @throws Exception
	 * @��С��  2007-8-4  ����11:55:49
	 */
	public void search()
	throws Exception
	{
		this.getUISearchString();
		
		//����������Լ�����������
		//if(this.searchString.length()>0)
		//	this.searchString = searchString + " and �Լ�����������";
		//else
		//	this.searchString = " and �Լ�����������";
		
		//���»�ȡ�б�����
		this.getResultList(true);
	}
	
	/**
	 * �Զ���ӿ�
	 * �����Զ���Ĭ�ϵ�һ�εĲ�ѯ�������򿪽���ʱִ��һ�Σ��Ժ�Ĳ�ѯ������ִ�и�����
	 * brianhong  2009-6-16
	 * @return
	 * @throws Exception 
	 */
	public String getDefaultQueryString()
	throws Exception
	{
		return null;
	}

	/**
	 * ������̳У�Ӧ�ó���ӿ�
	 * ����Ļ������ʾ���������Ļ�ֶ���Ȩ֮ǰ
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @throws Exception
	 */
	public void initData() throws Exception
	{
		//����û��Լ��Ĵ���
	}

	
	/**
	 * �¼��ӿ��ࣺ�û����tabҳʱ����
	 * brianhong  2008-10-10
	 * @param selectedTabid
	 */
	public void onSelectedTab(String selectedTabid)
	throws Exception
	{
		//����û��Լ��Ĵ���
	}

	/**
	 * �����Tab��ǩʱ����ʼ��Tabpanel����ʱ����
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @param tabpanel
	 * @throws Exception
	 */
	public void onInitTabpanel(Tabpanel tabpanel) throws Exception
	{
		//����û��Լ��Ĵ���
	}


	/**
	 * �û��ӿ�
	 * �ڱ��淽��֮ǰ������
	 * ����true-ִ�б��涯��������false-������
	 * ��С��  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave()
	throws Exception
	{
		return true;
	}
	
	
	
	/**
	 * �û��ӿ�
	 * ���淽��ִ�к�����û��ӿ� 
	 * ��С��  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterSave()
	throws Exception
	{
		//����Լ��Ĵ���
	}
	
	
	/**
	 * ɾ������֮ǰ���Զ���ӿ�  20091103
	 * ��С��  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeDelete()
	throws Exception
	{
		return true;
	}
	
	
	
	/**
	 * ɾ������֮ǰ���Զ���ӿ�  20091103
	 * ��С��  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public void afterDelete()
	throws Exception
	{
		//����Լ��Ĵ���
	}
	
	
	/**
	 * �Զ���ӿ�
	 * ����������ֹʱ��״̬����true��false��ʾ�ӱ��Ƿ�ֻ��--ֻ�������Ѿ��رյ�����²ſ���ʹ�ø÷���
	 * ����true-�������е��ӱ�Ϊֻ��
	 * ����false-�����������ӱ�Ϊֻ��
	 * ��С��  Nov 14, 2009
	 * @return
	 */
	public boolean isWFStopStatus()
	{
		//ʾ����
		/**
		 * Workorder workorder = (Workorder)this.getMainObject();
		 * String status = workorder.getStatus();
		 * if(status!=null && (status.equal("�ѹر�") || status.equal("��ȡ��"))
		 * 	return true;
		 * else
		 *  return false;
		 */
		return super.isWFStopStatus();
	}
}
