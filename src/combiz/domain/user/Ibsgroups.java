package combiz.domain.user;

import combiz.system.IBOBaseObject;

public class Ibsgroups extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String allsite;
     private java.lang.String bicenter;
     private java.lang.String description;
     private java.lang.String grpname;
     
    // Constructors
    /** default constructor */
    public Ibsgroups(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getAllsite() {
	   return this.allsite;
	}	    
	public void setAllsite(java.lang.String allsite) {
	   this.allsite = allsite;
	}
	public java.lang.String getBicenter() {
	   return this.bicenter;
	}	    
	public void setBicenter(java.lang.String bicenter) {
	   this.bicenter = bicenter;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getGrpname() {
	   return this.grpname;
	}	    
	public void setGrpname(java.lang.String grpname) {
	   this.grpname = grpname;
	}
}