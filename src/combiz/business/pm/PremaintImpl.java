package combiz.business.pm;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.Date;
import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class PremaintImpl extends IBOBaseImpl
implements PremaintSrv {


	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Premaint))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
	public Workorder createWorkorder(Premaint premain ,String description) throws Exception
	{
		Workorder workorder = new Workorder();
		
//		workorder.setWonum(worksrv.genInskey("WONUM"));
//		String date = this.getNowDate();
		Date sysdate = new Date();
		String wonum = this.genInskey("WONUM");
		String worktype = premain.getWorktype();
		wonum = "Sys-"+worktype +"-"+ wonum;
		workorder.setWonum(wonum);
//		String date = sysdate.toLocaleString();
//		String wodesc = date + " ��Ԥ����ά�� -" + description +"- �Զ�����";
		String wodesc = description +"- �Զ����ɣ�����:"+premain.getFrequency()+" "+premain.getFrequnit()+"��";
		workorder.setDescription(wodesc);
		//workorder.setSitenum(premain.getSitenum());
		//workorder.setCorpnum(premain.getCorpnum());
		
		/*****************����Ԥ����ά���е�ά�޴���*******************/
    	Long pmcounter=premain.getPmcounter();
		pmcounter++;
		premain.setPmcounter(pmcounter);
		this.getBaseDao().updateObject(premain);
		
		/*****************Ϊ���ɵ�����и�ֵ*******************/
		workorder.setStatus(premain.getWostatus());
		workorder.setEstlabhrs(new Double(0.0));
		workorder.setEstlabcost(new Double(0.0));
		workorder.setEstmatcost(new Double(0.0));
		workorder.setEsttoolcost(new Double(0.0));
		workorder.setActlabhrs(new Double(0.0));
		workorder.setActmatcost(new Double(0.0));
		workorder.setActlabcost(new Double(0.0));
		workorder.setActtoolcost(new Double(0.0));
		workorder.setEstatapprlabcost(new Double(0.0));
		workorder.setEstatapprlabhrs(new Double(0.0));
		workorder.setEstatapprmatcost(new Double(0.0));
		workorder.setEstatapprtoolcost(new Double(0.0));
		workorder.setEqdown("��");
		workorder.setHaschildren("��");
		workorder.setHasfollowupwork("��");
		workorder.setEstdur(0L);
		workorder.setStatusdate(new java.util.Date());
		workorder.setCrewid(premain.getCrewid());
		workorder.setPmnum(premain.getPmnum());
		workorder.setLocation(premain.getLocation());
		workorder.setEqnum(premain.getEqnum());
		workorder.setWorktype(premain.getWorktype());
		workorder.setSupervisor(premain.getSupervisor());
		workorder.setCrewid(premain.getCrewid());
		workorder.setJpnum(premain.getJpnum());
		workorder.setReportedby("Ĭ�Ϲ���Ա");
		workorder.setSchedstart(new Date());
		workorder.setSchedfinish(new Date());
		//workorder.setIsfinish("��");
		
		this.getBaseDao().saveObject(workorder);
		
		/*****************�û�ѡ���˱�׼��ҵ�ƻ�,�򽫱�׼��ҵ�ƻ�����д����Ӧ�ı���***************************/
		if (premain.getJpnum()!=null && !premain.getJpnum().equals("")) 
		{
			worksrv.genJobplan(workorder, premain.getJpnum());
			System.out.println("-------��׼��ҵ�ƻ�������� ��--------");
		}
		
		
		/****************�ж��Ƿ����ù�����***********************/
		if ("��".equals(premain.getAutowf())) {
			try
			{
				this.workflow(workorder,true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("����������ʧ�ܣ�");
			}
		}
		return workorder;
	}
	
}
