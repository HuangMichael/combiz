package combiz.business.test;

import combiz.business.corp.LaborSrv;
import combiz.business.pm.PmgenhistorySrv;
import combiz.business.pm.PremaintSrv;
import combiz.business.workorder.WorkorderSrv;
import combiz.domain.corp.Labor;
import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;
import combiz.system.schedultask.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zkplus.spring.SpringUtil;

/**
 * @author hp520
 * ����ʵ��
 */
public class SimpleTask1 extends Task
{
	/**
	 * @TODO ����ʵ�ֵ����񷽷�
	 * @param map
	 * yuanjq 2007-8-7 ����02:40:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	//PremaintSrv premainsrv = (PremaintSrv)IBOSrvUtil.getSrv("premaint");
	
	public void execute(Map map)
	{
		baseDao = (IBOBaseDao)map.get("baseDao");
		//����ִ�еĴ���...
		/***
		 * ����Ԥ����ά�����Ƿ������ݣ����õ���Ϣ
		 */
		System.out.println("&&&&&&&&&&&&&&&&&--------come into execute()------------&&&&&&&&&&&&&&&&&");
		List list = baseDao.findWithQuery(Premaint.class, "");
		if (list.size()>0)
		{
			for (int i=0;i<list.size();i++)
			{
				Premaint premain = (Premaint) list.get(i);
				String ifnext = premain.getUsefrequency();
				Long frequency = premain.getFrequency();
				String frequnit = premain.getFrequnit();
				
				Date nowdate = new Date();//��õ�ǰ��ʱ��
				Date firstdate = premain.getFirstdate();
				Date lastdate = premain.getNextdate();
				
				if (firstdate != null)
				{
					
					/***
					 * �ж��Ƿ�Ӧ�����ɹ���(���ǵ�ѭ�����ڵ�����)����������´�ִ��ʱ��
					 */
					if (lastdate != null && this.ifCreate(-12).before(lastdate) && lastdate.before(this.ifCreate(12)))// �����ʱ�䷶Χ֮��
					{
						//	��Ҫ�����йصļ�¼
						if ("��".equals(ifnext) && ifnext != null)
						{
							/**********�����´�ִ�е�ʱ��**************/
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
						try {
							
							this.createWorkorder(premain);
							System.out.println("**************�������ɳɹ�***************");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}	
						
					}
					else if (lastdate != null && lastdate.before(nowdate))
					{
						if ("��".equals(ifnext) && ifnext != null)
						{
							/*****************��Ҫ����ʱ�䵫����Ҫ���ɹ���***********************/
							//Date date1 = this.updateDate(lastdate, frequnit, frequency);
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
					}
					else if (lastdate == null)
					{
						/***************ʱ����Ȼ����ڵ�ǰʱ����Сʱ��Χ��˵���ǵ�һ�β�������*******************************/
						System.out.println("-------------��һ�����ɹ���---------------");
						
						if (this.ifCreate(-12).before(firstdate) && firstdate.before(this.ifCreate(12)))
						{
								if("��".equals(ifnext) && ifnext != null)
								{
									if (frequency>0) 
									{
										this.getDate(premain,firstdate,frequnit, frequency);
									}
								}
								try {
									this.createWorkorder(premain);
									System.out.println("__________��һ�β���������ɡ�_______________");
								} catch (Exception e) {
									// TODO �Զ����� catch ��
									e.printStackTrace();
								}
								
						}
						
						if (firstdate.after(nowdate))
						{
							if("��".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
						}
					}
						
					
				}
			}//for ѭ������(())
		}
		
		System.out.println(""+new Date()+"�� SimpleTask1 ..........................");
	}
	
	/********************���㲢����ʱ��************************/
	public void getDate(Premaint premain,Date date,String frequnit,Long frequency)
	{
		Date date1 = null;
		date1=date;
		do{
			date1 = this.updateDate(date1, frequnit, frequency);
		}while (date1.before(new Date()));
		
		/**********�����´�ִ�е�ʱ��**************/
		System.out.println("***********+++++++&"+date1+"&+++++++*************");
		/********�������ɺ�******�ڴ˴�����premain��Ҫ���µ������Ϣ****************/
		premain.setNextdate(date1);
		premain.setLaststartdate(date);//�ϴο�ʼ����
		premain.setLastcompdate(date);//���һ���������
		try {
			baseDao.updateObject(premain);
			System.out.println("__________����ʱ����ɡ�_______________");
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	/*********************�жϲ��õ�ʱ��*********************************/
	public Date updateDate(Date getdate,String frequnit,Long frequency)
	{
		Calendar time=Calendar.getInstance(); 
		time.setTime(getdate);
		if (frequnit.equals("��")) 
		{
			int year = (int) (frequency+0);
			time.add(time.YEAR, year);
			
		}else if(frequnit.equals("��")) {
			int month = (int) (frequency+0);
			time.add(time.MONTH, month);
			
		}else{
			int date = (int) (frequency+0);
			time.add(time.DATE, date);
			//time.add(field, amount)
		}
		return time.getTime();
	}
	
	/********************���ɹ���*************************/
	public Workorder createWorkorder(Premaint premain) throws Exception
	{
		//WorkorderSrv worksrv = (WorkorderSrv)SpringUtil.getBean("WorkorderSrv");
		//LaborSrv laborsrv = (LaborSrv)IBOSrvUtil.getSrv("laborSrv");
		Workorder workorder = new Workorder();
		
		workorder.setWonum(worksrv.genInskey("WONUM"));
		
		//workorder.setSitenum(premain.getSitenum());
		//workorder.setCorpnum(premain.getCorpnum());
		/*****************Ϊ���ɵ�����и�ֵ*******************/
		workorder.setStatus("��");
		workorder.setEstlabhrs(new Double(0.0));
		workorder.setEstlabcost(new Double(0.0));
		workorder.setEstmatcost(new Double(0.0));
		workorder.setEsttoolcost(new Double(0.0));
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
		
		workorder.setPmnum(premain.getPmnum());
		workorder.setDescription(premain.getDescription());
		workorder.setLocation(premain.getLocation());
		workorder.setEqnum(premain.getEqnum());
		workorder.setWorktype(premain.getWorktype());
		workorder.setSupervisor(premain.getSupervisor());
		workorder.setCrewid(premain.getCrewid());
		workorder.setJpnum(premain.getJpnum());
		
		baseDao.saveObject(workorder);
		
		/***
		 * ��ִ�в�������ά����ʷ������Ӽ�¼��Ϣ
		 */
		this.createPmhistory(premain, workorder);
		return workorder;
	}
	
	/*******************����ά����ʷ��*************************/
	public void createPmhistory(Premaint premain,Workorder workorder) throws Exception
	{
		PmgenhistorySrv pmhistorysrv = (PmgenhistorySrv)IBOSrvUtil.getSrv("pmgenhistorySrv");
		Pmgenhistory newobj = new Pmgenhistory();
		
		newobj.setPmnum(premain.getPmnum());
		newobj.setNextstartdate(premain.getNextdate());
		newobj.setStartdate(premain.getFirstdate());
		newobj.setJpnum(premain.getJpnum());
		newobj.setPerformdate(premain.getLastcompdate());
		newobj.setWonum(workorder.getWonum());
		
		baseDao.saveObject(newobj);
	}
	
	/***************�ж��Ƿ����ɹ���**************************/
	public Date ifCreate(int contime)
	{
		Date mydate = new Date();
		Calendar time=Calendar.getInstance(); 
		time.setTime(mydate);
		/******************�õ���ǰ��ʱ�䣨Сʱ��**************************/
		time.add(time.HOUR, contime);
		return time.getTime();
	}
}
