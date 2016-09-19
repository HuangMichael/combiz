package combiz.business.doclib;

import combiz.domain.doclib.Doclibary;
import combiz.domain.doclib.Document;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocumentSrv extends IBOBaseSrv
{
	/**
	 * @TODO ɾ�������б���ĵ�������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List documents) throws Exception;
	
	/**
	 * �����е��ĵ����п����ĵ�
	 * ��С��  2010-5-10
	 * @param docsList
	 * @throws Exception 
	 */
	public void copyDocument(Object mainObject, Doclibary doclibary, List docsList) 
	throws Exception;
}
