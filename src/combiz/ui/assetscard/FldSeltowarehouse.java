package combiz.ui.assetscard;

import java.util.List;

import combiz.domain.assetscard.Allocation;
import combiz.domain.contract.Contract;
import combiz.domain.invoice.Invoice;
import combiz.domain.location.Locations;
import combiz.domain.po.Po;
import combiz.system.FieldAdapter;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;



/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 1:31:53 PM  May 18, 2010 
 * ���ܣ�������ղֿⲻ��Ĭ�ϲֿ⣬����ѡ�񡰽��յص㡱�µ����вֿ⡣
 * �����Invrectrans
 * �����ֶΣ�towarehouse
 */
public class FldSeltowarehouse extends FieldAdapter
{
	//�ֶ�����ֵ�󣬻س�������뿪���ø÷���
	@Override
	public void action(Component arg0) throws Exception {

	}
	//�ֶγ�ʼ�����ø÷���
	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}
	//�����ƿ�Ч��
	@Override
	public void validate(Component arg0) throws Exception {
		// TODO Auto-generated method stub

	}




	public String getListWhere(Component ibandbox) 
	{
		//�ӱ༭���ڻ�ȡ��Ӧ��LIST����
//		MainWindow mainWnd = (MainWindow) this.getRecWnd();
		String whereStr = null;

		MainWindow mainwnd = (MainWindow) this.getRecWnd().getOwnerWnd();
		if(mainwnd!=null)
		{
			if(mainwnd.getApp().equals("ALLOCATIONREC"))
			{
				Allocation allocation  = (Allocation) mainwnd.getMainObject();
				String tosite = allocation.getTosite();
				if(Util.isNotNull(tosite))
				{
					whereStr = "warehouse in(select t.warehouse from Warehouse t where t.sitenum = '"+tosite+"')";
				}
				else
				{
					whereStr = "1=1";
				}
			}
			else
			{
				whereStr = "1=1";
			}

		}

		return whereStr;
	}


}
