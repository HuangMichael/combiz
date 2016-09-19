package combiz.business.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocapplibSrv extends IBOBaseSrv
{
	/**
	 * @TODO 删除给定列表的关联应用程序表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List docapplibs) throws Exception;
	/**
	 * @TODO 继承父目录的关联的应用程序 由子调用
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 下午07:05:55
	 */ 
	public void saveUpExtends(Object arg0) throws Exception;
	/**
	 * @TODO 继承父目录的关联的应用程序,由父调用
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 下午07:05:55
	 */ 
	public void saveDownExtends(Object arg0) throws Exception;

}
