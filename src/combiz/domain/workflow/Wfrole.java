package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfrole extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String department;
     private java.lang.String description;
     private java.lang.String labor;
     private java.lang.String laborgroup;
     private java.lang.String roletype;
     private java.lang.String tablename;
     private java.lang.String value;
     private java.lang.String wfrole;
     
    // Constructors
    /** default constructor */
    public Wfrole(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getDepartment() {
	  		return this.department;
	}	    
	public void setDepartment(java.lang.String department) {
	   this.department = department;
	}
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getLabor() {
	  		return this.labor;
	}	    
	public void setLabor(java.lang.String labor) {
	   this.labor = labor;
	}
	public java.lang.String getLaborgroup() {
	  		return this.laborgroup;
	}	    
	public void setLaborgroup(java.lang.String laborgroup) {
	   this.laborgroup = laborgroup;
	}
	public java.lang.String getRoletype() {
		if(this.roletype==null || this.roletype.length()<=0)
	  		return null;
	  	else
	  		return this.roletype;
	}	    
	public void setRoletype(java.lang.String roletype) {
	   this.roletype = roletype;
	}
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	public java.lang.String getValue() {
		if(this.value==null || this.value.length()<=0)
	  		return null;
	  	else
	  		return this.value;
	}	    
	public void setValue(java.lang.String value) {
	   this.value = value;
	}
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}	    
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
}