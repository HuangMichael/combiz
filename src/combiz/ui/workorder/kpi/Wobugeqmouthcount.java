package combiz.ui.workorder.kpi;


import combiz.domain.corp.Corpsiteauth;
import combiz.domain.kpi.Kpitarget;
import combiz.domain.user.Ibsusers;
import combiz.system.IBOBaseSrv;
import combiz.system.kpi.KpiCustomValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.inbasis.zk.ui.Executions;
/*
 * �������ȱ��
 * ��δ���ȱ��
 * ������ȱ��
 * */
public class Wobugeqmouthcount implements KpiCustomValue{
	
	
	public Double execute(Kpitarget kpitarget, IBOBaseSrv ibobaseSrv, Object mainObject)
	throws Exception 
	{
	//public Number execute(IBOBaseSrv ibobaseSrv) throws Exception {
        String wStatusStr=null;
		String status = "�����ر�"; //status�������رգ�����δ����  status=status ��ȡ���е�
 		if (kpitarget.getKpinum().equals("�������ȱ��")){ wStatusStr = " where  status like '%�����ر�%'";}
		if (kpitarget.getKpinum().equals("��δ���ȱ��")){ wStatusStr = " where  not status like '%�����ر�%' and status<>'����δ����'" ;}
		if (kpitarget.getKpinum().equals("������ȱ��")){ wStatusStr =   " where  status ='����δ����'";}
		
		//ȡ�����رյ�ȱ������
		//Labor labor = ((Labor)Executions.getCurrent().getDesktop().getSession().getAttribute("laborinfo"));
        //-------------------------------------------------------------------------
		Date date = new Date();
		String lastday = String.valueOf(date.getDate());
		if (lastday.length() ==1 ){lastday = '0'+lastday;}
		String whereSql =this.getSitenumber(ibobaseSrv);

		SimpleDateFormat startsdf = new SimpleDateFormat("yyyy-MM-01");
		SimpleDateFormat endsdf = new SimpleDateFormat("yyyy-MM-"+lastday+" 23:59:59");
		String startdate = startsdf.format(new Date());
		String enddate = endsdf.format(new Date());
		//--------------------------------------------------------------------
		String strSql = " select count(*) from Wobugeq  " ;
		strSql = strSql + wStatusStr; //����״̬��Ϣ
		 if (!kpitarget.getKpinum().equals("��δ���ȱ��")){ //δ���ȱ�ݰ�������δ��ɵ�ȱ��
             strSql = strSql + " and  findtime >=to_date('"+startdate+"','YYYY-MM-dd')  and  findtime <= to_date('"+ enddate +"','YYYY-MM-dd HH24:MI:SS')";
		 }
		String wsiteSql = this.getSitenumber(ibobaseSrv);
		 
		if (wsiteSql.equals(""))	
         {  //û����Ȩ�κ�վ�� 
			return 0.0D;
	      
         }else 
          if (wsiteSql.equals("allsite")){
        	  List list = ibobaseSrv.getBaseDao().selectListByHql(strSql); 
        	  return Double.valueOf(list.get(0).toString());	  
        	  
        	  
          }else {//����Ȩ�ص��ѯ
        	  strSql = strSql + "and Sitenum in ("+wsiteSql+")";
        	  List list = ibobaseSrv.getBaseDao().selectListByHql(strSql); 
        	  return Double.valueOf(list.get(0).toString());
          }
	//List list = ibobaseSrv.getBaseDao().selectListByHql();

	}
	
	
	//�õ�վ����Ȩ
	private String getSitenumber(IBOBaseSrv ibobaseSrv){
		Ibsusers ibsusers = ((Ibsusers)Executions.getCurrent().getDesktop().getSession().getAttribute("userinfo"));
	
		List list1 =  ibobaseSrv.getBaseDao().selectListByHql("from Ibsgroupusers  a,Ibsgroups b where a.grpname=b.grpname and  b.allsite = '��' and  a.userid = '"+ibsusers.getUserid() +"'");
		//�����еص���Ȩ 
	
		if (list1.size()>0 ) {return "allsite" ;} //��Ȩ����վ��
		
	
		List list = ibobaseSrv.getBaseDao().selectListByHql("from Corpsiteauth where grpname in(select t.grpname from Ibsgroupusers t where t.userid='"+ibsusers.getUserid() +"')" );

		
		//�������null ������Ȩ���еص�
		String result="" ;

			for (int i=0;i<list.size();i++){
				Corpsiteauth c = (Corpsiteauth)list.get(i);
				
			    if (c.getSitenum().equals("")) { continue;}
				result = result + "'" + c.getSitenum().trim()+ "'" +",";	
			}
		    if (result.length() > 0) {result = result+"'0'" ;}
		   //,�ź����һ��0�ַ�,�Ͳ���ȥ�����һ��,��
		  if (result.length() >0 ) {return result  ;}
		
	return "";	
	}




}
