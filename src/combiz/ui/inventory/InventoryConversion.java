package combiz.ui.inventory;

import combiz.domain.po.Conversion;
import combiz.system.ui.CommonListWindow;


public class InventoryConversion extends CommonListWindow {

	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InventoryConversion() {
		super();
	}
	
	
	

	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/**********************************
		 * 创建初始值
		 **********************************/
		Conversion conversion = new Conversion();
		mainObject = conversion;
		return true;
	}
	


}
