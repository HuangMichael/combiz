package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsapps extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String app;
     private java.lang.String apptype;
     private java.lang.String defaultquery;
     private java.lang.String description;
     private java.lang.String href;
     private java.lang.String image;
     private java.lang.String maintbname;
     private java.lang.String orderby;
     private java.lang.String originapp;
     private java.lang.String page;
     
    // Constructors
    /** default constructor */
    public Ibsapps(){}
    
   
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
	public java.lang.String getApptype() {
	   return this.apptype;
	}	    
	public void setApptype(java.lang.String apptype) {
	   this.apptype = apptype;
	}
	public java.lang.String getDefaultquery() {
	   return this.defaultquery;
	}	    
	public void setDefaultquery(java.lang.String defaultquery) {
	   this.defaultquery = defaultquery;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getHref() {
	   return this.href;
	}	    
	public void setHref(java.lang.String href) {
	   this.href = href;
	}
	public java.lang.String getImage() {
	   return this.image;
	}	    
	public void setImage(java.lang.String image) {
	   this.image = image;
	}
	public java.lang.String getMaintbname() {
	   return this.maintbname;
	}	    
	public void setMaintbname(java.lang.String maintbname) {
	   this.maintbname = maintbname;
	}
	public java.lang.String getOrderby() {
	   return this.orderby;
	}	    
	public void setOrderby(java.lang.String orderby) {
	   this.orderby = orderby;
	}
	public java.lang.String getOriginapp() {
	   return this.originapp;
	}	    
	public void setOriginapp(java.lang.String originapp) {
	   this.originapp = originapp;
	}
	public java.lang.String getPage() {
	   return this.page;
	}	    
	public void setPage(java.lang.String page) {
	   this.page = page;
	}
}