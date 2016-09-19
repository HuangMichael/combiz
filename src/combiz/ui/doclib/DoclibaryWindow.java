package combiz.ui.doclib;

import combiz.business.doclib.DoclibarySrv;
import combiz.domain.doclib.Doclibary;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import java.io.File;
import java.util.Date;

import com.inbasis.zk.ui.Execution;
import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class DoclibaryWindow extends TMainWindow implements InfWindow {

	// ///////////////////////////
	// ///�����������Ϊ Classification
	// //
	// /////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public DoclibaryWindow() {
		super();
	}

	/**
	 * ������¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Doclibary newobj = new Doclibary();
		
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Doclibary doclibary = (Doclibary) ti.getValue();
			newobj.setParent(doclibary.getLibnum());
			newobj.setCreator(this.getLaborInfo().getLabornum());
			newobj.setCreatedate(new Date());
			newobj.setLibpath(doclibary.getLibpath());
		}
		else
		{
			newobj.setCreator(this.getLaborInfo().getLabornum());
			newobj.setCreatedate(new Date());
			newobj.setLibpath("/");
		}
		newobj.setHaschild("��");
		mainObject = newobj;
		
		return true;
	}

	/**
	 * @TODO �ֶ�����ʹ�ã��õ���Ŀ¼��libpathֵ
	 * @return ��Ŀ¼��libpathֵ
	 * yuanjq 2007-8-17 ����11:13:07
	 */ 
	public String getParentPathStr()
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Doclibary doclibary = (Doclibary) ti.getValue();
			return doclibary.getLibpath(); 
		}
		return "/";
	}
	/**
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2007-3-17
	 */
	public void delete() throws Exception {
		if (Messagebox.show("ȷ��ɾ����Ŀ¼�����µ������ĵ�������Ӧ�ó����ĵ��汾���ĵ�Ȩ�޵������Ϣ?", "��ʾ",
				Messagebox.YES | Messagebox.NO, Messagebox.INFORMATION) == Messagebox.NO) {
			return;
		}
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		Doclibary Doclibary = (Doclibary) ti.getValue();
		if (Doclibary != null) {
			// ����ҵ������ɾ������
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}
	}
}
