package combiz.business.doclib;

import combiz.domain.doclib.Docversion;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocversionSrv extends IBOBaseSrv
{
	/**
	 * @TODO ɾ�������б�İ汾������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List docversions) throws Exception;
	/**
	 *  
	 * @TODO �޸��ļ��ϴ���ʶ
	 * @param docversion
	 * @throws Exception
	 * @��С��  2007-8-17  ����01:44:55
	 */
	public void changeUploadStatus(Docversion docversion) 
	throws Exception;
	
}
