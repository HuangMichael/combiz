package combiz.ui.tool;

import combiz.business.tool.TooltransSrv;
import combiz.domain.tool.Tooltrans;
import combiz.system.FieldAdapter;
import combiz.system.IBOSrvUtil;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Longbox;
import com.inbasis.zul.Messagebox;

/**
 *  @author ljh 应用程序
 */

public class FldSendcnt extends FieldAdapter {

	@Override
	public void action(Component com) throws Exception {
		// TODO 自动生成方法存根
		Tooltrans tooltrans = (Tooltrans) this.mainObject;
		Long id = tooltrans.getId();
		Longbox ilbox = null;
		ilbox = (Longbox) com;
		
		/******************查询已经归还的历史记录************************************/
		TooltransSrv worksrv = (TooltransSrv)IBOSrvUtil.getSrv("tooltrans");
		Long getcont =(Long) worksrv.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where sendnum = '"+tooltrans.getId()+"'");
		
		if (getcont == null)
		{
			getcont = 0L;
		}
		
		if (ilbox.getValue()!=null) {
			Long count = ilbox.getValue();
			Long coun = tooltrans.getToolqty();
			if((coun-getcont)==0) 
			{
				Messagebox.show("该工具已经归还完！");
				this.setValueByColname("sendcount", 0L);
				return;
			}else if (count <= 0)
			{
				Messagebox.show("工具数量必须为大于0的整数！");
				if((coun-getcont)>0) 
				{
					this.setValueByColname("sendcount", (coun-getcont));
				}else{
					this.setValueByColname("sendcount", 0L);
				}
			}else if ((count+getcont) > tooltrans.getToolqty())
			{
				Messagebox.show("归还的工具数量大于借用的工具数量！");
				this.setValueByColname("sendcount", 0L);
				return;
				
			}
		}
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}

	@Override
	public void validate(Component arg0) throws Exception {
		// TODO 自动生成方法存根
		
	}
	
}
