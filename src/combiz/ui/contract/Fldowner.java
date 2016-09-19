package combiz.ui.contract;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

import combiz.domain.company.Companies;
import combiz.domain.contract.Contract;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

public class Fldowner extends FieldAdapter {

	public void action(Component component) throws Exception {
    Contract cont=(Contract) this.mainObject;
    Textbox tx=(Textbox) component;
    List compcontact=this.mainSrv.getBaseDao().findWithQuery(Companies.class, "company='"+tx.getValue()+"'");
    if(compcontact.size()>=1){
    	Companies comp=(Companies) compcontact.get(0);
    	cont.setOwneronbf(comp.getDelegate());
    	cont.setOwnercontact(comp.getContact());
    	cont.setOwnerbank(comp.getBankname());
    	cont.setOwneraccount(comp.getBankaccount());
    	cont.setOwnerphone(comp.getPhone());
    	cont.setOwneremail(comp.getContactmail());
    	cont.setOwneraddress(comp.getAddress());
    }
    else{
    	
    }
    RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
	recWnd.bandData();
	}

	public void init(Component component) throws Exception {

	}

	public void validate(Component component) throws Exception {

	}

}
