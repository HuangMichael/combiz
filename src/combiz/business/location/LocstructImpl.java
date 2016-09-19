package combiz.business.location;

import combiz.domain.location.Locance;
import combiz.domain.location.Locstruct;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.List;

public class LocstructImpl extends IBOBaseImpl
implements LocstructSrv
{
	
	/**
	 * 添加到系统
	 * locstruct 原来的位置结构
	 * newobj  新位置结构
	 * 洪小林  Nov 24, 2009
	 * @param locstruct
	 */
	public void addToSystem(Locstruct locstruct, Locstruct newobj)
	throws Exception
	{
		this.save(newobj);
		//添加位置及所有子集位置到选中的系统
		if(Util.getBoolean(newobj.getAddchild()))
		{
			String systemid = newobj.getSystemid();
			List childs = this.getChildren(locstruct,systemid);
			for(int i=0;i<childs.size();i++)
			{
				Locstruct childLoc = (Locstruct) childs.get(i);
				Locstruct newchild = new Locstruct();
				newchild.setLocation(childLoc.getLocation());
				newchild.setHaschild(childLoc.getHaschild());
				newchild.setOrderby(childLoc.getOrderby());
				newchild.setSystemid(systemid); //系统不同
				newchild.setParent(childLoc.getParent());
				
				this.save(newchild);
				
				this.addChildToSystem(childLoc, systemid);
			}
		}
		else
			newobj.setHaschild("否");
	}
	
	/**
	 * 用于递归调用添加所有子集到指定的系统
	 * 洪小林  Nov 24, 2009
	 * @param parent
	 * @param systemid
	 * @throws Exception
	 */
	private void addChildToSystem(Locstruct parent, String systemid)
	throws Exception
	{
		List childs = this.getChildren(parent,systemid);  //取出老对象的子集
		for(int i=0;i<childs.size();i++)
		{
			Locstruct childLoc = (Locstruct) childs.get(i);
			Locstruct newchild = new Locstruct();
			newchild.setLocation(childLoc.getLocation());
			newchild.setSystemid(systemid); //系统不同
			newchild.setParent(childLoc.getParent());
			newchild.setHaschild(childLoc.getHaschild());
			newchild.setOrderby(childLoc.getOrderby());
			
			this.save(newchild);
			
			this.addChildToSystem(childLoc, systemid);
		}
	}
	
	/**
	 * 获取位置子集，过滤出在新系统中不存在的子集
	 * 
	 * @param loc
	 *            需要获取父级的位置参数
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	private List getChildren(Locstruct locstruct,String newsystemid)
	throws Exception
	{
		return this.getBaseDao().findWithQuery(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "' and location not in(select t.location from Locstruct t where t.systemid='"
				+ newsystemid + "' and t.parent='" + locstruct.getLocation() + "')");
	}
	
	
	/**
	 * 用户接口
	 * 保存对象之前的用户接口
	 * 返回false则不会保存该对象
	 * 洪小林  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		Locstruct locstruct = (Locstruct) obj;
		
		//更新父级记录
		Locstruct parent = this.getParent(locstruct);
		if (parent != null) 
		{
			parent.setHaschild("是");
			this.getBaseDao().updateObject(parent);
		}
		
		//重新生成祖先数据
		this.genAncestor(locstruct);

		return true;
	}
	
	
	
	@Override
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		Locstruct locstruct = (Locstruct) obj;
		//判断是否已经被修改
		int count = this.getBaseDao().selectCountByWhere(Locstruct.class,
				"location='"+locstruct.getLocation()+"' and systemid='"+locstruct.getSystemid()+
				"' and parent='"+locstruct.getParent()+"'");
		
		if(count<=0) //设定修改了父级字段parent标示，以便于在afterUpdate方法中进行后续的更新操作
			changeParent = true;  
		
		
		return true;
	}
	
	private boolean changeParent;
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
		if(changeParent)  //下面的更新操作必须写在afterUpdate方法中才正确
		{
			Locstruct locstruct = (Locstruct) obj;
			//说明locstruct数据已经被修改了父级parent字段
			//更新父级记录
			Locstruct parent = this.getParent(locstruct);
			if (parent != null) 
			{
				parent.setHaschild("是");
				this.getBaseDao().updateObject(parent);
			}
			//删除祖先表数据
			this.getBaseDao().executeSql("delete from locance where location='"
					+locstruct.getLocation()+"' and systemid='"+locstruct.getSystemid()+"'");
			//重新生成祖先数据
			this.genAncestor(locstruct);
			
			//循环子集，并为子集也重新生成ancestor
			List childs = this.getChildren(locstruct);
			if(childs.size()>0)
				this.updateChildsAncestor(childs);
		}
	}
	///由上一个方法调用递归
	private void updateChildsAncestor(List childs)
	throws Exception
	{
		for(int i=0;i<childs.size();i++)
		{
			Locstruct child = (Locstruct) childs.get(i);
			this.getBaseDao().executeSql("delete from locance where location='"
					+child.getLocation()+"' and systemid='"+child.getSystemid()+"'");
			//重新生成祖先数据
			this.genAncestor(child);
			
			List childs2 = this.getChildren(child);
			if(childs2.size()>0)
				this.updateChildsAncestor(childs2);
		}
	}
	
	
	/**
	 * 产生祖先表数据
	 * 洪小林  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void genAncestor(Locstruct locstruct)
	throws Exception
	{
		//产生祖先表数据
		Locance locance = new Locance();
		locance.setAncestor(locstruct.getLocation());
		locance.setLocation(locstruct.getLocation());
		locance.setSystemid(locstruct.getSystemid());
		this.getBaseDao().saveObject(locance);
		// 循环获取父级，产生祖先表数据
		Locstruct parent = this.getParent(locstruct);
		while (parent != null) 
		{
			locance = new Locance();
			locance.setLocation(locstruct.getLocation());
			locance.setAncestor(parent.getLocation());
			locance.setSystemid(parent.getSystemid());
			this.getBaseDao().saveObject(locance);

			parent = this.getParent(parent);
		}
	}
	
	

	@Override
	public void save(Object obj)
	throws Exception
	{
		super.save(obj);
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
		Locstruct locstruct = (Locstruct) obj;
		
		//更新父级
		this.updateParent(locstruct);
		
		//删除所有子集
		this.deleteAllChild(locstruct);
		
		//删除祖先表
		this.getBaseDao().deleteBatch(this.findWithQuery(Locance.class, "location='"+locstruct.getLocation()+"'"));
		//最后删除自己
		this.getBaseDao().deleteObject(locstruct);
	}
	
	
	/**
	 * 删除属于该系统的所有子集
	 * 洪小林  Nov 24, 2009
	 * @param parent
	 * @throws Exception
	 */
	public void deleteAllChild(Locstruct parent)
	throws Exception
	{
		List list = this.getChildren(parent);
		for(int i=0;i<list.size();i++)
		{
			Locstruct child = (Locstruct) list.get(i);
			//删除祖先表
			this.getBaseDao().deleteBatch(this.findWithQuery(Locance.class, "location='"+child.getLocation()+"'"));
			//自己
			this.getBaseDao().deleteObject(child);
			
			//递归
			this.deleteAllChild(child);
		}
	}
	
	
	/**
	 * 更新父级
	 * 洪小林  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void updateParent(Locstruct locstruct)
	throws Exception
	{
		Locstruct parentloc = this.getParent(locstruct);
		if (parentloc != null)
		{
			int sameParent = this.getBaseDao().selectCountByWhere(Locstruct.class,
					"parent = '" + locstruct.getParent() + "' and systemid='"
					+ locstruct.getSystemid() + "' and location<>'"
					+ locstruct.getLocation() + "'");
			if (sameParent == 0)
			{
				parentloc.setHaschild("否");
				this.getBaseDao().updateObject(parentloc);
			}
		}
	}
	
	
	
	/**
	 * 获取位置子集，过滤出在新系统中不存在的子集
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public List getChildren(Locstruct locstruct)
	throws Exception
	{
		return this.getBaseDao().findWithQuery(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
	}
	
	
	/**
	 * 判断是否位置有子集
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public boolean hasChildren(Locstruct locstruct)
	throws Exception
	{
		int count = this.getChildrenCount(locstruct);
		if(count>0)
			return true;
		else
			return false;
	}
	
	/**
	 * 获取位置子集的数量
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public int getChildrenCount(Locstruct locstruct)
	throws Exception
	{
		return this.getBaseDao().selectCountByWhere(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
	}
	
	/**
	 * 获取位置父级
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public Locstruct getParent(Locstruct locstruct)
	throws Exception
	{
		List parentList = this.getBaseDao().findWithQuery(Locstruct.class, "location = '" + locstruct.getParent()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
		if (parentList.size() > 0)
		{
			Locstruct parent = (Locstruct) parentList.get(0);
			return parent;
		}

		return null;
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
	
	
}