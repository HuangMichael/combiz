package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wftaskassign extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String sitefilter;
     private java.lang.Long nodeid;
     private java.lang.String app;
     private java.lang.String wfrole;
     private java.lang.String description;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String emailnotify;
     private java.lang.String condition;
     private java.lang.String conditionclass;
     private java.lang.String upwfrole;
     private java.lang.String dealtype;
     private java.lang.Long priority;
     private java.lang.Double dealtimeout;
     private java.lang.String needpass;
     
    /** default constructor */
    public Wftaskassign(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 地点过滤
    * @return java.lang.String
    */
	public java.lang.String getSitefilter() {
		if(this.sitefilter==null || this.sitefilter.length()<=0)
	  		return null;
	  	else
	  		return this.sitefilter;
	}
	
	/**
    * 地点过滤
    * @return java.lang.String
    */
	public void setSitefilter(java.lang.String sitefilter) {
	   this.sitefilter = sitefilter;
	}
	
	
    /**
    * 节点编号
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * 节点编号
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * 应用程序
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * 应用程序
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * 角色
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * 角色
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
	
	
    /**
    * 任务描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 任务描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
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
	
	
    /**
    * 是否EMAIL通知
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * 是否EMAIL通知
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
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
    * 上报角色
    * @return java.lang.String
    */
	public java.lang.String getUpwfrole() {
		if(this.upwfrole==null || this.upwfrole.length()<=0)
	  		return null;
	  	else
	  		return this.upwfrole;
	}
	
	/**
    * 上报角色
    * @return java.lang.String
    */
	public void setUpwfrole(java.lang.String upwfrole) {
	   this.upwfrole = upwfrole;
	}
	
	
    /**
    * 超时处理类型：上报/自动发送
    * @return java.lang.String
    */
	public java.lang.String getDealtype() {
		if(this.dealtype==null || this.dealtype.length()<=0)
	  		return null;
	  	else
	  		return this.dealtype;
	}
	
	/**
    * 超时处理类型：上报/自动发送
    * @return java.lang.String
    */
	public void setDealtype(java.lang.String dealtype) {
	   this.dealtype = dealtype;
	}
	
	
    /**
    * 优先级
    * @return java.lang.Long
    */
	public java.lang.Long getPriority() {
	  		return this.priority;
	}
	
	/**
    * 优先级
    * @return java.lang.Long
    */
	public void setPriority(java.lang.Long priority) {
	   this.priority = priority;
	}
	
	
    /**
    * 处理超时（小时）
    * @return java.lang.Double
    */
	public java.lang.Double getDealtimeout() {
	  		return this.dealtimeout;
	}
	
	/**
    * 处理超时（小时）
    * @return java.lang.Double
    */
	public void setDealtimeout(java.lang.Double dealtimeout) {
	   this.dealtimeout = dealtimeout;
	}
	
	
    /**
    * 是否需要二次密码验证
    * @return java.lang.String
    */
	public java.lang.String getNeedpass() {
		if(this.needpass==null || this.needpass.length()<=0)
	  		return null;
	  	else
	  		return this.needpass;
	}
	
	/**
    * 是否需要二次密码验证
    * @return java.lang.String
    */
	public void setNeedpass(java.lang.String needpass) {
	   this.needpass = needpass;
	}
	
	
}