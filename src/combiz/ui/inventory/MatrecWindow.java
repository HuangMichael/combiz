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
		// TODO 自动生成方法存根
		Po po = (Po) this.getMainObject();
		// if (this.getObjStatus() != this.MODIFIED
		// || this.getObjStatus() != this.ADDED) {
		// Messagebox.show("没有修改或新建记录，无法进行保存操作");
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
						// throw new Exception("接收数量应不为0，且不大于订购数量，请核实！");
						Messagebox.show("编号为：" + itemnum + "的库存项目接收数量应不为0，且\n不大于订购数量，请核实！");
						return;
						// this.setValueByComponent(target, "0");

					}
				}
			}
		}
		super.save();
	}

	/**
	 * @author 李阳 功能：在进行物料接收的时候，将采购单行的信息复制过来。
	 * @throws Exception
	 *             2008-1-29上午11:42:49
	 */
	public void addpoline() throws Exception {

		Po po = (Po) this.mainObject;

		if (po.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (po.getStatus().equals("已批准")) {
			CommonListWindow listWnd = (CommonListWindow) this
					.popupDialog(
							this.getMainObject(),
							"/inventory/polinelist.xul",
							"ponum='"
									+ po.getPonum()
									+ "' and warehouse is not null and receiptscomplete <> '全部接收'");
			if (listWnd == null)
				return;

			// 判断是否点击了确定按钮，还是取消按钮
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			((PoSrv) this.getMainSrv()).addpoline(retList, po);
			this.refreshData();
			Messagebox.show("数据成功保存，请在接收行明细里填写箱柜信息!");
		} else
			Messagebox.show("该采购单没有批准，不能进行接收");

		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

	}

	/**
	 * @author 李阳 功能：对应接收应用程序下菜单中的接收检验；接收时，通过接收检验修改接收行信息。
	 * @throws Exception
	 *             2008-1-22下午01:11:24
	 */
	public void verify() throws Exception {

		Po po = (Po) this.mainObject;

		if (po.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invrectrans.class,
				"ponum = '" + po.getPonum() + "' and status in('待检验','待编号')");
		if (retList.size() == 0) {
			Messagebox.show("没有需要检验的接收记录!");
		} else {
			List polineList = this.getMainSrv().getBaseDao().selectListBySql("select ponum,polinenum from Invrectrans  where ponum = '" + po.getPonum() + "' and status in('待检验','待编号')  group by ponum,polinenum");
			((PoSrv) this.getMainSrv()).verify(retList, po,polineList);
			this.refreshData();
			Messagebox.show("已经完成检验!");

		}
	}
}
