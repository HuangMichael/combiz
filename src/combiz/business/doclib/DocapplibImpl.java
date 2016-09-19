package combiz.business.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.domain.doclib.Doclibary;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class DocapplibImpl extends IBOBaseImpl
implements DocapplibSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Docapplib))
			throw new Exception("要删除的对象传递不正确！");
		Docapplib docapplib = (Docapplib)obj;
		//判断能否删除
		//if(this.findWithQuery("libnum='"+docapplib.getLibnum()+"' and id<>'" + docapplib.getId() + "'"))
		//{
			
		//}
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	/**
	 * @TODO 删除给定列表的关联应用程序表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List docapplibs) throws Exception {
		this.getBaseDao().deleteBatch(docapplibs);
	}


	@Override
	public void save(Object arg0) 
	throws Exception 
	{
		Docapplib docapplib = (Docapplib) arg0;
		
		//递归插入子目录
		if(docapplib.getId()==null || docapplib.getId().equals(""))
		{
			saveDownExtends(arg0);
		}
		
		//已经存在不用再插入
		/*if(this.getCountByWhere("libnum='" + docapplib.getLibnum() +"' and app='" + docapplib.getApp() +"' and ownertable='"+docapplib.getOwnertable()+"' and isrelapp='"+docapplib.getIsrelapp()+"'")>0)
		{
			return;
		}*/
		super.save(arg0);
	}
	/**
	 * @TODO 继承父目录的关联的应用程序 由子调用
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 下午07:05:55
	 */ 
	public void saveUpExtends(Object arg0) throws Exception
	{
		Doclibary doclibary = (Doclibary)arg0;
		List docapplibs = this.getBaseDao().findWithQuery(Docapplib.class, "libnum='" + doclibary.getParent() +"'");
		for (int i = 0; i < docapplibs.size(); i++)
		{
			Docapplib docapplib = (Docapplib)docapplibs.get(i);
			
			Docapplib docapplib1 = new Docapplib();
			
			docapplib1.setLibnum(doclibary.getLibnum());
			docapplib1.setApp(docapplib.getApp());
			docapplib1.setIsrelapp(docapplib.getIsrelapp());
			docapplib1.setRelquery(docapplib.getRelquery());
			docapplib1.setOwnertable(docapplib.getOwnertable());
			super.save(docapplib1);
		}
	}
	/**
	 * @TODO 继承父目录的关联的应用程序,由父调用
	 * @param  arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 下午07:05:55
	 */ 
	public void saveDownExtends(Object arg0) throws Exception
	{
		Docapplib docapplib = (Docapplib)arg0;
		
		List doclibarys = this.getBaseDao().findWithQuery(Doclibary.class, "parent='" + docapplib.getLibnum() +"'");
		for (int i = 0; i < doclibarys.size(); i++) {
			Doclibary doclibary1 = (Doclibary)doclibarys.get(i);
			//saveUpExtends(doclibary1);
			Docapplib docapplib1 = new Docapplib();
			
			docapplib1.setLibnum(doclibary1.getLibnum());
			docapplib1.setApp(docapplib.getApp());
			docapplib1.setIsrelapp(docapplib.getIsrelapp());
			docapplib1.setRelquery(docapplib.getRelquery());
			docapplib1.setOwnertable(docapplib.getOwnertable());
			//已经存在不用再插入
			/*if(this.getCountByWhere("libnum='" + docapplib1.getLibnum() +"' and app='" + docapplib1.getApp() +"' and ownertable='"+docapplib.getOwnertable()+"' and isrelapp='"+docapplib.getIsrelapp()+"'")>0)
			{
				return;
			}*/
			super.save(docapplib1);
			
			//递归
			saveDownExtends(docapplib1);
		}
	}
	
/////////////////////业务方法//////////////////////////////////
}
