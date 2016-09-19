package combiz.ui.pr;

import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class ReqplanWindow extends MainWindow implements InfWindow {

	public ReqplanWindow() {
		super();
	}

	/**
	 * 
	 * @TODO ����һ����¼
	 * @throws Exception
	 * @����� 2007-8-7 ����01:17:05
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Pr pr = new Pr();

		pr.setPrnum(this.genAutokey("prnum"));
		pr.setStatus("����δ����");
		pr.setStatusdate(new Date());
		pr.setRequestdate(new Date());
		//pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setRequestedby(this.getLaborInfo().getLabornum());
		pr.setRequestdept(this.getLaborInfo().getDeptnum());
		pr.setExchangerate(0d);
		pr.setExchangedate(new Date());
		//pr.setChangedate(new Date());
		pr.setPrtype("����ƻ�");
		mainObject = pr;

		return true;
	}


	/* 
	 * ���ܣ�����֮�����Ƿ�Ϊֻ��
	 * ���ߣ����
	 * ���ڣ�2008-11-10����04:34:13
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr) this.getMainObject();
		ListWindow prlineWnd = (ListWindow) this.getFellow("prline");
		if (pr.getStatus()!=null && pr.getStatus().equals("����׼")) {
			prlineWnd.setReadonlyList(true);
		}else{
			prlineWnd.setReadonlyList(false);
		}
		
		super.initData();
	}
	/**
	 * ������ �����豸ѡ�񱸼� 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		//Pr pr = (Pr) this.getMainObject();
		//if (!pr.getStatus().equals("����׼")) {
			Workorder workorder = new Workorder();
			CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
					"/common/prfindpartbyequip.xul");
			if (comdialog == null)
				return;
			if (comdialog.isConfirm())
				return;
			this.refreshData();
//		} else {
//			Messagebox.show("��������׼�޷���Ӳɹ�����");
//		}

	}
	/**
	 * �������ʱ������BOM������Ϣ
	 * 
	 * ����:������ ����:Mar 2, 2009
	 * 
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		// Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("����׼")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,
				"/common/prfindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("prline");
		listWnd.refreshData();// ˢ�²���
		// }else{
		// Messagebox.show("��������׼�޷���ӱ���");
		// }
	}
	
	/* workflow
	 * ���͹�������ʱ��У��ֿ⣬������������Ϣ
	 * ���
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr)this.getMainObject();
		String prnum = pr.getPrnum();
		boolean flasg = false;
		List polinelist = this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "prnum = '"+prnum+"'");
		if (polinelist.size()>0) {
			for (int i=0;i<polinelist.size();i++) {
				Prline prline = (Prline) polinelist.get(i);
				String warehouse = prline.getWarehouse();
				String itemnum = prline.getItemnum();
				Double qty = prline.getOrderqty();
				if (warehouse == null || itemnum == null || qty == null || qty<=0 ){
					flasg = true;	
				}
			}
			if (flasg) {
				Messagebox.show("���������ʱ��룬������������ֿ���Ϣ�����ƣ����ܷ��͹����������ʵ��");
				return;
			}
		}else{
			Messagebox.show("�ö���δ�κβɹ����У����ܷ��͹����������ʵ��");
			return;
		}
		super.workflow();
	}

}
