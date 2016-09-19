package combiz.ui.corp;
 
import combiz.domain.corp.Labor;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import com.inbasis.zul.Messagebox;


public class LaborWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public LaborWindow()
	{
		super();
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
		Labor newobj = new Labor();
		newobj.setPayrate(0.0D);
		newobj.setOtscale(0.0D);
		newobj.setEnabled("��");
		newobj.setOutside("��");
		newobj.setEmployeetype("��ʽԱ��");
		newobj.setNavmenutype("����");
		newobj.setCorpnum(this.getLaborInfo().getCorpnum());
		mainObject = newobj;
		return true;
	}
	
	
	@Override
	public void delete() throws Exception
	{
		Labor obj = (Labor) this.getMainObject();
		if(obj.getLabornum().equals("ADMIN"))
		{
			Messagebox.show("ADMIN��Ĭ��ϵͳ����Ա����ɾ����");
			return;
		}
		super.delete();
	}
}
