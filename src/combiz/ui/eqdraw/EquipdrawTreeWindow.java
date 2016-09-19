package combiz.ui.eqdraw;

import combiz.domain.doclib.Docversion;
import combiz.domain.eqdraw.Equipdraw;
import combiz.system.eqdraw.DocversionList;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Iframe;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Tabpanel;
import com.inbasis.zul.Window;

public class EquipdrawTreeWindow extends TMainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public EquipdrawTreeWindow()
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
		Equipdraw newobj = new Equipdraw();
		//����������Ӷ���ĳ�ʼ��ֵ
		newobj.setDrawnum(this.genAutokey("drawnum"));
		newobj.setCreateby(this.getLaborInfo().getLabornum());
		newobj.setCreatedate(new Date());
		newobj.setPosition(0L);
		
		mainObject = newobj;
		return true;
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
		/*if(this.objStatus == this.ADDED)
		{*/
			Equipdraw eqdraw = (Equipdraw) this.getMainObject();
			//String doclibroot = combiz.system.IBSServer.getIBSServer().getDoclibroot();
			String fileName = java.io.File.separator + "eqdraw" + 
				java.io.File.separator + "project" + java.io.File.separator + eqdraw.getDrawnum() + ".ibx";
			eqdraw.setDrawpath(fileName);
		//}
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
	 * ��ͼ�α༭��
	 * brianhong  2007-11-1
	 * @throws Exception
	 */
	public void redraw()
	throws Exception
	{
		String userid = this.getUserInfo().getUserid();
		String labornum = this.getUserInfo().getLabornum();
		Equipdraw eqdraw = (Equipdraw) this.getMainObject();
		
		String doclibroot = combiz.system.IBSServer.getIBSServer().getDoclibroot();
		String fileName = doclibroot + java.io.File.separator + "eqdraw" + 
			java.io.File.separator + "project" + java.io.File.separator + eqdraw.getDrawnum() + ".ibx";
		if(fileName==null)
			fileName = "";
		else
			fileName = java.net.URLEncoder.encode(fileName);

		Iframe hiframe = (Iframe) this.getFellow("hiddenIframe");
		hiframe.setSrc("/EqdrawWebStart?fileName="+fileName);
		hiframe.invalidate();
	}
	
	/**
	 * ɾ��ͼԪ��
	 * brianhong  2007-11-6
	 */
	public void deldrawlib()
	{
		Map param= new HashMap();
		Window cp = (Window)Executions.createComponents("/eqdraw/drawlibpopup.xul", null, param);
		try {
			cp.doModal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * ����JRE��װ�ļ�
	 * brianhong  2007-11-1
	 */
	public void downloadJRE()
	{
		Iframe hiframe = (Iframe) this.getFellow("hiddenIframe");
		hiframe.setSrc("/system/jre-1_5_0-windows-i586.exe");
		hiframe.invalidate();
	}
}
