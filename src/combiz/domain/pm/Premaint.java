package combiz.domain.pm;

import combiz.system.IBOBaseObject;

public class Premaint extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classid;
     private java.lang.String pmnum;
     private java.lang.String description;
     private java.lang.String eqnum;
     private java.lang.String location;
     private java.lang.String warehouse;
     private java.util.Date laststartdate;
     private java.util.Date firstdate;
     private java.util.Date lastcompdate;
     private java.lang.Long frequency;
     private java.lang.String frequnit;
     private java.lang.Long pmcounter;
     private java.lang.String worktype;
     private java.lang.String jpnum;
     private java.lang.String usejpseq;
     private java.util.Date nextdate;
     private java.lang.String supervisor;
     private java.lang.String crewid;
     private java.lang.String autowf;
     private java.lang.String wostatus;
     private java.lang.String eqdown;
     private java.lang.String parent;
     private java.lang.String haschild;
     private java.lang.String usefrequency;
     private java.lang.Long leadtime;
     private java.lang.String adjnextdue;
     
    /** default constructor */
    public Premaint(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 分类
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 分类
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * 预防性维护编号
    * @return java.lang.String
    */
	public java.lang.String getPmnum() {
		if(this.pmnum==null || this.pmnum.length()<=0)
	  		return null;
	  	else
	  		return this.pmnum;
	}
	
	/**
    * 预防性维护编号
    * @return java.lang.String
    */
	public void setPmnum(java.lang.String pmnum) {
	   this.pmnum = pmnum;
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
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 位置
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 位置
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 仓库
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * 仓库
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
    /**
    * 上次开始日期
    * @return java.util.Date
    */
	public java.util.Date getLaststartdate() {
	  		return this.laststartdate;
	}
	
	/**
    * 上次开始日期
    * @return java.util.Date
    */
	public void setLaststartdate(java.util.Date laststartdate) {
	   this.laststartdate = laststartdate;
	}
	
	
    /**
    * 第一次开始日期
    * @return java.util.Date
    */
	public java.util.Date getFirstdate() {
	  		return this.firstdate;
	}
	
	/**
    * 第一次开始日期
    * @return java.util.Date
    */
	public void setFirstdate(java.util.Date firstdate) {
	   this.firstdate = firstdate;
	}
	
	
    /**
    * 上次完成日期
    * @return java.util.Date
    */
	public java.util.Date getLastcompdate() {
	  		return this.lastcompdate;
	}
	
	/**
    * 上次完成日期
    * @return java.util.Date
    */
	public void setLastcompdate(java.util.Date lastcompdate) {
	   this.lastcompdate = lastcompdate;
	}
	
	
    /**
    * 频率
    * @return java.lang.Long
    */
	public java.lang.Long getFrequency() {
	  		return this.frequency;
	}
	
	/**
    * 频率
    * @return java.lang.Long
    */
	public void setFrequency(java.lang.Long frequency) {
	   this.frequency = frequency;
	}
	
	
    /**
    * 频率单位
    * @return java.lang.String
    */
	public java.lang.String getFrequnit() {
		if(this.frequnit==null || this.frequnit.length()<=0)
	  		return null;
	  	else
	  		return this.frequnit;
	}
	
	/**
    * 频率单位
    * @return java.lang.String
    */
	public void setFrequnit(java.lang.String frequnit) {
	   this.frequnit = frequnit;
	}
	
	
    /**
    * 自第一次启动以来生成的预防性维护数量
    * @return java.lang.Long
    */
	public java.lang.Long getPmcounter() {
	  		return this.pmcounter;
	}
	
	/**
    * 自第一次启动以来生成的预防性维护数量
    * @return java.lang.Long
    */
	public void setPmcounter(java.lang.Long pmcounter) {
	   this.pmcounter = pmcounter;
	}
	
	
    /**
    * 工单类型
    * @return java.lang.String
    */
	public java.lang.String getWorktype() {
		if(this.worktype==null || this.worktype.length()<=0)
	  		return null;
	  	else
	  		return this.worktype;
	}
	
	/**
    * 工单类型
    * @return java.lang.String
    */
	public void setWorktype(java.lang.String worktype) {
	   this.worktype = worktype;
	}
	
	
    /**
    * 标准作业计划编号
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * 标准作业计划编号
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * 是否使用标准作业计划
    * @return java.lang.String
    */
	public java.lang.String getUsejpseq() {
		if(this.usejpseq==null || this.usejpseq.length()<=0)
	  		return null;
	  	else
	  		return this.usejpseq;
	}
	
	/**
    * 是否使用标准作业计划
    * @return java.lang.String
    */
	public void setUsejpseq(java.lang.String usejpseq) {
	   this.usejpseq = usejpseq;
	}
	
	
    /**
    * 下一个到日期
    * @return java.util.Date
    */
	public java.util.Date getNextdate() {
	  		return this.nextdate;
	}
	
	/**
    * 下一个到日期
    * @return java.util.Date
    */
	public void setNextdate(java.util.Date nextdate) {
	   this.nextdate = nextdate;
	}
	
	
    /**
    * 主管人
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * 主管人
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
	
	
    /**
    * 班组
    * @return java.lang.String
    */
	public java.lang.String getCrewid() {
		if(this.crewid==null || this.crewid.length()<=0)
	  		return null;
	  	else
	  		return this.crewid;
	}
	
	/**
    * 班组
    * @return java.lang.String
    */
	public void setCrewid(java.lang.String crewid) {
	   this.crewid = crewid;
	}
	
	
    /**
    * 是否启用工作流
    * @return java.lang.String
    */
	public java.lang.String getAutowf() {
		if(this.autowf==null || this.autowf.length()<=0)
	  		return null;
	  	else
	  		return this.autowf;
	}
	
	/**
    * 是否启用工作流
    * @return java.lang.String
    */
	public void setAutowf(java.lang.String autowf) {
	   this.autowf = autowf;
	}
	
	
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getWostatus() {
		if(this.wostatus==null || this.wostatus.length()<=0)
	  		return null;
	  	else
	  		return this.wostatus;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setWostatus(java.lang.String wostatus) {
	   this.wostatus = wostatus;
	}
	
	
    /**
    * 需要停机
    * @return java.lang.String
    */
	public java.lang.String getEqdown() {
		if(this.eqdown==null || this.eqdown.length()<=0)
	  		return null;
	  	else
	  		return this.eqdown;
	}
	
	/**
    * 需要停机
    * @return java.lang.String
    */
	public void setEqdown(java.lang.String eqdown) {
	   this.eqdown = eqdown;
	}
	
	
    /**
    * 父级
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父级
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 有子级？
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * 有子级？
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * 以频率单位为计的频率，30天、1月
    * @return java.lang.String
    */
	public java.lang.String getUsefrequency() {
		if(this.usefrequency==null || this.usefrequency.length()<=0)
	  		return null;
	  	else
	  		return this.usefrequency;
	}
	
	/**
    * 以频率单位为计的频率，30天、1月
    * @return java.lang.String
    */
	public void setUsefrequency(java.lang.String usefrequency) {
	   this.usefrequency = usefrequency;
	}
	
	
    /**
    * 定义预防性维护需要的提前天数
    * @return java.lang.Long
    */
	public java.lang.Long getLeadtime() {
	  		return this.leadtime;
	}
	
	/**
    * 定义预防性维护需要的提前天数
    * @return java.lang.Long
    */
	public void setLeadtime(java.lang.Long leadtime) {
	   this.leadtime = leadtime;
	}
	
	
    /**
    * 是否调整下一个到期日
    * @return java.lang.String
    */
	public java.lang.String getAdjnextdue() {
		if(this.adjnextdue==null || this.adjnextdue.length()<=0)
	  		return null;
	  	else
	  		return this.adjnextdue;
	}
	
	/**
    * 是否调整下一个到期日
    * @return java.lang.String
    */
	public void setAdjnextdue(java.lang.String adjnextdue) {
	   this.adjnextdue = adjnextdue;
	}
	
	
}