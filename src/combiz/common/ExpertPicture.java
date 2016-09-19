package combiz.common;

import combiz.domain.doclib.Document;
import combiz.system.IBSServer;

import java.awt.Image;
import java.util.List;

import com.fr.base.BaseUtils;
import com.fr.report.script.core.FunctionHelper;
import com.fr.report.script.core.parser.ConditionalOrExpression;
import com.fr.report.script.core.parser.UtilEvalError;

public class ExpertPicture extends com.fr.report.script.AbstractFunction
{

	public String getCN() {
		return null;
	}

	public String getEN() {
		return null;
	}

	public Object[] processArguments(ConditionalOrExpression[] arg0) 
	throws UtilEvalError
	{
		if(arg0.length>0)
			return arg0;
		return null;
	}

	public Object run(Object[] arg0) 
	{
		String rootPath = "";
		try {
			rootPath = IBSServer.getIBSServer().getDoclibroot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//获取参数的值
		ConditionalOrExpression coe = (ConditionalOrExpression)arg0[0];
		String id = "0";
		try {
			id = (String) coe.eval(this.getCalculator());
		} catch (UtilEvalError e) {
			e.printStackTrace();
		}
		
		//读取图片
		try {
			List docList = IBSServer.getIBSServer().getBaseDao().findWithQuery(Document.class, "ownertable='EXPLABOR' and ownerid=" + id);
			if(docList.size()>0)
			{
				Document doc = (Document) docList.get(0);
				String imagePath = rootPath + doc.getUrlpath() + doc.getAttachname();  //"/文档库/附件/EXPLABOR/DOC100078/testimage.gif";
				Image image = BaseUtils.readImage(imagePath);
				return image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
