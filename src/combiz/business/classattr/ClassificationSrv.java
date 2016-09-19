package combiz.business.classattr;

import java.util.List;

import combiz.domain.classattr.Classification;
import combiz.system.IBOBaseSrv;

public interface ClassificationSrv extends IBOBaseSrv {
	//同步技术参数到物资
	public void synchspec(Object obj) throws Exception;
	/**
	 * 子集的数量
	 * 洪小林  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public List getChildrens(Classification classification)
	throws Exception;
	
	/**
	 * 子集的数量
	 * 洪小林  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public int getChildrenCount(Classification classification)
	throws Exception;
	
	/**
	 * 是否有子集
	 * 洪小林  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public boolean hasChildren(Classification classification)
	throws Exception;
	
	
	
}
