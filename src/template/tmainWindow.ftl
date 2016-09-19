package ${uipackage};

import ${mainObjectClass};
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import com.inbasis.zul.Tabpanel;

public class ${uiclassname} extends TMainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 */
	public ${uiclassname}()
	{
		super();
	}

	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		${ftablename} newobj = new ${ftablename}();
		//����������Ӷ���ĳ�ʼ��ֵ
		
		mainObject = newobj;
		return true;
	}
	

	
	/**
	 * �Զ���ӿ�
	 * �����Զ���Ĭ�ϵ�һ�εĲ�ѯ�������򿪽���ʱִ��һ�Σ��Ժ�Ĳ�ѯ������ִ�и�����
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
	 * @throws Exception
	 */
	public void initData() throws Exception
	{
		//����û��Լ��Ĵ���
	}

	
	/**
	 * �¼��ӿ��ࣺ�û����tabҳʱ����
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
	 * @throws Exception
	 */
	public void afterSave()
	throws Exception
	{
		//����Լ��Ĵ���
	}
	
	
	/**
	 * ɾ������֮ǰ���Զ���ӿ�  20091103
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
