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
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CheckqtyWindow() {
		super();
	}

	/**
	 * 添加记录
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Checkqty newobj = new Checkqty();
		// 请在下面添加对象的初始化值
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getMainSrv().findWithQuery(Labor.class,
				"labornum ='" + labornum + "'");
		Labor labor = (Labor) laborlist.get(0);
		String sitenum = labor.getSitenum();
		String checkqtynum = sitenum + "_" + this.genAutokey("checkqtynum");
		newobj.setCheckqtynum(checkqtynum);
		newobj.setStatusdate(new Date());
		newobj.setCheckdate(new Date());
		newobj.setIsall("是");
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
			if (checkqty.getIsall().equals("是")) {
				this.setReadonlyFields(field1);
			} else {
				this.setReadonlyFields(field2);
			}
		}

		super.initData();
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：生成盘点明细表 日期：Oct 20, 2008 11:03:22 AM
	 * 
	 */
	public void generate() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行生成盘点明细操作！");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("生成盘点明细操作前，请先保存数据！");
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
			if (isall.equals("否") && Util.isNull(warehouse)
					&& Util.isNull(binnum) && Util.isNull(itemnum)
					&& Util.isNull(classid))// 判断是否盘点所有库存
			{
				throw new Exception("请选择生成盘点明细单的条件！");
			}
			if (isall.equals("是")) {
				wherestr = "1=1";
			} else {
				if (Util.isNotNull(warehouse))// 盘点库房不为空的情况
				{
					String ware[] = warehouse.split("、");
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

				if (Util.isNotNull(binnum))// 盘点箱柜不为空的情况
				{
					String bin = "binnum like '%" + binnum + "%' ";
					if (Util.isNull(wherestr)) {
						wherestr = bin;
					} else {
						wherestr = wherestr + "and " + bin;
					}

				}

				if (Util.isNotNull(itemnum))// 编号不为空的情况
				{
					String item = "itemnum like '%" + itemnum + "%' ";
					if (Util.isNull(wherestr)) {
						wherestr = item;
					} else {
						wherestr = wherestr + "and " + item;
					}

				}

				if (Util.isNotNull(classid))// 库存分类不为空的情况
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
			Messagebox.show("已经生成盘点日期为'" + checkdatestr + "'的盘点明细！");
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：按照盘点结果修改库存余量 日期：4:27:49 PM Dec 29, 2008
	 * 
	 */
	public void modifycurbal() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行修改库存余量操作！");
			return;
		}
		Checkqty checkqty = (Checkqty) this.getMainObject();

		Boolean tomodify = null;
		if (Messagebox.show("确定要根据盘点结果修改库存存货余量吗？", "提示！！！", Messagebox.YES
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
				Messagebox.show("已经完成库存余量的修改！");
			} else {
				throw new Exception("盘点单号为'" + checkqty.getCheckqtynum()
						+ "'的盘单点明细不存在，无法根据明细修改库存余量！");
			}

		}
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：删除选定盘点表的明细 日期：Oct 20, 2008 4:14:05 PM
	 * 
	 */
	public void cleardata() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行清除明细操作！");
			return;
		}
		Checkqty checkqty = (Checkqty) this.getMainObject();
		// if(hgequ.getStatus().equals(Hgconstant.STOCK_STARTNO))
		// {
		Boolean todelete = null;
		if (Messagebox.show("确定要删除该盘库单的所有明细吗？", "提示！！！", Messagebox.YES
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
				throw new Exception("盘点单号为'" + checkqty.getCheckqtynum()
						+ "'的盘单点明细不存在，请确认！");
			}

		}

		// }
		// else
		// {
		// throw new Exception("状态为’"+hgequ.getStatus()+"'下，不能删除盘点明细，请核实！");
		// }

	}

}
