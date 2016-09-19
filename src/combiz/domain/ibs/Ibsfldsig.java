package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsfldsig extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String app;
     private java.lang.String condition;
     private java.lang.String description;
     private java.lang.String fldsig;
     private java.lang.String sigtype;
     
    // Constructors
    /** default constructor */
    public Ibsfldsig(){}
    
   
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
	public java.lang.String getCondition() {
	   return this.condition;
	}	    
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getFldsig() {
	   return this.fldsig;
	}	    
	public void setFldsig(java.lang.String fldsig) {
	   this.fldsig = fldsig;
	}
	public java.lang.String getSigtype() {
	   return this.sigtype;
	}	    
	public void setSigtype(java.lang.String sigtype) {
	   this.sigtype = sigtype;
	}
}