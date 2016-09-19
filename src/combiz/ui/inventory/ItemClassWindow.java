package combiz.ui.inventory;

import java.util.List;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class ItemClassWindow extends TMainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////该类的主对象为 Classification
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public ItemClassWindow()
	{
		super();
	}
	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Classification newobj = new Classification();
		newobj.setClasstype("物资");
		newobj.setHaschild("否");
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		
		mainObject = newobj;
		
		return true;
	}
	

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null)
		{
			Messagebox.show("没有选中要删除的记录！");
			return;
		}

		Classification classification = (Classification) ti.getValue();
		//调用业务对象的删除方法
		super.delete();
		// 重新构建树
		mainTree.afterDeleteItem();
	}

	
	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：classance中插入关联关系
	 * 日期：Sep 1, 2008 3:36:07 PM
	 *
	 */
	public void cretetree() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("生成父类操作前，请先保存当前的记录！");
			return;
		}
		int rtn = Messagebox.show("是否确定重新创建数据吗？","确认创建！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			ItemSrv itemsrv = (ItemSrv)IBOSrvUtil.getSrv("item");
			List classlist = this.getMainSrv().getBaseDao().findWithQuery(Classification.class, "classtype = '物资' and  parent is null");
			
			if (classlist.size()>0) {
				List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, " classid='"+((Classification)classlist.get(0)).getClassid()+"'");
				if (listance.size()>0) {
					Messagebox.show("数据已经存在，不能再次创建!");
					return;
				}
			}
			//List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, "");
			//this.getMainSrv().getBaseDao().executeSql("delete Classance where ");
			
			for (int i=0;i<classlist.size();i++) {
				Classification classification = (Classification) classlist.get(i);
				itemsrv.cretetree(classlist);
				this.refreshData();
				Messagebox.show("数据创建完毕!");
			}
		setMainData();

		}
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李建红 功能：在修改物资为周转件的时候验证库存必须为零
	 * 
	 */
	
	/* (non-Javadoc)
	 * @see combiz.system.ui.TMainWindow#save()
	 */
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行保存操作！");
			return;
		}
		ListWindow itemwnd = (ListWindow) this.getFellow("itemclass");//得到界面上的对象
		Item item =(Item) itemwnd.getSelectObject();
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+item.getItemnum()+"'");
		//List itemlist = itemwnd.getSelectObjects();
		if (itemlist.size()>0) {
			Item olditem =(Item) itemlist.get(0);
			
			if (item.getId() != null) {//说明不是新建记录
				if (!(item.getRotating().equals(olditem.getRotating()))) {
					if ("是".equals(item.getRotating())) {
						int rtn = Messagebox.show("是否确定修改为周转件吗？", "确认修改！", Messagebox.YES
								| Messagebox.NO, Messagebox.QUESTION);
						if (rtn == Messagebox.NO)
							return;
						else if (rtn == Messagebox.YES) {
							//得到库存是否有余量，有，则不能修改
							List invlist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+item.getItemnum()+"'");
							Double culbar = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.culbar) from Invstock i where i.itemnum='"+item.getItemnum()+"'");
							if (culbar!=null && culbar>0) {
								Messagebox.show("物资库存余量不为零，不能修改成周转件！");
								return;
							}
							if (invlist.size()>0) {
								Messagebox.show("物资库存余量不为零，不能修改成周转件！");
								return;
							}
							}
					}
				}
				
			}
		}
		
		
		super.save();
	}
	
}
