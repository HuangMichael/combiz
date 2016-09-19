package combiz.ui.pm;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class PmClassTree extends MainTree {
	PmClassWindow mainWnd;

	ClassificationSrv classificationSrv;

	public PmClassTree() {
		super();
	}

	public void onCreate() {
		this.setVflex(true);
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 120) + "px");
	}

	public void createRoot() throws Exception {
		this.getChildren().clear();
		mainWnd = (PmClassWindow) this.getFellow("mainWnd");
		classificationSrv = (ClassificationSrv) IBOSrvUtil.getSrv("classification");
		List list = classificationSrv
				.findWithQuery("classtype='Ԥ����ά��' and parent is null");
		if (list == null || list.isEmpty()) {
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�з������ݣ�"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
			return;
		}
		classificationSrv.setOrderBy("orderby");
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for (int i = 0; i < list.size(); i++) {
			Classification classification = (Classification) list.get(i);
			String desc = classification.getDescription();
			if (desc == null)
				desc = "";
//			ti = new Treeitem(classification.getClassid()); // +":"+lochiery.getLocation()
			ti = new Treeitem(desc);
			ti.setValue(classification);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			// ����Ҫ����β����ϣ������Զ������ͳ�������
			Treerow treerow = (Treerow) ti.getChildren().get(0);
			Treecell treecell = (Treecell) treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			// ////////////////////////////////////////////
			if (Util.getBoolean(classification.getHaschild())) {
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			tc.appendChild(ti);
		}
		this.appendChild(tc);

		if (this.getItemCount() > 0) {
			Treeitem treeitem = (Treeitem) this.getItems().iterator().next();
			this.selectItem(treeitem);
			mainWnd.fetchData(treeitem);
		} else
			mainWnd.fetchData(null);
	}

	public void expand(Treeitem parentitem) throws Exception {
		if (parentitem != null && parentitem.getValue() != null) // ���û�и���
		{
			// *********��һ�֣�ÿ�ζ�ɾ�������´����ݿ���ȡ��
			Treechildren tc = parentitem.getTreechildren();
			if (tc != null && tc.getChildren().size() > 0)
				tc.getChildren().clear();
			// *********�ڶ��֣���һ�ε���Ǵ����ݿ���ȡ���ݣ��Ժ�Ͳ�ɾ��Ҳ��ȡ��
			// if(tc.getChildren().size()>0)
			// return;

			Classification lochparent = (Classification) parentitem.getValue();
			List list = classificationSrv
					.findWithQuery("classtype in('Ԥ����ά��') and parent = '"
							+ lochparent.getClassid() + "'");
			Treeitem ti;
			for (int i = 0; i < list.size(); i++) {
				Classification classification = (Classification) list.get(i);
				String locdesc = classification.getDescription();
				if (locdesc == null)
					locdesc = "";
//				ti = new Treeitem(classification.getClassid()); 
				ti = new Treeitem(locdesc+":"+classification.getClassid()); 
				//ti = new Treeitem(locdesc);
				ti.setValue(classification);
				ti.setImage("/images/img_location.gif");
				ti.setOpen(false);
				// ����Ҫ����β����ϣ������Զ������ͳ�������
				Treerow treerow = (Treerow) ti.getChildren().get(0);
				Treecell treecell = (Treecell) treerow.getChildren().get(0);
				treecell.setStyle("white-space:nowrap;");
				// ////////////////////////////////////////////
				if (Util.getBoolean(classification.getHaschild())) {
					Treechildren nextChild = new Treechildren();
					ti.appendChild(nextChild);
					ti.addEventListener("onOpen", new openNode());
				}
				tc.appendChild(ti);
			}
		} else // ����ˢ����
		{
			this.createRoot();
		}
	}

	public void onSelect() throws Exception {
		Treeitem item = getSelectedItem();
		mainWnd.fetchData(item);
	}

	public void afterModifyItem() throws Exception {
		Treeitem item = getSelectedItem();
		if (item == null)
			return;
		Classification classification = (Classification) mainWnd
				.getMainObject();
		item.setValue(classification);
		item.setLabel( classification.getClassid());

		this.onSelect();
	}
}
