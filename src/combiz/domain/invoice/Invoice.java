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
    * ��Ӧ��
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * ��Ӧ��
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * ��Ʊ����
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * ��Ʊ����
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
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
    * ��ϵ��
    * @return java.lang.String
    */
	public java.lang.String getContact() {
		if(this.contact==null || this.contact.length()<=0)
	  		return null;
	  	else
	  		return this.contact;
	}
	
	/**
    * ��ϵ��
    * @return java.lang.String
    */
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
	
	
    /**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
	}
	
	
    /**
    * �޸�����
    * @return java.util.Date
    */
	public java.util.Date getExchangedate() {
	  		return this.exchangedate;
	}
	
	/**
    * �޸�����
    * @return java.util.Date
    */
	public void setExchangedate(java.util.Date exchangedate) {
	   this.exchangedate = exchangedate;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ״̬����
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ״̬����
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * ��Ʊ����
    * @return java.util.Date
    */
	public java.util.Date getInvoicedate() {
	  		return this.invoicedate;
	}
	
	/**
    * ��Ʊ����
    * @return java.util.Date
    */
	public void setInvoicedate(java.util.Date invoicedate) {
	   this.invoicedate = invoicedate;
	}
	
	
    /**
    * ������
    * @return java.util.Date
    */
	public java.util.Date getDuedate() {
	  		return this.duedate;
	}
	
	/**
    * ������
    * @return java.util.Date
    */
	public void setDuedate(java.util.Date duedate) {
	   this.duedate = duedate;
	}
	
	
    /**
    * ��Ʊ����
    * @return java.util.Date
    */
	public java.util.Date getPaiddate() {
	  		return this.paiddate;
	}
	
	/**
    * ��Ʊ����
    * @return java.util.Date
    */
	public void setPaiddate(java.util.Date paiddate) {
	   this.paiddate = paiddate;
	}
	
	
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
    * ������
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
    /**
    * �޸�����
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * �޸�����
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * �޸���
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * �޸���
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * ��˰�ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getTotaltax() {
	  		return this.totaltax;
	}
	
	/**
    * ��˰�ܳɱ�
    * @return java.lang.Double
    */
	public void setTotaltax(java.lang.Double totaltax) {
	   this.totaltax = totaltax;
	}
	
	
    /**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
	}
	
	
    /**
    * ��˰�ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getBasetotalcost() {
	  		return this.basetotalcost;
	}
	
	/**
    * ��˰�ܳɱ�
    * @return java.lang.Double
    */
	public void setBasetotalcost(java.lang.Double basetotalcost) {
	   this.basetotalcost = basetotalcost;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getFinperiod() {
		if(this.finperiod==null || this.finperiod.length()<=0)
	  		return null;
	  	else
	  		return this.finperiod;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setFinperiod(java.lang.String finperiod) {
	   this.finperiod = finperiod;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getBankname() {
		if(this.bankname==null || this.bankname.length()<=0)
	  		return null;
	  	else
	  		return this.bankname;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setBankname(java.lang.String bankname) {
	   this.bankname = bankname;
	}
	
	
    /**
    * �����ʺ�
    * @return java.lang.String
    */
	public java.lang.String getBankaccount2() {
		if(this.bankaccount2==null || this.bankaccount2.length()<=0)
	  		return null;
	  	else
	  		return this.bankaccount2;
	}
	
	/**
    * �����ʺ�
    * @return java.lang.String
    */
	public void setBankaccount2(java.lang.String bankaccount2) {
	   this.bankaccount2 = bankaccount2;
	}
	
	
    /**
    * ��Ʊ���
    * @return java.lang.String
    */
	public java.lang.String getInvoiceno() {
		if(this.invoiceno==null || this.invoiceno.length()<=0)
	  		return null;
	  	else
	  		return this.invoiceno;
	}
	
	/**
    * ��Ʊ���
    * @return java.lang.String
    */
	public void setInvoiceno(java.lang.String invoiceno) {
	   this.invoiceno = invoiceno;
	}
	
	
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
	
	
}