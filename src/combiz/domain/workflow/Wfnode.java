package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfnode extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String condition;
     private java.lang.String customclass;
     private java.lang.String description;
     private java.lang.String isaggre;
     private java.lang.String nodetype;
     private java.lang.String title;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.Long xcanvas;
     private java.lang.Long ycanvas;
     
    /** default constructor */
    public Wfnode(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ֶ�CONDITION
    * @return java.lang.String
    */
	public java.lang.String getCondition() {
		if(this.condition==null || this.condition.length()<=0)
	  		return null;
	  	else
	  		return this.condition;
	}
	
	/**
    * �ֶ�CONDITION
    * @return java.lang.String
    */
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	
	
    /**
    * �ֶ�CUSTOMCLASS
    * @return java.lang.String
    */
	public java.lang.String getCustomclass() {
		if(this.customclass==null || this.customclass.length()<=0)
	  		return null;
	  	else
	  		return this.customclass;
	}
	
	/**
    * �ֶ�CUSTOMCLASS
    * @return java.lang.String
    */
	public void setCustomclass(java.lang.String customclass) {
	   this.customclass = customclass;
	}
	
	
    /**
    * �ֶ�DESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * �ֶ�DESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �Ƿ��ǩ
    * @return java.lang.String
    */
	public java.lang.String getIsaggre() {
		if(this.isaggre==null || this.isaggre.length()<=0)
	  		return null;
	  	else
	  		return this.isaggre;
	}
	
	/**
    * �Ƿ��ǩ
    * @return java.lang.String
    */
	public void setIsaggre(java.lang.String isaggre) {
	   this.isaggre = isaggre;
	}
	
	
    /**
    * �ֶ�NODETYPE
    * @return java.lang.String
    */
	public java.lang.String getNodetype() {
		if(this.nodetype==null || this.nodetype.length()<=0)
	  		return null;
	  	else
	  		return this.nodetype;
	}
	
	/**
    * �ֶ�NODETYPE
    * @return java.lang.String
    */
	public void setNodetype(java.lang.String nodetype) {
	   this.nodetype = nodetype;
	}
	
	
    /**
    * �ֶ�TITLE
    * @return java.lang.String
    */
	public java.lang.String getTitle() {
		if(this.title==null || this.title.length()<=0)
	  		return null;
	  	else
	  		return this.title;
	}
	
	/**
    * �ֶ�TITLE
    * @return java.lang.String
    */
	public void setTitle(java.lang.String title) {
	   this.title = title;
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
	
	
    /**
    * �ֶ�XCANVAS
    * @return java.lang.Long
    */
	public java.lang.Long getXcanvas() {
	  		return this.xcanvas;
	}
	
	/**
    * �ֶ�XCANVAS
    * @return java.lang.Long
    */
	public void setXcanvas(java.lang.Long xcanvas) {
	   this.xcanvas = xcanvas;
	}
	
	
    /**
    * �ֶ�YCANVAS
    * @return java.lang.Long
    */
	public java.lang.Long getYcanvas() {
	  		return this.ycanvas;
	}
	
	/**
    * �ֶ�YCANVAS
    * @return java.lang.Long
    */
	public void setYcanvas(java.lang.Long ycanvas) {
	   this.ycanvas = ycanvas;
	}
	
	
}