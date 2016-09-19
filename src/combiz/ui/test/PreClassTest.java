package combiz.ui.test;

import combiz.system.ui.adapter.PreClassAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Image;
import com.inbasis.zul.Window;

public class PreClassTest implements PreClassAdapter {


	public Component execute(Window recWnd, Object mainObject, String fieldname, String fieldvalue) throws Exception {
		Image image = new Image("/images/list_cricle.gif");
		return image;
		
	/*	Progressmeter pg = new Progressmeter();
		pg.setValue(30);
		return pg;*/
		
		/*Label label = new Label("ÐéÄâÖµ");
		return label;*/
	}

}
