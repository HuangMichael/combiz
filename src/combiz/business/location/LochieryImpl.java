package combiz.business.location;

import combiz.domain.location.Locance;
import combiz.domain.location.Lochiery;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class LochieryImpl extends IBOBaseImpl
implements LochierySrv {
	
	private LocanceSrv locanceSrv;
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Lochiery))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
	/** 
	 * 
	 * @param lochiery
	 * @return
	 * 作者：洪小林 日期：2006-12-15
	 */
	public void save(Lochiery lochiery)
	throws Exception
	{
		if(lochiery.getId() != null) //修改
		{
			this.getBaseDao().updateObject(lochiery);
		}
		else  
		{
			//新建
			this.getBaseDao().saveObject(lochiery);
			//更新父级记录
			List parentList = this.findWithQuery("location = '"+lochiery.getParent()+"' and systemid='"+lochiery.getSystemid()+"'");
			if(parentList.size()>0)
			{
				Lochiery loch = (Lochiery)parentList.get(0);
				loch.setHaschild("是");
				this.getBaseDao().updateObject(loch);
			}

			//产生祖先表数据
			Locance locance = new Locance();
			locance.setAncestor(lochiery.getLocation());
			locance.setLocation(lochiery.getLocation());
			locance.setSystemid(lochiery.getSystemid());
			this.getBaseDao().saveObject(locance);
			//循环获取父级，产生祖先表数据
			Lochiery loc = this.getParent(lochiery);
			while(loc!=null)
			{
				locance = new Locance();
				locance.setAncestor(loc.getLocation());
				locance.setLocation(lochiery.getLocation());
				locance.setSystemid(loc.getSystemid());
				//this.locanceSrv.save(locance);
				this.getBaseDao().saveObject(locance);

				loc = this.getParent(loc);
			}

		}
	}
	
	/**
	 * 获取位置父级
	 * @param loc 需要获取父级的位置参数
	 * @return
	 * 作者：洪小林 日期：2007-5-4
	 */
	public Lochiery getParent(Lochiery loc)
	throws Exception 
	{
		List parentList = this.findWithQuery("location = '" + loc.getParent() + "' and systemid='"+loc.getSystemid()+"'");
		if(parentList.size()>0)
		{
			Lochiery parent = (Lochiery)parentList.get(0);
			return parent;
		}
		
		return null;
	}


	/**
	 * 删除记录
	 * @param 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-1-9
	 */
	public void delete(Lochiery lochiery)
	throws Exception
	{
		//删除LOCANCE祖先表
		List locanceList = this.getLocanceSrv().findWithQuery("location='"+lochiery.getLocation()
				+"' and systemid='"+lochiery.getSystemid()+"'");
		for(int i=0;i<locanceList.size();i++)
		{
			Locance locance = (Locance) locanceList.get(i);
			this.getBaseDao().deleteObject(locance);
		}
		//更新父级
		Lochiery loch = this.getParent(lochiery);
		if(loch!=null)
		{
			List sameParent = this.findWithQuery("parent = '"+loch.getLocation()+"' and systemid='"+loch.getSystemid()+"'");
			if(sameParent.size()==0)
			{
				loch.setHaschild("否");
				this.update(loch);
			}
		}
		//删除结构表LOCHIERY数据
		this.delete(lochiery);
	}

	public LocanceSrv getLocanceSrv() {
		return locanceSrv;
	}

	public void setLocanceSrv(LocanceSrv locanceSrv) {
		this.locanceSrv = locanceSrv;
	}


}
