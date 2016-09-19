package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstreesearch extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String description;
     private java.lang.String isdepartment;
     private java.lang.String islabor;
     private java.lang.String listfield;
     private java.lang.String listtype;
     private java.lang.String searchbandfld;
     private java.lang.String searchlist;
     private java.lang.String treelabel;
     
    /** default constructor */
    public Ibstreesearch(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ӧ�ó�����
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * Ӧ�ó�����
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * ��ѯ���Ĳ�ѯ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ��ѯ���Ĳ�ѯ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �ǲ�����
    * @return java.lang.String
    */
	public java.lang.String getIsdepartment() {
		if(this.isdepartment==null || this.isdepartment.length()<=0)
	  		return null;
	  	else
	  		return this.isdepartment;
	}
	
	/**
    * �ǲ�����
    * @return java.lang.String
    */
	public void setIsdepartment(java.lang.String isdepartment) {
	   this.isdepartment = isdepartment;
	}
	
	
    /**
    * ����Ա��
    * @return java.lang.String
    */
	public java.lang.String getIslabor() {
		if(this.islabor==null || this.islabor.length()<=0)
	  		return null;
	  	else
	  		return this.islabor;
	}
	
	/**
    * ����Ա��
    * @return java.lang.String
    */
	public void setIslabor(java.lang.String islabor) {
	   this.islabor = islabor;
	}
	
	
    /**
    * ֵ�б�洢ֵ���ֶ�(δʹ��)
    * @return java.lang.String
    */
	public java.lang.String getListfield() {
		if(this.listfield==null || this.listfield.length()<=0)
	  		return null;
	  	else
	  		return this.listfield;
	}
	
	/**
    * ֵ�б�洢ֵ���ֶ�(δʹ��)
    * @return java.lang.String
    */
	public void setListfield(java.lang.String listfield) {
	   this.listfield = listfield;
	}
	
	
    /**
    * ֵ�б������
    * @return java.lang.String
    */
	public java.lang.String getListtype() {
		if(this.listtype==null || this.listtype.length()<=0)
	  		return null;
	  	else
	  		return this.listtype;
	}
	
	/**
    * ֵ�б������
    * @return java.lang.String
    */
	public void setListtype(java.lang.String listtype) {
	   this.listtype = listtype;
	}
	
	
    /**
    * ֵ�б�󶨵��ֶ�
    * @return java.lang.String
    */
	public java.lang.String getSearchbandfld() {
		if(this.searchbandfld==null || this.searchbandfld.length()<=0)
	  		return null;
	  	else
	  		return this.searchbandfld;
	}
	
	/**
    * ֵ�б�󶨵��ֶ�
    * @return java.lang.String
    */
	public void setSearchbandfld(java.lang.String searchbandfld) {
	   this.searchbandfld = searchbandfld;
	}
	
	
    /**
    * ����ֵ�б�
    * @return java.lang.String
    */
	public java.lang.String getSearchlist() {
		if(this.searchlist==null || this.searchlist.length()<=0)
	  		return null;
	  	else
	  		return this.searchlist;
	}
	
	/**
    * ����ֵ�б�
    * @return java.lang.String
    */
	public void setSearchlist(java.lang.String searchlist) {
	   this.searchlist = searchlist;
	}
	
	
    /**
    * ��ѯ���ϵ���ʾ��ǩ��δʹ�ã�
    * @return java.lang.String
    */
	public java.lang.String getTreelabel() {
		if(this.treelabel==null || this.treelabel.length()<=0)
	  		return null;
	  	else
	  		return this.treelabel;
	}
	
	/**
    * ��ѯ���ϵ���ʾ��ǩ��δʹ�ã�
    * @return java.lang.String
    */
	public void setTreelabel(java.lang.String treelabel) {
	   this.treelabel = treelabel;
	}
	
	
}