package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Eqdowntime extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double downtime;
     private java.util.Date endtime;
     private java.lang.String eqnum;
     private java.lang.String location;
     private java.lang.String remark;
     private java.lang.String reportby;
     private java.util.Date reportdate;
     private java.util.Date starttime;
     private java.lang.String wonum;
     
    /** default constructor */
    public Eqdowntime(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ͣ��ʱ��
    * @return java.lang.Double
    */
	public java.lang.Double getDowntime() {
	  		return this.downtime;
	}
	
	/**
    * ͣ��ʱ��
    * @return java.lang.Double
    */
	public void setDowntime(java.lang.Double downtime) {
	   this.downtime = downtime;
	}
	
	
    /**
    * ͣ������ʱ��
    * @return java.util.Date
    */
	public java.util.Date getEndtime() {
	  		return this.endtime;
	}
	
	/**
    * ͣ������ʱ��
    * @return java.util.Date
    */
	public void setEndtime(java.util.Date endtime) {
	   this.endtime = endtime;
	}
	
	
    /**
    * �豸���
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸���
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * �㱨��
    * @return java.lang.String
    */
	public java.lang.String getReportby() {
		if(this.reportby==null || this.reportby.length()<=0)
	  		return null;
	  	else
	  		return this.reportby;
	}
	
	/**
    * �㱨��
    * @return java.lang.String
    */
	public void setReportby(java.lang.String reportby) {
	   this.reportby = reportby;
	}
	
	
    /**
    * �㱨ʱ��
    * @return java.util.Date
    */
	public java.util.Date getReportdate() {
	  		return this.reportdate;
	}
	
	/**
    * �㱨ʱ��
    * @return java.util.Date
    */
	public void setReportdate(java.util.Date reportdate) {
	   this.reportdate = reportdate;
	}
	
	
    /**
    * ͣ����ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getStarttime() {
	  		return this.starttime;
	}
	
	/**
    * ͣ����ʼʱ��
    * @return java.util.Date
    */
	public void setStarttime(java.util.Date starttime) {
	   this.starttime = starttime;
	}
	
	
    /**
    * ���ϵ�
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ���ϵ�
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}