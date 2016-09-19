package combiz.domain.tool;

import combiz.system.IBOBaseObject;

public class Toolissue extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String reqlabor;
     private java.lang.Long sendcount;
     private java.lang.String toolnum;
     private java.lang.Long toolqty;
     
    // Constructors
    /** default constructor */
    public Toolissue(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getReqlabor() {
		if(this.reqlabor==null || this.reqlabor.length()<=0)
	  		return null;
	  	else
	  		return this.reqlabor;
	}	    
	public void setReqlabor(java.lang.String reqlabor) {
	   this.reqlabor = reqlabor;
	}
	public java.lang.Long getSendcount() {
	  		return this.sendcount;
	}	    
	public void setSendcount(java.lang.Long sendcount) {
	   this.sendcount = sendcount;
	}
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}	    
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
	public java.lang.Long getToolqty() {
	  		return this.toolqty;
	}	    
	public void setToolqty(java.lang.Long toolqty) {
	   this.toolqty = toolqty;
	}
}