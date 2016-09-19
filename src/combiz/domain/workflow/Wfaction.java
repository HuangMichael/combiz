package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfaction extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String action;
     private java.lang.String actionstring;
     private java.lang.String actiontype;
     private java.lang.Long actorderby;
     private java.lang.String condition;
     private java.lang.String conditionclass;
     private java.lang.String defaultsel;
     private java.lang.String description;
     private java.lang.Long endnodeid;
     private java.lang.Long endpt;
     private java.lang.String ispositive;
     private java.lang.Long startnodeid;
     private java.lang.Long startpt;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     
    /** default constructor */
    public Wfaction(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 操作
    * @return java.lang.String
    */
	public java.lang.String getAction() {
		if(this.action==null || this.action.length()<=0)
	  		return null;
	  	else
	  		return this.action;
	}
	
	/**
    * 操作
    * @return java.lang.String
    */
	public void setAction(java.lang.String action) {
	   this.action = action;
	}
	
	
    /**
    * 操作字符串
    * @return java.lang.String
    */
	public java.lang.String getActionstring() {
		if(this.actionstring==null || this.actionstring.length()<=0)
	  		return null;
	  	else
	  		return this.actionstring;
	}
	
	/**
    * 操作字符串
    * @return java.lang.String
    */
	public void setActionstring(java.lang.String actionstring) {
	   this.actionstring = actionstring;
	}
	
	
    /**
    * 操作类型
    * @return java.lang.String
    */
	public java.lang.String getActiontype() {
		if(this.actiontype==null || this.actiontype.length()<=0)
	  		return null;
	  	else
	  		return this.actiontype;
	}
	
	/**
    * 操作类型
    * @return java.lang.String
    */
	public void setActiontype(java.lang.String actiontype) {
	   this.actiontype = actiontype;
	}
	
	
    /**
    * 操作显示排序编号
    * @return java.lang.Long
    */
	public java.lang.Long getActorderby() {
	  		return this.actorderby;
	}
	
	/**
    * 操作显示排序编号
    * @return java.lang.Long
    */
	public void setActorderby(java.lang.Long actorderby) {
	   this.actorderby = actorderby;
	}
	
	
    /**
    * 条件
    * @return java.lang.String
    */
	public java.lang.String getCondition() {
		if(this.condition==null || this.condition.length()<=0)
	  		return null;
	  	else
	  		return this.condition;
	}
	
	/**
    * 条件
    * @return java.lang.String
    */
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	
	
    /**
    * 条件类
    * @return java.lang.String
    */
	public java.lang.String getConditionclass() {
		if(this.conditionclass==null || this.conditionclass.length()<=0)
	  		return null;
	  	else
	  		return this.conditionclass;
	}
	
	/**
    * 条件类
    * @return java.lang.String
    */
	public void setConditionclass(java.lang.String conditionclass) {
	   this.conditionclass = conditionclass;
	}
	
	
    /**
    * 是否默认操作？
    * @return java.lang.String
    */
	public java.lang.String getDefaultsel() {
		if(this.defaultsel==null || this.defaultsel.length()<=0)
	  		return null;
	  	else
	  		return this.defaultsel;
	}
	
	/**
    * 是否默认操作？
    * @return java.lang.String
    */
	public void setDefaultsel(java.lang.String defaultsel) {
	   this.defaultsel = defaultsel;
	}
	
	
    /**
    * 描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 结束节点
    * @return java.lang.Long
    */
	public java.lang.Long getEndnodeid() {
	  		return this.endnodeid;
	}
	
	/**
    * 结束节点
    * @return java.lang.Long
    */
	public void setEndnodeid(java.lang.Long endnodeid) {
	   this.endnodeid = endnodeid;
	}
	
	
    /**
    * 图标终点
    * @return java.lang.Long
    */
	public java.lang.Long getEndpt() {
	  		return this.endpt;
	}
	
	/**
    * 图标终点
    * @return java.lang.Long
    */
	public void setEndpt(java.lang.Long endpt) {
	   this.endpt = endpt;
	}
	
	
    /**
    * 正向反向？
    * @return java.lang.String
    */
	public java.lang.String getIspositive() {
		if(this.ispositive==null || this.ispositive.length()<=0)
	  		return null;
	  	else
	  		return this.ispositive;
	}
	
	/**
    * 正向反向？
    * @return java.lang.String
    */
	public void setIspositive(java.lang.String ispositive) {
	   this.ispositive = ispositive;
	}
	
	
    /**
    * 开始节点
    * @return java.lang.Long
    */
	public java.lang.Long getStartnodeid() {
	  		return this.startnodeid;
	}
	
	/**
    * 开始节点
    * @return java.lang.Long
    */
	public void setStartnodeid(java.lang.Long startnodeid) {
	   this.startnodeid = startnodeid;
	}
	
	
    /**
    * 图标开始点
    * @return java.lang.Long
    */
	public java.lang.Long getStartpt() {
	  		return this.startpt;
	}
	
	/**
    * 图标开始点
    * @return java.lang.Long
    */
	public void setStartpt(java.lang.Long startpt) {
	   this.startpt = startpt;
	}
	
	
    /**
    * 流程
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * 流程
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * 版本
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * 版本
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
}