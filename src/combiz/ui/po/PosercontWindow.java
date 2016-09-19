package combiz.ui.po;
 
import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;


public class PosercontWindow extends MainWindow
implements InfWindow
{

	public PosercontWindow()
	{
		super();
	}

	
	
	/**
	 * 
	 * @TODO 新增一条记录
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午01:17:05
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Po po = new Po();
		//int count = this.mainSrv.getRowCountByWhere(po, "");
		po.setPonum(this.genAutokey("ponum"));
		po.setStatus("草稿");
		po.setPotype("服务采购");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		po.setReceipts("未接收");
		po.setChangedate(new Date());
		po.setChangeby(this.getLaborInfo().getLabornum());
		po.setOrderdate(new Date());
		po.setIsprotocol("否");
		po.setIsgov("否");
		mainObject = po;
		return true;
	}
	

	
	
	/**
	 * 
	 * @TODO 拷贝采购单申请行。弹出一个窗体，用户可以选择其中的部分已有数据。
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午04:20:44
	 */
	
	/**
	*@author 李阳
	*功能：复制采购单，将选中的采购单进行复制；
	*@throws Exception 
	*2008-1-22下午01:10:31
	*/
	public void copypo() throws Exception{
		
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再进行复制操作！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("采购单复制操作前，请先保存数据！");
			return;
		}
		Po po = (Po) this.getMainObject();
		List retList =  this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "prnum = '" + po.getPonum() +"'");
		Po newpo = ((PoSrv)this.getMainSrv()).copypo(po);
		this.setMainObject(newpo);
		Messagebox.show("已成功将采购单:" + po.getPonum() + "复制到采购单" + newpo.getPonum());
		this.refreshData();
	}



	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		super.save();
		this.refreshData();
	}
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po) this.mainObject;
		String s[] = {"po.vendor","po.supervisor","po.isprotocol","po.isgov","po.description","po.orderdate","po.supervisor","po.supervisor","po.totalcost","po.notifynum","po.poreason"};
		if (po.getStatus()!=null && po.getStatus().equals("执行采购")) {
			this.setReadonlyFields(s);
		}
		super.initData();
	}
	




	
	
}
