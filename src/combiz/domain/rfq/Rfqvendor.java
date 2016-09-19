package combiz.domain.rfq;

import combiz.system.IBOBaseObject;

public class Rfqvendor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String contact;
     private java.lang.String email;
     private java.util.Date exchangedate;
     private java.lang.Double exchangerate;
     private java.lang.String faxphone;
     private java.lang.String freightterms;
     private java.lang.String paymentterms;
     private java.lang.String phone;
     private java.util.Date replieddate;
     private java.lang.String rfqnum;
     private java.lang.String shipvia;
     private java.lang.String vendor;
     private java.lang.String vendorquotenum;
     
    /** default constructor */
    public Rfqvendor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 合同
    * @return java.lang.String
    */
	public java.lang.String getContact() {
		if(this.contact==null || this.contact.length()<=0)
	  		return null;
	  	else
	  		return this.contact;
	}
	
	/**
    * 合同
    * @return java.lang.String
    */
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
	
	
    /**
    * E_MAIL
    * @return java.lang.String
    */
	public java.lang.String getEmail() {
		if(this.email==null || this.email.length()<=0)
	  		return null;
	  	else
	  		return this.email;
	}
	
	/**
    * E_MAIL
    * @return java.lang.String
    */
	public void setEmail(java.lang.String email) {
	   this.email = email;
	}
	
	
    /**
    * 修改日期
    * @return java.util.Date
    */
	public java.util.Date getExchangedate() {
	  		return this.exchangedate;
	}
	
	/**
    * 修改日期
    * @return java.util.Date
    */
	public void setExchangedate(java.util.Date exchangedate) {
	   this.exchangedate = exchangedate;
	}
	
	
    /**
    * 汇率
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * 汇率
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
	}
	
	
    /**
    * 联系人传真
    * @return java.lang.String
    */
	public java.lang.String getFaxphone() {
		if(this.faxphone==null || this.faxphone.length()<=0)
	  		return null;
	  	else
	  		return this.faxphone;
	}
	
	/**
    * 联系人传真
    * @return java.lang.String
    */
	public void setFaxphone(java.lang.String faxphone) {
	   this.faxphone = faxphone;
	}
	
	
    /**
    * 运输条款
    * @return java.lang.String
    */
	public java.lang.String getFreightterms() {
		if(this.freightterms==null || this.freightterms.length()<=0)
	  		return null;
	  	else
	  		return this.freightterms;
	}
	
	/**
    * 运输条款
    * @return java.lang.String
    */
	public void setFreightterms(java.lang.String freightterms) {
	   this.freightterms = freightterms;
	}
	
	
    /**
    * 支付条款
    * @return java.lang.String
    */
	public java.lang.String getPaymentterms() {
		if(this.paymentterms==null || this.paymentterms.length()<=0)
	  		return null;
	  	else
	  		return this.paymentterms;
	}
	
	/**
    * 支付条款
    * @return java.lang.String
    */
	public void setPaymentterms(java.lang.String paymentterms) {
	   this.paymentterms = paymentterms;
	}
	
	
    /**
    * 联系人电话
    * @return java.lang.String
    */
	public java.lang.String getPhone() {
		if(this.phone==null || this.phone.length()<=0)
	  		return null;
	  	else
	  		return this.phone;
	}
	
	/**
    * 联系人电话
    * @return java.lang.String
    */
	public void setPhone(java.lang.String phone) {
	   this.phone = phone;
	}
	
	
    /**
    * 答复日期
    * @return java.util.Date
    */
	public java.util.Date getReplieddate() {
	  		return this.replieddate;
	}
	
	/**
    * 答复日期
    * @return java.util.Date
    */
	public void setReplieddate(java.util.Date replieddate) {
	   this.replieddate = replieddate;
	}
	
	
    /**
    * 询价单号
    * @return java.lang.String
    */
	public java.lang.String getRfqnum() {
		if(this.rfqnum==null || this.rfqnum.length()<=0)
	  		return null;
	  	else
	  		return this.rfqnum;
	}
	
	/**
    * 询价单号
    * @return java.lang.String
    */
	public void setRfqnum(java.lang.String rfqnum) {
	   this.rfqnum = rfqnum;
	}
	
	
    /**
    * 运输方式
    * @return java.lang.String
    */
	public java.lang.String getShipvia() {
		if(this.shipvia==null || this.shipvia.length()<=0)
	  		return null;
	  	else
	  		return this.shipvia;
	}
	
	/**
    * 运输方式
    * @return java.lang.String
    */
	public void setShipvia(java.lang.String shipvia) {
	   this.shipvia = shipvia;
	}
	
	
    /**
    * 供应商
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * 供应商
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * 供应商
    * @return java.lang.String
    */
	public java.lang.String getVendorquotenum() {
		if(this.vendorquotenum==null || this.vendorquotenum.length()<=0)
	  		return null;
	  	else
	  		return this.vendorquotenum;
	}
	
	/**
    * 供应商
    * @return java.lang.String
    */
	public void setVendorquotenum(java.lang.String vendorquotenum) {
	   this.vendorquotenum = vendorquotenum;
	}
	
	
}