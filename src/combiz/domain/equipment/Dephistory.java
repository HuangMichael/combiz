package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Dephistory extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double depcost;
     private java.util.Date depdate;
     private java.lang.Double deprate;
     private java.lang.String eqnum;
     private java.lang.String labornum;
     private java.lang.Double nowcost;
     private java.lang.Long planyears;
     
    /** default constructor */
    public Dephistory(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 折旧额
    * @return java.lang.Double
    */
	public java.lang.Double getDepcost() {
	  		return this.depcost;
	}
	
	/**
    * 折旧额
    * @return java.lang.Double
    */
	public void setDepcost(java.lang.Double depcost) {
	   this.depcost = depcost;
	}
	
	
    /**
    * 折旧时间
    * @return java.util.Date
    */
	public java.util.Date getDepdate() {
	  		return this.depdate;
	}
	
	/**
    * 折旧时间
    * @return java.util.Date
    */
	public void setDepdate(java.util.Date depdate) {
	   this.depdate = depdate;
	}
	
	
    /**
    * 折旧率
    * @return java.lang.Double
    */
	public java.lang.Double getDeprate() {
	  		return this.deprate;
	}
	
	/**
    * 折旧率
    * @return java.lang.Double
    */
	public void setDeprate(java.lang.Double deprate) {
	   this.deprate = deprate;
	}
	
	
    /**
    * 设备编码
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 设备编码
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 折旧员
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * 折旧员
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * 当前值
    * @return java.lang.Double
    */
	public java.lang.Double getNowcost() {
	  		return this.nowcost;
	}
	
	/**
    * 当前值
    * @return java.lang.Double
    */
	public void setNowcost(java.lang.Double nowcost) {
	   this.nowcost = nowcost;
	}
	
	
    /**
    * 预计使用年限
    * @return java.lang.Long
    */
	public java.lang.Long getPlanyears() {
	  		return this.planyears;
	}
	
	/**
    * 预计使用年限
    * @return java.lang.Long
    */
	public void setPlanyears(java.lang.Long planyears) {
	   this.planyears = planyears;
	}
	
	
}