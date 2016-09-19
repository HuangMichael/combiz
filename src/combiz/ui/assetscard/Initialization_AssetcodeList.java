package combiz.ui.assetscard;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.system.ui.ListWindow;
import com.inbasis.zul.Listitem;

public class Initialization_AssetcodeList extends ListWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public Initialization_AssetcodeList()
	{
		super();
	}

	
	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * @throws Exception 
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//��ȡ�������������
		Assetscard parent = (Assetscard) this.getOwnerWnd().getMainObject();
		
		Assetscardline newobj= new Assetscardline();
		//newobj.setXXXX(parent.getXXXX()); //������ӹ����ֶ�ֵ

		newobj.setAssetcode(parent.getAssetcode());
		newobj.setStatus("δ��ת");
		this.mainObject = newobj;
		return true;
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
	 * ��ʼ��һ��������ʱ����û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @param listitem  �����пؼ�
	 * @param objtmp  �����ж�Ӧ��������ֵ
	 * @throws Exception
	 */
	public void initRowData(Listitem listitem, Object objtmp) throws Exception
	{
		//����Զ������
	}
	
	/**
	 * �б���ѡ����ĳ����¼�����ø÷���
	 * brianhong  2007-11-2
	 */
	public void onRowSelected()
	{
		//����Զ������
	}


	/**
	 * �ڱ��水ť������ڱ��涯��ִ��֮����û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterSave() throws Exception
	{
		//����Զ������
	}

	/**
	 * �ڱ��水ť������ڱ��涯��ִ��ǰ���û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave() throws Exception
	{
		return true;
	}



	/**
	 * ��ɾ���а�ť�������ɾ������ִ��ǰ���û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @return
	 * @throws Exception
	 */
	public boolean beforeRemove() throws Exception
	{
		return true;
	}
	
	/**
	 * ��ɾ���а�ť�������ɾ����Ƕ���ִ��֮����û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @throws Exception
	 */
	public void afterRemove() throws Exception
	{
		//����Զ������
	}
	
}
