package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibscenter extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String bicenter;
     private java.lang.String description;
     private java.lang.String page;
     
    // Constructors
    /** default constructor */
    public Ibscenter(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
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
	public java.lang.String getPage() {
	   return this.page;
	}	    
	public void setPage(java.lang.String page) {
	   this.page = page;
	}
}