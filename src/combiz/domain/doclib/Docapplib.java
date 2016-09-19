package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Docapplib extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String isrelapp;
     private java.lang.String libnum;
     private java.lang.String ownertable;
     private java.lang.String relquery;
     
    /** default constructor */
    public Docapplib(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��Ӧ�ó���
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * ��Ӧ�ó���
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * �ǹ���Ӧ�ó���
    * @return java.lang.String
    */
	public java.lang.String getIsrelapp() {
		if(this.isrelapp==null || this.isrelapp.length()<=0)
	  		return null;
	  	else
	  		return this.isrelapp;
	}
	
	/**
    * �ǹ���Ӧ�ó���
    * @return java.lang.String
    */
	public void setIsrelapp(java.lang.String isrelapp) {
	   this.isrelapp = isrelapp;
	}
	
	
    /**
    * �ĵ�����
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * �ĵ�����
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * �����ĵ�������
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * �����ĵ�������
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * ��������ĵ��б��ѯ����
    * @return java.lang.String
    */
	public java.lang.String getRelquery() {
		if(this.relquery==null || this.relquery.length()<=0)
	  		return null;
	  	else
	  		return this.relquery;
	}
	
	/**
    * ��������ĵ��б��ѯ����
    * @return java.lang.String
    */
	public void setRelquery(java.lang.String relquery) {
	   this.relquery = relquery;
	}
	
	
}