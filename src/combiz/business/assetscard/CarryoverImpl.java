package combiz.business.assetscard;

import combiz.domain.assetscard.Assetscardline;
import combiz.domain.assetscard.Carryover;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.List;

public class CarryoverImpl extends IBOBaseImpl
implements CarryoverSrv
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
	 * 
	 * @TODO 向表carryover assetscardline中插入数据
	 * @param list
	 * @throws Exception
	 * @王伟 2010-05-10 下午14:41
	 */
	public void copycarryover() throws Exception {

		List carryoverlist = this.getBaseDao().findWithQuery(Carryover.class, "status='未结转'");
		if(carryoverlist.size()>0)
		{
			Carryover carryover = (Carryover) carryoverlist.get(0);
			String mon = carryover.getMon();
			String year = carryover.getYear();
			String nmon = null;
			String nyear = null;
			if(Util.isNotNull(mon)&&Util.isNotNull(year))
			{
				int hmon = Integer.parseInt(mon);
				int hyear = Integer.parseInt(year);
				if(hmon-12==0)
				{
					nmon = "01";
					nyear = String.valueOf(hyear + 1);
				}
				else
				{
					nmon= String.valueOf(hmon+1);
					if(nmon.length()==1)
					{
						nmon="0"+nmon;

					}
					nyear = String.valueOf(hyear);
				}
				List assetcardlinelist = this.findWithQuery(Assetscardline.class,"status='未结转'");



				if(assetcardlinelist.size()>0 )
				{

					for(int i = 0;i<assetcardlinelist.size();i++)
					{
						Assetscardline assetscardline = (Assetscardline) assetcardlinelist.get(i);

						if(assetscardline.getDepreciationmonth()<=assetscardline.getExpectedmonth())
						{
							Assetscardline newassetline =new  Assetscardline();  
							newassetline.setStatus("未结转");
							newassetline.setDepreciationmonth(assetscardline.getDepreciationmonth()+1);
							newassetline.setAccdepreciation(assetscardline.getAccdepreciation()+assetscardline.getMthamount());
							newassetline.setAssetcode(assetscardline.getAssetcode());
							newassetline.setQuantity(assetscardline.getQuantity());
							newassetline.setResidualvalues(assetscardline.getResidualvalues());
							newassetline.setMon(nmon);
							newassetline.setYear(nyear);
							
							newassetline.setCost(assetscardline.getCost());
							newassetline.setExpectedmonth(assetscardline.getExpectedmonth());
							newassetline.setImpairment(assetscardline.getImpairment());
							newassetline.setManufacturers(assetscardline.getManufacturers());
							newassetline.setModel(assetscardline.getModel());
							newassetline.setMthamount(assetscardline.getMthamount());
							newassetline.setMthval(assetscardline.getMthval());
							newassetline.setResidual(assetscardline.getResidual());
							newassetline.setSuppliers(assetscardline.getSuppliers());
							newassetline.setNetworth(assetscardline.getCost()-assetscardline.getAccdepreciation());
							newassetline.setNet(assetscardline.getCost()-assetscardline.getAccdepreciation()-assetscardline.getImpairment());
							this.getBaseDao().saveObject(newassetline);

						}
						else
						{
							Assetscardline newassetline =new  Assetscardline();  
							newassetline.setStatus("未结转");
							newassetline.setDepreciationmonth(assetscardline.getExpectedmonth());
							newassetline.setDepreciationmonth(assetscardline.getDepreciationmonth());
							newassetline.setAccdepreciation(assetscardline.getAccdepreciation());
							newassetline.setAssetcode(assetscardline.getAssetcode());
							newassetline.setMon(nmon);
							newassetline.setYear(nyear);
							newassetline.setQuantity(assetscardline.getQuantity());
							newassetline.setResidualvalues(assetscardline.getResidualvalues());
						
							newassetline.setCost(assetscardline.getCost());
							newassetline.setExpectedmonth(assetscardline.getExpectedmonth());
							newassetline.setImpairment(assetscardline.getImpairment());
							newassetline.setManufacturers(assetscardline.getManufacturers());
							newassetline.setModel(assetscardline.getModel());
							newassetline.setMthamount(assetscardline.getMthamount());
							newassetline.setMthval(assetscardline.getMthval());
							newassetline.setResidual(assetscardline.getResidual());
							newassetline.setSuppliers(assetscardline.getSuppliers());
							newassetline.setNetworth(assetscardline.getNetworth());
							newassetline.setNet(assetscardline.getNet());
							this.getBaseDao().saveObject(newassetline);


						}						




						assetscardline.setStatus("已结转");
						this.getBaseDao().updateObject(assetscardline);


					}

				}
				else
				{
					throw new Exception("数据异常！");
				}

				Carryover ncarryover = new Carryover();
				ncarryover.setYear(nyear);
				ncarryover.setMon(nmon);
				ncarryover.setSourceid(carryover.getId());
				ncarryover.setStatus("未结转");
				this.getBaseDao().saveObject(ncarryover);

				carryover.setStatus("已结转");
				this.getBaseDao().updateObject(carryover);

			}
		}

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
	 * @TODO 向表carryover assetscardline中插入数据
	 * @param list
	 * @throws Exception
	 * @王伟 2010-05-14 上午12:00
	 */
	public void copyuncarryover(Object obj ) throws Exception {


	
		Carryover carryover = (Carryover) obj;

	
		long sourceid = carryover.getSourceid();

		if(Util.isNotNull(String.valueOf(sourceid)))
		{
			Carryover scarryover = (Carryover) this.findUniqueBy(Carryover.class, "id", sourceid);
			if(scarryover!=null)
			{
				//更新月末结转中的记录为“未结转”
				scarryover.setStatus("未结转");
				this.getBaseDao().updateObject(scarryover);
				//把设备折旧明细中对应carryover的年月的记录，修改为“未结转”
				List assertlinelist = this.findWithQuery(Assetscardline.class,"mon='"+scarryover.getMon()+"' and year='"+scarryover.getYear()+"'");
				if(assertlinelist.size()>0)
				{
					for(int j=0;j<assertlinelist.size();j++)
					{
						Assetscardline assetscardline = (Assetscardline) assertlinelist.get(j);
						assetscardline.setStatus("未结转");
						
					}
				}
			}
		}

		//删除assetcardlinelist中结转中插入的数据  
		List assetcardlinelist = this.findWithQuery(Assetscardline.class,"mon='"+carryover.getMon()+"' and year='"+carryover.getYear()+"'");
		
		this.getBaseDao().deleteBatch(assetcardlinelist);
		this.getBaseDao().deleteObject(carryover);
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