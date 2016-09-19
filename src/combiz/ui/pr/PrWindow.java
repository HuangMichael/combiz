package combiz.ui.pr;

import combiz.business.pr.PrSrv;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class PrWindow extends MainWindow implements InfWindow {

	public PrWindow() {
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
		pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setRequestedby(this.getLaborInfo().getLabornum());
		pr.setRequestdept(this.getLaborInfo().getDeptnum());
		pr.setPrnumtype("�ֶ���д");
		//pr.setBudnum();
		pr.setChangedate(new Date());
		pr.setPrtype("�ɹ�����");
		mainObject = pr;

		return true;
	}

	/***************************************************************************
	 * 
	 * @TODO �Զ�����ѯ�۵���ѯ�۵��С���Ӧ������Դ�ڲɹ����롢�ɹ�������
	 * @throws Exception
	 * @����� 2007-8-15 ����11:28:05
	 */
	public void createrfq() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��д���ѯ�۵�������");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ڶԲɹ��������ǰ�����ȱ������ݣ�");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (pr.getStatus().equals("����׼")) {
			String rfqnum = ((PrSrv) this.getMainSrv()).ceaterfq(this
					.getMainObject());
			this.setMainData();
			Messagebox.show("�ɹ�����ѯ�۵���ѯ�۵���-��Ӧѯ�۵�:" + rfqnum);
		} else
			Messagebox.show("�ɹ����뻹û����׼�����ܴ���ѯ�۵�");

	}

	/**
	 * 
	 * @throws Exception
	 * @TODO �Զ����ɲɹ������ɹ����У���Ӧ������Դ�ڲɹ����롢�ɹ�������s
	 * @����� 2007-8-15 ����11:41:13
	 */
	public void createpo() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��д����ɹ���������");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ɲɹ����봴���ɹ�������ǰ�����ȱ������ݣ�");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (pr.getStatus().equals("����׼")) {
			String ponum = ((PrSrv) this.getMainSrv()).createpo(this
					.getMainObject());
			this.setMainData();
			Messagebox.show("�ɹ����ɲɹ����Ͳɹ�����-��Ӧ�ɹ���:" + ponum);
		} else
			Messagebox.show("�ɹ����뻹û����׼�����ܴ����ɹ���");

	}

	/**
	 * @author ���� ���ܣ����ɹ����븴�ƺ������µĲɹ�����
	 * @throws Exception
	 *             2008-1-22����11:12:56
	 */
	public void copypr() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��и��Ʋ�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ɹ����븴�Ʋ���ǰ�����ȱ������ݣ�");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Prline.class, "prnum = '" + pr.getPrnum() + "'");
		String prnum = ((PrSrv) this.getMainSrv()).copypr(retList, pr);
		this.setMainData();
		Messagebox.show("�ѳɹ����ɹ���:" + pr.getPrnum() + "���Ƶ��ɹ���" + prnum);

	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ѡ�е�����ƻ��кϲ����ɹ���������
	 * ���ڣ�Oct 22, 2008 3:06:56 PM
	 *
	 */
	public void unitepr() throws Exception{
		Pr pr = (Pr)this.mainObject;
		if (pr.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
//		����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��кϲ��ɹ������в�����");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�ϲ��ɹ������в���ǰ�����ȱ������ݣ�");
			return;
		}
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this.getMainObject(), "/pr/uniteprlinepopup.xul");
		if(listWnd==null)
			return;
		
		//�ж��Ƿ�����ȷ����ť������ȡ����ť
		if(!listWnd.isConfirm())
			return;
		}

	/* 
	 * ���ܣ������Ӵ����Ƿ�Ӧ��Ϊֻ��
	 * ���ߣ����
	 * ���ڣ�2008-11-7����03:52:13
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr) this.mainObject;
		ListWindow prlineWnd = (ListWindow)this.getFellow("prline");
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
