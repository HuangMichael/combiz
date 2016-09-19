package combiz.ui.finance;
 
import combiz.domain.po.Po;
import combiz.domain.finance.Currency;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;


public class CurrencyWindow extends MainWindow
implements InfWindow
{

	public CurrencyWindow()
	{
		super();
	}
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Currency newobj = new Currency();
		mainObject = newobj;
		return true;
	}
}
