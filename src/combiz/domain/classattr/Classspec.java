package combiz.domain.classattr;

import combiz.system.IBOBaseObject;

public class Classspec extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String classid;
     private java.lang.String classattr;
     private java.lang.String unitid;
     private java.lang.Double defaultnumvalue;
     private java.lang.String defaultstrvalue;
     private java.lang.String ismustbe;
     private java.lang.String remark;
     
    // Constructors
    /** default constructor */
    public Classspec(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getClassid() {
	   return this.classid;
	}	    
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	public java.lang.String getClassattr() {
	   return this.classattr;
	}	    
	public void setClassattr(java.lang.String classattr) {
	   this.classattr = classattr;
	}
	public java.lang.String getUnitid() {
	   return this.unitid;
	}	    
	public void setUnitid(java.lang.String unitid) {
	   this.unitid = unitid;
	}
	public java.lang.Double getDefaultnumvalue() {
	   return this.defaultnumvalue;
	}	    
	public void setDefaultnumvalue(java.lang.Double defaultnumvalue) {
	   this.defaultnumvalue = defaultnumvalue;
	}
	public java.lang.String getDefaultstrvalue() {
	   return this.defaultstrvalue;
	}	    
	public void setDefaultstrvalue(java.lang.String defaultstrvalue) {
	   this.defaultstrvalue = defaultstrvalue;
	}
	public java.lang.String getIsmustbe() {
	   return this.ismustbe;
	}	    
	public void setIsmustbe(java.lang.String ismustbe) {
	   this.ismustbe = ismustbe;
	}
	public java.lang.String getRemark() {
	   return this.remark;
	}	    
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
}