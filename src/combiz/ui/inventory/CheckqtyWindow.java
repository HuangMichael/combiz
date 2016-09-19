package combiz.ui.inventory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.inbasis.zul.Messagebox;
import combiz.business.inventory.CheckqtySrv;
import combiz.domain.corp.Labor;
import combiz.domain.inventory.Checkqty;
import combiz.domain.inventory.Checkqtyitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class CheckqtyWindow extends MainWindow implements InfWindow {
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public CheckqtyWindow() {
		super();
	}

	/**
	 * ��Ӽ�¼
	 * 
	 * @throws Exception
	 *             ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * ������ʼֵ
		 **********************************************************************/
		Checkqty newobj = new Checkqty();
		// ����������Ӷ���ĳ�ʼ��ֵ
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String checkqtynum = sitenum + "_" + this.genAutokey("checkqtynum");
		newobj.setCheckqtynum(checkqtynum);
		newobj.setStatusdate(new Date());
		newobj.setCheckdate(new Date());
		newobj.setIsall("��");
		mainObject = newobj;
		return true;
	}

	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Checkqty checkqty = (Checkqty) this.mainObject;

		String[] field1 = { "checkqty.chkwarehouse", "checkqty.checkbinnum",
				"checkqty.classid", "checkqty.itemnum" };
		String[] field2 = {};

		String isall = checkqty.getIsall();
		if (isall != null) {
			if (checkqty.getIsall().equals("��")) {
				this.setReadonlyFields(field1);
			} else {
				this.setReadonlyFields(field2);
			}
		}

		super.initData();
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ������̵���ϸ�� ���ڣ�Oct 20, 2008 11:03:22 AM
	 * 
	 */
	public void generate() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��������̵���ϸ������");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("�����̵���ϸ����ǰ�����ȱ������ݣ�");
			return;
		}
		Checkqty checkqty = (Checkqty) this.getMainObject();

		if (checkqty != null) {
			Date checkdate = checkqty.getCheckdate();
			String checkdatestr = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
			if (checkdate != null)
				checkdatestr = df.format(checkdate);
			String warehouse = checkqty.getChkwarehouse();
			String binnum = checkqty.getCheckbinnum();
			String itemnum = checkqty.getItemnum();
			String classid = checkqty.getClassid();
			String isall = checkqty.getIsall();

			String wherestr = null;
			if (isall.equals("��") && Util.isNull(warehouse)
					&& Util.isNull(binnum) && Util.isNull(itemnum)
					&& Util.isNull(classid))// �ж��Ƿ��̵����п��
			{
				throw new Exception("��ѡ�������̵���ϸ����������");
			}
			if (isall.equals("��")) {
				wherestr = "1=1";
			} else {
				if (Util.isNotNull(warehouse))// �̵�ⷿ��Ϊ�յ����
				{
					String ware[] = warehouse.split("��");
					int length = ware.length - 1;
					for (int i = 0; i < ware.length; i++) {
						String warecode = ware[i];
						if (Util.isNull(wherestr)) {
							wherestr = "'" + warecode + "'";
						} else {
							wherestr = wherestr + ",'" + warecode + "'";
						}
					}
					wherestr = "warehouse in (" + wherestr + ")";
				}

				if (Util.isNotNull(binnum))// �̵����Ϊ�յ����
				{
					String bin = "binnum like '%" + binnum + "%' ";
					if (Util.isNull(wherestr)) {
						wherestr = bin;
					} else {
						wherestr = wherestr + "and " + bin;
					}

				}

				if (Util.isNotNull(itemnum))// ��Ų�Ϊ�յ����
				{
					String item = "itemnum like '%" + itemnum + "%' ";
					if (Util.isNull(wherestr)) {
						wherestr = item;
					} else {
						wherestr = wherestr + "and " + item;
					}

				}

				if (Util.isNotNull(classid))// �����಻Ϊ�յ����
				{
					if (Util.isNull(wherestr)) {
						wherestr = "itemnum in (select t.itemnum from Item t where t.classid like '%"
								+ classid + "%')";
					} else {
						wherestr = wherestr
								+ "and itemnum in (select t.itemnum from Item t where t.classid like '%"
								+ classid + "%')";
					}

				}

			}
			((CheckqtySrv) this.getMainSrv()).generate(checkqty, wherestr);
			this.refreshData();
			Messagebox.show("�Ѿ������̵�����Ϊ'" + checkdatestr + "'���̵���ϸ��");
		}

	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ������̵����޸Ŀ������ ���ڣ�4:27:49 PM Dec 29, 2008
	 * 
	 */
	public void modifycurbal() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ����޸Ŀ������������");
			return;
		}
		Checkqty checkqty = (Checkqty) this.getMainObject();

		Boolean tomodify = null;
		if (Messagebox.show("ȷ��Ҫ�����̵����޸Ŀ����������", "��ʾ������", Messagebox.YES
				| Messagebox.NO, "") == Messagebox.YES)
			tomodify = true;
		else
			tomodify = false;
		if (tomodify) {
			List list = this.getMainSrv().getBaseDao().findWithQuery(
					Checkqtyitem.class,
					"checkqtynum = '" + checkqty.getCheckqtynum()
							+ "'");
			if (list.size() > 0) {
				((CheckqtySrv) this.getMainSrv()).modifycurbal(list);
				this.refreshChildData();
				Messagebox.show("�Ѿ���ɿ���������޸ģ�");
			} else {
				throw new Exception("�̵㵥��Ϊ'" + checkqty.getCheckqtynum()
						+ "'���̵�����ϸ�����ڣ��޷�������ϸ�޸Ŀ��������");
			}

		}
	}

	/**
	 * ����
	 * 
	 * ���ߣ����� ���ܣ�ɾ��ѡ���̵�����ϸ ���ڣ�Oct 20, 2008 4:14:05 PM
	 * 
	 */
	public void cleardata() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��������ϸ������");
			return;
		}
		Checkqty checkqty = (Checkqty) this.getMainObject();
		// if(hgequ.getStatus().equals(Hgconstant.STOCK_STARTNO))
		// {
		Boolean todelete = null;
		if (Messagebox.show("ȷ��Ҫɾ�����̿ⵥ��������ϸ��", "��ʾ������", Messagebox.YES
				| Messagebox.NO, "") == Messagebox.YES)
			todelete = true;
		else
			todelete = false;
		if (todelete) {

			List list = this.getMainSrv().getBaseDao().findWithQuery(
					Checkqtyitem.class,
					"checkqtynum = '" + checkqty.getCheckqtynum()
							+ "' ");
			if (list.size() > 0) {
				((CheckqtySrv) this.getMainSrv()).cleardata(list);
				this.refreshChildData();
			} else {
				throw new Exception("�̵㵥��Ϊ'" + checkqty.getCheckqtynum()
						+ "'���̵�����ϸ�����ڣ���ȷ�ϣ�");
			}

		}

		// }
		// else
		// {
		// throw new Exception("״̬Ϊ��"+hgequ.getStatus()+"'�£�����ɾ���̵���ϸ�����ʵ��");
		// }

	}

}
