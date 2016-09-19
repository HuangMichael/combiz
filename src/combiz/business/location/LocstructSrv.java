package combiz.business.location;

import java.util.List;

import combiz.domain.location.Locstruct;
import combiz.system.IBOBaseSrv;

public interface LocstructSrv  extends IBOBaseSrv
{

	/**
	 * 添加到系统
	 * 洪小林  Nov 24, 2009
	 * @param locstruct
	 */
	public void addToSystem(Locstruct locstruct, Locstruct newobj)
	throws Exception;
	
	/**
	 * 获取位置子集，过滤出在新系统中不存在的子集
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public List getChildren(Locstruct locstruct)
	throws Exception;
	
	/**
	 * 获取位置父级
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public Locstruct getParent(Locstruct locstruct)
	throws Exception;
	
	/**
	 * 更新父级
	 * 洪小林  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void updateParent(Locstruct locstruct)
	throws Exception;
	
	/**
	 * 判断是否位置有子集
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public boolean hasChildren(Locstruct locstruct)
	throws Exception;
	
	/**
	 * 获取位置子集的数量
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	public int getChildrenCount(Locstruct locstruct)
	throws Exception;
	
	
	
}
