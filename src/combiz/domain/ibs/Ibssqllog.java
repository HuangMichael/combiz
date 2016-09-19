package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibssqllog extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String labornum;
     private java.lang.String opcontent;
     private java.util.Date opdate;
     private java.lang.String opsql;
     private java.lang.String ownerid;
     private java.lang.String ownertable;
     private java.lang.String userip;
     
    // Constructors
    /** default constructor */
    public Ibssqllog(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}	    
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	public java.lang.String getOpcontent() {
		if(this.opcontent==null || this.opcontent.length()<=0)
	  		return null;
	  	else
	  		return this.opcontent;
	}	    
	public void setOpcontent(java.lang.String opcontent) {
	   this.opcontent = opcontent;
	}
	public java.util.Date getOpdate() {
	  		return this.opdate;
	}	    
	public void setOpdate(java.util.Date opdate) {
	   this.opdate = opdate;
	}
	public java.lang.String getOpsql() {
		if(this.opsql==null || this.opsql.length()<=0)
	  		return null;
	  	else
	  		return this.opsql;
	}	    
	public void setOpsql(java.lang.String opsql) {
	   this.opsql = opsql;
	}
	public java.lang.String getOwnerid() {
		if(this.ownerid==null || this.ownerid.length()<=0)
	  		return null;
	  	else
	  		return this.ownerid;
	}	    
	public void setOwnerid(java.lang.String ownerid) {
	   this.ownerid = ownerid;
	}
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}	    
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	public java.lang.String getUserip() {
		if(this.userip==null || this.userip.length()<=0)
	  		return null;
	  	else
	  		return this.userip;
	}	    
	public void setUserip(java.lang.String userip) {
	   this.userip = userip;
	}
}