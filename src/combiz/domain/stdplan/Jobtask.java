package combiz.domain.stdplan;

import combiz.system.IBOBaseObject;

public class Jobtask extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String eqnum;
     private java.lang.String jpnum;
     private java.lang.String jtwz;
     private java.lang.String location;
     private java.lang.String part;
     private java.lang.String pointnum;
     private java.lang.Double taskduration;
     private java.lang.String tasknum;
     
    /** default constructor */
    public Jobtask(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 标准作业计划编号
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * 标准作业计划编号
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * 具体位置
    * @return java.lang.String
    */
	public java.lang.String getJtwz() {
		if(this.jtwz==null || this.jtwz.length()<=0)
	  		return null;
	  	else
	  		return this.jtwz;
	}
	
	/**
    * 具体位置
    * @return java.lang.String
    */
	public void setJtwz(java.lang.String jtwz) {
	   this.jtwz = jtwz;
	}
	
	
    /**
    * 位置
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 位置
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 部位
    * @return java.lang.String
    */
	public java.lang.String getPart() {
		if(this.part==null || this.part.length()<=0)
	  		return null;
	  	else
	  		return this.part;
	}
	
	/**
    * 部位
    * @return java.lang.String
    */
	public void setPart(java.lang.String part) {
	   this.part = part;
	}
	
	
    /**
    * 行编号
    * @return java.lang.String
    */
	public java.lang.String getPointnum() {
		if(this.pointnum==null || this.pointnum.length()<=0)
	  		return null;
	  	else
	  		return this.pointnum;
	}
	
	/**
    * 行编号
    * @return java.lang.String
    */
	public void setPointnum(java.lang.String pointnum) {
	   this.pointnum = pointnum;
	}
	
	
    /**
    * 任务持续时间
    * @return java.lang.Double
    */
	public java.lang.Double getTaskduration() {
	  		return this.taskduration;
	}
	
	/**
    * 任务持续时间
    * @return java.lang.Double
    */
	public void setTaskduration(java.lang.Double taskduration) {
	   this.taskduration = taskduration;
	}
	
	
    /**
    * 任务编号
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * 任务编号
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
}