package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsfldsigline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String fieldid;
     private java.lang.String readonly;
     private java.lang.String required;
     private java.lang.String fldsig;
     
    // Constructors
    /** default constructor */
    public Ibsfldsigline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getFieldid() {
	   return this.fieldid;
	}	    
	public void setFieldid(java.lang.String fieldid) {
	   this.fieldid = fieldid;
	}
	public java.lang.String getReadonly() {
	   return this.readonly;
	}	    
	public void setReadonly(java.lang.String readonly) {
	   this.readonly = readonly;
	}
	public java.lang.String getRequired() {
	   return this.required;
	}	    
	public void setRequired(java.lang.String required) {
	   this.required = required;
	}
	public java.lang.String getFldsig() {
	   return this.fldsig;
	}	    
	public void setFldsig(java.lang.String fldsig) {
	   this.fldsig = fldsig;
	}
}