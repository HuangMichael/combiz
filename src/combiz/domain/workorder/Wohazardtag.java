package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wohazardtag extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String hazardid;
     private java.lang.String isotagid;
     
    // Constructors
    /** default constructor */
    public Wohazardtag(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getHazardid() {
	   return this.hazardid;
	}	    
	public void setHazardid(java.lang.String hazardid) {
	   this.hazardid = hazardid;
	}
	public java.lang.String getIsotagid() {
	   return this.isotagid;
	}	    
	public void setIsotagid(java.lang.String isotagid) {
	   this.isotagid = isotagid;
	}
}