package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsinskey extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String inskey;
     private java.lang.String prefix;
     private java.lang.Long seed;
     private java.lang.Long curvalue;
     
    // Constructors
    /** default constructor */
    public Ibsinskey(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getInskey() {
	   return this.inskey;
	}	    
	public void setInskey(java.lang.String inskey) {
	   this.inskey = inskey;
	}
	public java.lang.String getPrefix() {
	   return this.prefix;
	}	    
	public void setPrefix(java.lang.String prefix) {
	   this.prefix = prefix;
	}
	public java.lang.Long getSeed() {
	   return this.seed;
	}	    
	public void setSeed(java.lang.Long seed) {
	   this.seed = seed;
	}
	public java.lang.Long getCurvalue() {
	   return this.curvalue;
	}	    
	public void setCurvalue(java.lang.Long curvalue) {
	   this.curvalue = curvalue;
	}
}