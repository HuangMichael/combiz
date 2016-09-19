package combiz.ui.location;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class AssetClassWindow extends TMainWindow implements InfWindow {

	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AssetClassWindow() {
		super();
	}

	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Classification newobj = new Classification();
		newobj.setClasstype("�ʲ�");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		newobj.setHaschild("��");
		mainObject = newobj;

		return true;
	}

	/**
	 * 
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-3-17
	 */
	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			//����ҵ������ɾ������
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}
	}

	public void technicunit() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog(this.getMainObject(), "/location/technicunit.xul", "");

	}
}
