package combiz.domain.eqdraw;

import combiz.system.IBOBaseObject;

public class Equipdraw extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String drawnum;
     private java.lang.String description;
     private java.lang.String parent;
     private java.lang.String haschild;
     private java.lang.String drawpath;
     private java.lang.String createby;
     private java.util.Date createdate;
     private java.lang.Long position;
     
    /** default constructor */
    public Equipdraw(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ͼ�α��
    * @return java.lang.String
    */
	public java.lang.String getDrawnum() {
		if(this.drawnum==null || this.drawnum.length()<=0)
	  		return null;
	  	else
	  		return this.drawnum;
	}
	
	/**
    * ͼ�α��
    * @return java.lang.String
    */
	public void setDrawnum(java.lang.String drawnum) {
	   this.drawnum = drawnum;
	}
	
	
    /**
    * ͼ���ļ�����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ͼ���ļ�����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
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
    * �Ƿ����Ӽ�
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * �Ƿ����Ӽ�
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * ͼ���ļ�·��
    * @return java.lang.String
    */
	public java.lang.String getDrawpath() {
		if(this.drawpath==null || this.drawpath.length()<=0)
	  		return null;
	  	else
	  		return this.drawpath;
	}
	
	/**
    * ͼ���ļ�·��
    * @return java.lang.String
    */
	public void setDrawpath(java.lang.String drawpath) {
	   this.drawpath = drawpath;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getCreateby() {
		if(this.createby==null || this.createby.length()<=0)
	  		return null;
	  	else
	  		return this.createby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setCreateby(java.lang.String createby) {
	   this.createby = createby;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * ����
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * ����
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
}