package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsrptparam extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String autodata;
     private java.lang.String description;
     private java.lang.String lookupfile;
     private java.lang.String lookuplist;
     private java.lang.String lookuptype;
     private java.lang.String paramfield;
     private java.lang.String paramname;
     private java.lang.Long parampos;
     private java.lang.String paramvalue;
     private java.lang.String rptname;
     
    /** default constructor */
    public Ibsrptparam(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ӧ�ó���
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * Ӧ�ó���
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * �Ƿ��Զ���ֵ
    * @return java.lang.String
    */
	public java.lang.String getAutodata() {
		if(this.autodata==null || this.autodata.length()<=0)
	  		return null;
	  	else
	  		return this.autodata;
	}
	
	/**
    * �Ƿ��Զ���ֵ
    * @return java.lang.String
    */
	public void setAutodata(java.lang.String autodata) {
	   this.autodata = autodata;
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
    * lookup�ļ�
    * @return java.lang.String
    */
	public java.lang.String getLookupfile() {
		if(this.lookupfile==null || this.lookupfile.length()<=0)
	  		return null;
	  	else
	  		return this.lookupfile;
	}
	
	/**
    * lookup�ļ�
    * @return java.lang.String
    */
	public void setLookupfile(java.lang.String lookupfile) {
	   this.lookupfile = lookupfile;
	}
	
	
    /**
    * ����ֵ�б�
    * @return java.lang.String
    */
	public java.lang.String getLookuplist() {
		if(this.lookuplist==null || this.lookuplist.length()<=0)
	  		return null;
	  	else
	  		return this.lookuplist;
	}
	
	/**
    * ����ֵ�б�
    * @return java.lang.String
    */
	public void setLookuplist(java.lang.String lookuplist) {
	   this.lookuplist = lookuplist;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getLookuptype() {
		if(this.lookuptype==null || this.lookuptype.length()<=0)
	  		return null;
	  	else
	  		return this.lookuptype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setLookuptype(java.lang.String lookuptype) {
	   this.lookuptype = lookuptype;
	}
	
	
    /**
    * ����ȡֵ�ֶ�
    * @return java.lang.String
    */
	public java.lang.String getParamfield() {
		if(this.paramfield==null || this.paramfield.length()<=0)
	  		return null;
	  	else
	  		return this.paramfield;
	}
	
	/**
    * ����ȡֵ�ֶ�
    * @return java.lang.String
    */
	public void setParamfield(java.lang.String paramfield) {
	   this.paramfield = paramfield;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getParamname() {
		if(this.paramname==null || this.paramname.length()<=0)
	  		return null;
	  	else
	  		return this.paramname;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setParamname(java.lang.String paramname) {
	   this.paramname = paramname;
	}
	
	
    /**
    * ��������˳��
    * @return java.lang.Long
    */
	public java.lang.Long getParampos() {
	  		return this.parampos;
	}
	
	/**
    * ��������˳��
    * @return java.lang.Long
    */
	public void setParampos(java.lang.Long parampos) {
	   this.parampos = parampos;
	}
	
	
    /**
    * Ĭ�ϲ���ֵ
    * @return java.lang.String
    */
	public java.lang.String getParamvalue() {
		if(this.paramvalue==null || this.paramvalue.length()<=0)
	  		return null;
	  	else
	  		return this.paramvalue;
	}
	
	/**
    * Ĭ�ϲ���ֵ
    * @return java.lang.String
    */
	public void setParamvalue(java.lang.String paramvalue) {
	   this.paramvalue = paramvalue;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getRptname() {
		if(this.rptname==null || this.rptname.length()<=0)
	  		return null;
	  	else
	  		return this.rptname;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setRptname(java.lang.String rptname) {
	   this.rptname = rptname;
	}
	
	
}