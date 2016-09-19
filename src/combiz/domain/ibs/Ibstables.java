package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstables extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String authlevel;
     private java.lang.String changed;
     private java.lang.String classname;
     private java.lang.String description;
     private java.lang.String issystem;
     private java.lang.String logenable;
     private java.lang.String restoredata;
     private java.lang.String servicename;
     private java.lang.String tablename;
     private java.lang.String userdefined;
     
    // Constructors
    /** default constructor */
    public Ibstables(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getAuthlevel() {
	   return this.authlevel;
	}	    
	public void setAuthlevel(java.lang.String authlevel) {
	   this.authlevel = authlevel;
	}
	public java.lang.String getChanged() {
	   return this.changed;
	}	    
	public void setChanged(java.lang.String changed) {
	   this.changed = changed;
	}
	public java.lang.String getClassname() {
	   return this.classname;
	}	    
	public void setClassname(java.lang.String classname) {
	   this.classname = classname;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getIssystem() {
	   return this.issystem;
	}	    
	public void setIssystem(java.lang.String issystem) {
	   this.issystem = issystem;
	}
	public java.lang.String getLogenable() {
	   return this.logenable;
	}	    
	public void setLogenable(java.lang.String logenable) {
	   this.logenable = logenable;
	}
	public java.lang.String getRestoredata() {
	   return this.restoredata;
	}	    
	public void setRestoredata(java.lang.String restoredata) {
	   this.restoredata = restoredata;
	}
	public java.lang.String getServicename() {
	   return this.servicename;
	}	    
	public void setServicename(java.lang.String servicename) {
	   this.servicename = servicename;
	}
	public java.lang.String getTablename() {
	   return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	public java.lang.String getUserdefined() {
	   return this.userdefined;
	}	    
	public void setUserdefined(java.lang.String userdefined) {
	   this.userdefined = userdefined;
	}
}