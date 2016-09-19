package combiz.ui.invoice;

import java.util.List;

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
 * 功能：在编制发票是，根据界面上选择的供应商信息，将该供应商对应的没有开发票的采购单过滤出来。
 * 绑定表：invoice
 * 绑定字段：ponum
 *@author 李阳
 *2008-1-24上午11:05:39
 */

public class FldrelationPonum extends FieldAdapter
{
	//字段输完值后，回车或鼠标离开调用该方法
	@Override
	public void action(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		Object obj = this.getMainObject();
		if(obj instanceof Invoice)
		{
			Invoice invoice  = (Invoice) obj;
		}
		String ponum = (String) this.getValueByColname("ponum");
		if (Util.isNotNull(ponum)) 
		{
			List polist = this.mainSrv.getBaseDao().findWithQuery(Po.class, "ponum = '"+ponum+"'");
			if (polist.size()>0) {
				Po po = (Po) polist.get(0);
				this.setValueByColname("vendor", po.getVendor());
				this.setReadonly("invoice.vendor");
			}
			this.setValueByColname("cntnum", "");
		}else{
			this.setNoReadonly("invoice.vendor");
			this.setRequired("invoice.vendor");
		}
		
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


	/**
	*@author 李阳
	*功能：
	*@param ibandbox
	*@return 
	*2008-1-24上午11:41:43
	*/
	
	public String getListWhere(Component ibandbox) 
	{
		//从编辑窗口获取对应的LIST窗口
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
	
		Invoice invoice =(Invoice) this.getMainObject();
		String vendor = invoice.getVendor();
		if (vendor != null && vendor.trim().length() > 0)
		//在应用程序PDXL下面才生效
		{
			String whereStr = "status not in('流程未启动','关闭','取消') and vendor = '" + vendor +"'";
			return whereStr;
		}
		else
		{
			String whereStr = "status not in('流程未启动','关闭','取消')";
			return whereStr;
		}
	}


}
