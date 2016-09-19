package combiz.business.corp;

import combiz.domain.corp.Corpaddress;
import combiz.system.IBOBaseImpl;

import java.util.Date;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class CorpaddressImpl extends IBOBaseImpl
implements CorpaddressSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Corpaddress))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}


	/**
	 * @param msgsender
	 * @return
	 * @author:高群凯
	 * @description:保存
	 * @time:15:21 2007-8-6
	 */
	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if(!(obj instanceof Corpaddress))
			throw new Exception("要保存的对象传递不正确！");
		Corpaddress address = (Corpaddress)obj;
		address.setChangeby(this.getUserInfo().getLabornum());
		address.setChangedate(new Date());
		super.save(obj);
	}
	
/////////////////////业务方法//////////////////////////////////
}
