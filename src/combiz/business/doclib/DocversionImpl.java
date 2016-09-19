package combiz.business.doclib;

import combiz.domain.doclib.Docversion;
import combiz.system.IBOBaseImpl;
import combiz.system.util.DocUtil;

import java.util.List;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class DocversionImpl extends IBOBaseImpl
implements DocversionSrv {


	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Docversion))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		
		//删除文档版本的文件夹，并且删除实体文件
		/*Docversion docversion = (Docversion)obj;
		if(docversion.getId()!=null)
			DocUtil.deletePath(docversion.getUrlpath());*/
	}
	
	/**
	 * 
	 * @TODO 保存文档版本数据
	 * @param obj
	 * @throws Exception
	 * @洪小林  2007-8-17  下午03:22:56
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Docversion docversion = (Docversion)obj;
		if(docversion.getId()==null)
		{
			//当新建文档版本的时候同时创建文档版本目录
			docversion.setUrlpath(docversion.getUrlpath() + docversion.getVersion() + "/");
			//创建文件路径
			DocUtil.createPath(docversion.getUrlpath());
		}
		
		super.save(docversion);
	}
	
	
	/**
	 *  
	 * @TODO 修改文件上传标识
	 * @param docversion
	 * @throws Exception
	 * @洪小林  2007-8-17  下午01:44:55
	 */
	public void changeUploadStatus(Docversion docversion) 
	throws Exception
	{
		super.getBaseDao().updateObject(docversion);
	}
	
	/**
	 * @TODO 删除给定列表的版本表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List docversions) throws Exception{
		this.deleteBatch(docversions);
	}
	
/////////////////////业务方法//////////////////////////////////
}
