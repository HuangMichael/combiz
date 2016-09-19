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
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * ��Ա
    * @return java.lang.String
    */
	public java.lang.String getLabor() {
		if(this.labor==null || this.labor.length()<=0)
	  		return null;
	  	else
	  		return this.labor;
	}
	
	/**
    * ��Ա
    * @return java.lang.String
    */
	public void setLabor(java.lang.String labor) {
	   this.labor = labor;
	}
	
	
    /**
    * �ɹ�����
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * �ɹ�����
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * ʵ�ʹ���ʱ��
    * @return java.lang.Double
    */
	public java.lang.Double getRealworktime() {
	  		return this.realworktime;
	}
	
	/**
    * ʵ�ʹ���ʱ��
    * @return java.lang.Double
    */
	public void setRealworktime(java.lang.Double realworktime) {
	   this.realworktime = realworktime;
	}
	
	
    /**
    * ���������
    * @return java.lang.String
    */
	public java.lang.String getTecmoney() {
		if(this.tecmoney==null || this.tecmoney.length()<=0)
	  		return null;
	  	else
	  		return this.tecmoney;
	}
	
	/**
    * ���������
    * @return java.lang.String
    */
	public void setTecmoney(java.lang.String tecmoney) {
	   this.tecmoney = tecmoney;
	}
	
	
    /**
    * ��λ
    * @return java.lang.String
    */
	public java.lang.String getTecunit() {
		if(this.tecunit==null || this.tecunit.length()<=0)
	  		return null;
	  	else
	  		return this.tecunit;
	}
	
	/**
    * ��λ
    * @return java.lang.String
    */
	public void setTecunit(java.lang.String tecunit) {
	   this.tecunit = tecunit;
	}
	
	
    /**
    * ��λ
    * @return java.lang.String
    */
	public java.lang.String getTimeunit() {
		if(this.timeunit==null || this.timeunit.length()<=0)
	  		return null;
	  	else
	  		return this.timeunit;
	}
	
	/**
    * ��λ
    * @return java.lang.String
    */
	public void setTimeunit(java.lang.String timeunit) {
	   this.timeunit = timeunit;
	}
	
	
    /**
    * ���ý�����
    * @return java.lang.Double
    */
	public java.lang.Double getTotalmoney() {
	  		return this.totalmoney;
	}
	
	/**
    * ���ý�����
    * @return java.lang.Double
    */
	public void setTotalmoney(java.lang.Double totalmoney) {
	   this.totalmoney = totalmoney;
	}
	
	
}