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
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class WorkorderImpl extends IBOBaseImpl implements WorkorderSrv {

	PrSrv prsrv = (PrSrv)IBOSrvUtil.getSrv("pr");
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */			
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Workorder))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "wpmaterialTable");
		this.deleteAllChild(obj, "wptaskTable");
		this.deleteAllChild(obj, "wptoolTable");
		this.deleteAllChild(obj, "wplaborTable");
		this.deleteAllChild(obj, "wpvendorTable");
		this.deleteAllChild(obj, "invreserve");
		this.deleteAllChild(obj, "wofailclass");

	}	
	/* 
	 * ���ܣ����ƹ�����ѡ��һ��������¼�����и��ơ�
	 * ���ߣ�����
	 * ���ڣ�2008-3-10����10:20:50
	 */
	public Workorder copywo(Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
		Workorder wo = (Workorder) obj;
		Workorder newwo = new Workorder();	
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class,"labornum ='" + labornum+ "'");
		Labor labor = (Labor) laborlist.get(0);
		String project = labor.getDefaultstoreloc();		
		String wonum = this.genInskey("wonum");
		newwo.setWonum(wonum);
		newwo.setStatus("����δ����");
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
		/******************���Ʊ�׼��ҵ�ƻ���ϸ����************************/
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
		//******************���ƹ�����������Ϣ************************//*
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
		//******************���ƹ����й�����Ϣ************************//*
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
		//******************���ƹ������˹���Ϣ************************//*
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
		//******************���ƹ����га���λ��Ϣ************************//*
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
	 * ���ܣ����湤�����ڱ��湤��ʱ���жϱ�׼��ҵ�ƻ��Ƿ��޸ģ�����޸��ˣ��������ɹ����ƻ���
	 * ���ߣ�����
	 * ���ڣ�2008-3-12����08:44:39
	 */
	public void save(Object arg0) throws Exception 
	{
		if (arg0 instanceof Workorder)
		{
			Workorder workorder = (Workorder) arg0;
			String jpnum = workorder.getJpnum();
			if(workorder.getId()==null) //���½���¼
			{
				/////////////////ɾ���رյĹ��������е�Ԥ������//////////ljh///////
				if(!this.isWFActive(arg0))
				{
					this.deleteAllChild(workorder, "invreserve");
				}

				// ����û�ѡ���˱�׼��ҵ�ƻ�,�򽫱�׼��ҵ�ƻ�����д����Ӧ�ı���
				if(Util.isNotNull(jpnum))
				{
					this.genJobplan(workorder, jpnum);
				}
			}
			else //���¼�¼
			{
				if(Util.isNotNull(jpnum))
				{
					int count = this.getRowCountByWhere(workorder,
							"wonum='"+workorder.getWonum()+"' and jpnum='"+workorder.getJpnum()+"'");
					if(count<=0)
					{
						//��ҵ�ƻ�ԭ�������ڣ������޸���ӵļƻ���ţ���Ҫ������ҵ�ƻ�
						this.genJobplan(workorder, jpnum);
					}
				}
			}		

			//�ж�ѡ���ʱ���Ƿ���ȷ����ʼʱ������ڽ���ʱ��֮ǰ
			Date schedstart = workorder.getSchedstart();//�ƻ���ʼʱ��
			Date schedfinish = workorder.getSchedfinish();//�ƻ�����ʱ��

			Date actstart = workorder.getActstart();//ʵ�ʿ�ʼʱ��
			Date actfinish = workorder.getActfinish();//ʵ�����ʱ��

			if (schedstart != null) 
			{
				if (schedfinish != null){
					if (schedstart.after(schedfinish)){
						throw new Exception("��ʼʱ��������ڽ���ʱ�䣡");
					}
				}else {
					throw new Exception("��ѡ�����ʱ�䣡");
				}
			}

			if (actstart != null) 
			{
				if (actfinish != null){
					if (actstart.after(actfinish)){
						throw new Exception("��ʼʱ��������ڽ���ʱ�䣡");
					}
				}else {
					throw new Exception("��ѡ�����ʱ�䣡");
				}
			}
			//�����Լ�
			super.save(workorder);
		}	
	}	
	/* 
	 * ���ܣ����û����ƹ����󣬹����еı�׼��ҵ�ƻ���������׼��ҵ�ƻ�ģ����
	 * ���ߣ�����
	 * ���ڣ�2008-4-15����09:13:51
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
			newobj.setStatus("��ʽ");
			newobj.setStatusdate(new Date());
			newobj.setChangby(this.getUserInfo().getLabornum());
			newobj.setChangdate(new Date());
			newobj.setJpduration(jobplan.getJpduration());
			super.save(newobj);
			this.createjoplandetail(wo,jpnum);
			Messagebox.show("�ѳɹ�������׼��ҵ�ƻ�"+ jpnum);			
		}
		else
		{
			throw new Exception("�ù�����׼��ҵ�ƻ�Ϊ�գ����ܴ�����׼��ҵ�ƻ�");
		}

	}	
	/* 
	 * ���ܣ�������׼��ҵ�ƻ�����������ϡ����ߺ�������Ϣ��
	 * ���ߣ�����
	 * ���ڣ�2008-4-15����09:36:32
	 */
	public void createjoplandetail(Workorder workorder,String jpnum)//private
	throws Exception
	{
		// �����ƻ��˹���
		List wplaborlist = this.getBaseDao().findWithQuery(Wplabor.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wplaborlist.size(); i++)
		{
			Wplabor wplabor = (Wplabor)wplaborlist.get(i);
			Joblabor joblabor = new Joblabor();
			// ��֯����
			joblabor.setLaborhrs(wplabor.getLaborhrs());
			joblabor.setLabornum(wplabor.getLabornum());
			joblabor.setQty(wplabor.getLaborqty());
			joblabor.setRate(wplabor.getRate());
			joblabor.setTasknum(wplabor.getTasknum());
			joblabor.setJpnum(jpnum);
			joblabor.setVendor(wplabor.getVendor());
			super.save(joblabor);
		}
		// �����ƻ����ϱ�
		List wpmateriallist = this.getBaseDao().findWithQuery(Wpmaterial.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wpmateriallist.size(); i++)
		{
			Wpmaterial wpmaterial = (Wpmaterial)wpmateriallist.get(i);
			Jobmaterial jobmaterial = new Jobmaterial();
			// ��֯����
			jobmaterial.setItemnum(wpmaterial.getItemnum());
			jobmaterial.setQty(wpmaterial.getItemqty());
			jobmaterial.setTasknum(wpmaterial.getTasknum());
			jobmaterial.setJpnum(jpnum);
			jobmaterial.setVendor(wpmaterial.getVendor());
			jobmaterial.setWarehouse(wpmaterial.getWarehouse());
			super.save(jobmaterial);
		}
		// ���������
		List wptasklist = this.getBaseDao().findWithQuery(Wptask.class, " wonum='" +workorder.getWonum()+"'");
		for(int i=0;i < wptasklist.size(); i++)
		{
			Wptask wptask = (Wptask)wptasklist.get(i);
			Jobtask jobtask = new Jobtask();
			// ��֯����
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
		// �����ƻ����߱�
		List wptoollist = this.getBaseDao().findWithQuery(Wptool.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < wptoollist.size(); i++)
		{
			Wptool wptool = (Wptool)wptoollist.get(i);
			Jobtool jobtool = new Jobtool();
			// ��֯����
			jobtool.setTasknum(wptool.getTasknum());
			jobtool.setJpnum(jpnum);
			jobtool.setToolhrs(wptool.getToolhrs());
			jobtool.setToolnum(wptool.getToolnum());
			jobtool.setQty(wptool.getToolqty());
			super.save(jobtool);
		}
		// ά�޼ƻ��а���
		List wpvendorlist = this.getBaseDao().findWithQuery(Wpvendor.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < wpvendorlist.size(); i++)
		{
			Wpvendor wpvendor = (Wpvendor)wpvendorlist.get(i);
			Jobvendor jobvendor = new Jobvendor();
			// ��֯����
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
	 * ������ҵ�ƻ�
	 * brianhong  2008-2-20
	 * @param workorder
	 * @param jpnum
	 * @throws Exception
	 */

	public void genJobplan(Workorder workorder,String jpnum)//private
	throws Exception
	{
		// �����ƻ��˹���
		List joblabors = this.getBaseDao().findWithQuery(Joblabor.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < joblabors.size(); i++)
		{
			Wplabor wplabor = new Wplabor();
			Joblabor joblabor = (Joblabor)joblabors.get(i);
			// ��֯����
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
		// �����ƻ����ϱ�
		List jobmaterials = this.getBaseDao().findWithQuery(Jobmaterial.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobmaterials.size(); i++)
		{
			Jobmaterial jobmaterial = (Jobmaterial)jobmaterials.get(i);
			Wpmaterial wpmaterial = new Wpmaterial();
			// ��֯����
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
		// ���������
		List jobtasks = this.getBaseDao().findWithQuery(Jobtask.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobtasks.size(); i++)
		{
			Jobtask jobtask = (Jobtask)jobtasks.get(i);
			Wptask wptask = new Wptask();
			// ��֯����
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
		// �����ƻ����߱�
		List jobtools = this.getBaseDao().findWithQuery(Jobtool.class, " jpnum='" +jpnum+"'");
		for(int i=0;i < jobtools.size(); i++)
		{
			Jobtool jobtool = (Jobtool)jobtools.get(i);
			Wptool wptool = new Wptool();
			// ��֯����
			wptool.setWonum(workorder.getWonum());
			wptool.setRate(0.0);
			wptool.setTasknum(jobtool.getTasknum());
			wptool.setJpnum(jobtool.getJpnum());
			wptool.setToolhrs(jobtool.getToolhrs());
			wptool.setToolnum(jobtool.getToolnum());
			wptool.setToolqty(jobtool.getQty());
			super.save(wptool);
		}
		// ά�޼ƻ��а���
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
	 *      @author:yuanjq @description:ɾ����׼��ҵ�ƻ��������Ϣ @ 2007-7-6 ����10:18:24
	 */
	public void deljbplan(Object obj) throws Exception 
	{
		// TODO �Զ����ɷ������
		Workorder workorder = (Workorder) obj;
		// ɾ���ӱ���Ϣ
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

		// ���µ�ǰ�����ı�׼��ҵ�ƻ��ֶ�
		workorder.setJpnum("");
		this.update(workorder);		
	}


	/* 
	 * ���ܣ��ڷ��ţ��豸��Ӧ�ó����У�����ѡ�е���ת�����з��ţ�
	 * ���ߣ�����
	 * ���ڣ�Nov 4, 2008  12:53:04 PM
	 */
	public void genequse(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
		Workorder wo = (Workorder) obj;
		String wonum = wo.getWonum();

		/**************�ж�ѡ���Ҫ���ŵ������Ƿ����Ԥ������****************/
		String idstr2 = null;
		List InvrList = this.getBaseDao().findWithQuery(Invreserve.class, "wonum = '"+ wonum +"'");
		if(InvrList.size() > 0)
		{
			for(int i=0;i<InvrList.size();i++)
			{
				Invreserve invreserve = (Invreserve) InvrList.get(i);
				Double waitqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + wonum + "' and t.itemnum = '" + invreserve.getItemnum() + "' and state ='��ȷ��'");
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
					Double resqty =invres.getReservedqty();//Ԥ������
					Double sumqty = 0d;//ѡ�еĽ�Ҫ���ŵļ�¼
					for(int k=0;k<list.size();k++)
					{
						Invrectrans invrec = (Invrectrans) list.get(k);//ȡ��ѡ�еĽ��ռ�¼��
						if(invrec.getItemnum().equals(invres.getItemnum())&& invrec.getTowarehouse().equals(invres.getWarehouse()))
						{
							sumqty = sumqty + invrec.getQuantity();
						}

					}
					Double hasqty = (Double) this.getBaseDao().selectSumByHql("select  sum(t.quantity) from Invusetrans t where t.itemnum ='"+invres.getItemnum()+"' and t.wonum ='"+wonum+"'and t.issuetype = '����' and t.state = '��ȷ��' ");
					if(hasqty == null)
					{
						hasqty = 0d;
					}
					if(hasqty + sumqty-invres.getReservedqty()>0)
					{
						throw new Exception("��ѡ���Ҫ���ŵ��ʲ���������Ԥ�����������ܷ��ţ�");
					}

				}

			}


			for (int i = 0; i < list.size(); i++) {
				Invrectrans invrec = (Invrectrans) list.get(i);
				Invusetrans invuse = new Invusetrans();
				invuse.setItemnum(invrec.getItemnum());
				invuse.setWarehouse(invrec.getTowarehouse());
				invuse.setWonum(wo.getWonum());
				invuse.setIssuetype("����");
				invuse.setIsprint("��");
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

					invuse.setState("��ȷ��");
				} else
					throw new Exception("�����ʱû����Ҫ����Ŀ����Ŀ����������ɹ����룡");
				super.save(invuse);

			}
		}
	}

	/**
	 * @author ���� ���ܣ��ڹ�������Ӧ�ó����е���"��������"��ť�󣬴������¼�����invusetrans��������ݣ�
	 * @throws Exception
	 *             2008-1-28����02:12:39
	 */
	public void geninvuse(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
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
			invuse.setIssuetype("����");
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
			invuse.setState("��ȷ��");
			//---------------------------------
			//invuse.setSitenum(this.getLaborInfo().getSitenum());
			//invuse.setCorpnum(this.getLaborInfo().getCorpnum());

			super.save(invuse);

		}
	}

//	///////////////////ҵ�񷽷�//////////////////////////////////

	/* 
	 * ���ܣ�У�鷢�ż�¼��У�鷢�ż�¼�ж�Ӧ����ϵ������Ƿ����㷢������
	 * ���ߣ�����
	 * ���ڣ�2008-3-12����08:45:40
	 */

	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
		Workorder workorder = (Workorder) obj;

		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);			
			// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
			super.save(inv);
			/************************************************************************************************************
			 * ***************************�ж��Ƿ������ι����****************************************************************
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
				if(lottype.equals("���ι���") && Util.isNull(inv.getLotnum()))
				{
					throw new Exception("�����Ŀ'"+itemnum+"'Ϊ���ι�������ʣ����ڷ�������ѡ�����ȷ�����ŵ����Σ�");
				}
			}

			List invstocklist = null;
			Double issueqty = inv.getQuantity();
			/*******************************************************
			 *******************���Ų�Ϊ�����***********************
			 *******************************************************/
			if(Util.isNotNull(inv.getItemnum()))
			{
				if(lottype.equals("���ι���"))  //���ι���
				{
					if(Util.isNotNull(inv.getBinnum()))//���Ϊ��
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"' and lotnum = '"+inv.getLotnum()+"'");
					}
					else//���Ϊ��
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and lotnum = '"+inv.getLotnum()+"' and binnum is null ");
					}

					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						this.getBaseDao().updateObject(invstock);
						inv.setState("�����");
						this.getBaseDao().updateObject(inv);										
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							this.getBaseDao().updateObject(invstock);
							inv.setState("�����");
							this.getBaseDao().updateObject(inv);
						}
						else
							throw new Exception("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
					}




				}
				else    //�����ι���
				{
					if(Util.isNotNull(inv.getBinnum()))//���Ϊ��
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
					}
					else//���Ϊ��
					{
						invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
					}


					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						this.getBaseDao().updateObject(invstock);
						inv.setState("�����");
						super.save(inv);										
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							this.getBaseDao().updateObject(invstock);
//							super.delete(invstock);
							inv.setState("�����");
							this.getBaseDao().updateObject(inv);
						}
						else
							throw new Exception("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
					}
				}

				//Ԥ��������Ӧ�ļ��٣�������ϣ�ɾ��Invreserve
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
				throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");


			if(Util.isNotNull(workorder.getEqnum()))
			{
				Item item  = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum",itemnum);
				if(item != null)
				{
					if(item.getSpareautoadd().equals("��"))
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
			//�޸��ʲ�����
			if(Util.isNotNull(inv.getEqnum()))
			{
				// д��equipment������
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
					equipment.setStatus("����");
					this.getBaseDao().updateObject(equipment);

				}

				Eqtrans eqtrans = new Eqtrans();
				eqtrans.setEqnum(inv.getEqnum());
				eqtrans.setMoveby("Admin");
				eqtrans.setTransdate(workorder.getStatusdate());
				eqtrans.setDatemoved(workorder.getStatusdate());
				eqtrans.setToloc(inv.getLocation());
				eqtrans.setMemo("��ʶ:�Ӳֿⷢ��");
				this.getBaseDao().saveObject(eqtrans);
			}


		}
	}

	/*********************����Ԥ��*****************ljh*******************/
	/* 
	 * ���ܣ���׼������ͬʱ��Ϊ������������Ԥ����Ϣ��
	 * ���ߣ�����
	 * ���ڣ�2008-3-12����08:46:29
	 */
	public void createinvr(Object obj) throws Exception
	{
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
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
					throw new Exception("����Ϊ:'"+wpmat.getDescription()+"'���Ϊ'"+wpmat.getModelnum()+"'�ļ�¼���ɹ����벻��Ϊ��");
				}

				//��Ҫ�ɹ�����
				Double needorderqty = 0d;
				/****************�Ƿ����ɲɹ�����***********************/
				String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"+wpmat.getItemnum()+"' and warehouse ='"+wpmat.getWarehouse()+"'";
				//���������
				Double sumcurbal =(Double) this.getBaseDao().selectSumByHql(sql);
				//Ԥ������
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

				if(reserveqty - sumcurbal >=0)//��ǰ�Ŀ�������Ѿ�Ԥ�����Ѿ���׼�����룬ֻ����ʱû�з�����
				{
					needorderqty = -wpmat.getItemqty();
				}
				else//���п���ȥԤ�������������Ѿ�Ԥ������δ���������������н��ࡣ
				{
					needorderqty = sumcurbal-reserveqty-wpmat.getItemqty();
				}


				if (needorderqty<0) 
				{
					if (pr == null)
					{
						//���ɲɹ�����
						pr = (Pr)this.createpr(wpmat,wo);
					}
					if (pr instanceof Pr)
					{
						prline = this.createprline(wpmat, pr, -needorderqty);
					}
				}

				//��Ԥ�����в�������
				this.createserve(wpmat, wo,prline);


			}
			this.getBaseDao().updateObject(wo);


			//�ж��Ƿ��Զ�����������
			//************************�Զ�����������(��ʼ)������������������������������������������������������������������������//
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



			//************************�Զ�����������(����)������������������������������������������������������������������������//
//			Messagebox.show("Ԥ��������ϣ�");
		} 
		else 
		{
			Messagebox.show("�õ�����û�����������ļ�¼�����Ѿ�ȫ������\nԤ�����޷�����Ԥ����");
			return;
		}
	}

	//��������Ԥ��
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
			throw new Exception("�벹��ֿ���Ϣ��");
		}
		Invreserve invreserve = new Invreserve();
		invreserve.setDirectreq("��");
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

	//���ɲɹ�����
	public Pr createpr(Wpmaterial mater,Workorder wo) throws Exception 
	{
		Pr pr = new Pr();

		String desc = "�ɹ���{"+ wo.getWonum()+":"+wo.getDescription() +"}���ϼƻ�����";
		pr.setPrnum(prsrv.genInskey("prnum"));
		pr.setStatus("����׼");
		pr.setStatusdate(new Date());
		pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setChangedate(new Date());
		pr.setDescription(desc);
		pr.setRequestdate(new Date());
		pr.setRequestedby(wo.getReportedby());
		pr.setPrtype("�ɹ�����");
		//pr.setSitenum(wo.getSitenum());
		//pr.setCorpnum(wo.getCorpnum());
		this.getBaseDao().saveObject(pr);
		return pr;
	}

	//���ɲɹ�������
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
		pl.setProrateservice("��");
		pl.setIsservice("��");
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
				pl.setStocktype(inventory.getStocktype());//�������
				unitcost = inventory.getAvgcost();
			}

		}
		pl.setManufacturer(mater.getManufacturer());		
		pl.setOrderqty(genqty);//��������
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

	/*************************���ɹ���Ԥ���ķ���********************************/
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
	 * ���ܣ������Ŀ���ź��Ҽ���������Խ����˿����
	 * ���ߣ�����
	 * ���ڣ�2008-3-12����08:52:34
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
		Workorder workorder = (Workorder) obj;
		String wonum = workorder.getWonum();

		for (int i = 0; i < list.size(); i++) {
			Invusetrans invusetrans = (Invusetrans) list.get(i);
			List invuselist = this.getBaseDao().findWithQuery(Invusetrans.class, "wonum = '" + wonum + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype = '����' and state ='�����'");
			if(invuselist.size() > 0)
			{
				Invusetrans newinvuse = new Invusetrans();
				newinvuse.setItemnum(invusetrans.getItemnum());
				newinvuse.setWarehouse(invusetrans.getWarehouse());
				newinvuse.setWonum(invusetrans.getWonum());
				newinvuse.setLocation(invusetrans.getLocation());
				newinvuse.setIssuetype("�˻�");
				newinvuse.setTransdate(new Date());
				newinvuse.setActualdate(new Date());
				String binnum = invusetrans.getBinnum();

				//newinvuse.setSitenum(invusetrans.getSitenum());
				// newinvuse.setCorpnum(invusetrans.getCorpnum());
				newinvuse.setBinnum(binnum);
				Double issueqty = 0d;
				if(Util.isNotNull(binnum))
				{
					issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and state ='�����'");
					if(issueqty == null)
						issueqty = 0d;		    	
				}
				else
				{
					issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum is null and state ='�����'");
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
				newinvuse.setState("��ȷ��");
				//newinvuse.setSitenum(invusetrans.getSitenum());
				//newinvuse.setCorpnum(invusetrans.getCorpnum());
				super.save(newinvuse);
			}
			else
				throw new Exception("û�п����˿�ķ����У�");			
		}
	}

	/* 
	 * ���ܣ��ڽ����˿�����󣬶��˿���˿��м�¼����У�顣
	 * ���ߣ�����
	 * ���ڣ�2008-3-12����10:28:32
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Workorder))
			throw new Exception("���󴫵ݲ���ȷ!");
		Workorder workorder = (Workorder) obj;
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);

			// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
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
					hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and state ='�����'");
					if(hasissueqty + returnqty >= 0)
					{
						invstock.setCurbal(curbal - returnqty);
						avgcost = (- returnqty * inv.getUnitcost() + curbal * inventory.getAvgcost())/(curbal-returnqty);
						inventory.setAvgcost(avgcost);
						super.save(inventory);
						super.save(invstock);
						inv.setState("�����");
						super.save(inv);										
					}
					else
					{
						throw new Exception("�����Ŀ���Ϊ��"+inv.getItemnum()+"������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");
					}
				}
				else
					throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");										
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal1 = invstock.getCurbal();
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='����' and state ='�����'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal1 - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal1 * inventory.getAvgcost())/(curbal1-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("�����");
					super.save(inv);									
				}
				else
				{
					throw new Exception("�����Ŀ���Ϊ��"+inv.getItemnum()+"������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");

				}

			}
		}
	}
	/* 
	 * ���ܣ��������ϴ�����
	 * ���ߣ�����
	 * ���ڣ�Dec 17, 2008  1:57:37 PM
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
			/****************�ȱ���wofailcode*************/
			for (int i = 0; i < wofailcodelist.size(); i++) {
				Wofailcode wofailcode = (Wofailcode) wofailcodelist.get(i);
				{
					/**********�ж�wofailcode��failurecode���Ƿ����*******/
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

					/**********�ж�wofailproblem��failureproblem���Ƿ����*******/

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
						/**********�ж�wofailcause��failurecause���Ƿ����*******/
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
							/**********�ж�wofaildeal��failuredeal���Ƿ����*******/
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
			Messagebox.show("�ѳɹ��������ϴ��룡");
		}
		else
		{
			Messagebox.show("���ϴ����Ѵ��ڣ����贴�����ϴ��룡");
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
				if(!Util.isNotNull(dolabor)&&status.equals("����δ����"))
				{
					Messagebox.show("��ָ��Ԥ����ά������ִ���ˣ��ٷ��͹�������");
					return false;
				}

			}
		}						
		return true;
	}		
}