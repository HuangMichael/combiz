package combiz.ui.rfq;

import combiz.system.ui.adapter.PreClassAdapter;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Image;
import com.inbasis.zul.Window;

public class PreIsAwarded implements PreClassAdapter
{
	public Component execute(Window arg0, Object arg2, String arg3, String arg4)
	throws Exception 
	{
		Image image = new Image("/images/btn_subselecton.gif");
		Image image2 = new Image("/images/img_error_min.gif");
		if(!Util.isNull(arg4) && arg4.equals("ÊÇ"))
		{
			return image;
		}
		return image2;
	}

}
