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
 * 月已完成缺陷
 * 月未完成缺陷
 * 月新增缺陷
 * */
public class Wobugeqmouthcount implements KpiCustomValue{
	
	
	public Double execute(Kpitarget kpitarget, IBOBaseSrv ibobaseSrv, Object mainObject)
	throws Exception 
	{
	//public Number execute(IBOBaseSrv ibobaseSrv) throws Exception {
        String wStatusStr=null;
		String status = "工单关闭"; //status，工单关闭，流程未启用  status=status 是取所有的
 		if (kpitarget.getKpinum().equals("月已完成缺陷")){ wStatusStr = " where  status like '%工单关闭%'";}
		if (kpitarget.getKpinum().equals("月未完成缺陷")){ wStatusStr = " where  not status like '%工单关闭%' and status<>'流程未启用'" ;}
		if (kpitarget.getKpinum().equals("月新增缺陷")){ wStatusStr =   " where  status ='流程未启用'";}
		
		//取工单关闭的缺陷流程
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
		strSql = strSql + wStatusStr; //加入状态信息
		 if (!kpitarget.getKpinum().equals("月未完成缺陷")){ //未完成缺陷包括所有未完成的缺陷
             strSql = strSql + " and  findtime >=to_date('"+startdate+"','YYYY-MM-dd')  and  findtime <= to_date('"+ enddate +"','YYYY-MM-dd HH24:MI:SS')";
		 }
		String wsiteSql = this.getSitenumber(ibobaseSrv);
		 
		if (wsiteSql.equals(""))	
         {  //没有授权任何站点 
			return 0.0D;
	      
         }else 
          if (wsiteSql.equals("allsite")){
        	  List list = ibobaseSrv.getBaseDao().selectListByHql(strSql); 
        	  return Double.valueOf(list.get(0).toString());	  
        	  
        	  
          }else {//按授权地点查询
        	  strSql = strSql + "and Sitenum in ("+wsiteSql+")";
        	  List list = ibobaseSrv.getBaseDao().selectListByHql(strSql); 
        	  return Double.valueOf(list.get(0).toString());
          }
	//List list = ibobaseSrv.getBaseDao().selectListByHql();

	}
	
	
	//得到站点授权
	private String getSitenumber(IBOBaseSrv ibobaseSrv){
		Ibsusers ibsusers = ((Ibsusers)Executions.getCurrent().getDesktop().getSession().getAttribute("userinfo"));
	
		List list1 =  ibobaseSrv.getBaseDao().selectListByHql("from Ibsgroupusers  a,Ibsgroups b where a.grpname=b.grpname and  b.allsite = '是' and  a.userid = '"+ibsusers.getUserid() +"'");
		//有所有地点授权 
	
		if (list1.size()>0 ) {return "allsite" ;} //授权所有站点
		
	
		List list = ibobaseSrv.getBaseDao().selectListByHql("from Corpsiteauth where grpname in(select t.grpname from Ibsgroupusers t where t.userid='"+ibsusers.getUserid() +"')" );

		
		//如果包含null 则是授权所有地点
		String result="" ;

			for (int i=0;i<list.size();i++){
				Corpsiteauth c = (Corpsiteauth)list.get(i);
				
			    if (c.getSitenum().equals("")) { continue;}
				result = result + "'" + c.getSitenum().trim()+ "'" +",";	
			}
		    if (result.length() > 0) {result = result+"'0'" ;}
		   //,号后面加一个0字符,就不用去掉最后一个,号
		  if (result.length() >0 ) {return result  ;}
		
	return "";	
	}




}
