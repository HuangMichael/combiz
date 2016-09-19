package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Item extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classid;
     private java.lang.Double curbal;
     private java.lang.String description;
     private java.lang.String hazardid;
     private java.lang.String inspectreq;
     private java.lang.String issueunit;
     private java.lang.String itemnum;
     private java.lang.Double lifeexp;
     private java.lang.String lottype;
     private java.lang.String modelnum;
     private java.lang.String orderunit;
     private java.lang.String outside;
     private java.lang.String rotating;
     private java.lang.String spareautoadd;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String taxcode;
     
    /** default constructor */
    public Item(){}
    
   
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
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * �������
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * �������
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
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
    * Σ�ձ�ʶ
    * @return java.lang.String
    */
	public java.lang.String getHazardid() {
		if(this.hazardid==null || this.hazardid.length()<=0)
	  		return null;
	  	else
	  		return this.hazardid;
	}
	
	/**
    * Σ�ձ�ʶ
    * @return java.lang.String
    */
	public void setHazardid(java.lang.String hazardid) {
	   this.hazardid = hazardid;
	}
	
	
    /**
    * �Ƿ����
    * @return java.lang.String
    */
	public java.lang.String getInspectreq() {
		if(this.inspectreq==null || this.inspectreq.length()<=0)
	  		return null;
	  	else
	  		return this.inspectreq;
	}
	
	/**
    * �Ƿ����
    * @return java.lang.String
    */
	public void setInspectreq(java.lang.String inspectreq) {
	   this.inspectreq = inspectreq;
	}
	
	
    /**
    * ������λ
    * @return java.lang.String
    */
	public java.lang.String getIssueunit() {
		if(this.issueunit==null || this.issueunit.length()<=0)
	  		return null;
	  	else
	  		return this.issueunit;
	}
	
	/**
    * ������λ
    * @return java.lang.String
    */
	public void setIssueunit(java.lang.String issueunit) {
	   this.issueunit = issueunit;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * ��Ч��
    * @return java.lang.Double
    */
	public java.lang.Double getLifeexp() {
	  		return this.lifeexp;
	}
	
	/**
    * ��Ч��
    * @return java.lang.Double
    */
	public void setLifeexp(java.lang.Double lifeexp) {
	   this.lifeexp = lifeexp;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getLottype() {
		if(this.lottype==null || this.lottype.length()<=0)
	  		return null;
	  	else
	  		return this.lottype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setLottype(java.lang.String lottype) {
	   this.lottype = lottype;
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
    * �ⲿ����?
    * @return java.lang.String
    */
	public java.lang.String getOutside() {
		if(this.outside==null || this.outside.length()<=0)
	  		return null;
	  	else
	  		return this.outside;
	}
	
	/**
    * �ⲿ����?
    * @return java.lang.String
    */
	public void setOutside(java.lang.String outside) {
	   this.outside = outside;
	}
	
	
    /**
    * ��ת��?
    * @return java.lang.String
    */
	public java.lang.String getRotating() {
		if(this.rotating==null || this.rotating.length()<=0)
	  		return null;
	  	else
	  		return this.rotating;
	}
	
	/**
    * ��ת��?
    * @return java.lang.String
    */
	public void setRotating(java.lang.String rotating) {
	   this.rotating = rotating;
	}
	
	
    /**
    * �Ƿ����Ϊ����
    * @return java.lang.String
    */
	public java.lang.String getSpareautoadd() {
		if(this.spareautoadd==null || this.spareautoadd.length()<=0)
	  		return null;
	  	else
	  		return this.spareautoadd;
	}
	
	/**
    * �Ƿ����Ϊ����
    * @return java.lang.String
    */
	public void setSpareautoadd(java.lang.String spareautoadd) {
	   this.spareautoadd = spareautoadd;
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
	
	
}