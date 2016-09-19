package combiz.domain.contract;

import combiz.system.IBOBaseObject;

public class Contpay extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String cntnum;
     private java.lang.String haspay;
     private java.lang.String memo;
     private java.lang.Double payamount;
     private java.util.Date paydate;
     private java.lang.Long payline;
     private java.lang.String payrate;
     private java.lang.String paytype;
     private java.lang.String requestby;
     private java.util.Date requestdate;
     
    /** default constructor */
    public Contpay(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ͬ���
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * ��ͬ���
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * �Ƿ��Ѹ���
    * @return java.lang.String
    */
	public java.lang.String getHaspay() {
		if(this.haspay==null || this.haspay.length()<=0)
	  		return null;
	  	else
	  		return this.haspay;
	}
	
	/**
    * �Ƿ��Ѹ���
    * @return java.lang.String
    */
	public void setHaspay(java.lang.String haspay) {
	   this.haspay = haspay;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * �Ѹ����
    * @return java.lang.Double
    */
	public java.lang.Double getPayamount() {
	  		return this.payamount;
	}
	
	/**
    * �Ѹ����
    * @return java.lang.Double
    */
	public void setPayamount(java.lang.Double payamount) {
	   this.payamount = payamount;
	}
	
	
    /**
    * ֧������
    * @return java.util.Date
    */
	public java.util.Date getPaydate() {
	  		return this.paydate;
	}
	
	/**
    * ֧������
    * @return java.util.Date
    */
	public void setPaydate(java.util.Date paydate) {
	   this.paydate = paydate;
	}
	
	
    /**
    * �����к�
    * @return java.lang.Long
    */
	public java.lang.Long getPayline() {
	  		return this.payline;
	}
	
	/**
    * �����к�
    * @return java.lang.Long
    */
	public void setPayline(java.lang.Long payline) {
	   this.payline = payline;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getPayrate() {
		if(this.payrate==null || this.payrate.length()<=0)
	  		return null;
	  	else
	  		return this.payrate;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setPayrate(java.lang.String payrate) {
	   this.payrate = payrate;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getPaytype() {
		if(this.paytype==null || this.paytype.length()<=0)
	  		return null;
	  	else
	  		return this.paytype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setPaytype(java.lang.String paytype) {
	   this.paytype = paytype;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRequestby() {
		if(this.requestby==null || this.requestby.length()<=0)
	  		return null;
	  	else
	  		return this.requestby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRequestby(java.lang.String requestby) {
	   this.requestby = requestby;
	}
	
	
    /**
    * ����֧������
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * ����֧������
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
}