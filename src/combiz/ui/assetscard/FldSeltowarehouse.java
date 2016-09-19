package combiz.ui.assetscard;

import java.util.List;

import combiz.domain.assetscard.Allocation;
import combiz.domain.contract.Contract;
import combiz.domain.invoice.Invoice;
import combiz.domain.location.Locations;
import combiz.domain.po.Po;
import combiz.system.FieldAdapter;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;



/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 1:31:53 PM  May 18, 2010 
 * 功能：如果接收仓库不是默认仓库，可以选择“接收地点”下的所有仓库。
 * 捆绑表：Invrectrans
 * 捆绑字段：towarehouse
 */
public class FldSeltowarehouse extends FieldAdapter
{
	//字段输完值后，回车或鼠标离开调用该方法
	@Override
	public void action(Component arg0) throws Exception {

	}
	//字段初始化调用该方法
	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}
	//焦点移开效验
	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}




	public String getListWhere(Component ibandbox) 
	{
		//从编辑窗口获取对应的LIST窗口
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
		String whereStr = null;

		MainWindow mainwnd = (MainWindow) this.getRecWnd().getOwnerWnd();
		if(mainwnd!=null)
		{
			if(mainwnd.getApp().equals("ALLOCATIONREC"))
			{
				Allocation allocation  = (Allocation) mainwnd.getMainObject();
				String tosite = allocation.getTosite();
				if(Util.isNotNull(tosite))
				{
					whereStr = "warehouse in(select t.warehouse from Warehouse t where t.sitenum = '"+tosite+"')";
				}
				else
				{
					whereStr = "1=1";
				}
			}
			else
			{
				whereStr = "1=1";
			}

		}

		return whereStr;
	}


}
