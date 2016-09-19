package combiz.domain.invoice;

import combiz.system.IBOBaseObject;

public class Invoice extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ponum;
     private java.lang.String vendor;
     private java.lang.String invoicenum;
     private java.lang.String description;
     private java.lang.String contact;
     private java.lang.Double exchangerate;
     private java.util.Date exchangedate;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.util.Date invoicedate;
     private java.util.Date duedate;
     private java.util.Date paiddate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.Double totaltax;
     private java.lang.Double totalcost;
     private java.lang.Double basetotalcost;
     private java.lang.String finperiod;
     private java.lang.String bankname;
     private java.lang.String bankaccount2;
     private java.lang.String invoiceno;
     private java.lang.String cntnum;
     
    /** default constructor */
    public Invoice(){}
    
   
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
    * 发票编码
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * 发票编码
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
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
    * 转化系数
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * 转化系数
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
    * 发票日期
    * @return java.util.Date
    */
	public java.util.Date getInvoicedate() {
	  		return this.invoicedate;
	}
	
	/**
    * 发票日期
    * @return java.util.Date
    */
	public void setInvoicedate(java.util.Date invoicedate) {
	   this.invoicedate = invoicedate;
	}
	
	
    /**
    * 到期日
    * @return java.util.Date
    */
	public java.util.Date getDuedate() {
	  		return this.duedate;
	}
	
	/**
    * 到期日
    * @return java.util.Date
    */
	public void setDuedate(java.util.Date duedate) {
	   this.duedate = duedate;
	}
	
	
    /**
    * 发票日期
    * @return java.util.Date
    */
	public java.util.Date getPaiddate() {
	  		return this.paiddate;
	}
	
	/**
    * 发票日期
    * @return java.util.Date
    */
	public void setPaiddate(java.util.Date paiddate) {
	   this.paiddate = paiddate;
	}
	
	
    /**
    * 输入日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 输入日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 输入人
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * 输入人
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
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
    * 含税总成本
    * @return java.lang.Double
    */
	public java.lang.Double getBasetotalcost() {
	  		return this.basetotalcost;
	}
	
	/**
    * 含税总成本
    * @return java.lang.Double
    */
	public void setBasetotalcost(java.lang.Double basetotalcost) {
	   this.basetotalcost = basetotalcost;
	}
	
	
    /**
    * 财务周期
    * @return java.lang.String
    */
	public java.lang.String getFinperiod() {
		if(this.finperiod==null || this.finperiod.length()<=0)
	  		return null;
	  	else
	  		return this.finperiod;
	}
	
	/**
    * 财务周期
    * @return java.lang.String
    */
	public void setFinperiod(java.lang.String finperiod) {
	   this.finperiod = finperiod;
	}
	
	
    /**
    * 银行名称
    * @return java.lang.String
    */
	public java.lang.String getBankname() {
		if(this.bankname==null || this.bankname.length()<=0)
	  		return null;
	  	else
	  		return this.bankname;
	}
	
	/**
    * 银行名称
    * @return java.lang.String
    */
	public void setBankname(java.lang.String bankname) {
	   this.bankname = bankname;
	}
	
	
    /**
    * 银行帐号
    * @return java.lang.String
    */
	public java.lang.String getBankaccount2() {
		if(this.bankaccount2==null || this.bankaccount2.length()<=0)
	  		return null;
	  	else
	  		return this.bankaccount2;
	}
	
	/**
    * 银行帐号
    * @return java.lang.String
    */
	public void setBankaccount2(java.lang.String bankaccount2) {
	   this.bankaccount2 = bankaccount2;
	}
	
	
    /**
    * 发票编号
    * @return java.lang.String
    */
	public java.lang.String getInvoiceno() {
		if(this.invoiceno==null || this.invoiceno.length()<=0)
	  		return null;
	  	else
	  		return this.invoiceno;
	}
	
	/**
    * 发票编号
    * @return java.lang.String
    */
	public void setInvoiceno(java.lang.String invoiceno) {
	   this.invoiceno = invoiceno;
	}
	
	
    /**
    * 合同编号
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * 合同编号
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
}