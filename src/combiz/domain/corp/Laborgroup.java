package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Laborgroup extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String groupname;
     private java.lang.String description;
     
    // Constructors
    /** default constructor */
    public Laborgroup(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getGroupname() {
	   return this.groupname;
	}	    
	public void setGroupname(java.lang.String groupname) {
	   this.groupname = groupname;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
}