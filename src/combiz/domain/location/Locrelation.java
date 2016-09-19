package combiz.domain.location;

import combiz.system.IBOBaseObject;

public class Locrelation extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String eqnum;
     private java.lang.String equipment;
     private java.lang.String location;
     private java.lang.String rellocation;
     private java.lang.String reltype;
     private java.lang.String systemid;
     
    // Constructors
    /** default constructor */
    public Locrelation(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getEqnum() {
	   return this.eqnum;
	}	    
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	public java.lang.String getEquipment() {
	   return this.equipment;
	}	    
	public void setEquipment(java.lang.String equipment) {
	   this.equipment = equipment;
	}
	public java.lang.String getLocation() {
	   return this.location;
	}	    
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	public java.lang.String getRellocation() {
	   return this.rellocation;
	}	    
	public void setRellocation(java.lang.String rellocation) {
	   this.rellocation = rellocation;
	}
	public java.lang.String getReltype() {
	   return this.reltype;
	}	    
	public void setReltype(java.lang.String reltype) {
	   this.reltype = reltype;
	}
	public java.lang.String getSystemid() {
	   return this.systemid;
	}	    
	public void setSystemid(java.lang.String systemid) {
	   this.systemid = systemid;
	}
}