package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsfldsiglist extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String fldsig;
     private java.lang.String readonly;
     private java.lang.String relname;
     
    // Constructors
    /** default constructor */
    public Ibsfldsiglist(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getFldsig() {
		if(this.fldsig==null || this.fldsig.length()<=0)
	  		return null;
	  	else
	  		return this.fldsig;
	}	    
	public void setFldsig(java.lang.String fldsig) {
	   this.fldsig = fldsig;
	}
	public java.lang.String getReadonly() {
		if(this.readonly==null || this.readonly.length()<=0)
	  		return null;
	  	else
	  		return this.readonly;
	}	    
	public void setReadonly(java.lang.String readonly) {
	   this.readonly = readonly;
	}
	public java.lang.String getRelname() {
		if(this.relname==null || this.relname.length()<=0)
	  		return null;
	  	else
	  		return this.relname;
	}	    
	public void setRelname(java.lang.String relname) {
	   this.relname = relname;
	}
}