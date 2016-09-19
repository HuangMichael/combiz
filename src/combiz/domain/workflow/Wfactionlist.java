package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfactionlist extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String action;
     private java.lang.String actionstring;
     private java.lang.String actiontype;
     private java.lang.String description;
     private java.lang.String statuslist;
     private java.lang.String tablecol;
     private java.lang.String tablename;
     
    /** default constructor */
    public Wfactionlist(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getAction() {
		if(this.action==null || this.action.length()<=0)
	  		return null;
	  	else
	  		return this.action;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setAction(java.lang.String action) {
	   this.action = action;
	}
	
	
    /**
    * �����ַ���
    * @return java.lang.String
    */
	public java.lang.String getActionstring() {
		if(this.actionstring==null || this.actionstring.length()<=0)
	  		return null;
	  	else
	  		return this.actionstring;
	}
	
	/**
    * �����ַ���
    * @return java.lang.String
    */
	public void setActionstring(java.lang.String actionstring) {
	   this.actionstring = actionstring;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getActiontype() {
		if(this.actiontype==null || this.actiontype.length()<=0)
	  		return null;
	  	else
	  		return this.actiontype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setActiontype(java.lang.String actiontype) {
	   this.actiontype = actiontype;
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
    * ״ֵ̬�б�
    * @return java.lang.String
    */
	public java.lang.String getStatuslist() {
		if(this.statuslist==null || this.statuslist.length()<=0)
	  		return null;
	  	else
	  		return this.statuslist;
	}
	
	/**
    * ״ֵ̬�б�
    * @return java.lang.String
    */
	public void setStatuslist(java.lang.String statuslist) {
	   this.statuslist = statuslist;
	}
	
	
    /**
    * �Զ�����ֵ���ֶ���
    * @return java.lang.String
    */
	public java.lang.String getTablecol() {
		if(this.tablecol==null || this.tablecol.length()<=0)
	  		return null;
	  	else
	  		return this.tablecol;
	}
	
	/**
    * �Զ�����ֵ���ֶ���
    * @return java.lang.String
    */
	public void setTablecol(java.lang.String tablecol) {
	   this.tablecol = tablecol;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	
	
}