package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsquery extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String app;
     private java.lang.String description;
     private java.lang.String isdefault;
     private java.lang.String ispublic;
     private java.lang.String queryname;
     private java.lang.String querystring;
     private java.lang.String userid;
     
    // Constructors
    /** default constructor */
    public Ibsquery(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getApp() {
	   return this.app;
	}	    
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getIsdefault() {
	   return this.isdefault;
	}	    
	public void setIsdefault(java.lang.String isdefault) {
	   this.isdefault = isdefault;
	}
	public java.lang.String getIspublic() {
	   return this.ispublic;
	}	    
	public void setIspublic(java.lang.String ispublic) {
	   this.ispublic = ispublic;
	}
	public java.lang.String getQueryname() {
	   return this.queryname;
	}	    
	public void setQueryname(java.lang.String queryname) {
	   this.queryname = queryname;
	}
	public java.lang.String getQuerystring() {
	   return this.querystring;
	}	    
	public void setQuerystring(java.lang.String querystring) {
	   this.querystring = querystring;
	}
	public java.lang.String getUserid() {
	   return this.userid;
	}	    
	public void setUserid(java.lang.String userid) {
	   this.userid = userid;
	}
}