package combiz.ui.tool;

import java.util.List;

import combiz.domain.invoice.Invoice;
import combiz.domain.po.Poline;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Tooltrans;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;

/**
 * 功能：在发票行里复制采购单的时候，将满足条件的采购单行罗列出来。
 * 
 * @author 李阳 2008-1-24下午12:58:21
 */
public class RetrunLine extends CommonListWindow {

	public RetrunLine() {
		super();
	}

	public void onCreate() throws Exception {
		super.onCreate();

		// 获取 工具预留 列表
		ListWindow toolwnd = (ListWindow) this.getOwnerWnd().getFellow(
				"toolclass");
		Tool tool = (Tool) toolwnd.getSelectObject();

		if (!(tool instanceof Tool))
			throw new Exception("请选择一条记录!");


		// Long lng = 0L;
		Double lng = 0d;

		List inlist = this.getMainSrv().getBaseDao().findWithQuery(
				Tooltrans.class,
				"toolnum='" + tool.getToolnum() + "' and transtype = '借用'");

		String instr = null;
		if (inlist.size() > 0) { // 得到该工具是否有归还
			for (int m = 0; m < inlist.size(); m++) {
				Tooltrans ttrans = (Tooltrans) inlist.get(m);
				// 判断工具借用是否归还完毕
				String insql = "select sum(t.toolqty) from Tooltrans t where t.sendnum = '"
						+ ttrans.getId() + "' and t.transtype='归还'";
				lng = (Double) this.getMainSrv().getBaseDao().selectSumByHql(
						insql);
				System.out.println("===insql===" + insql);
				if (lng == null) {
					lng = 0d;
				}
				if (ttrans.getToolqty() > lng) {
					if (instr == null) {
						instr = ttrans.getId() + "";
					} else {
						instr = ttrans.getId() + "," + instr;
					}
				}

			}

		}
		if (instr != null)
			instr = "id in(" + instr + ")";
		else
			instr = "1=2";
		System.out.println("===lng====instr===" + lng + "===" + instr);

		this.setQueryString(instr);
		this.setOrderby("transdate desc");
		this.refreshData();
//		this.refreshChildData();
	}

}
