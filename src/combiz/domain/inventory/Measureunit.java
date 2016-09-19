package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Measureunit extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String description;
     private java.lang.String measureunitid;
     private java.lang.String unittype;
     
    // Constructors
    /** default constructor */
    public Measureunit(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getMeasureunitid() {
	   return this.measureunitid;
	}	    
	public void setMeasureunitid(java.lang.String measureunitid) {
	   this.measureunitid = measureunitid;
	}
	public java.lang.String getUnittype() {
	   return this.unittype;
	}	    
	public void setUnittype(java.lang.String unittype) {
	   this.unittype = unittype;
	}
}