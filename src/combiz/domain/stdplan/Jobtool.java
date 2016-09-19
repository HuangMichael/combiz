package combiz.domain.stdplan;

import combiz.system.IBOBaseObject;

public class Jobtool extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String jpnum;
     private java.lang.Long qty;
     private java.lang.String tasknum;
     private java.lang.Double toolhrs;
     private java.lang.String toolnum;
     
    // Constructors
    /** default constructor */
    public Jobtool(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}	    
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	public java.lang.Long getQty() {
	  		return this.qty;
	}	    
	public void setQty(java.lang.Long qty) {
	   this.qty = qty;
	}
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}	    
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	public java.lang.Double getToolhrs() {
	  		return this.toolhrs;
	}	    
	public void setToolhrs(java.lang.Double toolhrs) {
	   this.toolhrs = toolhrs;
	}
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}	    
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
}