package combiz.domain.po;

import combiz.system.IBOBaseObject;

public class Poservicedetail extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date enterdate;
     private java.lang.String labor;
     private java.lang.String ponum;
     private java.lang.Double realworktime;
     private java.lang.String tecmoney;
     private java.lang.String tecunit;
     private java.lang.String timeunit;
     private java.lang.Double totalmoney;
     
    /** default constructor */
    public Poservicedetail(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 结算日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 结算日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 人员
    * @return java.lang.String
    */
	public java.lang.String getLabor() {
		if(this.labor==null || this.labor.length()<=0)
	  		return null;
	  	else
	  		return this.labor;
	}
	
	/**
    * 人员
    * @return java.lang.String
    */
	public void setLabor(java.lang.String labor) {
	   this.labor = labor;
	}
	
	
    /**
    * 采购单号
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * 采购单号
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * 实际工作时间
    * @return java.lang.Double
    */
	public java.lang.Double getRealworktime() {
	  		return this.realworktime;
	}
	
	/**
    * 实际工作时间
    * @return java.lang.Double
    */
	public void setRealworktime(java.lang.Double realworktime) {
	   this.realworktime = realworktime;
	}
	
	
    /**
    * 技术服务费
    * @return java.lang.String
    */
	public java.lang.String getTecmoney() {
		if(this.tecmoney==null || this.tecmoney.length()<=0)
	  		return null;
	  	else
	  		return this.tecmoney;
	}
	
	/**
    * 技术服务费
    * @return java.lang.String
    */
	public void setTecmoney(java.lang.String tecmoney) {
	   this.tecmoney = tecmoney;
	}
	
	
    /**
    * 单位
    * @return java.lang.String
    */
	public java.lang.String getTecunit() {
		if(this.tecunit==null || this.tecunit.length()<=0)
	  		return null;
	  	else
	  		return this.tecunit;
	}
	
	/**
    * 单位
    * @return java.lang.String
    */
	public void setTecunit(java.lang.String tecunit) {
	   this.tecunit = tecunit;
	}
	
	
    /**
    * 单位
    * @return java.lang.String
    */
	public java.lang.String getTimeunit() {
		if(this.timeunit==null || this.timeunit.length()<=0)
	  		return null;
	  	else
	  		return this.timeunit;
	}
	
	/**
    * 单位
    * @return java.lang.String
    */
	public void setTimeunit(java.lang.String timeunit) {
	   this.timeunit = timeunit;
	}
	
	
    /**
    * 费用结算金额
    * @return java.lang.Double
    */
	public java.lang.Double getTotalmoney() {
	  		return this.totalmoney;
	}
	
	/**
    * 费用结算金额
    * @return java.lang.Double
    */
	public void setTotalmoney(java.lang.Double totalmoney) {
	   this.totalmoney = totalmoney;
	}
	
	
}