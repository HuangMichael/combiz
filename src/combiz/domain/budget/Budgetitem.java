package combiz.domain.budget;

import combiz.system.IBOBaseObject;

public class Budgetitem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String budclass;
     private java.lang.String buditem;
     private java.lang.String budperiod;
     private java.lang.String budtype;
     private java.lang.String childclass;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.lang.String haschild;
     private java.lang.String meaunit;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.lang.String remark;
     private java.lang.String version;
     
    /** default constructor */
    public Budgetitem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ԥ������:����Ԥ�㡢����Ԥ��
    * @return java.lang.String
    */
	public java.lang.String getBudclass() {
		if(this.budclass==null || this.budclass.length()<=0)
	  		return null;
	  	else
	  		return this.budclass;
	}
	
	/**
    * Ԥ������:����Ԥ�㡢����Ԥ��
    * @return java.lang.String
    */
	public void setBudclass(java.lang.String budclass) {
	   this.budclass = budclass;
	}
	
	
    /**
    * Ԥ����Ŀ��š���1.1��1-1
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * Ԥ����Ŀ��š���1.1��1-1
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * Ԥ�����ڣ���ȡ����ȡ��¶�
    * @return java.lang.String
    */
	public java.lang.String getBudperiod() {
		if(this.budperiod==null || this.budperiod.length()<=0)
	  		return null;
	  	else
	  		return this.budperiod;
	}
	
	/**
    * Ԥ�����ڣ���ȡ����ȡ��¶�
    * @return java.lang.String
    */
	public void setBudperiod(java.lang.String budperiod) {
	   this.budperiod = budperiod;
	}
	
	
    /**
    * �������ͣ���˾���㡢���ź���
    * @return java.lang.String
    */
	public java.lang.String getBudtype() {
		if(this.budtype==null || this.budtype.length()<=0)
	  		return null;
	  	else
	  		return this.budtype;
	}
	
	/**
    * �������ͣ���˾���㡢���ź���
    * @return java.lang.String
    */
	public void setBudtype(java.lang.String budtype) {
	   this.budtype = budtype;
	}
	
	
    /**
    * Ԥ�������ͣ����롢֧��
    * @return java.lang.String
    */
	public java.lang.String getChildclass() {
		if(this.childclass==null || this.childclass.length()<=0)
	  		return null;
	  	else
	  		return this.childclass;
	}
	
	/**
    * Ԥ�������ͣ����롢֧��
    * @return java.lang.String
    */
	public void setChildclass(java.lang.String childclass) {
	   this.childclass = childclass;
	}
	
	
    /**
    * Ԥ����Ŀ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * Ԥ����Ŀ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �Ƿ����ã��ǡ���
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * �Ƿ����ã��ǡ���
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * ���Ӽ���
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * ���Ӽ���
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * ������λ��Ԫ
    * @return java.lang.String
    */
	public java.lang.String getMeaunit() {
		if(this.meaunit==null || this.meaunit.length()<=0)
	  		return null;
	  	else
	  		return this.meaunit;
	}
	
	/**
    * ������λ��Ԫ
    * @return java.lang.String
    */
	public void setMeaunit(java.lang.String meaunit) {
	   this.meaunit = meaunit;
	}
	
	
    /**
    * ���
    * @return java.lang.Long
    */
	public java.lang.Long getOrderby() {
	  		return this.orderby;
	}
	
	/**
    * ���
    * @return java.lang.Long
    */
	public void setOrderby(java.lang.Long orderby) {
	   this.orderby = orderby;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * ��ע˵��
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע˵��
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * Ԥ����Ŀ�汾
    * @return java.lang.String
    */
	public java.lang.String getVersion() {
		if(this.version==null || this.version.length()<=0)
	  		return null;
	  	else
	  		return this.version;
	}
	
	/**
    * Ԥ����Ŀ�汾
    * @return java.lang.String
    */
	public void setVersion(java.lang.String version) {
	   this.version = version;
	}
	
	
}