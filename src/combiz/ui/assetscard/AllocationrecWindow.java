package combiz.ui.assetscard;

import java.util.List;

import combiz.business.assetscard.AllocationSrv;
import combiz.business.po.PoSrv;
import combiz.domain.assetscard.Allocation;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class AllocationrecWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AllocationrecWindow()
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

		mainObject = newobj;
		return true;
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


	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ��ӵ����������������ݵ��������������ɽ��ա�
	 * ���ڣ�11:10:40 AM  May 18, 2010 
	 *
	 */
	public void addallocline() throws Exception {

		Allocation allocation = (Allocation) this.mainObject;

		if (allocation.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (allocation.getStatus().equals("����׼")) {
			CommonListWindow listWnd = (CommonListWindow) this
			.popupDialog(
					this.getMainObject(),
					"/assetscard/allocationlinelist.xul",
					"allocationnum='"
					+ allocation.getAllocationnum()
					+ "' and eqnum not in(select i.eqnum from Invrectrans i where i.ponum = '"+allocation.getAllocationnum()+"')");
			if (listWnd == null)
				return;

			// �ж��Ƿ�����ȷ����ť������ȡ����ť
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((AllocationSrv) this.getMainSrv()).addallocline(retList, allocation);
			this.refreshData();
			Messagebox.show("���ݳɹ�����!");
		} else
			Messagebox.show("�õ������뵥û����׼�����ܽ��н���");

		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

	}


	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ��Ե��������н���У��
	 * ���ڣ�2:32:55 PM  May 18, 2010 
	 *
	 */
	public void verify() throws Exception {

		Allocation allocation = (Allocation) this.mainObject;

		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invrectrans.class,
				"ponum = '" + allocation.getAllocationnum() + "' and status ='������'");
		if (retList.size() == 0)
		{
			Messagebox.show("û����Ҫ����ĵ������ռ�¼!");
		} 
		else 
		{

			Boolean besure = null;
			if(Messagebox.show("�����,�����޸ļ�¼!��ȷ��'���ղֿ�'����?","��ʾ!!!",Messagebox.YES|Messagebox.NO,"") == Messagebox.YES)
				besure = true;
			else
				besure = false;
			if(besure)
			{
				((AllocationSrv) this.getMainSrv()).verify(retList, allocation);
				this.refreshData();
				Messagebox.show("�Ѿ���ɼ���!");
			}
			else
			{
				return;
			}
		}
	}
}
