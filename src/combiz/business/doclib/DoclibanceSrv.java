package combiz.business.doclib;

import combiz.domain.doclib.Doclibance;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DoclibanceSrv extends IBOBaseSrv
{
	/**
	 * @TODO 删除给定列表的版本表数据
	 * @param docapplibs
	 * yuanjq 2007-8-15 上午09:53:36
	 */ 
	public void deleteBatch(List doclibances) throws Exception;
}
