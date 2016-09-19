package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibshelps extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ancestor;
     private java.lang.String appmenu;
     private java.lang.String description;
     private java.lang.String haschild;
     private java.lang.String helppath;
     private java.lang.String menutype;
     private java.lang.String parent;
     private java.lang.Long position;
     
    /** default constructor */
    public Ibshelps(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ID��ϳɵ����Ƚṹ�ַ���
    * @return java.lang.String
    */
	public java.lang.String getAncestor() {
		if(this.ancestor==null || this.ancestor.length()<=0)
	  		return null;
	  	else
	  		return this.ancestor;
	}
	
	/**
    * ��ID��ϳɵ����Ƚṹ�ַ���
    * @return java.lang.String
    */
	public void setAncestor(java.lang.String ancestor) {
	   this.ancestor = ancestor;
	}
	
	
    /**
    * �����˵�
    * @return java.lang.String
    */
	public java.lang.String getAppmenu() {
		if(this.appmenu==null || this.appmenu.length()<=0)
	  		return null;
	  	else
	  		return this.appmenu;
	}
	
	/**
    * �����˵�
    * @return java.lang.String
    */
	public void setAppmenu(java.lang.String appmenu) {
	   this.appmenu = appmenu;
	}
	
	
    /**
    * �˵�����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * �˵�����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
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
    * �����ļ�·��
    * @return java.lang.String
    */
	public java.lang.String getHelppath() {
		if(this.helppath==null || this.helppath.length()<=0)
	  		return null;
	  	else
	  		return this.helppath;
	}
	
	/**
    * �����ļ�·��
    * @return java.lang.String
    */
	public void setHelppath(java.lang.String helppath) {
	   this.helppath = helppath;
	}
	
	
    /**
    * �˵����ͣ��Զ���/Ӧ�ó���
    * @return java.lang.String
    */
	public java.lang.String getMenutype() {
		if(this.menutype==null || this.menutype.length()<=0)
	  		return null;
	  	else
	  		return this.menutype;
	}
	
	/**
    * �˵����ͣ��Զ���/Ӧ�ó���
    * @return java.lang.String
    */
	public void setMenutype(java.lang.String menutype) {
	   this.menutype = menutype;
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
    * �����
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * �����
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
}