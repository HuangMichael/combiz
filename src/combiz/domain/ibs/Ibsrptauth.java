package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsrptauth extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String app;
     private java.lang.String grpname;
     private java.lang.String rptname;
     
    // Constructors
    /** default constructor */
    public Ibsrptauth(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getApp() {
	   return this.app;
	}	    
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	public java.lang.String getGrpname() {
	   return this.grpname;
	}	    
	public void setGrpname(java.lang.String grpname) {
	   this.grpname = grpname;
	}
	public java.lang.String getRptname() {
	   return this.rptname;
	}	    
	public void setRptname(java.lang.String rptname) {
	   this.rptname = rptname;
	}
}