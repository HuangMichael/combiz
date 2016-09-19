package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Doclibary extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.util.Date createdate;
     private java.lang.String creator;
     private java.lang.String description;
     private java.lang.String haschild;
     private java.lang.String libnum;
     private java.lang.String libpath;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.lang.String status;
     
    /** default constructor */
    public Doclibary(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getCreator() {
		if(this.creator==null || this.creator.length()<=0)
	  		return null;
	  	else
	  		return this.creator;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setCreator(java.lang.String creator) {
	   this.creator = creator;
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
    * ���Ӽ�
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * ���Ӽ�
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * Ŀ¼��
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * Ŀ¼��
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * Ŀ¼·��
    * @return java.lang.String
    */
	public java.lang.String getLibpath() {
		if(this.libpath==null || this.libpath.length()<=0)
	  		return null;
	  	else
	  		return this.libpath;
	}
	
	/**
    * Ŀ¼·��
    * @return java.lang.String
    */
	public void setLibpath(java.lang.String libpath) {
	   this.libpath = libpath;
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
    * ��Ŀ¼
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * ��Ŀ¼
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
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
	
	
}