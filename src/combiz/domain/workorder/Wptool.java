package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wptool extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String jpnum;
     private java.lang.Double linecost;
     private java.lang.Double rate;
     private java.lang.String tasknum;
     private java.lang.Double toolhrs;
     private java.lang.String toolnum;
     private java.lang.Long toolqty;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wptool(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 作业包编号
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * 作业包编号
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * 行总价
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * 行总价
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * 费率
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * 费率
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
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
	
	
    /**
    * 工具工时
    * @return java.lang.Double
    */
	public java.lang.Double getToolhrs() {
	  		return this.toolhrs;
	}
	
	/**
    * 工具工时
    * @return java.lang.Double
    */
	public void setToolhrs(java.lang.Double toolhrs) {
	   this.toolhrs = toolhrs;
	}
	
	
    /**
    * 工具编号
    * @return java.lang.String
    */
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}
	
	/**
    * 工具编号
    * @return java.lang.String
    */
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
	
	
    /**
    * 工具数量
    * @return java.lang.Long
    */
	public java.lang.Long getToolqty() {
	  		return this.toolqty;
	}
	
	/**
    * 工具数量
    * @return java.lang.Long
    */
	public void setToolqty(java.lang.Long toolqty) {
	   this.toolqty = toolqty;
	}
	
	
    /**
    * 工单编号
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * 工单编号
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}