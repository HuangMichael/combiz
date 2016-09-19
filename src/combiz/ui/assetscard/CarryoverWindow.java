package combiz.ui.assetscard;

import combiz.business.assetscard.CarryoverSrv;
import combiz.domain.assetscard.Carryover;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;

public class CarryoverWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CarryoverWindow()
	{
		super();
	}

	
	/**
	 * @author ��ΰ ���ܣ�����ת
	 * @throws Exception
	 *             2010-05-06����17:22
	 */
	public void uncarryover() throws Exception {
				
		Carryover carryover = (Carryover) this.getSelectObject();
	
		List eqlist = this.getSelectObjects();
		if(eqlist.size()==0 )
		{
			Messagebox.show("��ѡ������һ����¼");
			return;
		}
		if(!carryover.getStatus().equals("δ��ת"))
		{
			Messagebox.show("���·��Ѿ���ת����ѡ��һ��δ��ת�ļ�¼");
			return;
		}
		
		else
		{
			((CarryoverSrv) this.getMainSrv()).copyuncarryover(carryover);
			this.clear();
			Messagebox.show("����ת�ɹ���");
		}
	  	
	
	
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
	
	}


	/**
	 * @author ��ΰ ���ܣ����豸̨�˵Ĳ�����Ϣ�������̶��ʲ���ȥ
	 * @throws Exception
	 *             2010-05-06����17:22
	 */
	public void carryover() throws Exception {
				
		Carryover carryover = (Carryover) this.mainObject;
		List eqlist = this.getSelectObjects();
		if(eqlist.size()==0 )
		{
			Messagebox.show("��ѡ������һ����¼");
			return;
		}
		if(eqlist.size()!=0 && carryover.getStatus()=="�ѽ�ת")
		{
			Messagebox.show("���·��ѽ�ת");
			return;
		}
		
		else
		{
			((CarryoverSrv) this.getMainSrv()).copycarryover();
			this.clear();
			Messagebox.show("��ת�ɹ���");
		}
	  	
	
	
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
	
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
		Carryover newobj = new Carryover();
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
	 * �����Զ����ѯ����
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
