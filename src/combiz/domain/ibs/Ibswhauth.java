package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibswhauth extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String grpname;
     private java.lang.String memo;
     private java.lang.String warehouse;
     
    // Constructors
    /** default constructor */
    public Ibswhauth(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getGrpname() {
		if(this.grpname==null || this.grpname.length()<=0)
	  		return null;
	  	else
	  		return this.grpname;
	}	    
	public void setGrpname(java.lang.String grpname) {
	   this.grpname = grpname;
	}
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}	    
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}	    
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
}