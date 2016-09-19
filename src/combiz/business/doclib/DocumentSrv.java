package combiz.business.doclib;

import combiz.domain.doclib.Doclibary;
import combiz.domain.doclib.Document;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocumentSrv extends IBOBaseSrv
{
	/**
	 * @TODO 删除给定列表的文档表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List documents) throws Exception;
	
	/**
	 * 从已有的文档库中拷贝文档
	 * 洪小林  2010-5-10
	 * @param docsList
	 * @throws Exception 
	 */
	public void copyDocument(Object mainObject, Doclibary doclibary, List docsList) 
	throws Exception;
}
