package combiz.business.doclib;

import combiz.domain.doclib.Docapplib;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface DocapplibSrv extends IBOBaseSrv
{
	/**
	 * @TODO ɾ�������б�Ĺ���Ӧ�ó��������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List docapplibs) throws Exception;
	/**
	 * @TODO �̳и�Ŀ¼�Ĺ�����Ӧ�ó��� ���ӵ���
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 ����07:05:55
	 */ 
	public void saveUpExtends(Object arg0) throws Exception;
	/**
	 * @TODO �̳и�Ŀ¼�Ĺ�����Ӧ�ó���,�ɸ�����
	 * @param arg0
	 * @throws Exception
	 * yuanjq 2007-8-14 ����07:05:55
	 */ 
	public void saveDownExtends(Object arg0) throws Exception;

}
