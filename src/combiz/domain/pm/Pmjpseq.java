package combiz.domain.pm;

import combiz.system.IBOBaseObject;

public class Pmjpseq extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String pmnum;
     private java.lang.Long sequence;
     private java.lang.String jpnum;
     
    // Constructors
    /** default constructor */
    public Pmjpseq(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getPmnum() {
		if(this.pmnum==null || this.pmnum.length()<=0)
	  		return null;
	  	else
	  		return this.pmnum;
	}	    
	public void setPmnum(java.lang.String pmnum) {
	   this.pmnum = pmnum;
	}
	public java.lang.Long getSequence() {
	  		return this.sequence;
	}	    
	public void setSequence(java.lang.Long sequence) {
	   this.sequence = sequence;
	}
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}	    
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
}