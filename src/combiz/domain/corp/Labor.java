package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Labor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String navmenutype;
     private java.lang.String consign;
     private java.util.Date consigsdate;
     private java.util.Date consigedate;
     private java.lang.String sitenum;
     private java.lang.String corpnum;
     private java.lang.String laborname;
     private java.lang.String labornum;
     private java.lang.String craft;
     private java.lang.String deptnum;
     private java.lang.String crewid;
     private java.lang.String jobcode;
     private java.lang.String employeetype;
     private java.lang.String calnum;
     private java.lang.String shiftnum;
     private java.lang.String enabled;
     private java.lang.String email;
     private java.lang.String wkaddress;
     private java.lang.String postnum;
     private java.lang.String homeaddress;
     private java.lang.String phonenum;
     private java.lang.String mphone;
     private java.lang.String homephone;
     private java.lang.Double payrate;
     private java.lang.Double otscale;
     private java.util.Date birthdate;
     private java.util.Date hiredate;
     private java.lang.String outside;
     private java.lang.String glaccount;
     private java.lang.String vendor;
     private java.lang.String supervisor;
     private java.lang.String defaultstoreloc;
     private java.lang.String authalldept;
     private java.lang.String authdept;
     private java.lang.String showmind;
     private java.lang.String defaultloc;
     
    /** default constructor */
    public Labor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 导航菜单模式
    * @return java.lang.String
    */
	public java.lang.String getNavmenutype() {
		if(this.navmenutype==null || this.navmenutype.length()<=0)
	  		return null;
	  	else
	  		return this.navmenutype;
	}
	
	/**
    * 导航菜单模式
    * @return java.lang.String
    */
	public void setNavmenutype(java.lang.String navmenutype) {
	   this.navmenutype = navmenutype;
	}
	
	
    /**
    * 流程委托人
    * @return java.lang.String
    */
	public java.lang.String getConsign() {
		if(this.consign==null || this.consign.length()<=0)
	  		return null;
	  	else
	  		return this.consign;
	}
	
	/**
    * 流程委托人
    * @return java.lang.String
    */
	public void setConsign(java.lang.String consign) {
	   this.consign = consign;
	}
	
	
    /**
    * 委托开始时间
    * @return java.util.Date
    */
	public java.util.Date getConsigsdate() {
	  		return this.consigsdate;
	}
	
	/**
    * 委托开始时间
    * @return java.util.Date
    */
	public void setConsigsdate(java.util.Date consigsdate) {
	   this.consigsdate = consigsdate;
	}
	
	
    /**
    * 委托结束时间
    * @return java.util.Date
    */
	public java.util.Date getConsigedate() {
	  		return this.consigedate;
	}
	
	/**
    * 委托结束时间
    * @return java.util.Date
    */
	public void setConsigedate(java.util.Date consigedate) {
	   this.consigedate = consigedate;
	}
	
	
    /**
    * 默认新建记录地点
    * @return java.lang.String
    */
	public java.lang.String getSitenum() {
		if(this.sitenum==null || this.sitenum.length()<=0)
	  		return null;
	  	else
	  		return this.sitenum;
	}
	
	/**
    * 默认新建记录地点
    * @return java.lang.String
    */
	public void setSitenum(java.lang.String sitenum) {
	   this.sitenum = sitenum;
	}
	
	
    /**
    * 所在组织机构
    * @return java.lang.String
    */
	public java.lang.String getCorpnum() {
		if(this.corpnum==null || this.corpnum.length()<=0)
	  		return null;
	  	else
	  		return this.corpnum;
	}
	
	/**
    * 所在组织机构
    * @return java.lang.String
    */
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	
	
    /**
    * 姓名
    * @return java.lang.String
    */
	public java.lang.String getLaborname() {
		if(this.laborname==null || this.laborname.length()<=0)
	  		return null;
	  	else
	  		return this.laborname;
	}
	
	/**
    * 姓名
    * @return java.lang.String
    */
	public void setLaborname(java.lang.String laborname) {
	   this.laborname = laborname;
	}
	
	
    /**
    * 人员
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * 人员
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * 专业
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * 专业
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	
	
    /**
    * 部门
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * 部门
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
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
    * 职务
    * @return java.lang.String
    */
	public java.lang.String getJobcode() {
		if(this.jobcode==null || this.jobcode.length()<=0)
	  		return null;
	  	else
	  		return this.jobcode;
	}
	
	/**
    * 职务
    * @return java.lang.String
    */
	public void setJobcode(java.lang.String jobcode) {
	   this.jobcode = jobcode;
	}
	
	
    /**
    * 雇佣方式
    * @return java.lang.String
    */
	public java.lang.String getEmployeetype() {
		if(this.employeetype==null || this.employeetype.length()<=0)
	  		return null;
	  	else
	  		return this.employeetype;
	}
	
	/**
    * 雇佣方式
    * @return java.lang.String
    */
	public void setEmployeetype(java.lang.String employeetype) {
	   this.employeetype = employeetype;
	}
	
	
    /**
    * 日历
    * @return java.lang.String
    */
	public java.lang.String getCalnum() {
		if(this.calnum==null || this.calnum.length()<=0)
	  		return null;
	  	else
	  		return this.calnum;
	}
	
	/**
    * 日历
    * @return java.lang.String
    */
	public void setCalnum(java.lang.String calnum) {
	   this.calnum = calnum;
	}
	
	
    /**
    * 班次
    * @return java.lang.String
    */
	public java.lang.String getShiftnum() {
		if(this.shiftnum==null || this.shiftnum.length()<=0)
	  		return null;
	  	else
	  		return this.shiftnum;
	}
	
	/**
    * 班次
    * @return java.lang.String
    */
	public void setShiftnum(java.lang.String shiftnum) {
	   this.shiftnum = shiftnum;
	}
	
	
    /**
    * 有效
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * 有效
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * 电子邮箱
    * @return java.lang.String
    */
	public java.lang.String getEmail() {
		if(this.email==null || this.email.length()<=0)
	  		return null;
	  	else
	  		return this.email;
	}
	
	/**
    * 电子邮箱
    * @return java.lang.String
    */
	public void setEmail(java.lang.String email) {
	   this.email = email;
	}
	
	
    /**
    * 工作地址
    * @return java.lang.String
    */
	public java.lang.String getWkaddress() {
		if(this.wkaddress==null || this.wkaddress.length()<=0)
	  		return null;
	  	else
	  		return this.wkaddress;
	}
	
	/**
    * 工作地址
    * @return java.lang.String
    */
	public void setWkaddress(java.lang.String wkaddress) {
	   this.wkaddress = wkaddress;
	}
	
	
    /**
    * 邮编
    * @return java.lang.String
    */
	public java.lang.String getPostnum() {
		if(this.postnum==null || this.postnum.length()<=0)
	  		return null;
	  	else
	  		return this.postnum;
	}
	
	/**
    * 邮编
    * @return java.lang.String
    */
	public void setPostnum(java.lang.String postnum) {
	   this.postnum = postnum;
	}
	
	
    /**
    * 家庭住址
    * @return java.lang.String
    */
	public java.lang.String getHomeaddress() {
		if(this.homeaddress==null || this.homeaddress.length()<=0)
	  		return null;
	  	else
	  		return this.homeaddress;
	}
	
	/**
    * 家庭住址
    * @return java.lang.String
    */
	public void setHomeaddress(java.lang.String homeaddress) {
	   this.homeaddress = homeaddress;
	}
	
	
    /**
    * 办公电话
    * @return java.lang.String
    */
	public java.lang.String getPhonenum() {
		if(this.phonenum==null || this.phonenum.length()<=0)
	  		return null;
	  	else
	  		return this.phonenum;
	}
	
	/**
    * 办公电话
    * @return java.lang.String
    */
	public void setPhonenum(java.lang.String phonenum) {
	   this.phonenum = phonenum;
	}
	
	
    /**
    * 移动电话
    * @return java.lang.String
    */
	public java.lang.String getMphone() {
		if(this.mphone==null || this.mphone.length()<=0)
	  		return null;
	  	else
	  		return this.mphone;
	}
	
	/**
    * 移动电话
    * @return java.lang.String
    */
	public void setMphone(java.lang.String mphone) {
	   this.mphone = mphone;
	}
	
	
    /**
    * 家庭电话
    * @return java.lang.String
    */
	public java.lang.String getHomephone() {
		if(this.homephone==null || this.homephone.length()<=0)
	  		return null;
	  	else
	  		return this.homephone;
	}
	
	/**
    * 家庭电话
    * @return java.lang.String
    */
	public void setHomephone(java.lang.String homephone) {
	   this.homephone = homephone;
	}
	
	
    /**
    * 费率
    * @return java.lang.Double
    */
	public java.lang.Double getPayrate() {
	  		return this.payrate;
	}
	
	/**
    * 费率
    * @return java.lang.Double
    */
	public void setPayrate(java.lang.Double payrate) {
	   this.payrate = payrate;
	}
	
	
    /**
    * 加班系数
    * @return java.lang.Double
    */
	public java.lang.Double getOtscale() {
	  		return this.otscale;
	}
	
	/**
    * 加班系数
    * @return java.lang.Double
    */
	public void setOtscale(java.lang.Double otscale) {
	   this.otscale = otscale;
	}
	
	
    /**
    * 生日
    * @return java.util.Date
    */
	public java.util.Date getBirthdate() {
	  		return this.birthdate;
	}
	
	/**
    * 生日
    * @return java.util.Date
    */
	public void setBirthdate(java.util.Date birthdate) {
	   this.birthdate = birthdate;
	}
	
	
    /**
    * 雇佣日期
    * @return java.util.Date
    */
	public java.util.Date getHiredate() {
	  		return this.hiredate;
	}
	
	/**
    * 雇佣日期
    * @return java.util.Date
    */
	public void setHiredate(java.util.Date hiredate) {
	   this.hiredate = hiredate;
	}
	
	
    /**
    * 是否外部
    * @return java.lang.String
    */
	public java.lang.String getOutside() {
		if(this.outside==null || this.outside.length()<=0)
	  		return null;
	  	else
	  		return this.outside;
	}
	
	/**
    * 是否外部
    * @return java.lang.String
    */
	public void setOutside(java.lang.String outside) {
	   this.outside = outside;
	}
	
	
    /**
    * 字段GLACCOUNT
    * @return java.lang.String
    */
	public java.lang.String getGlaccount() {
		if(this.glaccount==null || this.glaccount.length()<=0)
	  		return null;
	  	else
	  		return this.glaccount;
	}
	
	/**
    * 字段GLACCOUNT
    * @return java.lang.String
    */
	public void setGlaccount(java.lang.String glaccount) {
	   this.glaccount = glaccount;
	}
	
	
    /**
    * 供应商
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * 供应商
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
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
    * 默认仓库
    * @return java.lang.String
    */
	public java.lang.String getDefaultstoreloc() {
		if(this.defaultstoreloc==null || this.defaultstoreloc.length()<=0)
	  		return null;
	  	else
	  		return this.defaultstoreloc;
	}
	
	/**
    * 默认仓库
    * @return java.lang.String
    */
	public void setDefaultstoreloc(java.lang.String defaultstoreloc) {
	   this.defaultstoreloc = defaultstoreloc;
	}
	
	
    /**
    * 是否授权所有部门数据
    * @return java.lang.String
    */
	public java.lang.String getAuthalldept() {
		if(this.authalldept==null || this.authalldept.length()<=0)
	  		return null;
	  	else
	  		return this.authalldept;
	}
	
	/**
    * 是否授权所有部门数据
    * @return java.lang.String
    */
	public void setAuthalldept(java.lang.String authalldept) {
	   this.authalldept = authalldept;
	}
	
	
    /**
    * 授权部门列表
    * @return java.lang.String
    */
	public java.lang.String getAuthdept() {
		if(this.authdept==null || this.authdept.length()<=0)
	  		return null;
	  	else
	  		return this.authdept;
	}
	
	/**
    * 授权部门列表
    * @return java.lang.String
    */
	public void setAuthdept(java.lang.String authdept) {
	   this.authdept = authdept;
	}
	
	
    /**
    * 显示消息提醒
    * @return java.lang.String
    */
	public java.lang.String getShowmind() {
		if(this.showmind==null || this.showmind.length()<=0)
	  		return null;
	  	else
	  		return this.showmind;
	}
	
	/**
    * 显示消息提醒
    * @return java.lang.String
    */
	public void setShowmind(java.lang.String showmind) {
	   this.showmind = showmind;
	}
	
	
    /**
    * 人员的默认位置
    * @return java.lang.String
    */
	public java.lang.String getDefaultloc() {
		if(this.defaultloc==null || this.defaultloc.length()<=0)
	  		return null;
	  	else
	  		return this.defaultloc;
	}
	
	/**
    * 人员的默认位置
    * @return java.lang.String
    */
	public void setDefaultloc(java.lang.String defaultloc) {
	   this.defaultloc = defaultloc;
	}
	
	
}