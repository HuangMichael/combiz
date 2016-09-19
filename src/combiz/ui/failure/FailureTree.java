package combiz.ui.failure;

import combiz.business.workorder.WofailcodeSrv;
import combiz.domain.workorder.Wofailcause;
import combiz.domain.workorder.Wofailcode;
import combiz.domain.workorder.Wofaildeal;
import combiz.domain.workorder.Wofailproblem;
import combiz.domain.workorder.Workorder;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.TopWindow;

import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Tree;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treecol;
import com.inbasis.zul.Treecols;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;
/**
 * ������
 * @author Administrator
 *
 */
public class FailureTree extends Tree {

	WofailcodeSrv wofailcodeSrv;

	public void onCreate() throws Exception{
		this.setVflex(true);
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 160) + "px");
		createRoot();
	}
	
	public void createRoot() throws Exception {
		// ����������¹���
		this.getChildren().clear();
		CommonDialog comdialog =(CommonDialog)this.getFellow("mainWnd");
		Workorder workorder = (Workorder)comdialog.getMainObject();
		List list = IBOSrvUtil.getBaseDao().findWithQuery(Wofailcode.class, "wonum in( select w.wonum from Workorder w where w.status!='ȡ��' and w.eqnum='"+workorder.getEqnum()+"')");
		if (list == null || list.isEmpty()) {
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			this.appendChild(tc);
			return;
		}
		Treecols tcols = new Treecols();
		Treecol tcol = new Treecol();
		tcol.setMaxlength(20);// ��������ʾ������ַ���
		tcols.appendChild(tcol);
		this.appendChild(tcols);

		Treechildren tc = new Treechildren();
		for (int i = 0; i < list.size(); i++) {
			Wofailcode wofailcode = (Wofailcode) list.get(i);
				Treeitem ti = new Treeitem("���ϴ���:"+wofailcode.getDescription());
			if(wofailcode.getDescription()!=null){
				ti.setTooltiptext("���ϴ���:"+wofailcode.getDescription());
				ti.setValue(wofailcode);
				ti.setImage("/images/node_image_dept.gif");
				ti.setOpen(true);
				tc.appendChild(ti);
			}
			List list1 = IBOSrvUtil.getBaseDao().findWithQuery(Wofailproblem.class,"failurecode = '" + wofailcode.getFailurecode()+"' and wonum in( select w.wonum from Workorder w where w.status!='ȡ��' and w.eqnum='"+workorder.getEqnum()+"')");
			Treechildren tc1 = new Treechildren();
			if(list1 !=null){
				for (int j = 0; j < list1.size(); j++) {
					Wofailproblem wofailproblem = (Wofailproblem)list1.get(j);
					
						Treeitem ti1 = new Treeitem("��������:"+wofailproblem.getDescription());
					if(wofailproblem.getDescription()!=null){
						ti1.setTooltiptext("��������:"+wofailproblem.getDescription());
						ti1.setValue(wofailproblem);
						ti1.setImage("/images/node_image_dept.gif");
						ti1.setOpen(true);  
						tc1.appendChild(ti1);
						ti.appendChild(tc1);//���µ�tc������һ��ti��
					}
					List list2 = IBOSrvUtil.getBaseDao().findWithQuery(Wofailcause.class,"failureproblem = '" + wofailproblem.getFailureproblem()+"' and wonum in( select w.wonum from Workorder w where w.status!='ȡ��' and w.eqnum='"+workorder.getEqnum()+"')");
					Treechildren tc2 = new Treechildren();// 
					if(list2 !=null){
						for (int k = 0; k < list2.size(); k++) {
							Wofailcause wofailcause = (Wofailcause)list2.get(k);
							Treeitem ti2 = new Treeitem("�ܽ�ԭ��:"+wofailcause.getDescription());
							if(wofailcause.getDescription()!=null){
								ti2.setTooltiptext("�ܽ�ԭ��:"+wofailcause.getDescription());
								ti2.setValue(wofailcause);
								ti2.setImage("/images/node_image_dept.gif");
								ti2.setOpen(true); 
								tc2.appendChild(ti2);
								ti1.appendChild(tc2);//���µ�tc������һ��ti��
							}
							List list3 = IBOSrvUtil.getBaseDao().findWithQuery(Wofaildeal.class,"failurecause = '" + wofailcause.getFailurecause()+"' and wonum in( select w.wonum from Workorder w where w.status!='ȡ��' and w.eqnum='"+workorder.getEqnum()+"')");
							Treechildren tc3 = new Treechildren();// 
							if(list3 !=null){
								for (int n = 0; n < list3.size(); n++) {
									Wofaildeal wofaildeal = (Wofaildeal)list3.get(n);
									Treeitem ti3 = new Treeitem("�����ʩ:"+wofaildeal.getDescription());
									if(wofaildeal.getDescription()!=null){
										ti3.setTooltiptext("�����ʩ:"+wofaildeal.getDescription());
										ti3.setValue(wofaildeal);
										ti3.setImage("/images/node_image_dept.gif");
										ti3.setOpen(true); 
										tc3.appendChild(ti3);
										ti2.appendChild(tc3);
									}
								}
							}
						}
					}
				}
			}
			// ����Ҫ����β����ϣ������Զ������ͳ�������
			Treerow treerow = (Treerow) ti.getChildren().get(0);
			Treecell treecell = (Treecell) treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			// ////////////////////////////////////////////
		}
		this.appendChild(tc);
	}

}
