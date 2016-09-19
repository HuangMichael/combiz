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
    * ×Ö¶ÎCONDITION
    * @return java.lang.String
    */
	public java.lang.String getCondition() {
		if(this.condition==null || this.condition.length()<=0)
	  		return null;
	  	else
	  		return this.condition;
	}
	
	/**
    * ×Ö¶ÎCONDITION
    * @return java.lang.String
    */
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	
	
    /**
    * ×Ö¶ÎCUSTOMCLASS
    * @return java.lang.String
    */
	public java.lang.String getCustomclass() {
		if(this.customclass==null || this.customclass.length()<=0)
	  		return null;
	  	else
	  		return this.customclass;
	}
	
	/**
    * ×Ö¶ÎCUSTOMCLASS
    * @return java.lang.String
    */
	public void setCustomclass(java.lang.String customclass) {
	   this.customclass = customclass;
	}
	
	
    /**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ÊÇ·ñ»áÇ©
    * @return java.lang.String
    */
	public java.lang.String getIsaggre() {
		if(this.isaggre==null || this.isaggre.length()<=0)
	  		return null;
	  	else
	  		return this.isaggre;
	}
	
	/**
    * ÊÇ·ñ»áÇ©
    * @return java.lang.String
    */
	public void setIsaggre(java.lang.String isaggre) {
	   this.isaggre = isaggre;
	}
	
	
    /**
    * ×Ö¶ÎNODETYPE
    * @return java.lang.String
    */
	public java.lang.String getNodetype() {
		if(this.nodetype==null || this.nodetype.length()<=0)
	  		return null;
	  	else
	  		return this.nodetype;
	}
	
	/**
    * ×Ö¶ÎNODETYPE
    * @return java.lang.String
    */
	public void setNodetype(java.lang.String nodetype) {
	   this.nodetype = nodetype;
	}
	
	
    /**
    * ×Ö¶ÎTITLE
    * @return java.lang.String
    */
	public java.lang.String getTitle() {
		if(this.title==null || this.title.length()<=0)
	  		return null;
	  	else
	  		return this.title;
	}
	
	/**
    * ×Ö¶ÎTITLE
    * @return java.lang.String
    */
	public void setTitle(java.lang.String title) {
	   this.title = title;
	}
	
	
    /**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
    /**
    * ×Ö¶ÎXCANVAS
    * @return java.lang.Long
    */
	public java.lang.Long getXcanvas() {
	  		return this.xcanvas;
	}
	
	/**
    * ×Ö¶ÎXCANVAS
    * @return java.lang.Long
    */
	public void setXcanvas(java.lang.Long xcanvas) {
	   this.xcanvas = xcanvas;
	}
	
	
    /**
    * ×Ö¶ÎYCANVAS
    * @return java.lang.Long
    */
	public java.lang.Long getYcanvas() {
	  		return this.ycanvas;
	}
	
	/**
    * ×Ö¶ÎYCANVAS
    * @return java.lang.Long
    */
	public void setYcanvas(java.lang.Long ycanvas) {
	   this.ycanvas = ycanvas;
	}
	
	
}