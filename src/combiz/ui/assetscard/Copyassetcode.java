package combiz.ui.assetscard;


import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;

import combiz.system.ui.ListWindow;
public class Copyassetcode extends ListWindow {

	// /////////////////////////////////������////////////////////////////////////////////////

	public Copyassetcode() {
		super();
	}

	/**
	 * ����½���¼��ť�Ĵ����¼������Ĭ���ֶ�ֵ
	 * 
	 * @throws Exception
	 * @ ��ΰ ���ڣ�2010-05-05
	 */
	public boolean addNew() throws Exception {
		// ��ȡ�������������
		//CghtWindow parentWnd = (CghtWindow) this.ownerWnd;
		Assetscard assetscard =(Assetscard) this.getOwnerWnd().getMainObject();
		Assetscardline assetscardline = new Assetscardline();
		assetscardline.setAssetcode(assetscard.getAssetcode());
		
		this.mainObject = assetscardline;
		return true;
	}

}
