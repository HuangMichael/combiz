package combiz.ui.inventory;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;
import combiz.ui.po.PoWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class FldItemnum2 extends FieldAdapter
{

	@Override
	public void action(Component arg0)
	throws Exception 
	{
		// TODO Auto-generated method stub
		IBandbox band = null;
		Invrectrans inv = (Invrectrans) this.mainObject;
//		
		EditWindow editWnd = (EditWindow) this.getEditWnd(arg0);
        PoWindow poWnd = (PoWindow) editWnd.getOwnerWnd();//从editwindow中获取所在的MainWindow;
		
		Po po = (Po) poWnd.getMainObject();//获取主窗口数据库对象
		
		
		band = (IBandbox) arg0;
		if (band.getValue()!= null) 
		{
			List list = this.mainSrv.getBaseDao().findWithQuery(Poline.class, "itemnum='"+band.getValue()+"'");
			if (list.size()>0) 
			{
				Poline poline = (Poline) list.get(0);
				Double conutnum = poline.getOrderqty();//采购总数
//				Double getnum = 0D;//已经接收的项目数量
				Invrectrans invrec = (Invrectrans) this.getMainObject();
				Long id = invrec.getId();
				List list1 = this.mainSrv.getBaseDao().findWithQuery(Poline.class, "ponum = '"+po.getPonum()+"' and polinenum='"+poline.getPolinenum()+"'");
				//List list2 = this.mainSrv.getBaseDao().findWithQuery(Invrectrans.class, "ponum = '"+po.getPonum()+"' and polinenum='"+poline.getPolinenum()+"' and id<>"+id+"");
				Double getnum = (Double) this.mainSrv.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where ponum = '"+po.getPonum()+"' and t.polinenum='"+poline.getPolinenum()+"' and t.id<>"+id+"");
				if(getnum == null)
					getnum = 0d;
				/*if (list2.size()>0) 
				{
					for(int i=0;i<list2.size();i++) 
					{
						Invrectrans invrectrans = (Invrectrans) list2.get(0);
						getnum = getnum + invrectrans.getQuantity();
					}
				}*/
				if (getnum == null) 
				{
					getnum = 0D;
				}
				
				Double abc = (Double)(conutnum- getnum); 
				if (abc < 0) 
				{
					Messagebox.show("该采购单项目数量有误，请核对后添加或修改！");
				}
				if (list1.size()>0) 
				{
					Poline ware = (Poline) list.get(0);
					inv.setTowarehouse(ware.getWarehouse());
					inv.setQuantity(abc);
					RecWindow recWnd = (RecWindow) arg0.getFellow("mainWnd");
					recWnd.bandData();	
				}
				else
				{
					Messagebox.show("您选择的项目还没有指定仓库，请完成指定仓库操作！");
				}
			}
		}
		else
		{
			Messagebox.show("请选择正确的列表值");
		}
		
	}

	@Override
	public void init(Component arg0)
	throws Exception 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Component arg0) 
	throws Exception 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public String getListWhere(Component arg0) {
		// TODO 自动生成方法存根
		EditWindow editWnd = (EditWindow) this.getEditWnd(arg0);
		PoWindow appWnd = (PoWindow) editWnd.getOwnerWnd();
		if(appWnd!=null )
		{
		Po po = (Po) appWnd.getMainObject();
		String whereStr = "ponum = '"+po.getPonum()+"'";
		return whereStr;
		}
		return null;
	}	
}
