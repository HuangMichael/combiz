package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfprocess extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String active;
     private java.lang.String app;
     private java.lang.String autoinit;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.lang.String tablename;
     private java.lang.String wfinstdesc;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     
    /** default constructor */
    public Wfprocess(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ֶ�ACTIVE
    * @return java.lang.String
    */
	public java.lang.String getActive() {
		if(this.active==null || this.active.length()<=0)
	  		return null;
	  	else
	  		return this.active;
	}
	
	/**
    * �ֶ�ACTIVE
    * @return java.lang.String
    */
	public void setActive(java.lang.String active) {
	   this.active = active;
	}
	
	
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
    * �ֶ�AUTOINIT
    * @return java.lang.String
    */
	public java.lang.String getAutoinit() {
		if(this.autoinit==null || this.autoinit.length()<=0)
	  		return null;
	  	else
	  		return this.autoinit;
	}
	
	/**
    * �ֶ�AUTOINIT
    * @return java.lang.String
    */
	public void setAutoinit(java.lang.String autoinit) {
	   this.autoinit = autoinit;
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
    * �ֶ�ENABLED
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * �ֶ�ENABLED
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * �ֶ�TABLENAME
    * @return java.lang.String
    */
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}
	
	/**
    * �ֶ�TABLENAME
    * @return java.lang.String
    */
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	
	
    /**
    * ����ʵ������-���Ե��ݲ���
    * @return java.lang.String
    */
	public java.lang.String getWfinstdesc() {
		if(this.wfinstdesc==null || this.wfinstdesc.length()<=0)
	  		return null;
	  	else
	  		return this.wfinstdesc;
	}
	
	/**
    * ����ʵ������-���Ե��ݲ���
    * @return java.lang.String
    */
	public void setWfinstdesc(java.lang.String wfinstdesc) {
	   this.wfinstdesc = wfinstdesc;
	}
	
	
    /**
    * �ֶ�WFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * �ֶ�WFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * �ֶ�WFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * �ֶ�WFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
}