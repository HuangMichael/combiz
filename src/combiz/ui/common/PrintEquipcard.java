package combiz.ui.common;

import combiz.system.IBOBaseDao;
import combiz.system.IBOSrvUtil;

import java.util.Map;

import com.inbasis.zk.ui.Execution;
import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Iframe;
import com.inbasis.zul.Window;

public class PrintEquipcard extends Window
{
	Map param;
	public PrintEquipcard()
	{
		Execution exec = Executions.getCurrent();
		param = exec.getArg();

	}
	
	
	public void onCreate()
	{
		IBOBaseDao baseDao = IBOSrvUtil.getBaseDao();
		
		Iframe iframe = (Iframe) this.getFellow("printPage");
		/*Groupbox groupbox = (Groupbox) this.getFellow("printContent");
		Vbox vbox = new Vbox();
		groupbox.appendChild(vbox);
		Label label = new Label("�Ѵ�ӡ���룺");
		vbox.appendChild(label);
		label = new Label("----------------------------");
		vbox.appendChild(label);*/
		String content="<body>\n";
		content = content + "<EMBED     ";
		content = content + "src=http://joke.tom.com/images/real/Track08.rm   width=250   height=33    "; 
		content = content + "type=audio/x-pn-realaudio-plugin   tppabs=\"http://www.wwenglish.org\"  ";   
			content = content + " autostart=\"false\"   controls=\"ControlPanel\"></EMBED>";
		/*for(int i=0;i<5;i++)
		{
			content = content + "<br>["+i+"]<a href=\"www.combiz.com\">�Ѵ�ӡ���룺getName()�л�ȡ��½����ͼ���뵽cookie�е�</a>��request.getUserPrincipal(). ... �ڴ˾�֮ǰʹ��";
		}*/
		content = content + "</body>";
		content = java.net.URLEncoder.encode(content);
		iframe.setSrc("/ExportService?exptype=html&content=" + content); //
	}

}
