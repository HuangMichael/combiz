package combiz.business.workorder;

import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqsparepart;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.domain.failure.Failurecause;
import combiz.domain.failure.Failurecode;
import combiz.domain.failure.Failuredeal;
import combiz.domain.failure.Failureproblem;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.domain.location.Locations;
import combiz.domain.po.Po;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.stdplan.Joblabor;
import combiz.domain.stdplan.Jobmaterial;
import combiz.domain.stdplan.Jobplan;
import combiz.domain.stdplan.Jobtask;
import combiz.domain.stdplan.Jobtool;
import combiz.domain.stdplan.Jobvendor;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workflow.Wfinstance;
import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofailcode;
import combiz.domain.workorder.Wofaildeal;
import combiz.domain.workorder.Wofailproblem;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wplabor;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workorder.Wptask;
import combiz.domain.workorder.Wptool;
import combiz.domain.workorder.Wpvendor;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;
/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class WorkorderImpl extends IBOBaseImpl implements WorkorderSrv {

	PrSrv prsrv = (PrSrv)IBOSrvUtil.getSrv("pr");
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */			
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Workorder))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		this.deleteAllChild(obj, "wpmaterialTable");
		this.deleteAllChild(obj, "wptaskTable");
		this.deleteAllChild(obj, "wptoolTable");
		this.deleteAllChild(obj, "wplaborTable");
		this.deleteAllChild(obj, "wpvendorTable");
		this.deleteAllChild(obj, "invreserve");
		this.deleteAllChild(obj, "wofailclass");

	}	
	/* 
	 * 功能：复制工单，选择一条工单记录，进行复制。
	 * 作者：李阳
	 * 日期：2008-3-10上午10:20:50
	 */
	public Workorder copywo(Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder wo = (Workorder) obj;
		Workorder newwo = new Workorder();	
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class,"labornum ='" + labornum+ "'");
		Labor labor = (Labor) laborlist.get(0);
		String project = labor.getDefaultstoreloc();		
		String wonum = this.genInskey("wonum");
		newwo.setWonum(wonum);
		newwo.setStatus("流程未启动");
		newwo.setDescription(wo.getDescription());
		newwo.setLocation(wo.getLocation());
		newwo.setEqnum(wo.getEqnum());
		newwo.setJpnum(wo.getJpnum());
		newwo.setWorktype(wo.getWorktype());
		newwo.setStatusdate(new Date());
		newwo.setFaillevel(wo.getFaillevel());
		newwo.setEqdown(wo.getEqdown());
		newwo.setParent(wo.getParent());
		newwo.setFailurecode(wo.getFailurecode());
		newwo.setHaschildren(wo.getHaschildren());
		newwo.setProblemcode(wo.getProblemcode());
		newwo.setFollowupfromwonum(wo.getFollowupfromwonum());
		newwo.setHasfollowupwork(wo.getHasfollowupwork());
		newwo.setCraft(wo.getCraft());
		newwo.setCrewid(wo.getCrewid());
		newwo.setEstdur(wo.getEstdur());
		newwo.setEstlabhrs(wo.getEstlabhrs());
		newwo.setEstlabcost(wo.getEstlabhrs());
		newwo.setActlabhrs(wo.getActlabhrs());
		newwo.setEstmatcost(wo.getEstmatcost());
		newwo.setEsttoolcost(wo.getEsttoolcost());
		newwo.setActlabcost(wo.getActlabcost());
		newwo.setActmatcost(wo.getActmatcost());
		newwo.setActtoolcost(wo.getActtoolcost());
		newwo.setEstatapprlabhrs(wo.getEstatapprlabhrs());
		newwo.setEstatapprlabcost(wo.getEstatapprlabcost());
		newwo.setEstatapprmatcost(wo.getEstatapprmatcost());
		newwo.setEstatapprtoolcost(wo.getEstatapprtoolcost());
		//newwo.setSitenum(wo.getSitenum());
		//newwo.setCorpnum(wo.getCorpnum());
		newwo.setProject(project);
		newwo.setReportedby(wo.getReportedby());
		super.save(newwo);				
		/******************复制标准作业计划详细内容************************/
		List wptasklist = this.getBaseDao().findWithQuery(Wptask.class, "wonum='" + wo.getWonum() + "'");
		if(wptasklist.size() > 0)
		{
			for (int i = 0; i < wptasklist.size(); i++) 
			{
				Wptask wptask = (Wptask) wptasklist.get(i);
				Wptask newwptask = new Wptask();
				newwptask.setWonum(wonum);
				newwptask.setJpnum(wptask.getJpnum());
				newwptask.setTasknum(wptask.getTasknum());
				newwptask.setDescription(wptask.getDescription());
				newwptask.setTaskduration(wptask.getTaskduration());
				newwptask.setStatus(wptask.getStatus());
				newwptask.setStatusdate(new Date());
				newwptask.setLocation(wptask.getLocation());
				super.save(newwptask);
			}
		}
		//******************复制工单中物料信息************************//*
		List wpmatlist = this.getBaseDao().findWithQuery(Wpmaterial.class, "wonum='" + wo.getWonum() + "'");
		if(wpmatlist.size() > 0)
		{
			for (int i = 0; i < wpmatlist.size(); i++) 
			{
				Wpmaterial wpmat = (Wpmaterial) wpmatlist.get(i);
				Double avgcost = 0d;
				Wpmaterial newwpmat = new Wpmaterial();
				newwpmat.setWonum(wonum);
				newwpmat.setItemnum(wpmat.getItemnum());
				newwpmat.setDescription(wpmat.getDescription());
				newwpmat.setJpnum(wpmat.getJpnum());
				newwpmat.setWarehouse(wpmat.getWarehouse());
				newwpmat.setItemqty(wpmat.getItemqty());
				List Inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='" + wpmat.getItemnum()+"'");
				if(Inventorylist.size() > 0)
				{
					Inventory inventory = (Inventory) Inventorylist.get(0);
					avgcost = inventory.getAvgcost();
				}						
				newwpmat.setUnitcost(avgcost);
				newwpmat.setLinecost(avgcost * wpmat.getItemqty());
				//newwpmat.setSitenum(wo.getSitenum());
				//newwpmat.setCorpnum(wo.getCorpnum());
				super.save(newwpmat);
			}
		}		
		//******************复制工单中工具信息************************//*
		List wptoollist = this.getBaseDao().findWithQuery(Wptool.class, "wonum='" + wo.getWonum() + "'");
		if(wptoollist.size() > 0)
		{
			for (int i = 0; i < wptoollist.size(); i++) 
			{
				Wptool wptool = (Wptool) wptoollist.get(i);
				Wptool newwptool = new Wptool();
				newwptool.setWonum(wonum);
				newwptool.setJpnum(wptool.getJpnum());
				newwptool.setTasknum(wptool.getTasknum());
				newwptool.setToolnum(wptool.getToolnum());
				newwptool.setRate(wptool.getRate());
				newwptool.setToolqty(wptool.getToolqty());
				newwptool.setToolhrs(wptool.getToolhrs());
				super.save(newwptool);
			}
		}		
		//******************复制工单中人工信息************************//*
		List wplaborlist = this.getBaseDao().findWithQuery(Wplabor.class, "wonum='" + wo.getWonum() + "'");
		if(wplaborlist.size() > 0)
		{
			for (int i = 0; i < wptoollist.size(); i++) 
			{
				Wplabor wplabor = (Wplabor) wplaborlist.get(i);
				Wplabor newwplabor = new Wplabor();
				newwplabor.setWonum(wonum);
				newwplabor.setJpnum(wplabor.getJpnum());
				newwplabor.setTasknum(wplabor.getTasknum());
				newwplabor.setLaborhrs(wplabor.getLaborhrs());
				newwplabor.setLabornum(wplabor.getLabornum());
				newwplabor.setLaborqty(wplabor.getLaborqty());
				newwplabor.setRate(wplabor.getRate());
				newwplabor.setVendor(wplabor.getVendor());
				newwplabor.setContact(wplabor.getContact());
				super.save(newwplabor);
			}
		}		
		//******************复制工单中承包单位信息************************//*
		List wpvendorlist = this.getBaseDao().findWithQuery(Wpvendor.class, "wonum='" + wo.getWonum() + "'");
		if(wpvendorlist.size() > 0)
		{
			for (int i = 0; i < wpvendorlist.size(); i++) 
			{
				Wpvendor wpvendor = (Wpvendor) wpvendorlist.get(i);
				Wpvendor newwpvendor = new Wpvendor();
				newwpvendor.setWonum(wonum);
				newwpvendor.setJpnum(wpvendor.getJpnum());
				newwpvendor.setTasknum(wpvendor.getTasknum());
				newwpvendor.setVendor(wpvendor.getVendor());
				newwpvendor.setVendorhrs(wpvendor.getVendorhrs());
				newwpvendor.setRate(wpvendor.getRate());
				newwpvendor.setContract(wpvendor.getContract());
				newwpvendor.setCntlinenum(wpvendor.getCntlinenum());
				super.save(newwpvendor);
			}
		}
		return newwo;
	}
	/* 
	 * 功能：保存工单，在保存工单时，判断标准作业计划是否修改，如果修改了，重新生成工单计划。
	 * 作者：李阳
	 * 日期：2008-3-12下午08:44:39
	 */
	public void save(Object arg0) throws Exception 
	{
		if (arg0 instanceof Workorder)
		{
			Workorder workorder = (Workorder) arg0;
			String jpnum = workorder.getJpnum();
			if(workorder.getId()==null) //是新建记录
			{
				/////////////////删除关闭的工单下所有的预留对象//////////ljh///////
				if(!this.isWFActive(arg0))
				{
					this.deleteAllChild(workorder, "invreserve");
				}

				// 如果用户选择了标准作业计划,则将标准作业计划数据写入相应的表中
				if(Util.isNotNull(jpnum))
				{
					this.genJobplan(workorder, jpnum);
				}
			}
			else //更新记录
			{
				if(Util.isNotNull(jpnum))
				{
					int count = this.getRowCountByWhere(workorder,
							"wonum='"+workorder.getWonum()+"' and jpnum='"+workorder.getJpnum()+"'");
					if(count<=0)
					{
						//作业计划原来不存在，是新修改添加的计划编号，需要生成作业计划
						this.genJobplan(workorder, jpnum);
					}
				}
			}		

			//判断选择的时间是否正确，开始时间必须在结束时间之前
			Date schedstart = workorder.getSchedstart();//计划开始时间
			Date schedfinish = workorder.getSchedfinish();//计划结束时间

			Date actstart = workorder.getActstart();//实际开始时间
			Date actfinish = workorder.getActfinish();//实际完成时间

			if (schedstart != null) 
			{
				if (schedfinish != null){
					if (schedstart.after(schedfinish)){
						throw new Exception("开始时间必须早于结束时间！");
					}
				}else {
					throw new Exception("请选择结束时间！");
				}
			}

			if (actstart != null) 
			{
				if (actfinish != null){
					if (actstart.after(actfinish)){
						throw new Exception("开始时间必须早于结束时间！");
					}
				}else {
					throw new Exception("请选择结束时间！");
				}
			}
			//保存自己
			super.save(workorder);
		}	
	}	
	/* 
	 * 功能：在用户编制工单后，工单中的标准作业计划创建到标准作业计划模板里
	 * 作者：李阳
	 * 日期：2008-4-15上午09:13:51
	 */
	public void createjbplan(Object obj,Object obj2) throws Exception 
	{
		Workorder wo = (Workorder) obj;
		Jobplan jobplan = (Jobplan) obj2;

		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class,"labornum ='" + labornum+ "'");
		Labor labor = (Labor) laborlist.get(0);
		String project = labor.getDefaultstoreloc();						
		String wonum = wo.getWonum();
		List wplist = this.getBaseDao().findWithQuery(Wptask.class, "wonum='"+wonum+"'");
		if(wplist.size() > 0)
		{		
			Jobplan newobj = new Jobplan();

			String jpnum = this.genInskey("jobplan");			
			newobj.setJpnum(jpnum);
			newobj.setDescription(jobplan.getDescription());
			newobj.setStatus("正式");
			newobj.setStatusdate(new Date());
			newobj.setChangby(this.getUserInfo().getLabornum());
			newobj.setChangdate(new Date());
			newobj.setJpduration(jobplan.getJpduration());
			super.save(newobj);
			this.createjoplandetail(wo,jpnum);
			Messagebox.show("已成功创建标准作业计划"+ jpnum);			
		}
		else
		{
			throw new Exception("该工单标准作业计划为空，不能创建标准作业计划");
		}

	}	
	/* 
	 * 功能：创建标准作业计划里的任务、物料、工具和其他信息。
	 * 作者：李阳
	 * 日期：2008-4-15上午09:36:32
	 */
	public void createjoplandetail(Workorder workorder,String jpnum)//private
	throws Exception
	{
		// 工作计划人工表
		List wplaborlist = this.getBaseDao().findWithQuery(Wplabor.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wplaborlist.size(); i++)
		{
			Wplabor wplabor = (Wplabor)wplaborlist.get(i);
			Joblabor joblabor = new Joblabor();
			// 组织数据
			joblabor.setLaborhrs(wplabor.getLaborhrs());
			joblabor.setLabornum(wplabor.getLabornum());
			joblabor.setQty(wplabor.getLaborqty());
			joblabor.setRate(wplabor.getRate());
			joblabor.setTasknum(wplabor.getTasknum());
			joblabor.setJpnum(jpnum);
			joblabor.setVendor(wplabor.getVendor());
			super.save(joblabor);
		}
		// 工作计划物料表
		List wpmateriallist = this.getBaseDao().findWithQuery(Wpmaterial.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wpmateriallist.size(); i++)
		{
			Wpmaterial wpmaterial = (Wpmaterial)wpmateriallist.get(i);
			Jobmaterial jobmaterial = new Jobmaterial();
			// 组织数据
			jobmaterial.setItemnum(wpmaterial.getItemnum());
			jobmaterial.setQty(wpmaterial.getItemqty());
			jobmaterial.setTasknum(wpmaterial.getTasknum());
			jobmaterial.setJpnum(jpnum);
			jobmaterial.setVendor(wpmaterial.getVendor());
			jobmaterial.setWarehouse(wpmaterial.getWarehouse());
			super.save(jobmaterial);
		}
		// 工单任务表
		List wptasklist = this.getBaseDao().findWithQuery(Wptask.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wptasklist.size(); i++)
		{
			Wptask wptask = (Wptask)wptasklist.get(i);
			Jobtask jobtask = new Jobtask();
			// 组织数据
			jobtask.setDescription(wptask.getDescription());
			jobtask.setEqnum(wptask.getEqnum());
			jobtask.setJpnum(jpnum);
			jobtask.setJtwz(wptask.getJtwz());
			jobtask.setLocation(wptask.getLocation());
			jobtask.setPart(wptask.getPart());
			jobtask.setPointnum(wptask.getPointnum());
			jobtask.setTaskduration(wptask.getTaskduration());
			jobtask.setTasknum(wptask.getTasknum());
			super.save(jobtask);
		}
		// 工作计划工具表
		List wptoollist = this.getBaseDao().findWithQuery(Wptool.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < wptoollist.size(); i++)
		{
			Wptool wptool = (Wptool)wptoollist.get(i);
			Jobtool jobtool = new Jobtool();
			// 组织数据
			jobtool.setTasknum(wptool.getTasknum());
			jobtool.setJpnum(jpnum);
			jobtool.setToolhrs(wptool.getToolhrs());
			jobtool.setToolnum(wptool.getToolnum());
			jobtool.setQty(wptool.getToolqty());
			super.save(jobtool);
		}
		// 维修计划承包商
		List wpvendorlist = this.getBaseDao().findWithQuery(Wpvendor.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < wpvendorlist.size(); i++)
		{
			Wpvendor wpvendor = (Wpvendor)wpvendorlist.get(i);
			Jobvendor jobvendor = new Jobvendor();
			// 组织数据
			jobvendor.setCntlinenum(wpvendor.getCntlinenum());
			jobvendor.setContract(wpvendor.getContract());
			jobvendor.setTasknum(wpvendor.getTasknum());
			jobvendor.setJpnum(jpnum);
			jobvendor.setVendor(wpvendor.getVendor());
			jobvendor.setRate(wpvendor.getRate());
			super.save(jobvendor);
		}
	}			
	/**
	 * 生成作业计划
	 * brianhong  2008-2-20
	 * @param workorder
	 * @param jpnum
	 * @throws Exception
	 */

	public void genJobplan(Workorder workorder,String jpnum)//private
	throws Exception
	{
		// 工作计划人工表
		List joblabors = this.getBaseDao().findWithQuery(Joblabor.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < joblabors.size(); i++)
		{
			Wplabor wplabor = new Wplabor();
			Joblabor joblabor = (Joblabor)joblabors.get(i);
			// 组织数据
			wplabor.setWonum(workorder.getWonum());
			wplabor.setContact("");
			wplabor.setLaborhrs(joblabor.getLaborhrs());
			wplabor.setLabornum(joblabor.getLabornum());
			wplabor.setLaborqty(joblabor.getQty());
			wplabor.setRate(joblabor.getRate());
			wplabor.setTasknum(joblabor.getTasknum());
			wplabor.setJpnum(joblabor.getJpnum());
			wplabor.setVendor(joblabor.getVendor());
			super.save(wplabor);
		}
		// 工作计划物料表
		List jobmaterials = this.getBaseDao().findWithQuery(Jobmaterial.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobmaterials.size(); i++)
		{
			Jobmaterial jobmaterial = (Jobmaterial)jobmaterials.get(i);
			Wpmaterial wpmaterial = new Wpmaterial();
			// 组织数据
			wpmaterial.setWonum(workorder.getWonum());

			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum ='"+jobmaterial.getItemnum()+"'");
			if (itemlist.size()>0) {
				Item item =(Item) itemlist.get(0);
				wpmaterial.setDescription(item.getDescription());
			}else{
				wpmaterial.setDescription("");
			}
			wpmaterial.setItemnum(jobmaterial.getItemnum());
			wpmaterial.setItemqty(jobmaterial.getQty());
			wpmaterial.setLinecost(0.0);
			wpmaterial.setTasknum(jobmaterial.getTasknum());
			wpmaterial.setJpnum(jobmaterial.getJpnum());
			wpmaterial.setUnitcost(0.0);
			wpmaterial.setVendor(jobmaterial.getVendor());
			if(Util.isNull(jobmaterial.getWarehouse()))
				jobmaterial.setWarehouse("0");
			wpmaterial.setWarehouse(jobmaterial.getWarehouse());
			//wpmaterial.setCorpnum(workorder.getCorpnum());
			//wpmaterial.setSitenum(workorder.getSitenum());
			super.save(wpmaterial);
		}
		// 工单任务表
		List jobtasks = this.getBaseDao().findWithQuery(Jobtask.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobtasks.size(); i++)
		{
			Jobtask jobtask = (Jobtask)jobtasks.get(i);
			Wptask wptask = new Wptask();
			// 组织数据
			wptask.setWonum(workorder.getWonum());
			wptask.setActfinish(workorder.getActfinish());
			wptask.setActstart(workorder.getActstart());
			wptask.setDescription(jobtask.getDescription());
			wptask.setJtwz(jobtask.getJtwz());
			wptask.setEqnum(jobtask.getEqnum());
			wptask.setJpnum(jobtask.getJpnum());
			wptask.setLocation(jobtask.getLocation());
			wptask.setPart(jobtask.getPart());
			wptask.setPointnum(jobtask.getPointnum());
			wptask.setSchedfinish(workorder.getSchedfinish());
			wptask.setSchedstart(workorder.getSchedstart());
			wptask.setStatus(workorder.getStatus());
			wptask.setStatusdate(workorder.getStatusdate());
			wptask.setTargcomp(workorder.getTargcomp());
			wptask.setTargstart(workorder.getTargstart());
			wptask.setTaskduration(jobtask.getTaskduration());
			wptask.setTasknum(jobtask.getTasknum());
			super.save(wptask);
		}
		// 工作计划工具表
		List jobtools = this.getBaseDao().findWithQuery(Jobtool.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobtools.size(); i++)
		{
			Jobtool jobtool = (Jobtool)jobtools.get(i);
			Wptool wptool = new Wptool();
			// 组织数据
			wptool.setWonum(workorder.getWonum());
			wptool.setRate(0.0);
			wptool.setTasknum(jobtool.getTasknum());
			wptool.setJpnum(jobtool.getJpnum());
			wptool.setToolhrs(jobtool.getToolhrs());
			wptool.setToolnum(jobtool.getToolnum());
			wptool.setToolqty(jobtool.getQty());
			super.save(wptool);
		}
		// 维修计划承包商
		List jobvendors = this.getBaseDao().findWithQuery(Jobvendor.class, " jpnum='" +jpnum+"'");
		if(jobvendors.size()>0)
		{
			for(int i=0;i < jobvendors.size(); i++)
			{
				Jobvendor jobvendor = (Jobvendor)jobvendors.get(i);
				Wpvendor wpvendor = new Wpvendor();
				wpvendor.setWonum(workorder.getWonum());
				wpvendor.setCntlinenum(jobvendor.getCntlinenum());
				wpvendor.setContract(jobvendor.getContract());
				wpvendor.setRate(jobvendor.getRate());
				wpvendor.setTasknum(jobvendor.getTasknum());
				wpvendor.setJpnum(jobvendor.getJpnum());
				wpvendor.setVendor(jobvendor.getVendor());
				wpvendor.setVendorhrs(0d);
				super.save(wpvendor);
			}
		}

	}

	/*
	 * @see combiz.business.wo.WorkorderSrv#deljbplan(java.lang.Object)
	 *      @author:yuanjq @description:删除标准作业计划及相关信息 @ 2007-7-6 上午10:18:24
	 */
	public void deljbplan(Object obj) throws Exception 
	{
		// TODO 自动生成方法存根
		Workorder workorder = (Workorder) obj;
		// 删除子表信息
		String wonum = workorder.getWonum();

		List wplabors = this.getBaseDao().findWithQuery(Wplabor.class," wonum = '" + wonum + "'");
		this.getBaseDao().deleteBatch(wplabors);

		List wpmaterials = this.getBaseDao().findWithQuery(Wpmaterial.class," wonum = '" + wonum + "'");
		this.getBaseDao().deleteBatch(wpmaterials);

		List wptasks = this.getBaseDao().findWithQuery(Wptask.class," wonum = '" + wonum + "'");
		this.getBaseDao().deleteBatch(wptasks);

		List wptools = this.getBaseDao().findWithQuery(Wptool.class," wonum = '" + wonum + "'");
		this.getBaseDao().deleteBatch(wptools);

		List wpvendors = this.getBaseDao().findWithQuery(Wpvendor.class," wonum = '" + wonum + "'");
		this.getBaseDao().deleteBatch(wpvendors);

		// 更新当前工单的标准作业计划字段
		workorder.setJpnum("");
		this.update(workorder);		
	}


	/* 
	 * 功能：在发放（设备）应用程序中，根据选中的周转件进行发放！
	 * 作者：李阳
	 * 日期：Nov 4, 2008  12:53:04 PM
	 */
	public void genequse(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder wo = (Workorder) obj;
		String wonum = wo.getWonum();

		/**************判断选择的要发放的数量是否大于预留数量****************/
		String idstr2 = null;
		List InvrList = this.getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		if(InvrList.size() > 0)
		{
			for(int i=0;i<InvrList.size();i++)
			{
				Invreserve invreserve = (Invreserve) InvrList.get(i);
				Double waitqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='待确认'");
				if (waitqty == null) {
					waitqty = 0D;
				}
				if(invreserve.getReservedqty()- waitqty > 0)
				{
					if(idstr2==null)
						idstr2 = invreserve.getId() + "";
					else
						idstr2 = invreserve.getId() + "," + idstr2;
				}
			}

			if(idstr2!=null)
				idstr2 = "id in(" + idstr2 + ")";
			else
				idstr2 = "1=2";


			List invreservelist = this.getBaseDao().findWithQuery(Invreserve.class, idstr2+" and wonum='"+wonum+"'");
			if(invreservelist.size()>0)
			{
				for(int j=0;j< invreservelist.size();j++)
				{
					Invreserve invres = (Invreserve) invreservelist.get(j);
					Double resqty =invres.getReservedqty();//预留数量
					Double sumqty = 0d;//选中的将要发放的记录
					for(int k=0;k<list.size();k++)
					{
						Invrectrans invrec = (Invrectrans) list.get(k);//取出选中的接收记录；
						if(invrec.getItemnum().equals(invres.getItemnum())&& invrec.getTowarehouse().equals(invres.getWarehouse()))
						{
							sumqty = sumqty + invrec.getQuantity();
						}

					}
					Double hasqty = (Double) this.getBaseDao().selectSumByHql("select  sum(t.quantity) from Invusetrans t where t.itemnum ='"+invres.getItemnum()+"' and t.wonum ='"+wonum+"'and t.issuetype = '发放' and t.state = '待确认' ");
					if(hasqty == null)
					{
						hasqty = 0d;
					}
					if(hasqty + sumqty-invres.getReservedqty()>0)
					{
						throw new Exception("您选择的要发放的资产数量大于预留数量，不能发放！");
					}

				}

			}


			for (int i = 0; i < list.size(); i++) {
				Invrectrans invrec = (Invrectrans) list.get(i);
				Invusetrans invuse = new Invusetrans();
				invuse.setItemnum(invrec.getItemnum());
				invuse.setWarehouse(invrec.getTowarehouse());
				invuse.setWonum(wo.getWonum());
				invuse.setIssuetype("发放");
				invuse.setIsprint("否");
				invuse.setTransdate(new Date());
				invuse.setActualdate(new Date());
				invuse.setRequestdate(wo.getStatusdate());
				invuse.setUsedate(wo.getStatusdate());
				invuse.setQuantity(1d);
				invuse.setReqqty(1d);
				invuse.setPonum(invrec.getPonum());
				invuse.setPolinenum(invrec.getPolinenum());
				Double curbalsum = (Double) this.getBaseDao().selectSumByHql(
						"select sum(t.curbal) from Invstock t where t.itemnum = '"
						+ invrec.getItemnum() + "' and t.warehouse = '"
						+ invrec.getTowarehouse() + "'");
				if (curbalsum != null) {
					invuse.setCurbal(curbalsum);
					invuse.setCurbal(0d);
					List invlotlist = this.getBaseDao().findWithQuery(
							Invlot.class,
							"itemnum = '" + invrec.getItemnum()
							+ "' and warehouse = '"
							+ invrec.getTowarehouse() + "' and lotnum='"
							+ invrec.getTolot() + "'");
					Double linecost = 0d;
					Double unitcost = 0d;
					Double actualcost = 0d;
					if (invlotlist.size() > 0) {
						Invlot invlot = (Invlot) invlotlist.get(0);
						unitcost = invlot.getLotcost();
						linecost = invlot.getLotcost();
						actualcost = invlot.getLotcost();

					}
					invuse.setActualcost(actualcost);
					invuse.setLinecost(linecost);
					invuse.setUnitcost(unitcost);
					invuse.setPhyscnt(0d);
					invuse.setConversion(1.0);
					invuse.setEnterby(this.getLaborInfo().getLabornum());
					invuse.setLotnum(invrec.getTolot());
					invuse.setDescription(invrec.getDescription());
					invuse.setEqnum(invrec.getEqnum());
					invuse.setInvrectransid(invrec.getId());

					List invreslist = this.getBaseDao().findWithQuery(Invreserve.class, "wonum='"+wonum+"' and itemnum ='"+invrec.getItemnum()+"' and warehouse='"+invrec.getTowarehouse()+"'");
					if(invreslist.size()>0)
					{
						Invreserve inverserve = (Invreserve) invreslist.get(0);
						invuse.setIssuetolabor(inverserve.getIssuetolabor());
						invuse.setLocation(inverserve.getLocation());
						invuse.setIssuedeptnum(inverserve.getIssuedeptnum());
					}

					invuse.setState("待确认");
				} else
					throw new Exception("库存暂时没有您要申请的库存项目，请先提出采购申请！");
				super.save(invuse);

			}
		}
	}

	/**
	 * @author 李阳 功能：在工单发放应用程序中点中"发放物料"按钮后，触发该事件，往invusetrans表里插数据；
	 * @throws Exception
	 *             2008-1-28下午02:12:39
	 */
	public void geninvuse(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder workorder = (Workorder) obj;
		for (int i = 0; i < list.size(); i++) {
			Invreserve inverserve = (Invreserve) list.get(i);
			Invusetrans invuse = new Invusetrans();
			invuse.setItemnum(inverserve.getItemnum());

			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+inverserve.getItemnum()+"'");
			String desc = null;
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);
				desc = item.getDescription();
			}
			invuse.setDescription(desc);
			invuse.setWarehouse(inverserve.getWarehouse());
			invuse.setWonum(workorder.getWonum());
			invuse.setLocation(workorder.getLocation());
			invuse.setIssuetype("发放");
			invuse.setReqqty(inverserve.getReservedqty());
			invuse.setTransdate(new Date());
			invuse.setActualdate(new Date());
			Double inuqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.quantity) from Invusetrans t where t.wonum ='"
					+ inverserve.getWonum() + "' and t.itemnum='"+inverserve.getItemnum()+"'");
			if (inuqty == null)
				inuqty = 0d;
			Double issueqty = inverserve.getReservedqty() - inuqty;
			invuse.setQuantity(issueqty);
			List invstock = this.getBaseDao().findWithQuery(
					Invstock.class,
					"itemnum = '" + inverserve.getItemnum()
					+ "' and warehouse = '" + inverserve.getWarehouse()
					+ "'");
			if(invstock.size()>0)
			{
				Invstock curbal = (Invstock) invstock.get(0);
				invuse.setCurbal(curbal.getCurbal());
			}
			else
			{
				invuse.setCurbal(0d);
			}

			List avgcostlist = this.getBaseDao().findWithQuery(
					Inventory.class,
					"itemnum = '" + inverserve.getItemnum()
					+ "' and warehouse = '" + inverserve.getWarehouse()
					+ "'");
			if(avgcostlist.size()>0)
			{
				Inventory avgcost = (Inventory) avgcostlist.get(0);
				invuse.setUnitcost(avgcost.getAvgcost());
				Double linecost = inverserve.getReservedqty() * avgcost.getAvgcost();
				invuse.setLinecost(linecost);
				invuse.setActualcost(avgcost.getAvgcost());
			}
			else
			{
				invuse.setUnitcost(0d);
				invuse.setLinecost(0d);
				invuse.setActualcost(0d);

			}


			invuse.setConversion(1.0);
			invuse.setPhyscnt(0d);
			invuse.setEnterby(this.getLaborInfo().getLabornum());
			invuse.setState("待确认");
			//---------------------------------
			//invuse.setSitenum(this.getLaborInfo().getSitenum());
			//invuse.setCorpnum(this.getLaborInfo().getCorpnum());

			super.save(invuse);

		}
	}

//	///////////////////业务方法//////////////////////////////////

	/* 
	 * 功能：校验发放记录。校验发放记录中对应箱柜上的余量是否满足发放需求。
	 * 作者：李阳
	 * 日期：2008-3-12下午08:45:40
	 */

	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder workorder = (Workorder) obj;

		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);			
			// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			super.save(inv);
			/************************************************************************************************************
			 * ***************************判断是否是批次管理的****************************************************************
			 * ************************************************************************************************************
			 * ************************************************************************************************************
			 */
			String lottype = null;
			String itemnum = inv.getItemnum();
			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);
				lottype = item.getLottype();
				if(lottype.equals("批次管理") && Util.isNull(inv.getLotnum()))
				{
					throw new Exception("库存项目'"+itemnum+"'为批次管理的物资，请在发放行中选择箱柜确定发放的批次！");
				}
			}

			List invstocklist = null;
			Double issueqty = inv.getQuantity();
			/*******************************************************
			 *******************箱柜号不为空情况***********************
			 *******************************************************/
			if(Util.isNotNull(inv.getItemnum()))
			{
				if(lottype.equals("批次管理"))  //批次管理
				{
					if(Util.isNotNull(inv.getBinnum()))//箱柜不为空
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"' and lotnum = '"+inv.getLotnum()+"'");
					}
					else//箱柜为空
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and lotnum = '"+inv.getLotnum()+"' and binnum is null ");
					}

					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						this.getBaseDao().updateObject(invstock);
						inv.setState("已完成");
						this.getBaseDao().updateObject(inv);										
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							this.getBaseDao().updateObject(invstock);
							inv.setState("已完成");
							this.getBaseDao().updateObject(inv);
						}
						else
							throw new Exception("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
					}




				}
				else    //非批次管理
				{
					if(Util.isNotNull(inv.getBinnum()))//箱柜不为空
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
					}
					else//箱柜为空
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
					}


					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						this.getBaseDao().updateObject(invstock);
						inv.setState("已完成");
						super.save(inv);										
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							this.getBaseDao().updateObject(invstock);
//							super.delete(invstock);
							inv.setState("已完成");
							this.getBaseDao().updateObject(inv);
						}
						else
							throw new Exception("发放行中库存项目编号为："+inv.getItemnum()+"库存余量小于发放数量,不能进行发放");
					}
				}

				//预留数量相应的减少，发放完毕，删除Invreserve
				List invlist = this.getBaseDao().findWithQuery(Invreserve.class, "itemnum='"+itemnum+"' and wonum='"+inv.getWonum()+"' and warehouse='"+inv.getWarehouse()+"'");
				if (invlist.size()>0) {
					Invreserve invre =(Invreserve) invlist.get(0);
					if ((invre.getReservedqty()-issueqty)==0){
						this.getBaseDao().deleteObject(invre);
					}else{
						invre.setReservedqty((invre.getReservedqty())-issueqty);
						this.getBaseDao().updateObject(invre);
					}
				}

			}
			else
				throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");


			if(Util.isNotNull(workorder.getEqnum()))
			{
				Item item  = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum",itemnum);
				if(item != null)
				{
					if(item.getSpareautoadd().equals("是"))
					{
						int existcount = this.getBaseDao().selectCountByHql("select count(*) from Eqsparepart t where t.eqnum ='"+workorder.getEqnum()+"'  and t.itemnum = '"+inv.getItemnum()+"'");
						if(existcount == 0)
						{
							Eqsparepart eqsparepart =  new Eqsparepart();
							eqsparepart.setEqnum(workorder.getEqnum());
							eqsparepart.setItemnum(item.getItemnum());
							eqsparepart.setDescription(item.getDescription());
							eqsparepart.setQuantity(inv.getQuantity());
							this.getBaseDao().saveObject(eqsparepart);

						}
					}
				}

			}
			//修改资产数据
			if(Util.isNotNull(inv.getEqnum()))
			{
				// 写回equipment表数据
				List equiplist = this.getBaseDao().findWithQuery(
						Equipment.class,
						"eqnum ='" + inv.getEqnum() + "'");
				if (equiplist.size() > 0) {
					Equipment equipment = (Equipment) equiplist.get(0);
					String location = null;
					if(Util.isNotNull(inv.getLocation()))
					{
						location = inv.getLocation();
					}
					else
					{
						if(Util.isNotNull(workorder.getLocation()))
						{
							location = workorder.getLocation();
						}
						else
						{
							location = "Admin";

						}



					}
					equipment.setLocation(inv.getLocation());
					equipment.setDeptnum(inv.getIssuedeptnum());
					equipment.setLabornum(inv.getIssuetolabor());
					equipment.setRundate(new Date());
					equipment.setStatus("在用");
					this.getBaseDao().updateObject(equipment);

				}

				Eqtrans eqtrans = new Eqtrans();
				eqtrans.setEqnum(inv.getEqnum());
				eqtrans.setMoveby("Admin");
				eqtrans.setTransdate(workorder.getStatusdate());
				eqtrans.setDatemoved(workorder.getStatusdate());
				eqtrans.setToloc(inv.getLocation());
				eqtrans.setMemo("标识:从仓库发放");
				this.getBaseDao().saveObject(eqtrans);
			}


		}
	}

	/*********************生成预留*****************ljh*******************/
	/* 
	 * 功能：批准工单的同时，为工单物料生成预留信息。
	 * 作者：李阳
	 * 日期：2008-3-12下午08:46:29
	 */
	public void createinvr(Object obj) throws Exception
	{
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder wo = (Workorder) obj;
		String wonum = wo.getWonum();
		List wplist = this.getBaseDao().findWithQuery(Wpmaterial.class,
				"wonum = '" + wonum + "' and prnum is null and prlinenum is null and itemqty>0");
		Pr pr = null;
		Po po = null;
		Prline prline = null;
		String requireeq = null;
		if (wplist.size() > 0) {
			for (int i = 0; i < wplist.size(); i++) {
				Wpmaterial wpmat = (Wpmaterial) wplist.get(i);

				if(Util.isNull(wpmat.getItemnum()))
				{
					throw new Exception("描述为:'"+wpmat.getDescription()+"'规格为'"+wpmat.getModelnum()+"'的记录，采购编码不能为空");
				}

				//需要采购数量
				Double needorderqty = 0d;
				/****************是否生成采购申请***********************/
				String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"+wpmat.getItemnum()+"' and warehouse ='"+wpmat.getWarehouse()+"'";
				//库存总余量
				Double sumcurbal =(Double) this.getBaseDao().selectSumByHql(sql);
				//预留数量
//				Double reserveqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.reservedqty) from Invreserve t where t.reqnum <> '"+wpmat.getMatreqnum()+"' and t.itemnum = '"+wpmat.getItemnum()+"' and t.warehouse = '"+wpmat.getWarehouse()+"' and t.ponum is null and t.polinenum is null");
				Double reserveqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.reservedqty) from Invreserve t where t.wonum <> '"+wonum+"' and t.itemnum = '"+wpmat.getItemnum()+"' and t.warehouse = '"+wpmat.getWarehouse()+"'");

				if(sumcurbal == null)
				{
					sumcurbal = 0d;
				}
				if(reserveqty == null)
				{
					reserveqty = 0d;
				}

				if(reserveqty - sumcurbal >=0)//当前的库存余量已经预留给已经批准的申请，只是暂时没有发出。
				{
					needorderqty = -wpmat.getItemqty();
				}
				else//现有库存除去预留数量（包括已经预留，但未发出数量），还有结余。
				{
					needorderqty = sumcurbal-reserveqty-wpmat.getItemqty();
				}


				if (needorderqty<0) 
				{
					if (pr == null)
					{
						//生成采购申请
						pr = (Pr)this.createpr(wpmat,wo);
					}
					if (pr instanceof Pr)
					{
						prline = this.createprline(wpmat, pr, -needorderqty);
					}
				}

				//往预留表中插入数据
				this.createserve(wpmat, wo,prline);


			}
			this.getBaseDao().updateObject(wo);


			//判断是否自动启动工作流
			//************************自动启动工作流(开始)××××××××××××××××××××××××××××××××××××//
			if(pr != null)
			{
				PrSrv prsrv = (PrSrv)IBOSrvUtil.getSrv("pr");
				try
				{
					prsrv.workflow(pr,true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}


			if(po != null)
			{
				PoSrv posrv = (PoSrv)IBOSrvUtil.getSrv("po");
				try
				{
					posrv.workflow(po,true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}



			//************************自动启动工作流(结束)××××××××××××××××××××××××××××××××××××//
//			Messagebox.show("预留生成完毕！");
		} 
		else 
		{
			Messagebox.show("该单据下没有满足条件的记录或者已经全部生成\n预留，无法生成预留！");
			return;
		}
	}

	//生成物料预留
	public void createserve(Object obj,Object obj2,Object obj3) throws Exception
	{
		Wpmaterial mater = (Wpmaterial) obj;
		Workorder wo = (Workorder) obj2;
		Prline prline = (Prline) obj3;
		Warehouse warehouse = null;
		String loc = null;
		if (mater.getWarehouse() != null) {
			List warelist = this.findWithQuery(Warehouse.class, "warehouse='"
					+ mater.getWarehouse() + "'");
			warehouse = (Warehouse) warelist.get(0);
		} else {
			throw new Exception("请补充仓库信息！");
		}
		Invreserve invreserve = new Invreserve();
		invreserve.setDirectreq("否");
		invreserve.setIssuetolabor(wo.getReportedby());

		String labornum = wo.getReportedby();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class, "labornum ='"+labornum +"'");
		if(laborlist.size()>0)
		{
			Labor labor = (Labor) laborlist.get(0);
			loc = labor.getDefaultloc();
		}
		else
		{
			List loclist = this.getBaseDao().findWithQuery(Locations.class, "parent is null");
			Locations location = (Locations) loclist.get(0);
			loc = location.getLocation();
		}
		String buditem = mater.getBuditem();
		String budnum = mater.getBudnum();
		if(Util.isNotNull(buditem)){
			invreserve.setBuditem(buditem);
		}
		else{
			invreserve.setBuditem("");
		}
		if(Util.isNotNull(budnum)){
			invreserve.setBudnum(budnum);
		}else{
			invreserve.setBudnum("");
		}
		invreserve.setLocation(loc);
		invreserve.setIssuedeptnum(wo.getCrewid());
		invreserve.setItemnum(mater.getItemnum());
		invreserve.setReqby(mater.getRequestby());
		invreserve.setIssuetolabor(mater.getIssueto());
		invreserve.setReqdate(new java.util.Date());
		invreserve.setReqnum(mater.getMatreqnum());
		invreserve.setRequireddate(mater.getRequiredate());
		invreserve.setReservedqty(mater.getItemqty());
		invreserve.setWarehouse(mater.getWarehouse());
		invreserve.setWonum(mater.getWonum());
		if(prline!=null)
		{
			invreserve.setPonum(prline.getPrnum());
			invreserve.setPolinenum(prline.getPrlinenum());
		}

		this.getBaseDao().saveObject(invreserve);
	}

	//生成采购申请
	public Pr createpr(Wpmaterial mater,Workorder wo) throws Exception 
	{
		Pr pr = new Pr();

		String desc = "由工单{"+ wo.getWonum()+":"+wo.getDescription() +"}物料计划生成";
		pr.setPrnum(prsrv.genInskey("prnum"));
		pr.setStatus("已批准");
		pr.setStatusdate(new Date());
		pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setChangedate(new Date());
		pr.setDescription(desc);
		pr.setRequestdate(new Date());
		pr.setRequestedby(wo.getReportedby());
		pr.setPrtype("采购申请");
		//pr.setSitenum(wo.getSitenum());
		//pr.setCorpnum(wo.getCorpnum());
		this.getBaseDao().saveObject(pr);
		return pr;
	}

	//生成采购申请行
	public Prline createprline(Wpmaterial mater,Pr pr,double genqty) throws Exception 
	{
		Prline pl = new Prline();

		int PrlInt = this.getRowCountByWhere(pl, "prnum='"+pr.getPrnum()+"'");	
		pl.setPrnum(pr.getPrnum());
		pl.setPrlinenum( (long)PrlInt+1);
		pl.setConversion(1.00);
		pl.setEnterdate(new Date());
		pl.setEnterby(this.getLaborInfo().getLaborname());
		pl.setPrlinenum((long)PrlInt+1);
		pl.setProrateservice("否");
		pl.setIsservice("否");
		pl.setOrderunit(mater.getOrderunit());
		pl.setLoadedcost(0d);	
		pl.setItemnum(mater.getItemnum());
		pl.setDescription(mater.getDescription());
		pl.setModelnum(mater.getModelnum());
		pl.setWarehouse(mater.getWarehouse());
		pl.setRemark(mater.getRemark());

		Double unitcost = 0d;
		List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum = '"+pl.getItemnum()+"'");
		List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+mater.getItemnum()+"' and warehouse = '"+mater.getWarehouse()+"'");
		if (itemlist.size()>0)
		{
			Item item = (Item)itemlist.get(0);
			pl.setInspection(item.getInspectreq());
			String unit = item.getOrderunit();
			if(inventorylist.size()>0)
			{
				Inventory inventory = (Inventory) inventorylist.get(0);
				pl.setStocktype(inventory.getStocktype());//库存类型
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(mater.getManufacturer());		
		pl.setOrderqty(genqty);//订购数量
		pl.setUnitcost(unitcost);
		pl.setLinecost(unitcost*genqty);
		this.getBaseDao().saveObject(pl);

		List invreslist = this.findWithQuery(Invreserve.class, "reqnum='"+mater.getMatreqnum()+"' and itemnum='"+mater.getItemnum()+"' and warehouse ='"+mater.getWarehouse()+"'");
		if(invreslist.size()>0)
		{
			Invreserve invr = (Invreserve) invreslist.get(0);
			invr.setPonum(pl.getPrnum());
			invr.setPolinenum(pl.getPrlinenum());
			this.getBaseDao().updateObject(invr);
		}
		mater.setPrnum(pl.getPrnum());
		mater.setPrlinenum(pl.getPrlinenum());
		this.getBaseDao().updateObject(mater);
		return pl;
	}

	/*************************生成工具预留的方法********************************/
	public void createToolreserve(Object obj) throws Exception
	{
		Wptool wptool = (Wptool) obj;
		Toolreserve toolreserve = new Toolreserve();

		toolreserve.setReqlabor(this.getLaborInfo().getLaborname());
		toolreserve.setReqdate(new Date());
		toolreserve.setReqnum(wptool.getWonum());
		toolreserve.setToolnum(wptool.getToolnum());
		toolreserve.setReservedqty(wptool.getToolqty());
		toolreserve.setLinecost(wptool.getToolhrs() * wptool.getRate());
		toolreserve.setToolhrs(wptool.getToolhrs());
		List getworkorder = this.getBaseDao().findWithQuery(Workorder.class, "wonum = '"+wptool.getWonum()+"'");
		if (getworkorder.size()>0)
		{
			Workorder workorder = (Workorder) getworkorder.get(0);
			toolreserve.setUserdept(workorder.getCrewid());
		}
		this.getBaseDao().saveObject(toolreserve);
	}

	/* 
	 * 功能：库存项目发放后，且检验过，可以进行退库操作
	 * 作者：李阳
	 * 日期：2008-3-12下午08:52:34
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder workorder = (Workorder) obj;
		String wonum = workorder.getWonum();

		for (int i = 0; i < list.size(); i++) {
			Invusetrans invusetrans = (Invusetrans) list.get(i);
			List invuselist = this.getBaseDao().findWithQuery(Invusetrans.class, "wonum = '" + wonum + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype = '发放' and state ='已完成'");
			if(invuselist.size() > 0)
			{
				Invusetrans newinvuse = new Invusetrans();
				newinvuse.setItemnum(invusetrans.getItemnum());
				newinvuse.setWarehouse(invusetrans.getWarehouse());
				newinvuse.setWonum(invusetrans.getWonum());
				newinvuse.setLocation(invusetrans.getLocation());
				newinvuse.setIssuetype("退回");
				newinvuse.setTransdate(new Date());
				newinvuse.setActualdate(new Date());
				String binnum = invusetrans.getBinnum();

				//newinvuse.setSitenum(invusetrans.getSitenum());
				// newinvuse.setCorpnum(invusetrans.getCorpnum());
				newinvuse.setBinnum(binnum);
				Double issueqty = 0d;
				if(Util.isNotNull(binnum))
				{
					issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and state ='已完成'");
					if(issueqty == null)
						issueqty = 0d;		    	
				}
				else
				{
					issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum is null and state ='已完成'");
					if(issueqty == null)
						issueqty = 0d;
				}
				newinvuse.setDescription(invusetrans.getDescription());
				newinvuse.setQuantity(-issueqty);
				List invstock =this.getBaseDao().findWithQuery(Invstock.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse() +"'");
				Invstock curbal = (Invstock) invstock.get(0);
				newinvuse.setCurbal(curbal.getCurbal());
				newinvuse.setPhyscnt(curbal.getPhyscnt());
				List avgcostlist =this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse()+"'");
				Inventory avgcost = (Inventory)avgcostlist.get(0);
				newinvuse.setUnitcost(avgcost.getAvgcost());
				Double linecost = (issueqty) * avgcost.getAvgcost();
				newinvuse.setLinecost(linecost);
				newinvuse.setActualcost(avgcost.getAvgcost());
				newinvuse.setConversion(1.0);
				newinvuse.setEnterby(this.getLaborInfo().getLabornum());
				newinvuse.setState("待确认");
				//newinvuse.setSitenum(invusetrans.getSitenum());
				//newinvuse.setCorpnum(invusetrans.getCorpnum());
				super.save(newinvuse);
			}
			else
				throw new Exception("没有可以退库的发放行！");			
		}
	}

	/* 
	 * 功能：在进行退库操作后，对退库的退库行记录进行校验。
	 * 作者：李阳
	 * 日期：2008-3-12下午10:28:32
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("对象传递不正确!");
		Workorder workorder = (Workorder) obj;
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);

			// 先更新invrectrans表中记录的 quantity ，否则把界面上修改的接收数量丢失；
			super.save(inv);
			Double returnqty = inv.getQuantity();
			Double hasissueqty = 0d;
			List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse()+ "'");
			Inventory inventory = (Inventory) inventorylist.get(0);
			Double avgcost = 0d;
			if(inv.getBinnum() != null)
			{
				Integer length = inv.getItemnum().trim().length();
				if(length > 0)
				{
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();	
					hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and state ='已完成'");
					if(hasissueqty + returnqty >= 0)
					{
						invstock.setCurbal(curbal - returnqty);
						avgcost = (- returnqty * inv.getUnitcost() + curbal * inventory.getAvgcost())/(curbal-returnqty);
						inventory.setAvgcost(avgcost);
						super.save(inventory);
						super.save(invstock);
						inv.setState("已完成");
						super.save(inv);										
					}
					else
					{
						throw new Exception("库存项目编号为："+inv.getItemnum()+"且类型为退库的发放行中退库数量大于发放合计数量，不能退库");
					}
				}
				else
					throw new Exception("请输入有效的箱柜号，箱柜号中全部为空格");										
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal1 = invstock.getCurbal();
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='发放' and state ='已完成'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal1 - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal1 * inventory.getAvgcost())/(curbal1-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("已完成");
					super.save(inv);									
				}
				else
				{
					throw new Exception("库存项目编号为："+inv.getItemnum()+"且类型为退库的发放行中退库数量大于发放合计数量，不能退库");

				}

			}
		}
	}
	/* 
	 * 功能：创建故障代码树
	 * 作者：李阳
	 * 日期：Dec 17, 2008  1:57:37 PM
	 */
	public void createfailcode(Object obj) throws Exception {
		Workorder wo = (Workorder) obj;
		String failcode = wo.getFailurecode();
		String wonum = wo.getWonum();
		//String sitenum = wo.getSitenum();
		int count=0;
		List wofailcodelist = this.getBaseDao().findWithQuery(Wofailcode.class,
				"wonum ='" + wonum +  "'");
		if (wofailcodelist.size() > 0) {
			/****************先遍历wofailcode*************/
			for (int i = 0; i < wofailcodelist.size(); i++) {
				Wofailcode wofailcode = (Wofailcode) wofailcodelist.get(i);
				{
					/**********判断wofailcode在failurecode中是否存在*******/
					int failurecodecount = this.getBaseDao().selectCountByHql("select count(*) from Failurecode t where t.failurecode ='"+wofailcode.getFailurecode()+"' and t.classid = '"+wofailcode.getClassid()+"'");
					if(failurecodecount == 0)
					{
						Failurecode newfailurecode = new Failurecode();
						newfailurecode.setClassid(wofailcode.getClassid());
						newfailurecode.setDescription(wofailcode.getDescription());
						newfailurecode.setFailurecode(wofailcode.getFailurecode());
						this.getBaseDao().saveObject(newfailurecode);
						count++;
					}

					/**********判断wofailproblem在failureproblem中是否存在*******/

					String fcode = wofailcode.getFailurecode();
					List wofailproblemlist = this.getBaseDao().findWithQuery(Wofailproblem.class,"wonum ='" + wonum + "' and failurecode ='" + fcode+ "'");
					for (int j = 0; j < wofailproblemlist.size(); j++) {
						Wofailproblem wofailproblem = (Wofailproblem) wofailproblemlist.get(j);
						int problemcount = this.getBaseDao().selectCountByHql("select count(*) from Failureproblem t where t.failurecode='" + fcode + "'and t.failureproblem='"+wofailproblem.getFailureproblem()+"'");
						if(problemcount == 0)
						{
							Failureproblem  newfailproplem = new Failureproblem();
							newfailproplem.setFailurecode(fcode);
							newfailproplem.setFailureproblem(wofailproblem.getFailureproblem());
							newfailproplem.setDescription(wofailproblem.getDescription());
							count++;
							this.getBaseDao().saveObject(newfailproplem);

						}
						/**********判断wofailcause在failurecause中是否存在*******/
						List wofailcauselist = this.getBaseDao().findWithQuery(Wofailcause.class,"wonum='" + wonum + "'and failureproblem='"+ wofailproblem.getFailureproblem()+ "'");
						for (int k = 0; k < wofailcauselist.size(); k++) {
							Wofailcause wofailcause = (Wofailcause) wofailcauselist.get(k);
							int causecount = this.getBaseDao().selectCountByHql("select count(*) from Failurecause t where t.failurecause='" + wofailcause.getFailurecause() + "'and t.failureproblem='"+wofailcause.getFailureproblem()+"'");
							if(causecount == 0)
							{
								Failurecause  newfailurecause = new Failurecause();
								newfailurecause.setFailureproblem(wofailcause.getFailureproblem());
								newfailurecause.setFailurecause(wofailcause.getFailurecause());
								newfailurecause.setDescription(wofailcause.getDescription());
								count++;
								this.getBaseDao().saveObject(newfailurecause);

							}
							/**********判断wofaildeal在failuredeal中是否存在*******/
							List wofaildeallist = this.getBaseDao().findWithQuery(Wofaildeal.class,"wonum='" + wonum + "'and failurecause='"+ wofailcause.getFailurecause()+ "'");
							for(int m = 0; m<wofaildeallist.size();m++)
							{
								Wofaildeal wofaildeal = (Wofaildeal) wofaildeallist.get(m);
								int dealcount = this.getBaseDao().selectCountByHql("select count(*) from Failuredeal t where t.failurecause='" + wofaildeal.getFailurecause() + "'and t.failuredeal='"+wofaildeal.getFailuredeal()+"'");
								if(causecount == 0)
								{
									Failuredeal  newfailuredeal = new Failuredeal();
									newfailuredeal.setFailurecause(wofaildeal.getFailurecause());
									newfailuredeal.setFailuredeal(wofaildeal.getFailuredeal());
									newfailuredeal.setDescription(wofaildeal.getDescription());
									count++;
									this.getBaseDao().saveObject(newfailuredeal);
								}



							}

						}

					}
				}

			}

		}
		if(count>0)
		{
			Messagebox.show("已成功创建故障代码！");
		}
		else
		{
			Messagebox.show("故障代码已存在，无需创建故障代码！");
		}
	}

	@Override
	public boolean validData(Object mainobject, Wfinstance wfinstance) throws Exception {
		if(this.getRecWnd()!=null)
		{
			String app = this.getRecWnd().getApp();
			if(app.equals("PMWORKORDER"))
			{
				Workorder workorder = (Workorder)mainobject;
				String dolabor = workorder.getSupervisor();
				String status = workorder.getStatus();
				if(!Util.isNotNull(dolabor)&&status.equals("流程未启动"))
				{
					Messagebox.show("请指定预防性维护工作执行人，再发送工作流！");
					return false;
				}

			}
		}						
		return true;
	}		
}