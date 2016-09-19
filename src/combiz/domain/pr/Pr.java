package combiz.domain.pr;

import combiz.system.IBOBaseObject;

public class Pr extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String prnum;
     private java.lang.String description;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.util.Date requestdate;
     private java.lang.String requestedby;
     private java.lang.String requestdept;
     private java.util.Date requireddate;
     private java.lang.String vendor;
     private java.lang.String contact;
     private java.lang.String customernum;
     private java.lang.String fobprice;
     private java.lang.String freightterms;
     private java.lang.String shipvia;
     private java.lang.String paymentterms;
     private java.lang.String shiptolabor;
     private java.lang.String shiptoaddr;
     private java.lang.String billtolabor;
     private java.lang.String billtoaddr;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.Double totalcost;
     private java.lang.String supervisor;
     private java.lang.Double exchangerate;
     private java.util.Date exchangedate;
     private java.lang.Double totaltax;
     private java.lang.String prtype;
     private java.lang.String budnum;
     private java.lang.String buditem;
     private java.lang.String prnumtype;
     
    /** default constructor */
    public Pr(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 采购申请
    * @return java.lang.String
    */
	public java.lang.String getPrnum() {
		if(this.prnum==null || this.prnum.length()<=0)
	  		return null;
	  	else
	  		return this.prnum;
	}
	
	/**
    * 采购申请
    * @return java.lang.String
    */
	public void setPrnum(java.lang.String prnum) {
	   this.prnum = prnum;
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
    * 申请日期
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * 申请日期
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
    /**
    * 申请人
    * @return java.lang.String
    */
	public java.lang.String getRequestedby() {
		if(this.requestedby==null || this.requestedby.length()<=0)
	  		return null;
	  	else
	  		return this.requestedby;
	}
	
	/**
    * 申请人
    * @return java.lang.String
    */
	public void setRequestedby(java.lang.String requestedby) {
	   this.requestedby = requestedby;
	}
	
	
    /**
    * 需求部门
    * @return java.lang.String
    */
	public java.lang.String getRequestdept() {
		if(this.requestdept==null || this.requestdept.length()<=0)
	  		return null;
	  	else
	  		return this.requestdept;
	}
	
	/**
    * 需求部门
    * @return java.lang.String
    */
	public void setRequestdept(java.lang.String requestdept) {
	   this.requestdept = requestdept;
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
    * 客户编号
    * @return java.lang.String
    */
	public java.lang.String getCustomernum() {
		if(this.customernum==null || this.customernum.length()<=0)
	  		return null;
	  	else
	  		return this.customernum;
	}
	
	/**
    * 客户编号
    * @return java.lang.String
    */
	public void setCustomernum(java.lang.String customernum) {
	   this.customernum = customernum;
	}
	
	
    /**
    * 离岸价格
    * @return java.lang.String
    */
	public java.lang.String getFobprice() {
		if(this.fobprice==null || this.fobprice.length()<=0)
	  		return null;
	  	else
	  		return this.fobprice;
	}
	
	/**
    * 离岸价格
    * @return java.lang.String
    */
	public void setFobprice(java.lang.String fobprice) {
	   this.fobprice = fobprice;
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
    * 修改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 修改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
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
    * 主管人
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * 主管人
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
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
    * 税总额
    * @return java.lang.Double
    */
	public java.lang.Double getTotaltax() {
	  		return this.totaltax;
	}
	
	/**
    * 税总额
    * @return java.lang.Double
    */
	public void setTotaltax(java.lang.Double totaltax) {
	   this.totaltax = totaltax;
	}
	
	
    /**
    * 采购类型（需求计划/采购申请）
    * @return java.lang.String
    */
	public java.lang.String getPrtype() {
		if(this.prtype==null || this.prtype.length()<=0)
	  		return null;
	  	else
	  		return this.prtype;
	}
	
	/**
    * 采购类型（需求计划/采购申请）
    * @return java.lang.String
    */
	public void setPrtype(java.lang.String prtype) {
	   this.prtype = prtype;
	}
	
	
    /**
    * 预算编号
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算编号
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * 预算项目
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * 预算项目
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * 申请类型
    * @return java.lang.String
    */
	public java.lang.String getPrnumtype() {
		if(this.prnumtype==null || this.prnumtype.length()<=0)
	  		return null;
	  	else
	  		return this.prnumtype;
	}
	
	/**
    * 申请类型
    * @return java.lang.String
    */
	public void setPrnumtype(java.lang.String prnumtype) {
	   this.prnumtype = prnumtype;
	}
	
	
}