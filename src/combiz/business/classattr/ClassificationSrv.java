package combiz.business.classattr;

import java.util.List;

import combiz.domain.classattr.Classification;
import combiz.system.IBOBaseSrv;

public interface ClassificationSrv extends IBOBaseSrv {
	//ͬ����������������
	public void synchspec(Object obj) throws Exception;
	/**
	 * �Ӽ�������
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public List getChildrens(Classification classification)
	throws Exception;
	
	/**
	 * �Ӽ�������
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public int getChildrenCount(Classification classification)
	throws Exception;
	
	/**
	 * �Ƿ����Ӽ�
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public boolean hasChildren(Classification classification)
	throws Exception;
	
	
	
}
