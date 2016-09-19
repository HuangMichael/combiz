package combiz.ui.equipment;

import combiz.domain.inventory.Measureunit;
import combiz.system.ui.CommonListWindow;


public class AssetMeasureunit extends CommonListWindow {

	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public AssetMeasureunit() {
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
		Measureunit measureunit = new Measureunit(); 
		measureunit.setUnittype("计量单位");
		mainObject = measureunit;
		return true;
	}
	
	public void onCreate() throws Exception
	{
		//this.setQueryString("unittype= 'N'");
		this.setQueryString("unittype= '计量单位'");
		super.onCreate();
		
	}


}
