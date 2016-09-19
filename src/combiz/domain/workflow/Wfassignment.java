package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfassignment extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String assigncode;
     private java.lang.String assignstatus;
     private java.lang.Double dealtimeout;
     private java.lang.String dealtype;
     private java.lang.String description;
     private java.lang.String emailnotify;
     private java.util.Date enddate;
     private java.lang.String initperson;
     private java.lang.String lastmemo;
     private java.lang.String needpass;
     private java.lang.Long nodeid;
     private java.lang.Long ownerid;
     private java.lang.String ownertable;
     private java.lang.Long priority;
     private java.lang.String reassign;
     private java.util.Date startdate;
     private java.lang.Long taskid;
     private java.lang.String upwfrole;
     private java.lang.Long wfinstid;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String wfrole;
     
    /** default constructor */
    public Wfassignment(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 分配人
    * @return java.lang.String
    */
	public java.lang.String getAssigncode() {
		if(this.assigncode==null || this.assigncode.length()<=0)
	  		return null;
	  	else
	  		return this.assigncode;
	}
	
	/**
    * 分配人
    * @return java.lang.String
    */
	public void setAssigncode(java.lang.String assigncode) {
	   this.assigncode = assigncode;
	}
	
	
    /**
    * 分配状态
    * @return java.lang.String
    */
	public java.lang.String getAssignstatus() {
		if(this.assignstatus==null || this.assignstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assignstatus;
	}
	
	/**
    * 分配状态
    * @return java.lang.String
    */
	public void setAssignstatus(java.lang.String assignstatus) {
	   this.assignstatus = assignstatus;
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
    * EMAIL通知
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * EMAIL通知
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
	}
	
	
    /**
    * 结束时间
    * @return java.util.Date
    */
	public java.util.Date getEnddate() {
	  		return this.enddate;
	}
	
	/**
    * 结束时间
    * @return java.util.Date
    */
	public void setEnddate(java.util.Date enddate) {
	   this.enddate = enddate;
	}
	
	
    /**
    * 启动人
    * @return java.lang.String
    */
	public java.lang.String getInitperson() {
		if(this.initperson==null || this.initperson.length()<=0)
	  		return null;
	  	else
	  		return this.initperson;
	}
	
	/**
    * 启动人
    * @return java.lang.String
    */
	public void setInitperson(java.lang.String initperson) {
	   this.initperson = initperson;
	}
	
	
    /**
    * 上一次审批意见
    * @return java.lang.String
    */
	public java.lang.String getLastmemo() {
		if(this.lastmemo==null || this.lastmemo.length()<=0)
	  		return null;
	  	else
	  		return this.lastmemo;
	}
	
	/**
    * 上一次审批意见
    * @return java.lang.String
    */
	public void setLastmemo(java.lang.String lastmemo) {
	   this.lastmemo = lastmemo;
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
	
	
    /**
    * 节点ID
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * 节点ID
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * 流程关联主键
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * 流程关联主键
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * 流程主表
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * 流程主表
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
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
    * 重新分配人（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getReassign() {
		if(this.reassign==null || this.reassign.length()<=0)
	  		return null;
	  	else
	  		return this.reassign;
	}
	
	/**
    * 重新分配人（虚拟字段）
    * @return java.lang.String
    */
	public void setReassign(java.lang.String reassign) {
	   this.reassign = reassign;
	}
	
	
    /**
    * 开始时间
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * 开始时间
    * @return java.util.Date
    */
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
	}
	
	
    /**
    * 任务分配ID
    * @return java.lang.Long
    */
	public java.lang.Long getTaskid() {
	  		return this.taskid;
	}
	
	/**
    * 任务分配ID
    * @return java.lang.Long
    */
	public void setTaskid(java.lang.Long taskid) {
	   this.taskid = taskid;
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
    * WFINANCE关联主键
    * @return java.lang.Long
    */
	public java.lang.Long getWfinstid() {
	  		return this.wfinstid;
	}
	
	/**
    * WFINANCE关联主键
    * @return java.lang.Long
    */
	public void setWfinstid(java.lang.Long wfinstid) {
	   this.wfinstid = wfinstid;
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
    * 任务分配角色
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * 任务分配角色
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
	
	
}