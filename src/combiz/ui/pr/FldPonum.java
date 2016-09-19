package combiz.ui.pr;


import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.system.FieldAdapter;
import combiz.system.ui.RecWindow;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Textbox;

public class FldPonum extends FieldAdapter
{	
	
	/**
	 * 初始化字段数据的时候调用该方法，该方法是在字段数据值被设置后（绑定后）调用的。
	 * 如果
	 */
	public void init(Component component) 
	{
		
	}

	/**
	 * 
	 * @TODO 字段上鼠标移开后调用该方法。本方法的作用是将供应商的联系人赋值给另一个文本框
	 * @param component
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午03:04:39
	 */
	public void validate(Component component) 
	throws Exception 
	{
		//得到主窗体类
		Prline pl = (Prline)this.mainObject;
		//得到控件
		Textbox textbox = (Textbox)component;
		
		List polinelist = this.mainSrv.getBaseDao().findWithQuery(Poline.class, "ponum='"+textbox.getValue()+"'");
		
		if(polinelist.size()>0){
			Poline pol = (Poline)polinelist.get(0);
			pl.setPolinenum(Long.parseLong(pol.getPonum()));
		}
		//值绑定，如果不绑定，则之前的赋值将白费工夫
		RecWindow  recWnd = (RecWindow)component.getFellow("mainWnd");
		recWnd.bandData();
	}

	/**
	 * 
	 */
	public void action(Component component)
	{
		
	}

}
