package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibssession extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.util.Date lastlogouttime;
     private java.lang.String loginid;
     private java.util.Date logintime;
     private java.lang.String password;
     private java.lang.String sessionid;
     private java.lang.String status;
     private java.lang.String userid;
     
    // Constructors
    /** default constructor */
    public Ibssession(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.util.Date getLastlogouttime() {
	   return this.lastlogouttime;
	}	    
	public void setLastlogouttime(java.util.Date lastlogouttime) {
	   this.lastlogouttime = lastlogouttime;
	}
	public java.lang.String getLoginid() {
	   return this.loginid;
	}	    
	public void setLoginid(java.lang.String loginid) {
	   this.loginid = loginid;
	}
	public java.util.Date getLogintime() {
	   return this.logintime;
	}	    
	public void setLogintime(java.util.Date logintime) {
	   this.logintime = logintime;
	}
	public java.lang.String getPassword() {
	   return this.password;
	}	    
	public void setPassword(java.lang.String password) {
	   this.password = password;
	}
	public java.lang.String getSessionid() {
	   return this.sessionid;
	}	    
	public void setSessionid(java.lang.String sessionid) {
	   this.sessionid = sessionid;
	}
	public java.lang.String getStatus() {
	   return this.status;
	}	    
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	public java.lang.String getUserid() {
	   return this.userid;
	}	    
	public void setUserid(java.lang.String userid) {
	   this.userid = userid;
	}
}