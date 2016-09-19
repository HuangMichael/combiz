package combiz.ui.inventory;

import combiz.system.FieldAdapter;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ICheckbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Bandbox;
import com.inbasis.zul.Textbox;


/**
 * @author 李阳 E-mail:superyang4208731@yahoo.com.cn
 * 2:44:11 PM  Jan 12, 2009 
 * 功能：如果物资编码不为空，设置为只读。
 * 捆绑表：wpmaterial
 * 捆绑字段：classid
 */
public class FldWpClassid extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component)
	throws Exception
	{
		IBandbox arg = (IBandbox) this.getFellow("wpmaterial.itemnum");
		if(Util.isNotNull(arg.getValue()))
		{
			this.setReadonly(component);
		}
		else
		{
			this.setNoReadonly(component);
		}		
	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * 
	 */
	public void action(Component component)
	throws Exception
	{
		
	}

	@Override
	public String getListWhere(Component arg0) {
		// TODO Auto-generated method stub
		String app = this.getRecWnd().getOwnerWnd().getApp();
		String classtype = "";
		if(app.equals("EQREQ") || app.equals("BORROWEQU")) {
			classtype = "classtype in ('资产')";
		}else{
			classtype = "classtype in ('物资','工具')";
		}
		return classtype;
	}

}
