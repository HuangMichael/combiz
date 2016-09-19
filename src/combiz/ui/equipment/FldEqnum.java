package combiz.ui.equipment;

import combiz.domain.equipment.Equipment;
import combiz.system.FieldAdapter;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;

import java.util.List;

import com.inbasis.zk.ui.Component;

public class FldEqnum extends FieldAdapter
{	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 
	 */
	public void init(Component component)
	throws Exception 
	{
		
	}

	/**
	 * 字段上鼠标移开后调用该方法。
	 * 可以利用该方法来进行数据的合法性效验。
	 */
	public void validate(Component component) 
	throws Exception 
	{
		/*Equipment equipment = (Equipment)this.getMainObject();
		String parent =  equipment.getParent();
		
		String eqnum = (String) this.getValueByColname("eqnum");
		if (parent != null && parent !="") {
			if (eqnum !=null && eqnum !="") {
				if (parent.equals(eqnum)) {
					this.throwException(component, "不能添加 ["+parent+"] 为 ["+parent+"] 的部件！");
				}
			}
		}
		List eqlist = this.getMainSrv().getBaseDao().findWithQuery(Equipment.class, " parent = '"+parent+"' and eqnum='"+eqnum+"'");
		if (!(eqlist.size()>0)) {
			this.throwException(component, "["+eqnum+"] 已经为 ["+parent+"] 的部件，不需要添加，请核实！");
		}*/
		
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
				
		Equipment equipment = (Equipment)this.getMainObject();
		String parent = equipment.getParent();
		String str = "";
		if (parent != null && parent != "") {
			str = " eqnum = '"+parent+"' ";
			return str;
		}
		return super.getListWhere(arg0);
	}

}
