package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Workorder extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String prjnum;
     private java.lang.String tasknum;
     private java.lang.String location;
     private java.lang.String eqnum;
     private java.lang.String parent;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String worktype;
     private java.lang.String faillevel;
     private java.lang.String failtype;
     private java.lang.String reportedby;
     private java.util.Date reportdate;
     private java.lang.String phone;
     private java.lang.String digoutby;
     private java.util.Date didoutdate;
     private java.lang.String craft;
     private java.lang.String crewid;
     private java.lang.String supervisor;
     private java.lang.String jpnum;
     private java.lang.String pmnum;
     private java.util.Date faildate;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.Double estlabhrs;
     private java.lang.Double estlabcost;
     private java.lang.Double estmatcost;
     private java.lang.Double esttoolcost;
     private java.lang.Double actlabhrs;
     private java.lang.Double actmatcost;
     private java.lang.Double actlabcost;
     private java.lang.Double acttoolcost;
     private java.lang.String haschildren;
     private java.lang.Double wopriority;
     private java.lang.String failurecode;
     private java.lang.String problemcode;
     private java.lang.String calnum;
     private java.lang.String eqdown;
     private java.util.Date targstart;
     private java.util.Date targcomp;
     private java.util.Date schedstart;
     private java.lang.Long estdur;
     private java.util.Date schedfinish;
     private java.util.Date actstart;
     private java.util.Date actfinish;
     private java.lang.Long remdur;
     private java.lang.Double estatapprlabhrs;
     private java.lang.Double estatapprlabcost;
     private java.lang.Double estatapprmatcost;
     private java.lang.Double estatapprtoolcost;
     private java.lang.String wosequence;
     private java.lang.String hasfollowupwork;
     private java.lang.String followupfromwonum;
     private java.lang.Double measurementvalue;
     private java.util.Date measuredate;
     private java.lang.String pointnum;
     private java.lang.String wonum;
     private java.lang.String description;
     private java.lang.String assetnum;
     private java.lang.String project;
     
    /** default constructor */
    public Workorder(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 项目编号
    * @return java.lang.String
    */
	public java.lang.String getPrjnum() {
		if(this.prjnum==null || this.prjnum.length()<=0)
	  		return null;
	  	else
	  		return this.prjnum;
	}
	
	/**
    * 项目编号
    * @return java.lang.String
    */
	public void setPrjnum(java.lang.String prjnum) {
	   this.prjnum = prjnum;
	}
	
	
    /**
    * 任务编号
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * 任务编号
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * LOCATION
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * LOCATION
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * EQNUM
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * EQNUM
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * PARENT
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * PARENT
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * STATUS
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * STATUS
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * STATUSDATE
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * STATUSDATE
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * WORKTYPE
    * @return java.lang.String
    */
	public java.lang.String getWorktype() {
		if(this.worktype==null || this.worktype.length()<=0)
	  		return null;
	  	else
	  		return this.worktype;
	}
	
	/**
    * WORKTYPE
    * @return java.lang.String
    */
	public void setWorktype(java.lang.String worktype) {
	   this.worktype = worktype;
	}
	
	
    /**
    * FAILLEVEL
    * @return java.lang.String
    */
	public java.lang.String getFaillevel() {
		if(this.faillevel==null || this.faillevel.length()<=0)
	  		return null;
	  	else
	  		return this.faillevel;
	}
	
	/**
    * FAILLEVEL
    * @return java.lang.String
    */
	public void setFaillevel(java.lang.String faillevel) {
	   this.faillevel = faillevel;
	}
	
	
    /**
    * FAILTYPE
    * @return java.lang.String
    */
	public java.lang.String getFailtype() {
		if(this.failtype==null || this.failtype.length()<=0)
	  		return null;
	  	else
	  		return this.failtype;
	}
	
	/**
    * FAILTYPE
    * @return java.lang.String
    */
	public void setFailtype(java.lang.String failtype) {
	   this.failtype = failtype;
	}
	
	
    /**
    * REPORTEDBY
    * @return java.lang.String
    */
	public java.lang.String getReportedby() {
		if(this.reportedby==null || this.reportedby.length()<=0)
	  		return null;
	  	else
	  		return this.reportedby;
	}
	
	/**
    * REPORTEDBY
    * @return java.lang.String
    */
	public void setReportedby(java.lang.String reportedby) {
	   this.reportedby = reportedby;
	}
	
	
    /**
    * REPORTDATE
    * @return java.util.Date
    */
	public java.util.Date getReportdate() {
	  		return this.reportdate;
	}
	
	/**
    * REPORTDATE
    * @return java.util.Date
    */
	public void setReportdate(java.util.Date reportdate) {
	   this.reportdate = reportdate;
	}
	
	
    /**
    * PHONE
    * @return java.lang.String
    */
	public java.lang.String getPhone() {
		if(this.phone==null || this.phone.length()<=0)
	  		return null;
	  	else
	  		return this.phone;
	}
	
	/**
    * PHONE
    * @return java.lang.String
    */
	public void setPhone(java.lang.String phone) {
	   this.phone = phone;
	}
	
	
    /**
    * DIGOUTBY
    * @return java.lang.String
    */
	public java.lang.String getDigoutby() {
		if(this.digoutby==null || this.digoutby.length()<=0)
	  		return null;
	  	else
	  		return this.digoutby;
	}
	
	/**
    * DIGOUTBY
    * @return java.lang.String
    */
	public void setDigoutby(java.lang.String digoutby) {
	   this.digoutby = digoutby;
	}
	
	
    /**
    * DIDOUTDATE
    * @return java.util.Date
    */
	public java.util.Date getDidoutdate() {
	  		return this.didoutdate;
	}
	
	/**
    * DIDOUTDATE
    * @return java.util.Date
    */
	public void setDidoutdate(java.util.Date didoutdate) {
	   this.didoutdate = didoutdate;
	}
	
	
    /**
    * CRAFT
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * CRAFT
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	
	
    /**
    * CREWID
    * @return java.lang.String
    */
	public java.lang.String getCrewid() {
		if(this.crewid==null || this.crewid.length()<=0)
	  		return null;
	  	else
	  		return this.crewid;
	}
	
	/**
    * CREWID
    * @return java.lang.String
    */
	public void setCrewid(java.lang.String crewid) {
	   this.crewid = crewid;
	}
	
	
    /**
    * SUPERVISOR
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * SUPERVISOR
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
	
	
    /**
    * JPNUM
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * JPNUM
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * PMNUM
    * @return java.lang.String
    */
	public java.lang.String getPmnum() {
		if(this.pmnum==null || this.pmnum.length()<=0)
	  		return null;
	  	else
	  		return this.pmnum;
	}
	
	/**
    * PMNUM
    * @return java.lang.String
    */
	public void setPmnum(java.lang.String pmnum) {
	   this.pmnum = pmnum;
	}
	
	
    /**
    * FAILDATE
    * @return java.util.Date
    */
	public java.util.Date getFaildate() {
	  		return this.faildate;
	}
	
	/**
    * FAILDATE
    * @return java.util.Date
    */
	public void setFaildate(java.util.Date faildate) {
	   this.faildate = faildate;
	}
	
	
    /**
    * CHANGEBY
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * CHANGEBY
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * CHANGEDATE
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * CHANGEDATE
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * ESTLABHRS
    * @return java.lang.Double
    */
	public java.lang.Double getEstlabhrs() {
	  		return this.estlabhrs;
	}
	
	/**
    * ESTLABHRS
    * @return java.lang.Double
    */
	public void setEstlabhrs(java.lang.Double estlabhrs) {
	   this.estlabhrs = estlabhrs;
	}
	
	
    /**
    * ESTLABCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEstlabcost() {
	  		return this.estlabcost;
	}
	
	/**
    * ESTLABCOST
    * @return java.lang.Double
    */
	public void setEstlabcost(java.lang.Double estlabcost) {
	   this.estlabcost = estlabcost;
	}
	
	
    /**
    * ESTMATCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEstmatcost() {
	  		return this.estmatcost;
	}
	
	/**
    * ESTMATCOST
    * @return java.lang.Double
    */
	public void setEstmatcost(java.lang.Double estmatcost) {
	   this.estmatcost = estmatcost;
	}
	
	
    /**
    * ESTTOOLCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEsttoolcost() {
	  		return this.esttoolcost;
	}
	
	/**
    * ESTTOOLCOST
    * @return java.lang.Double
    */
	public void setEsttoolcost(java.lang.Double esttoolcost) {
	   this.esttoolcost = esttoolcost;
	}
	
	
    /**
    * ACTLABHRS
    * @return java.lang.Double
    */
	public java.lang.Double getActlabhrs() {
	  		return this.actlabhrs;
	}
	
	/**
    * ACTLABHRS
    * @return java.lang.Double
    */
	public void setActlabhrs(java.lang.Double actlabhrs) {
	   this.actlabhrs = actlabhrs;
	}
	
	
    /**
    * ACTMATCOST
    * @return java.lang.Double
    */
	public java.lang.Double getActmatcost() {
	  		return this.actmatcost;
	}
	
	/**
    * ACTMATCOST
    * @return java.lang.Double
    */
	public void setActmatcost(java.lang.Double actmatcost) {
	   this.actmatcost = actmatcost;
	}
	
	
    /**
    * ACTLABCOST
    * @return java.lang.Double
    */
	public java.lang.Double getActlabcost() {
	  		return this.actlabcost;
	}
	
	/**
    * ACTLABCOST
    * @return java.lang.Double
    */
	public void setActlabcost(java.lang.Double actlabcost) {
	   this.actlabcost = actlabcost;
	}
	
	
    /**
    * ACTTOOLCOST
    * @return java.lang.Double
    */
	public java.lang.Double getActtoolcost() {
	  		return this.acttoolcost;
	}
	
	/**
    * ACTTOOLCOST
    * @return java.lang.Double
    */
	public void setActtoolcost(java.lang.Double acttoolcost) {
	   this.acttoolcost = acttoolcost;
	}
	
	
    /**
    * HASCHILDREN
    * @return java.lang.String
    */
	public java.lang.String getHaschildren() {
		if(this.haschildren==null || this.haschildren.length()<=0)
	  		return null;
	  	else
	  		return this.haschildren;
	}
	
	/**
    * HASCHILDREN
    * @return java.lang.String
    */
	public void setHaschildren(java.lang.String haschildren) {
	   this.haschildren = haschildren;
	}
	
	
    /**
    * WOPRIORITY
    * @return java.lang.Double
    */
	public java.lang.Double getWopriority() {
	  		return this.wopriority;
	}
	
	/**
    * WOPRIORITY
    * @return java.lang.Double
    */
	public void setWopriority(java.lang.Double wopriority) {
	   this.wopriority = wopriority;
	}
	
	
    /**
    * FAILURECODE
    * @return java.lang.String
    */
	public java.lang.String getFailurecode() {
		if(this.failurecode==null || this.failurecode.length()<=0)
	  		return null;
	  	else
	  		return this.failurecode;
	}
	
	/**
    * FAILURECODE
    * @return java.lang.String
    */
	public void setFailurecode(java.lang.String failurecode) {
	   this.failurecode = failurecode;
	}
	
	
    /**
    * PROBLEMCODE
    * @return java.lang.String
    */
	public java.lang.String getProblemcode() {
		if(this.problemcode==null || this.problemcode.length()<=0)
	  		return null;
	  	else
	  		return this.problemcode;
	}
	
	/**
    * PROBLEMCODE
    * @return java.lang.String
    */
	public void setProblemcode(java.lang.String problemcode) {
	   this.problemcode = problemcode;
	}
	
	
    /**
    * CALNUM
    * @return java.lang.String
    */
	public java.lang.String getCalnum() {
		if(this.calnum==null || this.calnum.length()<=0)
	  		return null;
	  	else
	  		return this.calnum;
	}
	
	/**
    * CALNUM
    * @return java.lang.String
    */
	public void setCalnum(java.lang.String calnum) {
	   this.calnum = calnum;
	}
	
	
    /**
    * EQDOWN
    * @return java.lang.String
    */
	public java.lang.String getEqdown() {
		if(this.eqdown==null || this.eqdown.length()<=0)
	  		return null;
	  	else
	  		return this.eqdown;
	}
	
	/**
    * EQDOWN
    * @return java.lang.String
    */
	public void setEqdown(java.lang.String eqdown) {
	   this.eqdown = eqdown;
	}
	
	
    /**
    * TARGSTART
    * @return java.util.Date
    */
	public java.util.Date getTargstart() {
	  		return this.targstart;
	}
	
	/**
    * TARGSTART
    * @return java.util.Date
    */
	public void setTargstart(java.util.Date targstart) {
	   this.targstart = targstart;
	}
	
	
    /**
    * TARGCOMP
    * @return java.util.Date
    */
	public java.util.Date getTargcomp() {
	  		return this.targcomp;
	}
	
	/**
    * TARGCOMP
    * @return java.util.Date
    */
	public void setTargcomp(java.util.Date targcomp) {
	   this.targcomp = targcomp;
	}
	
	
    /**
    * 计划开始
    * @return java.util.Date
    */
	public java.util.Date getSchedstart() {
	  		return this.schedstart;
	}
	
	/**
    * 计划开始
    * @return java.util.Date
    */
	public void setSchedstart(java.util.Date schedstart) {
	   this.schedstart = schedstart;
	}
	
	
    /**
    * ESTDUR
    * @return java.lang.Long
    */
	public java.lang.Long getEstdur() {
	  		return this.estdur;
	}
	
	/**
    * ESTDUR
    * @return java.lang.Long
    */
	public void setEstdur(java.lang.Long estdur) {
	   this.estdur = estdur;
	}
	
	
    /**
    * SCHEDFINISH
    * @return java.util.Date
    */
	public java.util.Date getSchedfinish() {
	  		return this.schedfinish;
	}
	
	/**
    * SCHEDFINISH
    * @return java.util.Date
    */
	public void setSchedfinish(java.util.Date schedfinish) {
	   this.schedfinish = schedfinish;
	}
	
	
    /**
    * 实际开始
    * @return java.util.Date
    */
	public java.util.Date getActstart() {
	  		return this.actstart;
	}
	
	/**
    * 实际开始
    * @return java.util.Date
    */
	public void setActstart(java.util.Date actstart) {
	   this.actstart = actstart;
	}
	
	
    /**
    * ACTFINISH
    * @return java.util.Date
    */
	public java.util.Date getActfinish() {
	  		return this.actfinish;
	}
	
	/**
    * ACTFINISH
    * @return java.util.Date
    */
	public void setActfinish(java.util.Date actfinish) {
	   this.actfinish = actfinish;
	}
	
	
    /**
    * REMDUR
    * @return java.lang.Long
    */
	public java.lang.Long getRemdur() {
	  		return this.remdur;
	}
	
	/**
    * REMDUR
    * @return java.lang.Long
    */
	public void setRemdur(java.lang.Long remdur) {
	   this.remdur = remdur;
	}
	
	
    /**
    * ESTATAPPRLABHRS
    * @return java.lang.Double
    */
	public java.lang.Double getEstatapprlabhrs() {
	  		return this.estatapprlabhrs;
	}
	
	/**
    * ESTATAPPRLABHRS
    * @return java.lang.Double
    */
	public void setEstatapprlabhrs(java.lang.Double estatapprlabhrs) {
	   this.estatapprlabhrs = estatapprlabhrs;
	}
	
	
    /**
    * ESTATAPPRLABCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEstatapprlabcost() {
	  		return this.estatapprlabcost;
	}
	
	/**
    * ESTATAPPRLABCOST
    * @return java.lang.Double
    */
	public void setEstatapprlabcost(java.lang.Double estatapprlabcost) {
	   this.estatapprlabcost = estatapprlabcost;
	}
	
	
    /**
    * ESTATAPPRMATCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEstatapprmatcost() {
	  		return this.estatapprmatcost;
	}
	
	/**
    * ESTATAPPRMATCOST
    * @return java.lang.Double
    */
	public void setEstatapprmatcost(java.lang.Double estatapprmatcost) {
	   this.estatapprmatcost = estatapprmatcost;
	}
	
	
    /**
    * ESTATAPPRTOOLCOST
    * @return java.lang.Double
    */
	public java.lang.Double getEstatapprtoolcost() {
	  		return this.estatapprtoolcost;
	}
	
	/**
    * ESTATAPPRTOOLCOST
    * @return java.lang.Double
    */
	public void setEstatapprtoolcost(java.lang.Double estatapprtoolcost) {
	   this.estatapprtoolcost = estatapprtoolcost;
	}
	
	
    /**
    * WOSEQUENCE
    * @return java.lang.String
    */
	public java.lang.String getWosequence() {
		if(this.wosequence==null || this.wosequence.length()<=0)
	  		return null;
	  	else
	  		return this.wosequence;
	}
	
	/**
    * WOSEQUENCE
    * @return java.lang.String
    */
	public void setWosequence(java.lang.String wosequence) {
	   this.wosequence = wosequence;
	}
	
	
    /**
    * HASFOLLOWUPWORK
    * @return java.lang.String
    */
	public java.lang.String getHasfollowupwork() {
		if(this.hasfollowupwork==null || this.hasfollowupwork.length()<=0)
	  		return null;
	  	else
	  		return this.hasfollowupwork;
	}
	
	/**
    * HASFOLLOWUPWORK
    * @return java.lang.String
    */
	public void setHasfollowupwork(java.lang.String hasfollowupwork) {
	   this.hasfollowupwork = hasfollowupwork;
	}
	
	
    /**
    * FOLLOWUPFROMWONUM
    * @return java.lang.String
    */
	public java.lang.String getFollowupfromwonum() {
		if(this.followupfromwonum==null || this.followupfromwonum.length()<=0)
	  		return null;
	  	else
	  		return this.followupfromwonum;
	}
	
	/**
    * FOLLOWUPFROMWONUM
    * @return java.lang.String
    */
	public void setFollowupfromwonum(java.lang.String followupfromwonum) {
	   this.followupfromwonum = followupfromwonum;
	}
	
	
    /**
    * MEASUREMENTVALUE
    * @return java.lang.Double
    */
	public java.lang.Double getMeasurementvalue() {
	  		return this.measurementvalue;
	}
	
	/**
    * MEASUREMENTVALUE
    * @return java.lang.Double
    */
	public void setMeasurementvalue(java.lang.Double measurementvalue) {
	   this.measurementvalue = measurementvalue;
	}
	
	
    /**
    * MEASUREDATE
    * @return java.util.Date
    */
	public java.util.Date getMeasuredate() {
	  		return this.measuredate;
	}
	
	/**
    * MEASUREDATE
    * @return java.util.Date
    */
	public void setMeasuredate(java.util.Date measuredate) {
	   this.measuredate = measuredate;
	}
	
	
    /**
    * POINTNUM
    * @return java.lang.String
    */
	public java.lang.String getPointnum() {
		if(this.pointnum==null || this.pointnum.length()<=0)
	  		return null;
	  	else
	  		return this.pointnum;
	}
	
	/**
    * POINTNUM
    * @return java.lang.String
    */
	public void setPointnum(java.lang.String pointnum) {
	   this.pointnum = pointnum;
	}
	
	
    /**
    * WONUM
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * WONUM
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
    /**
    * DESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * DESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getAssetnum() {
		if(this.assetnum==null || this.assetnum.length()<=0)
	  		return null;
	  	else
	  		return this.assetnum;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setAssetnum(java.lang.String assetnum) {
	   this.assetnum = assetnum;
	}
	
	
    /**
    * 项目部
    * @return java.lang.String
    */
	public java.lang.String getProject() {
		if(this.project==null || this.project.length()<=0)
	  		return null;
	  	else
	  		return this.project;
	}
	
	/**
    * 项目部
    * @return java.lang.String
    */
	public void setProject(java.lang.String project) {
	   this.project = project;
	}
	
	
}