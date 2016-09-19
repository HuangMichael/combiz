package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Depreciation extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date begintime;
     private java.lang.String classid;
     private java.util.Date depdate;
     private java.lang.String depfaction;
     private java.lang.String depnum;
     private java.lang.String description;
     private java.util.Date endtime;
     private java.lang.String labornum;
     private java.lang.Double scraprate;
     private java.lang.String status;
     private java.util.Date statusdate;
     
    /** default constructor */
    public Depreciation(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getBegintime() {
	  		return this.begintime;
	}
	
	/**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public void setBegintime(java.util.Date begintime) {
	   this.begintime = begintime;
	}
	
	
    /**
    * �豸����
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * �豸����
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * �۾�ʱ��
    * @return java.util.Date
    */
	public java.util.Date getDepdate() {
	  		return this.depdate;
	}
	
	/**
    * �۾�ʱ��
    * @return java.util.Date
    */
	public void setDepdate(java.util.Date depdate) {
	   this.depdate = depdate;
	}
	
	
    /**
    * �����۾ɷ�
    * @return java.lang.String
    */
	public java.lang.String getDepfaction() {
		if(this.depfaction==null || this.depfaction.length()<=0)
	  		return null;
	  	else
	  		return this.depfaction;
	}
	
	/**
    * �����۾ɷ�
    * @return java.lang.String
    */
	public void setDepfaction(java.lang.String depfaction) {
	   this.depfaction = depfaction;
	}
	
	
    /**
    * �۾ɵ�����
    * @return java.lang.String
    */
	public java.lang.String getDepnum() {
		if(this.depnum==null || this.depnum.length()<=0)
	  		return null;
	  	else
	  		return this.depnum;
	}
	
	/**
    * �۾ɵ�����
    * @return java.lang.String
    */
	public void setDepnum(java.lang.String depnum) {
	   this.depnum = depnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getEndtime() {
	  		return this.endtime;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setEndtime(java.util.Date endtime) {
	   this.endtime = endtime;
	}
	
	
    /**
    * �۾�Ա
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * �۾�Ա
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * ��ֵ��
    * @return java.lang.Double
    */
	public java.lang.Double getScraprate() {
	  		return this.scraprate;
	}
	
	/**
    * ��ֵ��
    * @return java.lang.Double
    */
	public void setScraprate(java.lang.Double scraprate) {
	   this.scraprate = scraprate;
	}
	
	
    /**
    * ����״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ����״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
}