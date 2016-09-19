package combiz.ui.basedata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.system.IBOSrvUtil;
import combiz.system.IBSServer;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class ClassificationWindow extends TMainWindow
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
	/**
	 * 
	 */
	public ClassificationWindow()
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

		newobj.setHaschild("否");
		Treeitem ti = mainTree.getSelectedItem();
		String newclassid = "";
		String classtype = null;
		String parent = null;
		long largeid = 0l;
		int count = 0;
		Double maxbh = 0d;
		/*Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
			//设置编码 ，每一层加01
			String classid = "";
			String format = "";//把字符串格式化成2位格式
			String type = classification.getClasstype();//得到分类
			List classlist = this.getMainSrv().getBaseDao().selectListByHql(" from Classification l where l.parent='"+classification.getClassid()+"' and l.classtype='"+type+"' order by l.classid desc");
			if (classlist.size()>0) {

				Classification classif = (Classification) classlist.get(0);
				String bigclassid = classif.getClassid();//得到最大编号
				String cutloc = bigclassid.substring(bigclassid.length()-2, bigclassid.length());
				String deloc = bigclassid.substring(0,bigclassid.length()-3);

				int lenth = cutloc.length();//得到初始时的classid的长度
				int setclassid = Integer.parseInt(cutloc)+1;//得到转换成int类型+1后的数字

				String srvlenth = lenth +"";
				String srvsetclassid = setclassid +"";
				format = "%02d";
				if (cutloc.length()<srvlenth.length()) {//判断转换后的长度是否小于原始长度
					//int addcount = srvsetclassid.length()+2;
					format = "%03d";
					classid = String.format(format, srvsetclassid);
				}
			}else{
				classid = classification.getClassid()+"01";
				//	newobj.setClassid(deloc+classid);
			}
			//newobj.setClassid(deloc+classid);
			newobj.setClasstype(classification.getClasstype());*/
		Classification classification = (Classification) ti.getValue();
		if(classification==null)//判断是否取到的是虚拟的根节点
		{
			String classlabel = ti.getLabel();
			if(classlabel.equals("物资分类"))
			{
				classtype = "物资";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '物资') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '物资') and  t.ancestor is null");
				}


			}else if(classlabel.equals("设备分类"))
			{
				classtype = "资产";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '资产') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '资产') and  t.ancestor  is null");
				}

			}else
			{
				classtype = "工具";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '工具') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '工具') and  t.ancestor  is null");
				}



			}
			if(count == 0)
			{
				newclassid = "01";
			}
			else
			{
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(t.classid)) as largeid from classification  t where t.classtype = '"+classtype+"' and t.parent is null or DATALENGTH(t.parent) = 0");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
//							BigDecimal maxvalue = (BigDecimal) map
//							.get("LARGEID");
							maxbh = (Double)map.get("LARGEID") + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				else
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(t.classid)) as largeid from classification  t where t.classtype = '"+classtype+"'and t.parent is null");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
							BigDecimal maxvalue = (BigDecimal) map
							.get("LARGEID");
							maxbh = maxvalue.doubleValue() + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				String s = String.valueOf(largeid);
				int len1 = s.length();
				if(len1<2)
				{
					for(int j=0;j<2-len1;j++)
					{
						newclassid = newclassid + "0";
					}
					newclassid = newclassid + s;
				}


			}

		}
		else
		{
			String classid = classification.getClassid();
			parent = classid;
			int len = classid.length();
			classtype = classification.getClasstype();
			count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.ancestor = '"+classid+"' and t.classid <>'"+classid+"' and t.classtype='"+classtype+"'");
			if(count>0)
			{
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(substring(t.classid,"+len+"+1,len(t.classid)))) as largeid from classification  t where t.parent = '"
							+ classid+"' and t.classtype='"+classtype+"'");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
//							BigDecimal maxvalue = (BigDecimal) map
//							.get("LARGEID");
							maxbh = (Double)map.get("LARGEID") + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				else
				{
					String as = 
						"select max(abs(substr(t.classid,"+len+"+1,length(t.classid)))) as largeid from classification  t where t.parent = '"
						+ classid+"' and t.classtype='"+classtype+"'";
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(substr(t.classid,"+len+"+1,length(t.classid)))) as largeid from classification  t where t.parent = '"
							+ classid+"' and t.classtype='"+classtype+"'");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
							BigDecimal maxvalue = (BigDecimal) map
							.get("LARGEID");
							maxbh = maxvalue.doubleValue() + 1;
							largeid = maxbh.longValue();
						}
					}
				}

				String s = String.valueOf(largeid);
				newclassid = classid;
				int len1 = s.length();
				if(len1<2)
				{
					for(int j=0;j<2-len1;j++)
					{
						newclassid = newclassid + "0";
					}
				}
				newclassid = newclassid + s;
			}
			else
			{
				newclassid = classid +"01";
			}

		}



		newobj.setClassid(newclassid);
		newobj.setParent(parent);
		newobj.setClasstype(classtype);
		newobj.setOrderby(0L);





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
		String classid = null;
		String getva = ti.getLabel();
		if (getva.equals("物资分类") || getva.equals("设备分类") || getva.equals("工具分类")) {
			Messagebox.show("顶级分类不能删除，请选择分类下节点操作！");
			return;
		}else{
			classid = classification.getClassid();
		}
		// 是否删除的是最底层
		ClassificationSrv classificationSrv = (ClassificationSrv)this.getMainSrv();
		if (classificationSrv.hasChildren(classification))
		{
			Messagebox.show("有子集分类，请选择最底层分类进行删除！");
			return;
		}

		//判断分类
		String sql = null;
		String type = classification.getClasstype();
		if ("物资".equals(type)) {//物资分类节点下的操作
			sql = "select count(i.id) from Item i where i.classid ='"+classid+"'";
		}else if("资产".equals(type)) {//设备分类节点的操作
			sql = "select count(e.id) from Equipment e where e.classid ='"+classid+"'";
		}else{//工具分类节点下的操作
			sql = "select count(t.id) from Tool t where t.classid ='"+classid+"'";
		}
		
		System.out.println("sql = "+sql);
		int count = this.getMainSrv().getBaseDao().selectCountByHql(sql);
		if (count >0) {
			Messagebox.show("该分类已关联数据，不能删除该分类！");
			return;
		}
		
		//调用业务对象的删除方法
		super.delete();
		// 重新构建树
		mainTree.afterDeleteItem();
	}
	
	
	/**
	 * 
	 * 功能：同步位置和设备关联的技术参数
	 * 作者：李建红 日期：2009-9-9
	 */
	/**
	 * @throws Exception
	 */
	public void synchspec () throws Exception{
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行同步操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("进行位置信息修改操作前，请先保存数据！");
			return;
		}
		Treeitem ti = mainTree.getSelectedItem();
		if(ti==null)
		{
			throw new Exception("请选择一个分类节点！");
		}
		Classification classification = (Classification) ti.getValue();//得到界面上的对象
		//判断该分类下是否有物资，是否添加了技术参数
		int classapce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classspec c where c.classid='"+classification.getClassid()+"'");
		if (classapce <= 0) {
			Messagebox.show("该分类下没有任何技术参数，不需要同步，请核实！");
			return;
		}
		if (classification.getClasstype().equals("物资")) {
			int itempce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Item e where e.classid='"+classification.getClassid()+"'");
			if (itempce <= 0) {
				Messagebox.show("该分类下没有任何物资，不需要同步，请核实！");
				return;
			}
		}
		if (classification.getClasstype().equals("工具")) {
			int toolpce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Tool t where t.classid='"+classification.getClassid()+"'");
			if (toolpce <= 0) {
				Messagebox.show("该分类下没有任何工具，不需要同步，请核实！");
				return;
			}
		}
		if (classification.getClasstype().equals("资产")) {
			int itempce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment e where e.classid='"+classification.getClassid()+"'");
			if (itempce <= 0) {
				Messagebox.show("该分类下没有任何备件，不需要同步，请核实！");
				return;
			}
		}
		
		
		int rtn = Messagebox.show("是否确认同步该分类下物资的技术参数吗？", "同步确认！",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			((ClassificationSrv) (this.getMainSrv())).synchspec(classification);//处理同步数据
			Messagebox.show("技术参数同步完成!");
		}
		
	}
	
	//维护计量单位
	public void technicunit() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		this.popupDialog(this.getMainObject(), "/location/technicunit.xul", "");

	}
}
