package combiz.ui.assetscard;


import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;

import combiz.system.ui.ListWindow;
public class Copyassetcode extends ListWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////

	public Copyassetcode() {
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * 
	 * @throws Exception
	 * @ 王伟 日期：2010-05-05
	 */
	public boolean addNew() throws Exception {
		// 获取父级主窗体对象
		//CghtWindow parentWnd = (CghtWindow) this.ownerWnd;
		Assetscard assetscard =(Assetscard) this.getOwnerWnd().getMainObject();
		Assetscardline assetscardline = new Assetscardline();
		assetscardline.setAssetcode(assetscard.getAssetcode());
		
		this.mainObject = assetscardline;
		return true;
	}

}
