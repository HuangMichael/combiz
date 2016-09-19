package combiz.ui.tool;
 
import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.business.tool.ToolSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Tooltrans;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.TMainWindow;


/**
 * @author ljhadministrator
 *
 */
public class ToolWindow extends TMainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public ToolWindow()
	{
		super();
	}

	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ�ljh ���ڣ�2008-03-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		
		Classification newobj = new Classification();
		newobj.setClasstype("����");
		newobj.setHaschild("��");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}

		mainObject = newobj;
		return true;
	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ����Ź���
	 * ���ڣ�2008-4-12 ����09:38:01
	 *
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���߲������Ų���ǰ�����ȱ����Ѿ����ŵļ�¼��");
			return;
		}
		//Classification classification = (Classification) this.mainObject;
		ListWindow toolwnd = (ListWindow) this.getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("��ѡ��һ����¼!");
		
			this.popupDialog(this
					.getMainObject(), "/tool/toolreservepopup.xul",
					"toolnum = '"+tool.getToolnum()+"'");
			this.refreshChildData();
		
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ��黹����
	 * ���ڣ�2008-4-12 ����09:37:43
	 *
	 */
	public void verify() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��з��Ų�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�黹���߲���ǰ�����ȱ����ϴβ����ļ�¼��");
			return;
		}
		ListWindow toolwnd = (ListWindow) this.getFellow("toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();
		
		if (!(tool instanceof Tool))
			throw new Exception("��ѡ��һ����¼!");
		
		this.popupDialog(this
				.getMainObject(), "/tool/toolreturnpopup.xul",
				"toolnum = '"+tool.getToolnum()+"'");
		this.refreshChildData();
		
	}

	
	
	/* (non-Javadoc)
	 * @see combiz.system.ui.TMainWindow#delete()
	 */
	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			// ����ҵ������ɾ������
			String sql = "select count(t.id) from Tool t where t.classid in (select c.classid from Classance c where c.ancestor='"+classification.getClassid()+"' and c.classtype = '����')";
			
			int count = this.getMainSrv().getBaseDao().selectCountByHql(sql);
			if (count >0) {
				Messagebox.show("�÷����ѹ������ݣ�����ɾ���÷��࣡");
				return;
			}
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}
	}

}
