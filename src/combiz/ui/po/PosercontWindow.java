package combiz.ui.po;
 
import combiz.business.po.PoSrv;
import combiz.business.pr.PrSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;


public class PosercontWindow extends MainWindow
implements InfWindow
{

	public PosercontWindow()
	{
		super();
	}

	
	
	/**
	 * 
	 * @TODO ����һ����¼
	 * @throws Exception
	 * @����� 2007-8-7 ����01:17:05
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Po po = new Po();
		//int count = this.mainSrv.getRowCountByWhere(po, "");
		po.setPonum(this.genAutokey("ponum"));
		po.setStatus("�ݸ�");
		po.setPotype("����ɹ�");
		po.setStatusdate(new Date());
		po.setTotalcost(0d);
		po.setReceipts("δ����");
		po.setChangedate(new Date());
		po.setChangeby(this.getLaborInfo().getLabornum());
		po.setOrderdate(new Date());
		po.setIsprotocol("��");
		po.setIsgov("��");
		mainObject = po;
		return true;
	}
	

	
	
	/**
	 * 
	 * @TODO �����ɹ��������С�����һ�����壬�û�����ѡ�����еĲ����������ݡ�
	 * @throws Exception
	 * @����� 2007-8-24 ����04:20:44
	 */
	
	/**
	*@author ����
	*���ܣ����Ʋɹ�������ѡ�еĲɹ������и��ƣ�
	*@throws Exception 
	*2008-1-22����01:10:31
	*/
	public void copypo() throws Exception{
		
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��и��Ʋ�����");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("�ɹ������Ʋ���ǰ�����ȱ������ݣ�");
			return;
		}
		Po po = (Po) this.getMainObject();
		List retList =  this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "prnum = '" + po.getPonum() +"'");
		Po newpo = ((PoSrv)this.getMainSrv()).copypo(po);
		this.setMainObject(newpo);
		Messagebox.show("�ѳɹ����ɹ���:" + po.getPonum() + "���Ƶ��ɹ���" + newpo.getPonum());
		this.refreshData();
	}



	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		super.save();
		this.refreshData();
	}
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Po po = (Po) this.mainObject;
		String s[] = {"po.vendor","po.supervisor","po.isprotocol","po.isgov","po.description","po.orderdate","po.supervisor","po.supervisor","po.totalcost","po.notifynum","po.poreason"};
		if (po.getStatus()!=null && po.getStatus().equals("ִ�вɹ�")) {
			this.setReadonlyFields(s);
		}
		super.initData();
	}
	




	
	
}
