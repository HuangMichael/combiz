package combiz.domain.tool;

import combiz.system.IBOBaseObject;

public class Toolreserve extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.Double linecost;
     private java.util.Date reqdate;
     private java.lang.String reqlabor;
     private java.lang.String reqnum;
     private java.lang.Long reservedqty;
     private java.lang.Long sendcount;
     private java.lang.Double toolhrs;
     private java.lang.String toolnum;
     private java.lang.String userdept;
     private java.lang.String warehouse;
     
    // Constructors
    /** default constructor */
    public Toolreserve(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}	    
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	public java.util.Date getReqdate() {
	  		return this.reqdate;
	}	    
	public void setReqdate(java.util.Date reqdate) {
	   this.reqdate = reqdate;
	}
	public java.lang.String getReqlabor() {
		if(this.reqlabor==null || this.reqlabor.length()<=0)
	  		return null;
	  	else
	  		return this.reqlabor;
	}	    
	public void setReqlabor(java.lang.String reqlabor) {
	   this.reqlabor = reqlabor;
	}
	public java.lang.String getReqnum() {
		if(this.reqnum==null || this.reqnum.length()<=0)
	  		return null;
	  	else
	  		return this.reqnum;
	}	    
	public void setReqnum(java.lang.String reqnum) {
	   this.reqnum = reqnum;
	}
	public java.lang.Long getReservedqty() {
	  		return this.reservedqty;
	}	    
	public void setReservedqty(java.lang.Long reservedqty) {
	   this.reservedqty = reservedqty;
	}
	public java.lang.Long getSendcount() {
	  		return this.sendcount;
	}	    
	public void setSendcount(java.lang.Long sendcount) {
	   this.sendcount = sendcount;
	}
	public java.lang.Double getToolhrs() {
	  		return this.toolhrs;
	}	    
	public void setToolhrs(java.lang.Double toolhrs) {
	   this.toolhrs = toolhrs;
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
	public java.lang.String getUserdept() {
		if(this.userdept==null || this.userdept.length()<=0)
	  		return null;
	  	else
	  		return this.userdept;
	}	    
	public void setUserdept(java.lang.String userdept) {
	   this.userdept = userdept;
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