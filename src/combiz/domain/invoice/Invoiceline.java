package combiz.domain.invoice;

import combiz.system.IBOBaseObject;

public class Invoiceline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String state;
     private java.lang.String invoicenum;
     private java.lang.Long invoicelinenum;
     private java.lang.String vendor;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.Double invoiceqty;
     private java.lang.String invoiceunit;
     private java.lang.Double unitcost;
     private java.lang.Double taxunitcost;
     private java.lang.Double linecost;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.String enterby;
     private java.lang.String receiptreqd;
     private java.util.Date enterdate;
     private java.lang.String service;
     private java.lang.Double loadedcost;
     private java.lang.String proratetoinv;
     private java.lang.String prorated;
     private java.lang.Double proratecost;
     private java.lang.Double taxlinecost;
     private java.lang.Double conversion;
     private java.lang.String taxcode;
     private java.lang.String paytype;
     
    /** default constructor */
    public Invoiceline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��״̬
    * @return java.lang.String
    */
	public java.lang.String getState() {
		if(this.state==null || this.state.length()<=0)
	  		return null;
	  	else
	  		return this.state;
	}
	
	/**
    * ��״̬
    * @return java.lang.String
    */
	public void setState(java.lang.String state) {
	   this.state = state;
	}
	
	
    /**
    * ��Ʊ���
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * ��Ʊ���
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
	}
	
	
    /**
    * ��Ʊ�к�
    * @return java.lang.Long
    */
	public java.lang.Long getInvoicelinenum() {
	  		return this.invoicelinenum;
	}
	
	/**
    * ��Ʊ�к�
    * @return java.lang.Long
    */
	public void setInvoicelinenum(java.lang.Long invoicelinenum) {
	   this.invoicelinenum = invoicelinenum;
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
    * �ɹ������
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * �ɹ������
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * �ɹ����к�
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * �ɹ����к�
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * �����
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �����
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * ��Ʊ����
    * @return java.lang.Double
    */
	public java.lang.Double getInvoiceqty() {
	  		return this.invoiceqty;
	}
	
	/**
    * ��Ʊ����
    * @return java.lang.Double
    */
	public void setInvoiceqty(java.lang.Double invoiceqty) {
	   this.invoiceqty = invoiceqty;
	}
	
	
    /**
    * ��Ʊ��λ
    * @return java.lang.String
    */
	public java.lang.String getInvoiceunit() {
		if(this.invoiceunit==null || this.invoiceunit.length()<=0)
	  		return null;
	  	else
	  		return this.invoiceunit;
	}
	
	/**
    * ��Ʊ��λ
    * @return java.lang.String
    */
	public void setInvoiceunit(java.lang.String invoiceunit) {
	   this.invoiceunit = invoiceunit;
	}
	
	
    /**
    * ��λ�ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * ��λ�ɱ�
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
	}
	
	
    /**
    * ��˰����
    * @return java.lang.Double
    */
	public java.lang.Double getTaxunitcost() {
	  		return this.taxunitcost;
	}
	
	/**
    * ��˰����
    * @return java.lang.Double
    */
	public void setTaxunitcost(java.lang.Double taxunitcost) {
	   this.taxunitcost = taxunitcost;
	}
	
	
    /**
    * �гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * �гɱ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ˰��
    * @return java.lang.Double
    */
	public java.lang.Double getTaxrate() {
	  		return this.taxrate;
	}
	
	/**
    * ˰��
    * @return java.lang.Double
    */
	public void setTaxrate(java.lang.Double taxrate) {
	   this.taxrate = taxrate;
	}
	
	
    /**
    * ˰
    * @return java.lang.Double
    */
	public java.lang.Double getTax() {
	  		return this.tax;
	}
	
	/**
    * ˰
    * @return java.lang.Double
    */
	public void setTax(java.lang.Double tax) {
	   this.tax = tax;
	}
	
	
    /**
    * ¼����
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * ¼����
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
    /**
    * ƥ�����
    * @return java.lang.String
    */
	public java.lang.String getReceiptreqd() {
		if(this.receiptreqd==null || this.receiptreqd.length()<=0)
	  		return null;
	  	else
	  		return this.receiptreqd;
	}
	
	/**
    * ƥ�����
    * @return java.lang.String
    */
	public void setReceiptreqd(java.lang.String receiptreqd) {
	   this.receiptreqd = receiptreqd;
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
    * ����
    * @return java.lang.String
    */
	public java.lang.String getService() {
		if(this.service==null || this.service.length()<=0)
	  		return null;
	  	else
	  		return this.service;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setService(java.lang.String service) {
	   this.service = service;
	}
	
	
    /**
    * ����ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLoadedcost() {
	  		return this.loadedcost;
	}
	
	/**
    * ����ɱ�
    * @return java.lang.Double
    */
	public void setLoadedcost(java.lang.Double loadedcost) {
	   this.loadedcost = loadedcost;
	}
	
	
    /**
    * ̯�����ʳɱ�
    * @return java.lang.String
    */
	public java.lang.String getProratetoinv() {
		if(this.proratetoinv==null || this.proratetoinv.length()<=0)
	  		return null;
	  	else
	  		return this.proratetoinv;
	}
	
	/**
    * ̯�����ʳɱ�
    * @return java.lang.String
    */
	public void setProratetoinv(java.lang.String proratetoinv) {
	   this.proratetoinv = proratetoinv;
	}
	
	
    /**
    * �ѷ�̯
    * @return java.lang.String
    */
	public java.lang.String getProrated() {
		if(this.prorated==null || this.prorated.length()<=0)
	  		return null;
	  	else
	  		return this.prorated;
	}
	
	/**
    * �ѷ�̯
    * @return java.lang.String
    */
	public void setProrated(java.lang.String prorated) {
	   this.prorated = prorated;
	}
	
	
    /**
    * �ѷ�̯�ķ���ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getProratecost() {
	  		return this.proratecost;
	}
	
	/**
    * �ѷ�̯�ķ���ɱ�
    * @return java.lang.Double
    */
	public void setProratecost(java.lang.Double proratecost) {
	   this.proratecost = proratecost;
	}
	
	
    /**
    * ��˰�ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getTaxlinecost() {
	  		return this.taxlinecost;
	}
	
	/**
    * ��˰�ɱ�
    * @return java.lang.Double
    */
	public void setTaxlinecost(java.lang.Double taxlinecost) {
	   this.taxlinecost = taxlinecost;
	}
	
	
    /**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public java.lang.Double getConversion() {
	  		return this.conversion;
	}
	
	/**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
	}
	
	
    /**
    * ˰����
    * @return java.lang.String
    */
	public java.lang.String getTaxcode() {
		if(this.taxcode==null || this.taxcode.length()<=0)
	  		return null;
	  	else
	  		return this.taxcode;
	}
	
	/**
    * ˰����
    * @return java.lang.String
    */
	public void setTaxcode(java.lang.String taxcode) {
	   this.taxcode = taxcode;
	}
	
	
    /**
    * ��ͬ��������
    * @return java.lang.String
    */
	public java.lang.String getPaytype() {
		if(this.paytype==null || this.paytype.length()<=0)
	  		return null;
	  	else
	  		return this.paytype;
	}
	
	/**
    * ��ͬ��������
    * @return java.lang.String
    */
	public void setPaytype(java.lang.String paytype) {
	   this.paytype = paytype;
	}
	
	
}