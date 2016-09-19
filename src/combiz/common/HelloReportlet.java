package combiz.common;

import com.fr.report.CellElement;
import com.fr.report.DefaultCellElement;
import com.fr.report.ReportTemplate;   
import com.fr.report.WorkSheet;
import com.fr.web.Reportlet;   
import com.fr.web.ReportletException;   
import com.fr.web.ReportletRequest;   
  
public class HelloReportlet implements Reportlet {

	public ReportTemplate createReport(ReportletRequest arg0)
	throws ReportletException 
	{
		
		WorkSheet worksheet = new WorkSheet();   
		CellElement cellElem = new DefaultCellElement(0, 0, "≤‚ ‘≥Ã–Ú±®±Ì!");   
		worksheet.addCellElement(cellElem);  

	    return worksheet;
	}
  
}  
