package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invrectrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String statuschangeby;
     private java.lang.String wonum;
     private java.lang.String location;
     private java.lang.String eqnum;
     private java.lang.String tasknum;
     private java.lang.String rejectcode;
     private java.lang.Double rejectqty;
     private java.lang.Double conversion;
     private java.lang.String changeby;
     private java.lang.String issuetolabor;
     private java.lang.String packnum;
     private java.lang.String reqby;
     private java.lang.Double curbal;
     private java.lang.String tobin;
     private java.lang.Double exchangerate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String remark;
     private java.lang.String fromwarehouse;
     private java.lang.String frombin;
     private java.lang.String fromlot;
     private java.lang.String tolot;
     private java.lang.Double loadedcost;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String taxcode;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String invoicenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String towarehouse;
     private java.util.Date transdate;
     private java.util.Date actualdate;
     private java.lang.Double quantity;
     private java.lang.String recunit;
     private java.lang.String rectype;
     private java.lang.Double unitcost;
     private java.lang.Double linecost;
     private java.lang.Double actualcost;
     private java.lang.Double oldavgcost;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Invrectrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatuschangeby() {
		if(this.statuschangeby==null || this.statuschangeby.length()<=0)
	  		return null;
	  	else
	  		return this.statuschangeby;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatuschangeby(java.lang.String statuschangeby) {
	   this.statuschangeby = statuschangeby;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
    /**
    * λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * �ʲ����
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �ʲ����
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * ��Ŀ���
    * @return java.lang.String
    */
	public java.lang.String getRejectcode() {
		if(this.rejectcode==null || this.rejectcode.length()<=0)
	  		return null;
	  	else
	  		return this.rejectcode;
	}
	
	/**
    * ��Ŀ���
    * @return java.lang.String
    */
	public void setRejectcode(java.lang.String rejectcode) {
	   this.rejectcode = rejectcode;
	}
	
	
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getRejectqty() {
	  		return this.rejectqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setRejectqty(java.lang.Double rejectqty) {
	   this.rejectqty = rejectqty;
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
    * ������Ա
    * @return java.lang.String
    */
	public java.lang.String getIssuetolabor() {
		if(this.issuetolabor==null || this.issuetolabor.length()<=0)
	  		return null;
	  	else
	  		return this.issuetolabor;
	}
	
	/**
    * ������Ա
    * @return java.lang.String
    */
	public void setIssuetolabor(java.lang.String issuetolabor) {
	   this.issuetolabor = issuetolabor;
	}
	
	
    /**
    * �ֶ�PACKNUM
    * @return java.lang.String
    */
	public java.lang.String getPacknum() {
		if(this.packnum==null || this.packnum.length()<=0)
	  		return null;
	  	else
	  		return this.packnum;
	}
	
	/**
    * �ֶ�PACKNUM
    * @return java.lang.String
    */
	public void setPacknum(java.lang.String packnum) {
	   this.packnum = packnum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getReqby() {
		if(this.reqby==null || this.reqby.length()<=0)
	  		return null;
	  	else
	  		return this.reqby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setReqby(java.lang.String reqby) {
	   this.reqby = reqby;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
	}
	
	
    /**
    * ���
    * @return java.lang.String
    */
	public java.lang.String getTobin() {
		if(this.tobin==null || this.tobin.length()<=0)
	  		return null;
	  	else
	  		return this.tobin;
	}
	
	/**
    * ���
    * @return java.lang.String
    */
	public void setTobin(java.lang.String tobin) {
	   this.tobin = tobin;
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
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ��������
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
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * Դ�ֿ�
    * @return java.lang.String
    */
	public java.lang.String getFromwarehouse() {
		if(this.fromwarehouse==null || this.fromwarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.fromwarehouse;
	}
	
	/**
    * Դ�ֿ�
    * @return java.lang.String
    */
	public void setFromwarehouse(java.lang.String fromwarehouse) {
	   this.fromwarehouse = fromwarehouse;
	}
	
	
    /**
    * Դ���
    * @return java.lang.String
    */
	public java.lang.String getFrombin() {
		if(this.frombin==null || this.frombin.length()<=0)
	  		return null;
	  	else
	  		return this.frombin;
	}
	
	/**
    * Դ���
    * @return java.lang.String
    */
	public void setFrombin(java.lang.String frombin) {
	   this.frombin = frombin;
	}
	
	
    /**
    * Դ����
    * @return java.lang.String
    */
	public java.lang.String getFromlot() {
		if(this.fromlot==null || this.fromlot.length()<=0)
	  		return null;
	  	else
	  		return this.fromlot;
	}
	
	/**
    * Դ����
    * @return java.lang.String
    */
	public void setFromlot(java.lang.String fromlot) {
	   this.fromlot = fromlot;
	}
	
	
    /**
    * Ŀ������
    * @return java.lang.String
    */
	public java.lang.String getTolot() {
		if(this.tolot==null || this.tolot.length()<=0)
	  		return null;
	  	else
	  		return this.tolot;
	}
	
	/**
    * Ŀ������
    * @return java.lang.String
    */
	public void setTolot(java.lang.String tolot) {
	   this.tolot = tolot;
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
    * Ŀ�Ĳֿ�
    * @return java.lang.String
    */
	public java.lang.String getTowarehouse() {
		if(this.towarehouse==null || this.towarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.towarehouse;
	}
	
	/**
    * Ŀ�Ĳֿ�
    * @return java.lang.String
    */
	public void setTowarehouse(java.lang.String towarehouse) {
	   this.towarehouse = towarehouse;
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
    * ʵ������
    * @return java.util.Date
    */
	public java.util.Date getActualdate() {
	  		return this.actualdate;
	}
	
	/**
    * ʵ������
    * @return java.util.Date
    */
	public void setActualdate(java.util.Date actualdate) {
	   this.actualdate = actualdate;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
    /**
    * ���յ�λ
    * @return java.lang.String
    */
	public java.lang.String getRecunit() {
		if(this.recunit==null || this.recunit.length()<=0)
	  		return null;
	  	else
	  		return this.recunit;
	}
	
	/**
    * ���յ�λ
    * @return java.lang.String
    */
	public void setRecunit(java.lang.String recunit) {
	   this.recunit = recunit;
	}
	
	
    /**
    * �վ�����
    * @return java.lang.String
    */
	public java.lang.String getRectype() {
		if(this.rectype==null || this.rectype.length()<=0)
	  		return null;
	  	else
	  		return this.rectype;
	}
	
	/**
    * �վ�����
    * @return java.lang.String
    */
	public void setRectype(java.lang.String rectype) {
	   this.rectype = rectype;
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
    * ʵ�ʳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getActualcost() {
	  		return this.actualcost;
	}
	
	/**
    * ʵ�ʳɱ�
    * @return java.lang.Double
    */
	public void setActualcost(java.lang.Double actualcost) {
	   this.actualcost = actualcost;
	}
	
	
    /**
    * ��ƽ���ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getOldavgcost() {
	  		return this.oldavgcost;
	}
	
	/**
    * ��ƽ���ɱ�
    * @return java.lang.Double
    */
	public void setOldavgcost(java.lang.Double oldavgcost) {
	   this.oldavgcost = oldavgcost;
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
	
	
}