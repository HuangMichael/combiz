package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Corpsite extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String sitenum;
     private java.lang.String description;
     private java.lang.String corpnum;
     private java.lang.String enterby;
     private java.util.Date enterdate;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String active;
     private java.lang.String contact;
     
    // Constructors
    /** default constructor */
    public Corpsite(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getSitenum() {
	   return this.sitenum;
	}	    
	public void setSitenum(java.lang.String sitenum) {
	   this.sitenum = sitenum;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getCorpnum() {
	   return this.corpnum;
	}	    
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	public java.lang.String getEnterby() {
	   return this.enterby;
	}	    
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	public java.util.Date getEnterdate() {
	   return this.enterdate;
	}	    
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	public java.lang.String getChangeby() {
	   return this.changeby;
	}	    
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	public java.util.Date getChangedate() {
	   return this.changedate;
	}	    
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	public java.lang.String getActive() {
	   return this.active;
	}	    
	public void setActive(java.lang.String active) {
	   this.active = active;
	}
	public java.lang.String getContact() {
	   return this.contact;
	}	    
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
}