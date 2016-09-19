package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wptask extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date actfinish;
     private java.util.Date actstart;
     private java.lang.String description;
     private java.lang.String eqnum;
     private java.lang.String jpnum;
     private java.lang.String jtwz;
     private java.lang.String location;
     private java.util.Date meadate;
     private java.lang.Double meavalue;
     private java.lang.String part;
     private java.lang.String pointnum;
     private java.util.Date schedfinish;
     private java.util.Date schedstart;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.util.Date targcomp;
     private java.util.Date targstart;
     private java.lang.Double taskduration;
     private java.lang.String tasknum;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wptask(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ×Ö¶ÎACTFINISH
    * @return java.util.Date
    */
	public java.util.Date getActfinish() {
	  		return this.actfinish;
	}
	
	/**
    * ×Ö¶ÎACTFINISH
    * @return java.util.Date
    */
	public void setActfinish(java.util.Date actfinish) {
	   this.actfinish = actfinish;
	}
	
	
    /**
    * ×Ö¶ÎACTSTART
    * @return java.util.Date
    */
	public java.util.Date getActstart() {
	  		return this.actstart;
	}
	
	/**
    * ×Ö¶ÎACTSTART
    * @return java.util.Date
    */
	public void setActstart(java.util.Date actstart) {
	   this.actstart = actstart;
	}
	
	
    /**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ×Ö¶ÎEQNUM
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * ×Ö¶ÎEQNUM
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * ×Ö¶ÎJPNUM
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * ×Ö¶ÎJPNUM
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * ¾ßÌåÎ»ÖÃ
    * @return java.lang.String
    */
	public java.lang.String getJtwz() {
		if(this.jtwz==null || this.jtwz.length()<=0)
	  		return null;
	  	else
	  		return this.jtwz;
	}
	
	/**
    * ¾ßÌåÎ»ÖÃ
    * @return java.lang.String
    */
	public void setJtwz(java.lang.String jtwz) {
	   this.jtwz = jtwz;
	}
	
	
    /**
    * ×Ö¶ÎLOCATION
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * ×Ö¶ÎLOCATION
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ×Ö¶ÎMEADATE
    * @return java.util.Date
    */
	public java.util.Date getMeadate() {
	  		return this.meadate;
	}
	
	/**
    * ×Ö¶ÎMEADATE
    * @return java.util.Date
    */
	public void setMeadate(java.util.Date meadate) {
	   this.meadate = meadate;
	}
	
	
    /**
    * ×Ö¶ÎMEAVALUE
    * @return java.lang.Double
    */
	public java.lang.Double getMeavalue() {
	  		return this.meavalue;
	}
	
	/**
    * ×Ö¶ÎMEAVALUE
    * @return java.lang.Double
    */
	public void setMeavalue(java.lang.Double meavalue) {
	   this.meavalue = meavalue;
	}
	
	
    /**
    * ²¿Î»
    * @return java.lang.String
    */
	public java.lang.String getPart() {
		if(this.part==null || this.part.length()<=0)
	  		return null;
	  	else
	  		return this.part;
	}
	
	/**
    * ²¿Î»
    * @return java.lang.String
    */
	public void setPart(java.lang.String part) {
	   this.part = part;
	}
	
	
    /**
    * ×Ö¶ÎPOINTNUM
    * @return java.lang.String
    */
	public java.lang.String getPointnum() {
		if(this.pointnum==null || this.pointnum.length()<=0)
	  		return null;
	  	else
	  		return this.pointnum;
	}
	
	/**
    * ×Ö¶ÎPOINTNUM
    * @return java.lang.String
    */
	public void setPointnum(java.lang.String pointnum) {
	   this.pointnum = pointnum;
	}
	
	
    /**
    * ×Ö¶ÎSCHEDFINISH
    * @return java.util.Date
    */
	public java.util.Date getSchedfinish() {
	  		return this.schedfinish;
	}
	
	/**
    * ×Ö¶ÎSCHEDFINISH
    * @return java.util.Date
    */
	public void setSchedfinish(java.util.Date schedfinish) {
	   this.schedfinish = schedfinish;
	}
	
	
    /**
    * ×Ö¶ÎSCHEDSTART
    * @return java.util.Date
    */
	public java.util.Date getSchedstart() {
	  		return this.schedstart;
	}
	
	/**
    * ×Ö¶ÎSCHEDSTART
    * @return java.util.Date
    */
	public void setSchedstart(java.util.Date schedstart) {
	   this.schedstart = schedstart;
	}
	
	
    /**
    * ×Ö¶ÎSTATUS
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ×Ö¶ÎSTATUS
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ×Ö¶ÎSTATUSDATE
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ×Ö¶ÎSTATUSDATE
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * ×Ö¶ÎTARGCOMP
    * @return java.util.Date
    */
	public java.util.Date getTargcomp() {
	  		return this.targcomp;
	}
	
	/**
    * ×Ö¶ÎTARGCOMP
    * @return java.util.Date
    */
	public void setTargcomp(java.util.Date targcomp) {
	   this.targcomp = targcomp;
	}
	
	
    /**
    * ×Ö¶ÎTARGSTART
    * @return java.util.Date
    */
	public java.util.Date getTargstart() {
	  		return this.targstart;
	}
	
	/**
    * ×Ö¶ÎTARGSTART
    * @return java.util.Date
    */
	public void setTargstart(java.util.Date targstart) {
	   this.targstart = targstart;
	}
	
	
    /**
    * ×Ö¶ÎTASKDURATION
    * @return java.lang.Double
    */
	public java.lang.Double getTaskduration() {
	  		return this.taskduration;
	}
	
	/**
    * ×Ö¶ÎTASKDURATION
    * @return java.lang.Double
    */
	public void setTaskduration(java.lang.Double taskduration) {
	   this.taskduration = taskduration;
	}
	
	
    /**
    * ×Ö¶ÎTASKNUM
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * ×Ö¶ÎTASKNUM
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * ×Ö¶ÎWONUM
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ×Ö¶ÎWONUM
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}