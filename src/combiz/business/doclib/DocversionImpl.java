package combiz.business.doclib;

import combiz.domain.doclib.Docversion;
import combiz.system.IBOBaseImpl;
import combiz.system.util.DocUtil;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class DocversionImpl extends IBOBaseImpl
implements DocversionSrv {


	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Docversion))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		
		//ɾ���ĵ��汾���ļ��У�����ɾ��ʵ���ļ�
		/*Docversion docversion = (Docversion)obj;
		if(docversion.getId()!=null)
			DocUtil.deletePath(docversion.getUrlpath());*/
	}
	
	/**
	 * 
	 * @TODO �����ĵ��汾����
	 * @param obj
	 * @throws Exception
	 * @��С��  2007-8-17  ����03:22:56
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		Docversion docversion = (Docversion)obj;
		if(docversion.getId()==null)
		{
			//���½��ĵ��汾��ʱ��ͬʱ�����ĵ��汾Ŀ¼
			docversion.setUrlpath(docversion.getUrlpath() + docversion.getVersion() + "/");
			//�����ļ�·��
			DocUtil.createPath(docversion.getUrlpath());
		}
		
		super.save(docversion);
	}
	
	
	/**
	 *  
	 * @TODO �޸��ļ��ϴ���ʶ
	 * @param docversion
	 * @throws Exception
	 * @��С��  2007-8-17  ����01:44:55
	 */
	public void changeUploadStatus(Docversion docversion) 
	throws Exception
	{
		super.getBaseDao().updateObject(docversion);
	}
	
	/**
	 * @TODO ɾ�������б�İ汾������
	 * @param docapplibs
	 * yuanjq 2007-8-15 ����09:53:36
	 */ 
	public void deleteBatch(List docversions) throws Exception{
		this.deleteBatch(docversions);
	}
	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
