package combiz.ui.contract;

import java.util.Date;

import combiz.domain.contract.Contchange;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class ContchangeWindow extends MainWindow implements InfWindow {


	public ContchangeWindow() {
		super();
	}

	public boolean addNew() throws Exception {
		Contchange conc = new Contchange();
		conc.setStatus("Á÷³ÌÎ´Æô¶¯");
		conc.setStatusdate(new Date());
		mainObject = conc;
		return true;  
	}
}
