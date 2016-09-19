package combiz.ui.inventory;
 
import combiz.business.inventory.MatreqSrv;
import combiz.domain.corp.Department;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.pr.Pr;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workorder.Wptool;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class BorrowequWindow extends MainWindow
implements InfWindow
{
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public BorrowequWindow()
	{
		super();
	}
	
	public void save() throws Exception {
		Matreq mr = (Matreq)this.getMainObject();
		Date usedate = mr.getUsedate();//归还日期
		Date requireddate = mr.getRequireddate();//借用日期
		String idr1 = this.getIntoDate(usedate);
		String idr2 = this.getIntoDate(requireddate);
		if(requireddate != null && usedate !=null)
		{
			if(requireddate.after(usedate) && (!idr1.equals(idr2)))
			{
				Messagebox.show("归还日期应晚于借用日期,请核实！");				
				return;
			}
		}
		else
		{
			Messagebox.show("请输入借用日期和归还日期！");				
			return;
		}
		super.save();
	}
	
	public String getIntoDate(Date date) throws InterruptedException 
	{
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		if(date != null){
		String date1=ntime.format(date.getTime());		
		return date1;
		}else{
			return null;
		}
		
	}
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Matreq newobj = new Matreq();
		String matreqnum = this.genAutokey("matreqnum");
		if(Util.isNotNull(matreqnum)){
			matreqnum = matreqnum.replace("MR", "BQ");
		}
		newobj.setMatreqnum(matreqnum);
		newobj.setRequestdate(new Date());
		newobj.setStatus("流程未启动");
		newobj.setStatusdate(new Date());
		newobj.setReqtype("设备借用申请");
		newobj.setReqdept(this.getLaborInfo().getDeptnum());
		newobj.setRequestby(this.getUserInfo().getLabornum());
		newobj.setRequireddate(new Date());		
		mainObject = newobj;
		return true;
	}
	/******************生成预留信息*************ljh***************/
	public void createinvr() throws Exception
	{
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("请在生成预留前保存数据！");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();
		/**
		 *判断是否是重复生成预留，是否需要生成预留
		 * 一个工单只能生成一次预留，判断状态必需为批准状态的才能生成预留
		 */
		String matreqnum = matreq.getMatreqnum();
		String status = matreq.getStatus();
		if (!(status.equals("已批准"))) {
			throw new Exception ("该借用申请未被批准，不能生成预留！");
		}
		List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(Toolreserve.class, "reqnum = '"+matreqnum+"'");
		if (toolreservelist.size()>0) {
			throw new Exception ("该申请单已经生成预留，不能重复生成！");
		}
		
		List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "reqnum = '"+matreqnum+"'");
		if (invreservelist.size()>0) {
			throw new Exception ("该申请单已经生成预留，不能重复生成！");
		}
		
		((MatreqSrv)this.getMainSrv()).createinvr(matreq);
		Messagebox.show("预留生成完毕！");
		this.refreshData();
	}

	/* 
	 * 功能：设置子窗口只读
	 * 作者：雷涛
	 * 日期：2008-11-28上午10:56:15
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq)this.getMainObject();
		ListWindow listwnd =(ListWindow) this.getFellow("wpmaterial");
		if (matreq.getStatus()!=null && matreq.getStatus().equals("已批准")) 
		{
			listwnd.setReadonlyList(true);
		}
		else
		{
			listwnd.setReadonlyList(false);
		}
		super.initData();
	}


	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		String matreqnum = matreq.getMatreqnum();
		String exc = "Y";//标识工作流是否往下继续走？当为“Y”的时候，往下走，否则，返回。
		List wpmatlist = this.getMainSrv().getBaseDao().findWithQuery(Wpmaterial.class, "matreqnum = '"+matreqnum+"'");
		if(wpmatlist.size()>0)
		{
			if(matreq.getStatus().equals("流程未启动"))
			{
				for(int i=0;i<wpmatlist.size();i++)
				{
					Wpmaterial wpmat = (Wpmaterial) wpmatlist.get(i);
					/****************是否生成采购申请***********************/
					String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"+wpmat.getItemnum()+"' and warehouse ='"+wpmat.getWarehouse()+"'";
					//库存总余量
					Double sumcurbal =(Double) this.getMainSrv().getBaseDao().selectSumByHql(sql);
					//预留数量
					Double reserveqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.reservedqty) from Invreserve t where t.reqnum <> '"+wpmat.getMatreqnum()+"' and t.itemnum = '"+wpmat.getItemnum()+"' and t.warehouse = '"+wpmat.getWarehouse()+"' and t.ponum is null and t.polinenum is null");
					
					if(sumcurbal == null)
					{
						sumcurbal = 0d;
					}
					if(reserveqty == null)
					{
						reserveqty = 0d;
					}
					//需要采购数量
					Double needorderqty = sumcurbal-reserveqty-wpmat.getItemqty();
					if (needorderqty<0) 
					{
						int rtn = Messagebox.show("采购编号为:'"+wpmat.getItemnum()+"'的资产，库存余量不足，确定发送工作流？","请确定是否发送工作流！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
						if(rtn == Messagebox.NO)
						{
							exc = "N";//流程就不发送，返回到用户修改借用数量界面
							break;
						}
							
					}
				}
				if(exc.equals("Y"))
				{
					super.workflow();
				}
				else
				{
					return;
				}
			}
			else
			{
				super.workflow();
			}
			
			
		}
		else
		{
			throw new Exception("借用申请行为空，不能发送工作流，请确认！");
		}
	}
	
	
	
}
