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
		newobj.setHaschild("��");
		newobj.setAdjnextdue("��");
		newobj.setAutowf("��");
		newobj.setUsefrequency("��");
		newobj.setFrequnit("��");
		newobj.setEqdown("��");
		newobj.setUsejpseq("��");
		newobj.setAutowf("��");
		newobj.setWostatus("����δ����");

		this.mainObject = newobj;
		return true;
	}

 	
	 
}
