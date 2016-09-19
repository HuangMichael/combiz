package combiz.business.doclib;

import combiz.domain.doclib.Docversion;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocversionSrv extends IBOBaseSrv
{
	/**
	 * @TODO 删除给定列表的版本表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List docversions) throws Exception;
	/**
	 *  
	 * @TODO 修改文件上传标识
	 * @param docversion
	 * @throws Exception
	 * @洪小林  2007-8-17  下午01:44:55
	 */
	public void changeUploadStatus(Docversion docversion) 
	throws Exception;
	
}
