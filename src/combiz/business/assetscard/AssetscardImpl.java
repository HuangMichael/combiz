package combiz.business.assetscard;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.domain.assetscard.Carryover;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AssetscardImpl extends IBOBaseImpl
implements AssetscardSrv
{

	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave(Object obj) 
	throws Exception
	{
		return true;
	}

	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		return true;
	}

	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		return true;
	}



	/**
	 * 用户接口
	 * 保存新建对象后的用户接口方法
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterInsert(Object obj) 
	throws Exception
	{
	}

	/**
	 * 用户接口
	 * 保存更新对象后的用户接口方法
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterUpdate(Object obj) 
	throws Exception
	{
	}

	/**
	 * 
	 * @TODO 向初始化表（carryover）中出入一行初始化年月日
	 * @param 
	 * @throws Exception
	 * @王伟 2010-05-07 下午22:01
	 */
	public void insert() throws Exception {
//		Assetscard assetscard= new Assetscard();
		Carryover carryover = new Carryover();
		//将将日期改为字符串型
		Date  today = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat month = new SimpleDateFormat("MM"); 
		String year = sdf.format(today); 
		String mon = month.format(today); 
//在carryover如果没有相同的年月插入数据
		List carryoverlist = this.findWithQuery(Carryover.class,"year='"+year+"' and mon='"+mon+"'");
		if(carryoverlist.size()==0)
		{
			carryover.setStatus("未结转");
			carryover.setYear(year);
			carryover.setMon(mon);
			this.getBaseDao().saveObject(carryover);

		}
					
			//在assetscard中更新数据
			List assetcardlist = this.getBaseDao().findWithQuery(Assetscard.class, "status='未初始化'");
			if(assetcardlist.size()>0)
			{
				for(int i=0;i<assetcardlist.size();i++)
				{
					Assetscard assetscard = (Assetscard) assetcardlist.get(i);
					assetscard.setStatus("已初始化");
					this.getBaseDao().updateObject(assetscard);
				}
			
			//在assetscardline中插入"未结转"（status）
           List assetscardlinelist = this.getBaseDao().findWithQuery(Assetscardline.class," status=''");
               if(assetscardlinelist.size()>0)
               {
            	   for( int i=0; i<assetscardlinelist.size();i++)
            	   {
            		  Assetscardline assetscardline=(Assetscardline) assetscardlinelist.get(i);
            		  assetscardline.setStatus("未结转");
            		  this.getBaseDao().updateObject(assetscardline);
            	   }
               }
			

		}

	}








	/**
	 * 删除对象之前的用户接口
	 * 返回false的话不会删除该对象
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public boolean beforeDelete(Object obj) throws Exception
	{
		return true;
	}

	/**
	 * 系统删除对象的方法
	 * 可以在该方法中手工指定删除哪些子表结果集（通过关系名）
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj 主对象
	 * @throws Exception
	 */
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");  //通过关系名删除字表结果集
		super.delete(obj);
	}


	/**
	 * 
	 * @TODO 向初始化表（carryover）中出入一行初始化年月日
	 * @param 
	 * @throws Exception
	 * @王伟 2010-05-07 下午22:01
	 */
	public void uninsert() throws Exception {
	
		Date nowdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat month = new SimpleDateFormat("MM"); 
		String year = sdf.format(nowdate); 
		String mon = month.format(nowdate);
		List carryoverlist = this.findWithQuery(Carryover.class,"year='"+year+"' and mon='"+mon+"'");
		if(carryoverlist.size()>0 )
		{

			Carryover carryover = (Carryover) carryoverlist.get(0);
			carryover.setStatus("未结转");
			super.update(carryover);

		}
		List assetscardlist=this.getBaseDao().findWithQuery(Assetscard.class, "status='已初始化'");
		
		if(assetscardlist.size()>0)
		{
			for(int i=0;i<assetscardlist.size();i++)
				{Assetscard assetscard= (Assetscard) assetscardlist.get(i);
				assetscard.setStatus("未初始化");
				}
			
			
		}

	}

	/**
	 * 删除对象后的用户接口
	 * 
	 * 英贝思  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public void afterDelete(Object obj) throws Exception
	{

	}


	/**
	 * 工作流操作中通过该方法更改主对象的状态
	 * 英贝思 Nov 14, 2009
	 * @param obj 主对象
	 * @param toStatus 状态
	 * @throws Exception
	 */
	public void changeStatus(Object obj, String toStatus) throws Exception
	{
		super.changeStatus(obj, toStatus);
	}

	/**
	 * 工作流发送时，调用此接口。
	 * 在发送工作流之前判断数据的完整性。
	 * 在发送流程之前调用该方法,效验工作流对应的主表数据是否有效
	 * 或者通过主对象获取子表记录集进行效验:List childs = this.getListByRelation(parentObj, relation)
	 * 如果是第一次启动工作流,那么参数wfinstance==null
	 * 如果有错误,可以弹出Messagebox消息,或者抛出异常 throw new Exception("异常消息");
	 * 返回true可以正常往下走，如果返回false，流程中断
	 * 英贝思  Nov 14, 2009
	 * @param mainObject 主对象
	 * @param wfinstance 工作流程实例
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance) throws Exception
	{
		return true;
	}


	/**
	 * 工作流发送时，弹出下一步操作选择窗口，选择完操作线后，调用此接口。
	 * 可以根据选择的下一步操作来判断数据的完整性，决定是否执行下一步工作流。
	 * 在发送流程选择操作后调用该方法,效验工作流对应的主表数据是否有效
	 * 或者通过主对象获取子表记录集进行效验:List childs = this.getListByRelation(parentObj, relation)
	 * 如果是第一次启动工作流,那么参数wfinstance==null
	 * 如果有错误,可以弹出Messagebox消息,或者抛出异常 throw new Exception("异常消息");
	 * 返回true可以正常往下走，如果返回false，流程中断
	 * 英贝思  Nov 14, 2009
	 * @param mainObject 主对象
	 * @param wfinstance 工作流程实例
	 * @param wfaction  下一步的操作
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance, Wfaction wfaction) throws Exception
	{
		return true;
	}


	/**
	 * 工作流任务用户重新分配
	 * 用户接口
	 * 洪小林  Dec 21, 2009
	 */
	public void wfReassign()
	throws Exception
	{
		//用户自定义接口方法
	}

}