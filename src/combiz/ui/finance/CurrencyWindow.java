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
	 * ������¼
	 * @throws Exception
	 * ���ߣ���Ⱥ�� ���ڣ�2007-8-20
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Currency newobj = new Currency();
		mainObject = newobj;
		return true;
	}
}
