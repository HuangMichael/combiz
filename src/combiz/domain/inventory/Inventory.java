package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Inventory extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double sumreadyqty;
     private java.lang.String vendor;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.Double minlevel;
     private java.lang.Double maxlevel;
     private java.lang.String stocktype;
     private java.lang.String orderunit;
     private java.lang.String issueunit;
     private java.lang.Double conversion;
     private java.lang.Double orderqty;
     private java.lang.Double stdcost;
     private java.lang.Double avgcost;
     private java.lang.Double lastcost;
     private java.util.Date lastissuedate;
     private java.lang.Double issueytd;
     private java.lang.String abctype;
     private java.lang.Double sstock;
     private java.lang.Double sumresqty;
     private java.lang.String warehouse;
     private java.lang.String binnum;
     private java.lang.Double sumpoorderqty;
     private java.lang.String itemdesc;
     private java.lang.Double invcurbal;
     private java.lang.Double invphycnt;
     private java.util.Date invphydate;
     private java.lang.String itemnum;
     private java.lang.Double sumcurbal;
     
    /** default constructor */
    public Inventory(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��������_�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getSumreadyqty() {
	  		return this.sumreadyqty;
	}
	
	/**
    * ��������_�����ֶ�
    * @return java.lang.Double
    */
	public void setSumreadyqty(java.lang.Double sumreadyqty) {
	   this.sumreadyqty = sumreadyqty;
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
    * ���ɳ���
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ���ɳ���
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * �ͺ�
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * �ͺ�
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * �ض�����
    * @return java.lang.Double
    */
	public java.lang.Double getMinlevel() {
	  		return this.minlevel;
	}
	
	/**
    * �ض�����
    * @return java.lang.Double
    */
	public void setMinlevel(java.lang.Double minlevel) {
	   this.minlevel = minlevel;
	}
	
	
    /**
    * �����
    * @return java.lang.Double
    */
	public java.lang.Double getMaxlevel() {
	  		return this.maxlevel;
	}
	
	/**
    * �����
    * @return java.lang.Double
    */
	public void setMaxlevel(java.lang.Double maxlevel) {
	   this.maxlevel = maxlevel;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getStocktype() {
		if(this.stocktype==null || this.stocktype.length()<=0)
	  		return null;
	  	else
	  		return this.stocktype;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setStocktype(java.lang.String stocktype) {
	   this.stocktype = stocktype;
	}
	
	
    /**
    * ������λ
    * @return java.lang.String
    */
	public java.lang.String getOrderunit() {
		if(this.orderunit==null || this.orderunit.length()<=0)
	  		return null;
	  	else
	  		return this.orderunit;
	}
	
	/**
    * ������λ
    * @return java.lang.String
    */
	public void setOrderunit(java.lang.String orderunit) {
	   this.orderunit = orderunit;
	}
	
	
    /**
    * ���ŵ�λ
    * @return java.lang.String
    */
	public java.lang.String getIssueunit() {
		if(this.issueunit==null || this.issueunit.length()<=0)
	  		return null;
	  	else
	  		return this.issueunit;
	}
	
	/**
    * ���ŵ�λ
    * @return java.lang.String
    */
	public void setIssueunit(java.lang.String issueunit) {
	   this.issueunit = issueunit;
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
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getOrderqty() {
	  		return this.orderqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setOrderqty(java.lang.Double orderqty) {
	   this.orderqty = orderqty;
	}
	
	
    /**
    * ��׼�ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getStdcost() {
	  		return this.stdcost;
	}
	
	/**
    * ��׼�ɱ�
    * @return java.lang.Double
    */
	public void setStdcost(java.lang.Double stdcost) {
	   this.stdcost = stdcost;
	}
	
	
    /**
    * ƽ���ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getAvgcost() {
	  		return this.avgcost;
	}
	
	/**
    * ƽ���ɱ�
    * @return java.lang.Double
    */
	public void setAvgcost(java.lang.Double avgcost) {
	   this.avgcost = avgcost;
	}
	
	
    /**
    * �ϴν��ճɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLastcost() {
	  		return this.lastcost;
	}
	
	/**
    * �ϴν��ճɱ�
    * @return java.lang.Double
    */
	public void setLastcost(java.lang.Double lastcost) {
	   this.lastcost = lastcost;
	}
	
	
    /**
    * ���һ�η�������
    * @return java.util.Date
    */
	public java.util.Date getLastissuedate() {
	  		return this.lastissuedate;
	}
	
	/**
    * ���һ�η�������
    * @return java.util.Date
    */
	public void setLastissuedate(java.util.Date lastissuedate) {
	   this.lastissuedate = lastissuedate;
	}
	
	
    /**
    * ���ۼƷ�������
    * @return java.lang.Double
    */
	public java.lang.Double getIssueytd() {
	  		return this.issueytd;
	}
	
	/**
    * ���ۼƷ�������
    * @return java.lang.Double
    */
	public void setIssueytd(java.lang.Double issueytd) {
	   this.issueytd = issueytd;
	}
	
	
    /**
    * ABC����
    * @return java.lang.String
    */
	public java.lang.String getAbctype() {
		if(this.abctype==null || this.abctype.length()<=0)
	  		return null;
	  	else
	  		return this.abctype;
	}
	
	/**
    * ABC����
    * @return java.lang.String
    */
	public void setAbctype(java.lang.String abctype) {
	   this.abctype = abctype;
	}
	
	
    /**
    * �������
    * @return java.lang.Double
    */
	public java.lang.Double getSstock() {
	  		return this.sstock;
	}
	
	/**
    * �������
    * @return java.lang.Double
    */
	public void setSstock(java.lang.Double sstock) {
	   this.sstock = sstock;
	}
	
	
    /**
    * Ԥ������_�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getSumresqty() {
	  		return this.sumresqty;
	}
	
	/**
    * Ԥ������_�����ֶ�
    * @return java.lang.Double
    */
	public void setSumresqty(java.lang.Double sumresqty) {
	   this.sumresqty = sumresqty;
	}
	
	
    /**
    * �ֿ�
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ֿ�
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
    /**
    * Ĭ�����
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * Ĭ�����
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * ���ɹ�����_�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getSumpoorderqty() {
	  		return this.sumpoorderqty;
	}
	
	/**
    * ���ɹ�����_�����ֶ�
    * @return java.lang.Double
    */
	public void setSumpoorderqty(java.lang.Double sumpoorderqty) {
	   this.sumpoorderqty = sumpoorderqty;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getItemdesc() {
		if(this.itemdesc==null || this.itemdesc.length()<=0)
	  		return null;
	  	else
	  		return this.itemdesc;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setItemdesc(java.lang.String itemdesc) {
	   this.itemdesc = itemdesc;
	}
	
	
    /**
    * ���������ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getInvcurbal() {
	  		return this.invcurbal;
	}
	
	/**
    * ���������ֶ�
    * @return java.lang.Double
    */
	public void setInvcurbal(java.lang.Double invcurbal) {
	   this.invcurbal = invcurbal;
	}
	
	
    /**
    * �����̵������ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getInvphycnt() {
	  		return this.invphycnt;
	}
	
	/**
    * �����̵������ֶ�
    * @return java.lang.Double
    */
	public void setInvphycnt(java.lang.Double invphycnt) {
	   this.invphycnt = invphycnt;
	}
	
	
    /**
    * �̵�ʱ�������ֶ�
    * @return java.util.Date
    */
	public java.util.Date getInvphydate() {
	  		return this.invphydate;
	}
	
	/**
    * �̵�ʱ�������ֶ�
    * @return java.util.Date
    */
	public void setInvphydate(java.util.Date invphydate) {
	   this.invphydate = invphydate;
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
    * �������_�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getSumcurbal() {
	  		return this.sumcurbal;
	}
	
	/**
    * �������_�����ֶ�
    * @return java.lang.Double
    */
	public void setSumcurbal(java.lang.Double sumcurbal) {
	   this.sumcurbal = sumcurbal;
	}
	
	
}