package combiz.ui.inventory;

import combiz.domain.po.Conversion;
import combiz.system.ui.CommonListWindow;


public class InventoryConversion extends CommonListWindow {

	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InventoryConversion() {
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
		Conversion conversion = new Conversion();
		mainObject = conversion;
		return true;
	}
	


}
