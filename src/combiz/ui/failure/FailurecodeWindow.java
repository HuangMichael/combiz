package combiz.ui.failure;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

import combiz.domain.classattr.Classification;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

public class FailurecodeWindow extends TMainWindow implements InfWindow {
	public FailurecodeWindow() {
		super();
	}

	public boolean addNew() throws Exception {
		Classification newobj = new Classification();
		newobj.setClasstype("���ϴ���");
		newobj.setHaschild("��");
		Treeitem ti = mainTree.getSelectedItem();
		if (ti != null && ti.getValue() != null) {
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}

		mainObject = newobj;

		return true;
	}

	public void delete() throws Exception {
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null) {
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		if (classification != null) {
			String classid = classification.getClassid();
			String sql = "select count(a.id) from Failurecode a where a.classid ='"+classid+"'";
			
			int getpm = this.getMainSrv().getBaseDao().selectCountByHql(sql);
			if (getpm >0) {
				Messagebox.show("�÷����ѹ������ݣ�����ɾ���÷��࣡");
				return;
			}
			// ����ҵ������ɾ������
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}
	}
}
