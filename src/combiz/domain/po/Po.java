package combiz.domain.po;

import combiz.system.IBOBaseObject;

public class Po extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ponum;
     private java.lang.String description;
     private java.util.Date orderdate;
     private java.util.Date requireddate;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String vendor;
     private java.lang.String contact;
     private java.lang.String freightterms;
     private java.lang.String paymentterms;
     private java.lang.String shipvia;
     private java.lang.String shiptolabor;
     private java.lang.String shiptoaddr;
     private java.lang.String billtolabor;
     private java.lang.String billtoaddr;
     private java.lang.Double totalcost;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.util.Date vendeliverydate;
     private java.lang.String receipts;
     private java.lang.Double exchangerate;
     private java.util.Date exchangedate;
     private java.lang.Double totaltax;
     private java.util.Date startdate;
     private java.util.Date enddate;
     private java.lang.String ebssent;
     private java.util.Date ebsstatusdate;
     private java.lang.String potype;
     private java.lang.String supervisor;
     private java.lang.String isprotocol;
     private java.lang.String isgov;
     private java.lang.String poreason;
     private java.lang.String notifynum;
     
    /** default constructor */
    public Po(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 订购日期
    * @return java.util.Date
    */
	public java.util.Date getOrderdate() {
	  		return this.orderdate;
	}
	
	/**
    * 订购日期
    * @return java.util.Date
    */
	public void setOrderdate(java.util.Date orderdate) {
	   this.orderdate = orderdate;
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
    * 联系人
    * @return java.lang.String
    */
	public java.lang.String getContact() {
		if(this.contact==null || this.contact.length()<=0)
	  		return null;
	  	else
	  		return this.contact;
	}
	
	/**
    * 联系人
    * @return java.lang.String
    */
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
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
    * 总成本
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * 总成本
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
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
    * 供应商交付日期
    * @return java.util.Date
    */
	public java.util.Date getVendeliverydate() {
	  		return this.vendeliverydate;
	}
	
	/**
    * 供应商交付日期
    * @return java.util.Date
    */
	public void setVendeliverydate(java.util.Date vendeliverydate) {
	   this.vendeliverydate = vendeliverydate;
	}
	
	
    /**
    * 是否接收
    * @return java.lang.String
    */
	public java.lang.String getReceipts() {
		if(this.receipts==null || this.receipts.length()<=0)
	  		return null;
	  	else
	  		return this.receipts;
	}
	
	/**
    * 是否接收
    * @return java.lang.String
    */
	public void setReceipts(java.lang.String receipts) {
	   this.receipts = receipts;
	}
	
	
    /**
    * 转换系数
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * 转换系数
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
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
    * 含税总成本
    * @return java.lang.Double
    */
	public java.lang.Double getTotaltax() {
	  		return this.totaltax;
	}
	
	/**
    * 含税总成本
    * @return java.lang.Double
    */
	public void setTotaltax(java.lang.Double totaltax) {
	   this.totaltax = totaltax;
	}
	
	
    /**
    * 开始日期
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * 开始日期
    * @return java.util.Date
    */
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
	}
	
	
    /**
    * 录入日期
    * @return java.util.Date
    */
	public java.util.Date getEnddate() {
	  		return this.enddate;
	}
	
	/**
    * 录入日期
    * @return java.util.Date
    */
	public void setEnddate(java.util.Date enddate) {
	   this.enddate = enddate;
	}
	
	
    /**
    * 以电子方式发送
    * @return java.lang.String
    */
	public java.lang.String getEbssent() {
		if(this.ebssent==null || this.ebssent.length()<=0)
	  		return null;
	  	else
	  		return this.ebssent;
	}
	
	/**
    * 以电子方式发送
    * @return java.lang.String
    */
	public void setEbssent(java.lang.String ebssent) {
	   this.ebssent = ebssent;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getEbsstatusdate() {
	  		return this.ebsstatusdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setEbsstatusdate(java.util.Date ebsstatusdate) {
	   this.ebsstatusdate = ebsstatusdate;
	}
	
	
    /**
    * 订单类型
    * @return java.lang.String
    */
	public java.lang.String getPotype() {
		if(this.potype==null || this.potype.length()<=0)
	  		return null;
	  	else
	  		return this.potype;
	}
	
	/**
    * 订单类型
    * @return java.lang.String
    */
	public void setPotype(java.lang.String potype) {
	   this.potype = potype;
	}
	
	
    /**
    * 主送人
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * 主送人
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
	
	
    /**
    * 是否协议采购
    * @return java.lang.String
    */
	public java.lang.String getIsprotocol() {
		if(this.isprotocol==null || this.isprotocol.length()<=0)
	  		return null;
	  	else
	  		return this.isprotocol;
	}
	
	/**
    * 是否协议采购
    * @return java.lang.String
    */
	public void setIsprotocol(java.lang.String isprotocol) {
	   this.isprotocol = isprotocol;
	}
	
	
    /**
    * 是否政府采购
    * @return java.lang.String
    */
	public java.lang.String getIsgov() {
		if(this.isgov==null || this.isgov.length()<=0)
	  		return null;
	  	else
	  		return this.isgov;
	}
	
	/**
    * 是否政府采购
    * @return java.lang.String
    */
	public void setIsgov(java.lang.String isgov) {
	   this.isgov = isgov;
	}
	
	
    /**
    * 确定供应商原因
    * @return java.lang.String
    */
	public java.lang.String getPoreason() {
		if(this.poreason==null || this.poreason.length()<=0)
	  		return null;
	  	else
	  		return this.poreason;
	}
	
	/**
    * 确定供应商原因
    * @return java.lang.String
    */
	public void setPoreason(java.lang.String poreason) {
	   this.poreason = poreason;
	}
	
	
    /**
    * 通知单号
    * @return java.lang.String
    */
	public java.lang.String getNotifynum() {
		if(this.notifynum==null || this.notifynum.length()<=0)
	  		return null;
	  	else
	  		return this.notifynum;
	}
	
	/**
    * 通知单号
    * @return java.lang.String
    */
	public void setNotifynum(java.lang.String notifynum) {
	   this.notifynum = notifynum;
	}
	
	
}