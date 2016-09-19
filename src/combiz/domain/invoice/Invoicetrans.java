package combiz.domain.invoice;

import combiz.system.IBOBaseObject;

public class Invoicetrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String enterby;
     private java.lang.Long invoicelinenum;
     private java.lang.String invoicenum;
     private java.lang.Double linecost;
     private java.util.Date transdate;
     private java.lang.String transtype;
     private java.lang.String vendor;
     
    /** default constructor */
    public Invoicetrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * ��Ʊ��
    * @return java.lang.Long
    */
	public java.lang.Long getInvoicelinenum() {
	  		return this.invoicelinenum;
	}
	
	/**
    * ��Ʊ��
    * @return java.lang.Long
    */
	public void setInvoicelinenum(java.lang.Long invoicelinenum) {
	   this.invoicelinenum = invoicelinenum;
	}
	
	
    /**
    * ��Ʊ
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * ��Ʊ
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
	}
	
	
    /**
    * �����гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * �����гɱ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getTranstype() {
		if(this.transtype==null || this.transtype.length()<=0)
	  		return null;
	  	else
	  		return this.transtype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setTranstype(java.lang.String transtype) {
	   this.transtype = transtype;
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
	
	
}