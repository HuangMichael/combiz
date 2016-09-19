package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Woisotag extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String wonum;
     private java.lang.String isotagid;
     private java.lang.String description;
     private java.lang.String location;
     private java.lang.String eqnum;
     private java.lang.Long aplyseq;
     private java.lang.String state;
     private java.lang.String hazardid;
     
    // Constructors
    /** default constructor */
    public Woisotag(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getWonum() {
	   return this.wonum;
	}	    
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	public java.lang.String getIsotagid() {
	   return this.isotagid;
	}	    
	public void setIsotagid(java.lang.String isotagid) {
	   this.isotagid = isotagid;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getLocation() {
	   return this.location;
	}	    
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	public java.lang.String getEqnum() {
	   return this.eqnum;
	}	    
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	public java.lang.Long getAplyseq() {
	   return this.aplyseq;
	}	    
	public void setAplyseq(java.lang.Long aplyseq) {
	   this.aplyseq = aplyseq;
	}
	public java.lang.String getState() {
	   return this.state;
	}	    
	public void setState(java.lang.String state) {
	   this.state = state;
	}
	public java.lang.String getHazardid() {
	   return this.hazardid;
	}	    
	public void setHazardid(java.lang.String hazardid) {
	   this.hazardid = hazardid;
	}
}