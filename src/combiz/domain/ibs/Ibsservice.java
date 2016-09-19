package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsservice extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classname;
     private java.lang.String description;
     private java.lang.String enable;
     private java.lang.String srvname;
     
    /** default constructor */
    public Ibsservice(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �������������ȫ·����
    * @return java.lang.String
    */
	public java.lang.String getClassname() {
		if(this.classname==null || this.classname.length()<=0)
	  		return null;
	  	else
	  		return this.classname;
	}
	
	/**
    * �������������ȫ·����
    * @return java.lang.String
    */
	public void setClassname(java.lang.String classname) {
	   this.classname = classname;
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
    * �Ƿ����ã�
    * @return java.lang.String
    */
	public java.lang.String getEnable() {
		if(this.enable==null || this.enable.length()<=0)
	  		return null;
	  	else
	  		return this.enable;
	}
	
	/**
    * �Ƿ����ã�
    * @return java.lang.String
    */
	public void setEnable(java.lang.String enable) {
	   this.enable = enable;
	}
	
	
    /**
    * �������ƣ����������ģ�
    * @return java.lang.String
    */
	public java.lang.String getSrvname() {
		if(this.srvname==null || this.srvname.length()<=0)
	  		return null;
	  	else
	  		return this.srvname;
	}
	
	/**
    * �������ƣ����������ģ�
    * @return java.lang.String
    */
	public void setSrvname(java.lang.String srvname) {
	   this.srvname = srvname;
	}
	
	
}