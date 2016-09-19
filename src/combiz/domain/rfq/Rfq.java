package combiz.domain.rfq;

import combiz.system.IBOBaseObject;

public class Rfq extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date changedate;
     private java.util.Date replydate;
     private java.util.Date closeondate;
     private java.lang.String rfqtype;
     private java.util.Date requireddate;
     private java.util.Date printdate;
     private java.lang.String shiptolabor;
     private java.lang.String shiptoaddr;
     private java.lang.String billtolabor;
     private java.lang.String billtoaddr;
     private java.lang.String freightterms;
     private java.lang.String shipvia;
     private java.lang.String paymentterms;
     private java.lang.String changeby;
     private java.lang.String rfqnum;
     private java.lang.String description;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     
    /** default constructor */
    public Rfq(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 修改日期
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 修改日期
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * 回复日期
    * @return java.util.Date
    */
	public java.util.Date getReplydate() {
	  		return this.replydate;
	}
	
	/**
    * 回复日期
    * @return java.util.Date
    */
	public void setReplydate(java.util.Date replydate) {
	   this.replydate = replydate;
	}
	
	
    /**
    * 关闭日期
    * @return java.util.Date
    */
	public java.util.Date getCloseondate() {
	  		return this.closeondate;
	}
	
	/**
    * 关闭日期
    * @return java.util.Date
    */
	public void setCloseondate(java.util.Date closeondate) {
	   this.closeondate = closeondate;
	}
	
	
    /**
    * 类型
    * @return java.lang.String
    */
	public java.lang.String getRfqtype() {
		if(this.rfqtype==null || this.rfqtype.length()<=0)
	  		return null;
	  	else
	  		return this.rfqtype;
	}
	
	/**
    * 类型
    * @return java.lang.String
    */
	public void setRfqtype(java.lang.String rfqtype) {
	   this.rfqtype = rfqtype;
	}
	
	
    /**
    * 需求日期
    * @return java.util.Date
    */
	public java.util.Date getRequireddate() {
	  		return this.requireddate;
	}
	
	/**
    * 需求日期
    * @return java.util.Date
    */
	public void setRequireddate(java.util.Date requireddate) {
	   this.requireddate = requireddate;
	}
	
	
    /**
    * 打印的日期
    * @return java.util.Date
    */
	public java.util.Date getPrintdate() {
	  		return this.printdate;
	}
	
	/**
    * 打印的日期
    * @return java.util.Date
    */
	public void setPrintdate(java.util.Date printdate) {
	   this.printdate = printdate;
	}
	
	
    /**
    * 收货人
    * @return java.lang.String
    */
	public java.lang.String getShiptolabor() {
		if(this.shiptolabor==null || this.shiptolabor.length()<=0)
	  		return null;
	  	else
	  		return this.shiptolabor;
	}
	
	/**
    * 收货人
    * @return java.lang.String
    */
	public void setShiptolabor(java.lang.String shiptolabor) {
	   this.shiptolabor = shiptolabor;
	}
	
	
    /**
    * 收货地址
    * @return java.lang.String
    */
	public java.lang.String getShiptoaddr() {
		if(this.shiptoaddr==null || this.shiptoaddr.length()<=0)
	  		return null;
	  	else
	  		return this.shiptoaddr;
	}
	
	/**
    * 收货地址
    * @return java.lang.String
    */
	public void setShiptoaddr(java.lang.String shiptoaddr) {
	   this.shiptoaddr = shiptoaddr;
	}
	
	
    /**
    * 收票人
    * @return java.lang.String
    */
	public java.lang.String getBilltolabor() {
		if(this.billtolabor==null || this.billtolabor.length()<=0)
	  		return null;
	  	else
	  		return this.billtolabor;
	}
	
	/**
    * 收票人
    * @return java.lang.String
    */
	public void setBilltolabor(java.lang.String billtolabor) {
	   this.billtolabor = billtolabor;
	}
	
	
    /**
    * 收票地址
    * @return java.lang.String
    */
	public java.lang.String getBilltoaddr() {
		if(this.billtoaddr==null || this.billtoaddr.length()<=0)
	  		return null;
	  	else
	  		return this.billtoaddr;
	}
	
	/**
    * 收票地址
    * @return java.lang.String
    */
	public void setBilltoaddr(java.lang.String billtoaddr) {
	   this.billtoaddr = billtoaddr;
	}
	
	
    /**
    * 条款
    * @return java.lang.String
    */
	public java.lang.String getFreightterms() {
		if(this.freightterms==null || this.freightterms.length()<=0)
	  		return null;
	  	else
	  		return this.freightterms;
	}
	
	/**
    * 条款
    * @return java.lang.String
    */
	public void setFreightterms(java.lang.String freightterms) {
	   this.freightterms = freightterms;
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
    * 更改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 更改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 询价单
    * @return java.lang.String
    */
	public java.lang.String getRfqnum() {
		if(this.rfqnum==null || this.rfqnum.length()<=0)
	  		return null;
	  	else
	  		return this.rfqnum;
	}
	
	/**
    * 询价单
    * @return java.lang.String
    */
	public void setRfqnum(java.lang.String rfqnum) {
	   this.rfqnum = rfqnum;
	}
	
	
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
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * 录入日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 录入日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 录入人
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * 录入人
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
}