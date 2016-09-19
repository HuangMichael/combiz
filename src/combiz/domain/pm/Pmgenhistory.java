package combiz.domain.pm;

import combiz.system.IBOBaseObject;

public class Pmgenhistory extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String wonum;
     private java.lang.String jpnum;
     private java.util.Date nextstartdate;
     private java.util.Date performdate;
     private java.lang.String pmnum;
     private java.util.Date startdate;
     
    // Constructors
    /** default constructor */
    public Pmgenhistory(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}	    
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
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
	public java.util.Date getNextstartdate() {
	  		return this.nextstartdate;
	}	    
	public void setNextstartdate(java.util.Date nextstartdate) {
	   this.nextstartdate = nextstartdate;
	}
	public java.util.Date getPerformdate() {
	  		return this.performdate;
	}	    
	public void setPerformdate(java.util.Date performdate) {
	   this.performdate = performdate;
	}
	public java.lang.String getPmnum() {
		if(this.pmnum==null || this.pmnum.length()<=0)
	  		return null;
	  	else
	  		return this.pmnum;
	}	    
	public void setPmnum(java.lang.String pmnum) {
	   this.pmnum = pmnum;
	}
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}	    
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
	}
}