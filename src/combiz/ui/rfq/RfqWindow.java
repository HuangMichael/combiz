package combiz.ui.rfq;
 
import combiz.business.rfq.RfqSrv;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.TableList;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;


public class RfqWindow extends MainWindow
implements InfWindow
{

	public RfqWindow()
	{
		super();
	}

	
	/**
	 * 
	 * @TODO 新建一张询价单
	 * @throws Exception
	 * @蒋祖兵 2007-8-13 上午11:50:22
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Rfq rfq = new Rfq();
		//int count = this.mainSrv.getRowCountByWhere(rfq, "");
		rfq.setRfqnum(this.genAutokey("rfqnum"));
		rfq.setStatus("已批准");
		rfq.setStatusdate(new Date());

		mainObject = rfq;
		return true;
	}

	/**
	 * 
	 * @TODO 通过询价供应商和询价单行生成报价行
	 * @throws Exception
	 * @蒋祖兵 2007-8-20 上午10:52:12
	 */
	public void createquot() throws Exception{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"'");
		
		if(count >0)
		{
			throw new Exception("已经对询价单'"+rfq.getRfqnum()+"'生成报价行，不能重复生成报价行！");
		}
		((RfqSrv)this.mainSrv).createquot(this.getMainObject());
		this.setMainData();
		this.refreshData();
		Messagebox.show("成功生成报价行");
	}
	/* 
	 * 功能：通过询价供应商及对应的报价行生成采购单和采购单行
	 * 作者：李建红
	 * 日期：Sep 17, 2008 4:33:54 PM
	 */
	public void createpo()
	throws Exception
	{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"'");
		if(count == 0)
		{
			throw new Exception("询价单'"+rfq.getRfqnum()+"'中没有报价行，不能生成采购单！");
		}
		//确认
		int rtn = Messagebox.show("是否确定根据选中的供应商生成订单？","是否确定！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		/*ListWindow listWnd = (ListWindow) this.getFellow("rfqvendor");
		List retList = listWnd.getSelectObjects();
		*/
		
		List retList = this.getMainSrv().getBaseDao().findWithQuery(Rfqvendor.class, "rfqnum='"+rfq.getRfqnum()+"'");
		if(retList.size() == 0)
		{
			Messagebox.show("请选择要生成订单的供应商！");
			return;
		}
		((RfqSrv)this.mainSrv).CreatePo(retList,rfq);
		this.setMainData();
		Messagebox.show("成功生成采购单和采购行!");
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：根据授予的报价行生成合同行
	 * 日期：Oct 24, 2008 3:55:49 PM
	 *
	 */
	public void createcont()
	throws Exception
	{
		
		Rfq rfq = (Rfq) this.getMainObject();
		int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Rfqquoteline t where t.rfqnum = '"+rfq.getRfqnum()+"' and t.isawarded ='是'");
		if(count == 0)
		{
			throw new Exception("询价单'"+rfq.getRfqnum()+"'中没有授予的报价行，不能生成合同！");
		}
		//确认
		int rtn = Messagebox.show("是否按照授予的报价行生成合同？","是否确定！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		List rvlist = this.getMainSrv().getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rfq.getRfqnum()+"' and isawarded ='是'");
		List vendorlist = this.getMainSrv().getBaseDao().selectListBySql("select t.vendor from Rfqquoteline t  where t.rfqnum = '"+rfq.getRfqnum()+"' and t.isawarded ='是' group by t.vendor");
		if(rvlist.size()>0 && vendorlist.size()>0)
		{
			((RfqSrv)this.mainSrv).createcont(rvlist,vendorlist,rfq);
			this.setMainData();
			Messagebox.show("成功生成合同和合同行!");
		}
		
	}
	
	
	/**
	 * 
	 * 通过供应商及供应商对应的报价行生成采购单和采购单行
	 * @throws Exception
	 * @蒋祖兵 2007-8-20 上午11:02:20
	 */
/*	public void CreatePo()
	throws Exception
	{
		//确认
		int rtn = Messagebox.show("是否确定根据选中的供应商生成订单？","是否确定！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
		{
			return;
		}
		Rfq rfq = (Rfq)this.getMainObject();
		//通过关系名获取listWindwo
		ListWindow listWnd = (ListWindow) this.getFellow("rfqvendor1");
		//得到选中的某一条记录;
		Rfqvendor rv = (Rfqvendor)listWnd.getMainObject();
		
		String ponum = ((RfqSrv)this.mainSrv).CreatePo(rv,rfq);
		this.setMainData();
		Messagebox.show("成功生成采购单和采购行--对应编号"+ponum);
	}*/
	/**
	 * 
	 * @TODO 拷贝采购申请行，弹出一个窗体，数据为所有的采购申请行。可以由用户选择其中的一部分数据
	 * @throws Exception
	 * @蒋祖兵 2007-8-24 下午03:16:24
	 */
	public void copypr() throws Exception{
		Rfq rfq = (Rfq)this.getMainObject();
//		弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this.getMainObject(), "/pr/prlineList.xul","rfqnum is null and rfqlinenum is null");
		
		if(listWnd==null)
			return;
		
		//判断是否点击了确定按钮，还是取消按钮
		if(!listWnd.isConfirm())
			return;
		
		//TableList tabblelist = (TableList) listWnd.getFellow(listWnd.getId()+"List");
		
		List retList = listWnd.getSelectObjects();
		((RfqSrv)this.getMainSrv()).CopyPrline( rfq,retList);
		this.refreshData();
		Messagebox.show("成功拷贝采购申请行!");
	}
	
}
