package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Department extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String craft;
     private java.lang.String deptnum;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.util.Date enddate;
     private java.lang.String haschild;
     private java.lang.String iscrewid;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.util.Date startdate;
     private java.lang.String supervisor;
     
    /** default constructor */
    public Department(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * רҵ
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * רҵ
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ��Ч
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * ��Ч
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getEnddate() {
	  		return this.enddate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setEnddate(java.util.Date enddate) {
	   this.enddate = enddate;
	}
	
	
    /**
    * �Ƿ����ӽڵ�
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * �Ƿ����ӽڵ�
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * �Ƿ��ǰ���
    * @return java.lang.String
    */
	public java.lang.String getIscrewid() {
		if(this.iscrewid==null || this.iscrewid.length()<=0)
	  		return null;
	  	else
	  		return this.iscrewid;
	}
	
	/**
    * �Ƿ��ǰ���
    * @return java.lang.String
    */
	public void setIscrewid(java.lang.String iscrewid) {
	   this.iscrewid = iscrewid;
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
    * �ϼ�����
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * �ϼ�����
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
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
	
	
}