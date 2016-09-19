package combiz.business.location;

import java.util.List;

import combiz.domain.location.Locstruct;
import combiz.system.IBOBaseSrv;

public interface LocstructSrv  extends IBOBaseSrv
{

	/**
	 * ��ӵ�ϵͳ
	 * ��С��  Nov 24, 2009
	 * @param locstruct
	 */
	public void addToSystem(Locstruct locstruct, Locstruct newobj)
	throws Exception;
	
	/**
	 * ��ȡλ���Ӽ������˳�����ϵͳ�в����ڵ��Ӽ�
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public List getChildren(Locstruct locstruct)
	throws Exception;
	
	/**
	 * ��ȡλ�ø���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public Locstruct getParent(Locstruct locstruct)
	throws Exception;
	
	/**
	 * ���¸���
	 * ��С��  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void updateParent(Locstruct locstruct)
	throws Exception;
	
	/**
	 * �ж��Ƿ�λ�����Ӽ�
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public boolean hasChildren(Locstruct locstruct)
	throws Exception;
	
	/**
	 * ��ȡλ���Ӽ�������
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public int getChildrenCount(Locstruct locstruct)
	throws Exception;
	
	
	
}
