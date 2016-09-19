package combiz.ui.pm;

import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Item;
import combiz.domain.pm.Premaint;
import combiz.system.ui.ListWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.ITextbox;
import combiz.ui.inventory.ItemClassWindow;

public class PmTreeList extends ListWindow {
	public PmTreeList() {
		super();
	}

	public boolean addNew() throws Exception {
		PmClassWindow parentWnd = (PmClassWindow) ownerWnd;
		Classification parent = (Classification) parentWnd.getMainObject();
		
//		Premaint premaint = (Premaint) this.getMainObject();
		

		Premaint newobj = new Premaint();
		newobj.setClassid(parent.getClassid());
		newobj.setWorktype(parent.getClassid());
		newobj.setPmcounter(0L);
		newobj.setHaschild("否");
		newobj.setAdjnextdue("否");
		newobj.setAutowf("否");
		newobj.setUsefrequency("是");
		newobj.setFrequnit("天");
		newobj.setEqdown("否");
		newobj.setUsejpseq("否");
		newobj.setAutowf("是");
		newobj.setWostatus("流程未启动");

		this.mainObject = newobj;
		return true;
	}

 	
	 
}
