package combiz.business.doclib;

import combiz.domain.doclib.Doclibance;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DoclibanceSrv extends IBOBaseSrv
{
	/**
	 * @TODO ɾ�������б�İ汾������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List doclibances) throws Exception;
}
