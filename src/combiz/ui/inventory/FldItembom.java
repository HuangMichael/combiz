package combiz.ui.inventory;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

import combiz.domain.inventory.Itemeqbom;
import combiz.system.FieldAdapter;

public class FldItembom extends FieldAdapter {

	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Component arg0) throws Exception {
		// ���������
		Itemeqbom itemeqbom = (Itemeqbom) this.mainObject;
		String parent = itemeqbom.getParent();
		String itemnum =(String) this.getValueByColname("itemnum");
		if (parent.equals(itemnum)) {
			this.setValueByColname("itemnum", "");
			Messagebox.show("���ϲ���Ϊ�Լ��ı���BOM�����ʵ��");
			return;
		}
	}
}
