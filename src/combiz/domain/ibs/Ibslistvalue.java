package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibslistvalue extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String defaults;
     private java.lang.String listname;
     private java.lang.Long orderby;
     private java.lang.String valdesc;
     private java.lang.String value;
     
    // Constructors
    /** default constructor */
    public Ibslistvalue(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getDefaults() {
	   return this.defaults;
	}	    
	public void setDefaults(java.lang.String defaults) {
	   this.defaults = defaults;
	}
	public java.lang.String getListname() {
	   return this.listname;
	}	    
	public void setListname(java.lang.String listname) {
	   this.listname = listname;
	}
	public java.lang.Long getOrderby() {
	   return this.orderby;
	}	    
	public void setOrderby(java.lang.Long orderby) {
	   this.orderby = orderby;
	}
	public java.lang.String getValdesc() {
	   return this.valdesc;
	}	    
	public void setValdesc(java.lang.String valdesc) {
	   this.valdesc = valdesc;
	}
	public java.lang.String getValue() {
	   return this.value;
	}	    
	public void setValue(java.lang.String value) {
	   this.value = value;
	}
}