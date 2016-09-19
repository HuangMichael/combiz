package combiz.business.pm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import combiz.business.workorder.WorkorderSrv;
import combiz.domain.pm.Pmgenhistory;
import combiz.domain.pm.Premaint;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;
import combiz.system.schedultask.Task;

public class PmGenerator  extends Task
{
	/**
	 * @TODO ����ʵ�ֵ����񷽷�
	 * @param map
	 * yuanjq 2007-8-7 ����02:40:19
	 * @see combiz.system.schedultask.Task#execute(java.util.Map)
	 */
	IBOBaseDao baseDao = null;
	WorkorderSrv worksrv = (WorkorderSrv)IBOSrvUtil.getSrv("workorder");
	
	public void execute(Map map) throws Exception
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
				String ifnext = premain.getUsefrequency();//�õ��Ƿ�ʹ��Ƶ��
				Long frequency = premain.getFrequency();//�õ�Ƶ��
				String frequnit = premain.getFrequnit();//�õ�Ƶ�ʵ�λ
				String desc = premain.getDescription();
				Long pmcounter = premain.getPmcounter();
				
				Date nowdate = new Date();//��õ�ǰ��ʱ��
				Date firstdate = premain.getFirstdate();
				Date lastdate = premain.getNextdate();
				
				if (firstdate != null)
				{
					
					/***
					 * �ж��Ƿ�Ӧ�����ɹ���(���ǵ�ѭ�����ڵ�����)����������´�ִ��ʱ��
					 */
					
					if (lastdate != null && this.getNowDate().equals(this.getIntoDate(lastdate)))// �����ʱ�䷶Χ֮��
					{
						
						Workorder workorder = this.createWorkorder(premain,desc);
						//	��Ҫ�����йصļ�¼
						
						if ("��".equals(ifnext) && ifnext != null)
						{
							/**********�����´�ִ�е�ʱ��**************/
							if (frequency>0) 
							{
								this.getDate(premain,lastdate,frequnit, frequency);
							}
						}
						/***
						 * ��ִ�в�������ά����ʷ�������Ӽ�¼��Ϣ
						 */
						this.createPmhistory(premain, workorder);
						
							
							System.out.println("**************��һ���������ں���һ���������ڡ���һ�������ն���Ϊ�գ��ҵ�ǰ���ڵ�����һ�������յ�����¡����������������������������������ɳɹ���������***************");
						
					}
					else if (lastdate != null && lastdate.before(nowdate))//�����һ�����������ڵ�ǰϵͳ���ڣ�ֻ������һ�������գ�
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
						System.out.println("�����������������������������������������¸�����������ϵͳ��ǰ���ڣ�ֻ������һ�������գ������ɹ���������������������������������������������������������");
					}
					else if (lastdate == null)
					{
						/***************ʱ����Ȼ����ڵ�ǰʱ����Сʱ��Χ��˵���ǵ�һ�β�������*******************************/
						
						if (this.getNowDate().equals(this.getIntoDate(firstdate)))
						{
							
							Workorder workorder = this.createWorkorder(premain,desc);
							if("��".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
							this.createPmhistory(premain, workorder);
							
							//System.out.println("__________��һ�β���������ɡ�_______________");
							System.out.println("��������������������������������������һ��������Ϊ�գ���һ����������������ڵ�ǰϵͳ���ڣ����ɹ����ɹ���������������������������������������������");
							
						}
						
						if (firstdate.before(nowdate))
						{
							if("��".equals(ifnext) && ifnext != null)
							{
								if (frequency>0) 
								{
									this.getDate(premain,firstdate,frequnit, frequency);
								}
							}
							System.out.println("������������������������������������"+premain.getPmnum()+"-----------------��һ�����������ڵ�ǰϵͳ���ڣ�������һ�������ճɹ���������������������������������������������");
							
						}
						
					}
						
					
				}
			}//for ѭ������(())
		}
		
		System.out.println(""+new Date()+"�� SimpleTask1 ..........................");
	}
	
	
	
	/********************���㲢����ʱ��************************/
	public void getDate(Premaint premain,Date date,String frequnit,Long frequency) 
	throws Exception
	{
		Date date1 = null;
		date1=date;
		do{
			date1 = this.updateDate(date1, frequnit, frequency);
		}while (date1.before(new Date()));
		
		/**********�����´�ִ�е�ʱ��**************/
		//System.out.println("***********+++++++&"+date1+"&+++++++*************");
		/********�������ɺ�******�ڴ˴�����premain��Ҫ���µ������Ϣ****************/
		premain.setNextdate(date1);
		premain.setLaststartdate(date);//�ϴο�ʼ����
		premain.setLastcompdate(date);//���һ���������
		baseDao.updateObject(premain);
		System.out.println("__________"+premain.getPmnum()+"-----------------����ʱ����ɡ�_______________");
		
	}
	
	
	/*********************�жϲ��õ�ʱ��*********************************/
	public Date updateDate(Date getdate,String frequnit,Long frequency)
	{
		Calendar time=Calendar.getInstance(); 
		time.setTime(getdate);
		if (frequnit.equals("��")) 
		{
			System.out.println("====������======="+frequency+frequnit);
			int year = (int) (frequency+0);
			time.add(time.YEAR, year);
			
		}else if(frequnit.equals("��")) {
			System.out.println("====������======="+frequency+frequnit);
			int month = (int) (frequency+0);
			time.add(time.MONTH, month);
			
		}else{
			System.out.println("====������======="+frequency+frequnit);
			int date = (int) (frequency+0);
			time.add(time.DATE, date);
			//time.add(field, amount)
		}
		return time.getTime();
	}
	
	/********************���ɹ���*************************/
	public Workorder createWorkorder(Premaint premain ,String description) throws Exception
	{
		Workorder workorder = new Workorder();
		
//		workorder.setWonum(worksrv.genInskey("WONUM"));
//		String date = this.getNowDate();
		Date sysdate = new Date();
		String wonum = worksrv.genInskey("WONUM");
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
		baseDao.updateObject(premain);
		
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
		
		baseDao.saveObject(workorder);
		
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
				worksrv.workflow(workorder,true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("����������ʧ�ܣ�");
			}
		}
		return workorder;
	}
	
	/*******************����ά����ʷ��*************************/
	public void createPmhistory(Premaint premain,Workorder workorder) throws Exception
	{
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
	public String getNowDate()
	{
		//Date mydate = new Date();
		Calendar time=Calendar.getInstance(); 
		
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		String date=ntime.format(time.getTime());

		return date;
		
/*		time.setTime(mydate);
		*//******************�õ���ǰ��ʱ�䣨Сʱ��**************************//*
		time.add(time.HOUR, contime);
		return time.getTime();*/
	}
	
	public String getIntoDate(Date date) 
	{
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		String date1=ntime.format(date.getTime());

		return date1;
	}
	
	/***************String >>Date�õ���Ӧ���ڸ�ʽ����������YYYY-MM-RR***********************/
    public Date strToDate(String strDate, String format) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
            date = dateFormat.parse(strDate);
        return date;
    }
	
}