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
    * �����˵�ģʽ
    * @return java.lang.String
    */
	public java.lang.String getNavmenutype() {
		if(this.navmenutype==null || this.navmenutype.length()<=0)
	  		return null;
	  	else
	  		return this.navmenutype;
	}
	
	/**
    * �����˵�ģʽ
    * @return java.lang.String
    */
	public void setNavmenutype(java.lang.String navmenutype) {
	   this.navmenutype = navmenutype;
	}
	
	
    /**
    * ����ί����
    * @return java.lang.String
    */
	public java.lang.String getConsign() {
		if(this.consign==null || this.consign.length()<=0)
	  		return null;
	  	else
	  		return this.consign;
	}
	
	/**
    * ����ί����
    * @return java.lang.String
    */
	public void setConsign(java.lang.String consign) {
	   this.consign = consign;
	}
	
	
    /**
    * ί�п�ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getConsigsdate() {
	  		return this.consigsdate;
	}
	
	/**
    * ί�п�ʼʱ��
    * @return java.util.Date
    */
	public void setConsigsdate(java.util.Date consigsdate) {
	   this.consigsdate = consigsdate;
	}
	
	
    /**
    * ί�н���ʱ��
    * @return java.util.Date
    */
	public java.util.Date getConsigedate() {
	  		return this.consigedate;
	}
	
	/**
    * ί�н���ʱ��
    * @return java.util.Date
    */
	public void setConsigedate(java.util.Date consigedate) {
	   this.consigedate = consigedate;
	}
	
	
    /**
    * Ĭ���½���¼�ص�
    * @return java.lang.String
    */
	public java.lang.String getSitenum() {
		if(this.sitenum==null || this.sitenum.length()<=0)
	  		return null;
	  	else
	  		return this.sitenum;
	}
	
	/**
    * Ĭ���½���¼�ص�
    * @return java.lang.String
    */
	public void setSitenum(java.lang.String sitenum) {
	   this.sitenum = sitenum;
	}
	
	
    /**
    * ������֯����
    * @return java.lang.String
    */
	public java.lang.String getCorpnum() {
		if(this.corpnum==null || this.corpnum.length()<=0)
	  		return null;
	  	else
	  		return this.corpnum;
	}
	
	/**
    * ������֯����
    * @return java.lang.String
    */
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getLaborname() {
		if(this.laborname==null || this.laborname.length()<=0)
	  		return null;
	  	else
	  		return this.laborname;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setLaborname(java.lang.String laborname) {
	   this.laborname = laborname;
	}
	
	
    /**
    * ��Ա
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * ��Ա
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * רҵ
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * רҵ
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getCrewid() {
		if(this.crewid==null || this.crewid.length()<=0)
	  		return null;
	  	else
	  		return this.crewid;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setCrewid(java.lang.String crewid) {
	   this.crewid = crewid;
	}
	
	
    /**
    * ְ��
    * @return java.lang.String
    */
	public java.lang.String getJobcode() {
		if(this.jobcode==null || this.jobcode.length()<=0)
	  		return null;
	  	else
	  		return this.jobcode;
	}
	
	/**
    * ְ��
    * @return java.lang.String
    */
	public void setJobcode(java.lang.String jobcode) {
	   this.jobcode = jobcode;
	}
	
	
    /**
    * ��Ӷ��ʽ
    * @return java.lang.String
    */
	public java.lang.String getEmployeetype() {
		if(this.employeetype==null || this.employeetype.length()<=0)
	  		return null;
	  	else
	  		return this.employeetype;
	}
	
	/**
    * ��Ӷ��ʽ
    * @return java.lang.String
    */
	public void setEmployeetype(java.lang.String employeetype) {
	   this.employeetype = employeetype;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getCalnum() {
		if(this.calnum==null || this.calnum.length()<=0)
	  		return null;
	  	else
	  		return this.calnum;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setCalnum(java.lang.String calnum) {
	   this.calnum = calnum;
	}
	
	
    /**
    * ���
    * @return java.lang.String
    */
	public java.lang.String getShiftnum() {
		if(this.shiftnum==null || this.shiftnum.length()<=0)
	  		return null;
	  	else
	  		return this.shiftnum;
	}
	
	/**
    * ���
    * @return java.lang.String
    */
	public void setShiftnum(java.lang.String shiftnum) {
	   this.shiftnum = shiftnum;
	}
	
	
    /**
    * ��Ч
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * ��Ч
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getEmail() {
		if(this.email==null || this.email.length()<=0)
	  		return null;
	  	else
	  		return this.email;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setEmail(java.lang.String email) {
	   this.email = email;
	}
	
	
    /**
    * ������ַ
    * @return java.lang.String
    */
	public java.lang.String getWkaddress() {
		if(this.wkaddress==null || this.wkaddress.length()<=0)
	  		return null;
	  	else
	  		return this.wkaddress;
	}
	
	/**
    * ������ַ
    * @return java.lang.String
    */
	public void setWkaddress(java.lang.String wkaddress) {
	   this.wkaddress = wkaddress;
	}
	
	
    /**
    * �ʱ�
    * @return java.lang.String
    */
	public java.lang.String getPostnum() {
		if(this.postnum==null || this.postnum.length()<=0)
	  		return null;
	  	else
	  		return this.postnum;
	}
	
	/**
    * �ʱ�
    * @return java.lang.String
    */
	public void setPostnum(java.lang.String postnum) {
	   this.postnum = postnum;
	}
	
	
    /**
    * ��ͥסַ
    * @return java.lang.String
    */
	public java.lang.String getHomeaddress() {
		if(this.homeaddress==null || this.homeaddress.length()<=0)
	  		return null;
	  	else
	  		return this.homeaddress;
	}
	
	/**
    * ��ͥסַ
    * @return java.lang.String
    */
	public void setHomeaddress(java.lang.String homeaddress) {
	   this.homeaddress = homeaddress;
	}
	
	
    /**
    * �칫�绰
    * @return java.lang.String
    */
	public java.lang.String getPhonenum() {
		if(this.phonenum==null || this.phonenum.length()<=0)
	  		return null;
	  	else
	  		return this.phonenum;
	}
	
	/**
    * �칫�绰
    * @return java.lang.String
    */
	public void setPhonenum(java.lang.String phonenum) {
	   this.phonenum = phonenum;
	}
	
	
    /**
    * �ƶ��绰
    * @return java.lang.String
    */
	public java.lang.String getMphone() {
		if(this.mphone==null || this.mphone.length()<=0)
	  		return null;
	  	else
	  		return this.mphone;
	}
	
	/**
    * �ƶ��绰
    * @return java.lang.String
    */
	public void setMphone(java.lang.String mphone) {
	   this.mphone = mphone;
	}
	
	
    /**
    * ��ͥ�绰
    * @return java.lang.String
    */
	public java.lang.String getHomephone() {
		if(this.homephone==null || this.homephone.length()<=0)
	  		return null;
	  	else
	  		return this.homephone;
	}
	
	/**
    * ��ͥ�绰
    * @return java.lang.String
    */
	public void setHomephone(java.lang.String homephone) {
	   this.homephone = homephone;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getPayrate() {
	  		return this.payrate;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setPayrate(java.lang.Double payrate) {
	   this.payrate = payrate;
	}
	
	
    /**
    * �Ӱ�ϵ��
    * @return java.lang.Double
    */
	public java.lang.Double getOtscale() {
	  		return this.otscale;
	}
	
	/**
    * �Ӱ�ϵ��
    * @return java.lang.Double
    */
	public void setOtscale(java.lang.Double otscale) {
	   this.otscale = otscale;
	}
	
	
    /**
    * ����
    * @return java.util.Date
    */
	public java.util.Date getBirthdate() {
	  		return this.birthdate;
	}
	
	/**
    * ����
    * @return java.util.Date
    */
	public void setBirthdate(java.util.Date birthdate) {
	   this.birthdate = birthdate;
	}
	
	
    /**
    * ��Ӷ����
    * @return java.util.Date
    */
	public java.util.Date getHiredate() {
	  		return this.hiredate;
	}
	
	/**
    * ��Ӷ����
    * @return java.util.Date
    */
	public void setHiredate(java.util.Date hiredate) {
	   this.hiredate = hiredate;
	}
	
	
    /**
    * �Ƿ��ⲿ
    * @return java.lang.String
    */
	public java.lang.String getOutside() {
		if(this.outside==null || this.outside.length()<=0)
	  		return null;
	  	else
	  		return this.outside;
	}
	
	/**
    * �Ƿ��ⲿ
    * @return java.lang.String
    */
	public void setOutside(java.lang.String outside) {
	   this.outside = outside;
	}
	
	
    /**
    * �ֶ�GLACCOUNT
    * @return java.lang.String
    */
	public java.lang.String getGlaccount() {
		if(this.glaccount==null || this.glaccount.length()<=0)
	  		return null;
	  	else
	  		return this.glaccount;
	}
	
	/**
    * �ֶ�GLACCOUNT
    * @return java.lang.String
    */
	public void setGlaccount(java.lang.String glaccount) {
	   this.glaccount = glaccount;
	}
	
	
    /**
    * ��Ӧ��
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * ��Ӧ��
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
	}
	
	
    /**
    * Ĭ�ϲֿ�
    * @return java.lang.String
    */
	public java.lang.String getDefaultstoreloc() {
		if(this.defaultstoreloc==null || this.defaultstoreloc.length()<=0)
	  		return null;
	  	else
	  		return this.defaultstoreloc;
	}
	
	/**
    * Ĭ�ϲֿ�
    * @return java.lang.String
    */
	public void setDefaultstoreloc(java.lang.String defaultstoreloc) {
	   this.defaultstoreloc = defaultstoreloc;
	}
	
	
    /**
    * �Ƿ���Ȩ���в�������
    * @return java.lang.String
    */
	public java.lang.String getAuthalldept() {
		if(this.authalldept==null || this.authalldept.length()<=0)
	  		return null;
	  	else
	  		return this.authalldept;
	}
	
	/**
    * �Ƿ���Ȩ���в�������
    * @return java.lang.String
    */
	public void setAuthalldept(java.lang.String authalldept) {
	   this.authalldept = authalldept;
	}
	
	
    /**
    * ��Ȩ�����б�
    * @return java.lang.String
    */
	public java.lang.String getAuthdept() {
		if(this.authdept==null || this.authdept.length()<=0)
	  		return null;
	  	else
	  		return this.authdept;
	}
	
	/**
    * ��Ȩ�����б�
    * @return java.lang.String
    */
	public void setAuthdept(java.lang.String authdept) {
	   this.authdept = authdept;
	}
	
	
    /**
    * ��ʾ��Ϣ����
    * @return java.lang.String
    */
	public java.lang.String getShowmind() {
		if(this.showmind==null || this.showmind.length()<=0)
	  		return null;
	  	else
	  		return this.showmind;
	}
	
	/**
    * ��ʾ��Ϣ����
    * @return java.lang.String
    */
	public void setShowmind(java.lang.String showmind) {
	   this.showmind = showmind;
	}
	
	
    /**
    * ��Ա��Ĭ��λ��
    * @return java.lang.String
    */
	public java.lang.String getDefaultloc() {
		if(this.defaultloc==null || this.defaultloc.length()<=0)
	  		return null;
	  	else
	  		return this.defaultloc;
	}
	
	/**
    * ��Ա��Ĭ��λ��
    * @return java.lang.String
    */
	public void setDefaultloc(java.lang.String defaultloc) {
	   this.defaultloc = defaultloc;
	}
	
	
}