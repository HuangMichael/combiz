package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfinstance extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String active;
     private java.lang.Long curnodeid;
     private java.lang.String description;
     private java.lang.Long ownerid;
     private java.lang.String ownertable;
     private java.lang.String startor;
     private java.util.Date starttime;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     
    /** default constructor */
    public Wfinstance(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ×Ö¶ÎACTIVE
    * @return java.lang.String
    */
	public java.lang.String getActive() {
		if(this.active==null || this.active.length()<=0)
	  		return null;
	  	else
	  		return this.active;
	}
	
	/**
    * ×Ö¶ÎACTIVE
    * @return java.lang.String
    */
	public void setActive(java.lang.String active) {
	   this.active = active;
	}
	
	
    /**
    * µ±Ç°½ÚµãID
    * @return java.lang.Long
    */
	public java.lang.Long getCurnodeid() {
	  		return this.curnodeid;
	}
	
	/**
    * µ±Ç°½ÚµãID
    * @return java.lang.Long
    */
	public void setCurnodeid(java.lang.Long curnodeid) {
	   this.curnodeid = curnodeid;
	}
	
	
    /**
    * Á÷³ÌÊµÀýÃèÊö
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * Á÷³ÌÊµÀýÃèÊö
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ×Ö¶ÎOWNERID
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * ×Ö¶ÎOWNERID
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * ×Ö¶ÎOWNERTABLE
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * ×Ö¶ÎOWNERTABLE
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * ×Ö¶ÎSTARTOR
    * @return java.lang.String
    */
	public java.lang.String getStartor() {
		if(this.startor==null || this.startor.length()<=0)
	  		return null;
	  	else
	  		return this.startor;
	}
	
	/**
    * ×Ö¶ÎSTARTOR
    * @return java.lang.String
    */
	public void setStartor(java.lang.String startor) {
	   this.startor = startor;
	}
	
	
    /**
    * ×Ö¶ÎSTARTTIME
    * @return java.util.Date
    */
	public java.util.Date getStarttime() {
	  		return this.starttime;
	}
	
	/**
    * ×Ö¶ÎSTARTTIME
    * @return java.util.Date
    */
	public void setStarttime(java.util.Date starttime) {
	   this.starttime = starttime;
	}
	
	
    /**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
}