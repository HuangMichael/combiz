package combiz.ui.inventory;

import combiz.domain.inventory.Measureunit;
import combiz.system.ui.CommonListWindow;


public class InventoryMeasureunit extends CommonListWindow {

	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public InventoryMeasureunit() {
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
		Measureunit measureunit = new Measureunit();
		measureunit.setUnittype("��浥λ");
		mainObject = measureunit;
		return true;
	}
	
	public void onCreate() throws Exception
	{
		//this.setQueryString("sfjldw= 'Y'"); 
		this.setQueryString("unittype= '��浥λ'");
		super.onCreate();
		
	}


}
