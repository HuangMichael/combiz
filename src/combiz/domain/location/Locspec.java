package combiz.domain.location;

import combiz.system.IBOBaseObject;

public class Locspec extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String location;
     private java.lang.String classid;
     private java.lang.String classattr;
     private java.lang.Double numvalue;
     private java.lang.String strvalue;
     private java.lang.String unitid;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.String ismustbe;
     private java.lang.String remark;
     
    // Constructors
    /** default constructor */
    public Locspec(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getLocation() {
	   return this.location;
	}	    
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
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
	public java.lang.Double getNumvalue() {
	   return this.numvalue;
	}	    
	public void setNumvalue(java.lang.Double numvalue) {
	   this.numvalue = numvalue;
	}
	public java.lang.String getStrvalue() {
	   return this.strvalue;
	}	    
	public void setStrvalue(java.lang.String strvalue) {
	   this.strvalue = strvalue;
	}
	public java.lang.String getUnitid() {
	   return this.unitid;
	}	    
	public void setUnitid(java.lang.String unitid) {
	   this.unitid = unitid;
	}
	public java.util.Date getChangedate() {
	   return this.changedate;
	}	    
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	public java.lang.String getChangeby() {
	   return this.changeby;
	}	    
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
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