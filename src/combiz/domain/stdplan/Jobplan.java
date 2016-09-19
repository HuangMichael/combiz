package combiz.domain.stdplan;

import combiz.system.IBOBaseObject;

public class Jobplan extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String changby;
     private java.util.Date changdate;
     private java.lang.String crewid;
     private java.lang.String description;
     private java.lang.String eqdown;
     private java.lang.Double jpduration;
     private java.lang.String jpnum;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String supervisor;
     
    // Constructors
    /** default constructor */
    public Jobplan(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getChangby() {
		if(this.changby==null || this.changby.length()<=0)
	  		return null;
	  	else
	  		return this.changby;
	}	    
	public void setChangby(java.lang.String changby) {
	   this.changby = changby;
	}
	public java.util.Date getChangdate() {
	  		return this.changdate;
	}	    
	public void setChangdate(java.util.Date changdate) {
	   this.changdate = changdate;
	}
	public java.lang.String getCrewid() {
		if(this.crewid==null || this.crewid.length()<=0)
	  		return null;
	  	else
	  		return this.crewid;
	}	    
	public void setCrewid(java.lang.String crewid) {
	   this.crewid = crewid;
	}
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getEqdown() {
		if(this.eqdown==null || this.eqdown.length()<=0)
	  		return null;
	  	else
	  		return this.eqdown;
	}	    
	public void setEqdown(java.lang.String eqdown) {
	   this.eqdown = eqdown;
	}
	public java.lang.Double getJpduration() {
	  		return this.jpduration;
	}	    
	public void setJpduration(java.lang.Double jpduration) {
	   this.jpduration = jpduration;
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
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}	    
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}	    
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}	    
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
}