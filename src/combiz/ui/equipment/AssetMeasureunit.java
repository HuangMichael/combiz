package combiz.ui.equipment;

import combiz.domain.inventory.Measureunit;
import combiz.system.ui.CommonListWindow;


public class AssetMeasureunit extends CommonListWindow {

	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public AssetMeasureunit() {
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
		measureunit.setUnittype("������λ");
		mainObject = measureunit;
		return true;
	}
	
	public void onCreate() throws Exception
	{
		//this.setQueryString("unittype= 'N'");
		this.setQueryString("unittype= '������λ'");
		super.onCreate();
		
	}


}
