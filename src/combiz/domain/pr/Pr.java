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
    * �ɹ�����
    * @return java.lang.String
    */
	public java.lang.String getPrnum() {
		if(this.prnum==null || this.prnum.length()<=0)
	  		return null;
	  	else
	  		return this.prnum;
	}
	
	/**
    * �ɹ�����
    * @return java.lang.String
    */
	public void setPrnum(java.lang.String prnum) {
	   this.prnum = prnum;
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
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRequestedby() {
		if(this.requestedby==null || this.requestedby.length()<=0)
	  		return null;
	  	else
	  		return this.requestedby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRequestedby(java.lang.String requestedby) {
	   this.requestedby = requestedby;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRequestdept() {
		if(this.requestdept==null || this.requestdept.length()<=0)
	  		return null;
	  	else
	  		return this.requestdept;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRequestdept(java.lang.String requestdept) {
	   this.requestdept = requestdept;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRequireddate() {
	  		return this.requireddate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRequireddate(java.util.Date requireddate) {
	   this.requireddate = requireddate;
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
    * �ͻ����
    * @return java.lang.String
    */
	public java.lang.String getCustomernum() {
		if(this.customernum==null || this.customernum.length()<=0)
	  		return null;
	  	else
	  		return this.customernum;
	}
	
	/**
    * �ͻ����
    * @return java.lang.String
    */
	public void setCustomernum(java.lang.String customernum) {
	   this.customernum = customernum;
	}
	
	
    /**
    * �밶�۸�
    * @return java.lang.String
    */
	public java.lang.String getFobprice() {
		if(this.fobprice==null || this.fobprice.length()<=0)
	  		return null;
	  	else
	  		return this.fobprice;
	}
	
	/**
    * �밶�۸�
    * @return java.lang.String
    */
	public void setFobprice(java.lang.String fobprice) {
	   this.fobprice = fobprice;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getFreightterms() {
		if(this.freightterms==null || this.freightterms.length()<=0)
	  		return null;
	  	else
	  		return this.freightterms;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setFreightterms(java.lang.String freightterms) {
	   this.freightterms = freightterms;
	}
	
	
    /**
    * ���䷽ʽ
    * @return java.lang.String
    */
	public java.lang.String getShipvia() {
		if(this.shipvia==null || this.shipvia.length()<=0)
	  		return null;
	  	else
	  		return this.shipvia;
	}
	
	/**
    * ���䷽ʽ
    * @return java.lang.String
    */
	public void setShipvia(java.lang.String shipvia) {
	   this.shipvia = shipvia;
	}
	
	
    /**
    * ֧������
    * @return java.lang.String
    */
	public java.lang.String getPaymentterms() {
		if(this.paymentterms==null || this.paymentterms.length()<=0)
	  		return null;
	  	else
	  		return this.paymentterms;
	}
	
	/**
    * ֧������
    * @return java.lang.String
    */
	public void setPaymentterms(java.lang.String paymentterms) {
	   this.paymentterms = paymentterms;
	}
	
	
    /**
    * �ջ���
    * @return java.lang.String
    */
	public java.lang.String getShiptolabor() {
		if(this.shiptolabor==null || this.shiptolabor.length()<=0)
	  		return null;
	  	else
	  		return this.shiptolabor;
	}
	
	/**
    * �ջ���
    * @return java.lang.String
    */
	public void setShiptolabor(java.lang.String shiptolabor) {
	   this.shiptolabor = shiptolabor;
	}
	
	
    /**
    * �ջ���ַ
    * @return java.lang.String
    */
	public java.lang.String getShiptoaddr() {
		if(this.shiptoaddr==null || this.shiptoaddr.length()<=0)
	  		return null;
	  	else
	  		return this.shiptoaddr;
	}
	
	/**
    * �ջ���ַ
    * @return java.lang.String
    */
	public void setShiptoaddr(java.lang.String shiptoaddr) {
	   this.shiptoaddr = shiptoaddr;
	}
	
	
    /**
    * ��Ʊ��
    * @return java.lang.String
    */
	public java.lang.String getBilltolabor() {
		if(this.billtolabor==null || this.billtolabor.length()<=0)
	  		return null;
	  	else
	  		return this.billtolabor;
	}
	
	/**
    * ��Ʊ��
    * @return java.lang.String
    */
	public void setBilltolabor(java.lang.String billtolabor) {
	   this.billtolabor = billtolabor;
	}
	
	
    /**
    * ��Ʊ��ַ
    * @return java.lang.String
    */
	public java.lang.String getBilltoaddr() {
		if(this.billtoaddr==null || this.billtoaddr.length()<=0)
	  		return null;
	  	else
	  		return this.billtoaddr;
	}
	
	/**
    * ��Ʊ��ַ
    * @return java.lang.String
    */
	public void setBilltoaddr(java.lang.String billtoaddr) {
	   this.billtoaddr = billtoaddr;
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
    * ������
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * ����
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
    * ˰�ܶ�
    * @return java.lang.Double
    */
	public java.lang.Double getTotaltax() {
	  		return this.totaltax;
	}
	
	/**
    * ˰�ܶ�
    * @return java.lang.Double
    */
	public void setTotaltax(java.lang.Double totaltax) {
	   this.totaltax = totaltax;
	}
	
	
    /**
    * �ɹ����ͣ�����ƻ�/�ɹ����룩
    * @return java.lang.String
    */
	public java.lang.String getPrtype() {
		if(this.prtype==null || this.prtype.length()<=0)
	  		return null;
	  	else
	  		return this.prtype;
	}
	
	/**
    * �ɹ����ͣ�����ƻ�/�ɹ����룩
    * @return java.lang.String
    */
	public void setPrtype(java.lang.String prtype) {
	   this.prtype = prtype;
	}
	
	
    /**
    * Ԥ����
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * Ԥ����
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getPrnumtype() {
		if(this.prnumtype==null || this.prnumtype.length()<=0)
	  		return null;
	  	else
	  		return this.prnumtype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setPrnumtype(java.lang.String prnumtype) {
	   this.prnumtype = prnumtype;
	}
	
	
}