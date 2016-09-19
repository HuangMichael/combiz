package combiz.ui.inventory;

import combiz.business.po.PoSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;

import java.util.List;

import com.inbasis.zul.Messagebox;

public class MatrecWindow extends MainWindow implements InfWindow {

	public MatrecWindow() {
		super();
	}

	public void save() throws Exception {
		// TODO �Զ����ɷ������
		Po po = (Po) this.getMainObject();
		// if (this.getObjStatus() != this.MODIFIED
		// || this.getObjStatus() != this.ADDED) {
		// Messagebox.show("û���޸Ļ��½���¼���޷����б������");
		// }
		ListWindow listwnd = (ListWindow) this.getFellow("invrectrans");
		List invl = (List) listwnd.getModifiedObjectList();
		if (invl.size() > 0) {

			for (int j = 0; j < invl.size(); j++) {
				Invrectrans invrectrans = (Invrectrans) invl.get(j);
				Long id = invrectrans.getId();
				String itemnum = invrectrans.getItemnum();
				Double quantity = invrectrans.getQuantity();
				Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invrectrans t where polinenum = '"
								+ invrectrans.getPolinenum()
								+ "' and ponum = '" + invrectrans.getPonum()
								+ "' and id <> " + id + "");
				if (num == null)
				num = 0d;
				List polist = this.mainSrv.getBaseDao().findWithQuery(
						Poline.class,
						"polinenum = '" + invrectrans.getPolinenum()
								+ "' and ponum = '" + invrectrans.getPonum()
								+ "'");
				if (polist.size() > 0) {
					Poline pol = (Poline) polist.get(0);
					Double orderqty = pol.getOrderqty();
					if (quantity <= 0 || (quantity + num)/invrectrans.getConversion() > orderqty) {
						// throw new Exception("��������Ӧ��Ϊ0���Ҳ����ڶ������������ʵ��");
						Messagebox.show("���Ϊ��" + itemnum + "�Ŀ����Ŀ��������Ӧ��Ϊ0����\n�����ڶ������������ʵ��");
						return;
						// this.setValueByComponent(target, "0");

					}
				}
			}
		}
		super.save();
	}

	/**
	 * @author ���� ���ܣ��ڽ������Ͻ��յ�ʱ�򣬽��ɹ����е���Ϣ���ƹ�����
	 * @throws Exception
	 *             2008-1-29����11:42:49
	 */
	public void addpoline() throws Exception {

		Po po = (Po) this.mainObject;

		if (po.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (po.getStatus().equals("����׼")) {
			CommonListWindow listWnd = (CommonListWindow) this
					.popupDialog(
							this.getMainObject(),
							"/inventory/polinelist.xul",
							"ponum='"
									+ po.getPonum()
									+ "' and warehouse is not null and receiptscomplete <> 'ȫ������'");
			if (listWnd == null)
				return;

			// �ж��Ƿ�����ȷ����ť������ȡ����ť
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((PoSrv) this.getMainSrv()).addpoline(retList, po);
			this.refreshData();
			Messagebox.show("���ݳɹ����棬���ڽ�������ϸ����д�����Ϣ!");
		} else
			Messagebox.show("�òɹ���û����׼�����ܽ��н���");

		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

	}

	/**
	 * @author ���� ���ܣ���Ӧ����Ӧ�ó����²˵��еĽ��ռ��飻����ʱ��ͨ�����ռ����޸Ľ�������Ϣ��
	 * @throws Exception
	 *             2008-1-22����01:11:24
	 */
	public void verify() throws Exception {

		Po po = (Po) this.mainObject;

		if (po.getId()==null) {
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invrectrans.class,
				"ponum = '" + po.getPonum() + "' and status in('������','�����')");
		if (retList.size() == 0) {
			Messagebox.show("û����Ҫ����Ľ��ռ�¼!");
		} else {
			List polineList = this.getMainSrv().getBaseDao().selectListBySql("select ponum,polinenum from Invrectrans  where ponum = '" + po.getPonum() + "' and status in('������','�����')  group by ponum,polinenum");
			((PoSrv) this.getMainSrv()).verify(retList, po,polineList);
			this.refreshData();
			Messagebox.show("�Ѿ���ɼ���!");

		}
	}
}
