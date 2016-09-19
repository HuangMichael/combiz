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
    * ����
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * Ԥ����ά�����
    * @return java.lang.String
    */
	public java.lang.String getPmnum() {
		if(this.pmnum==null || this.pmnum.length()<=0)
	  		return null;
	  	else
	  		return this.pmnum;
	}
	
	/**
    * Ԥ����ά�����
    * @return java.lang.String
    */
	public void setPmnum(java.lang.String pmnum) {
	   this.pmnum = pmnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �ʲ����
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �ʲ����
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * �ֿ�
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ֿ�
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
    /**
    * �ϴο�ʼ����
    * @return java.util.Date
    */
	public java.util.Date getLaststartdate() {
	  		return this.laststartdate;
	}
	
	/**
    * �ϴο�ʼ����
    * @return java.util.Date
    */
	public void setLaststartdate(java.util.Date laststartdate) {
	   this.laststartdate = laststartdate;
	}
	
	
    /**
    * ��һ�ο�ʼ����
    * @return java.util.Date
    */
	public java.util.Date getFirstdate() {
	  		return this.firstdate;
	}
	
	/**
    * ��һ�ο�ʼ����
    * @return java.util.Date
    */
	public void setFirstdate(java.util.Date firstdate) {
	   this.firstdate = firstdate;
	}
	
	
    /**
    * �ϴ��������
    * @return java.util.Date
    */
	public java.util.Date getLastcompdate() {
	  		return this.lastcompdate;
	}
	
	/**
    * �ϴ��������
    * @return java.util.Date
    */
	public void setLastcompdate(java.util.Date lastcompdate) {
	   this.lastcompdate = lastcompdate;
	}
	
	
    /**
    * Ƶ��
    * @return java.lang.Long
    */
	public java.lang.Long getFrequency() {
	  		return this.frequency;
	}
	
	/**
    * Ƶ��
    * @return java.lang.Long
    */
	public void setFrequency(java.lang.Long frequency) {
	   this.frequency = frequency;
	}
	
	
    /**
    * Ƶ�ʵ�λ
    * @return java.lang.String
    */
	public java.lang.String getFrequnit() {
		if(this.frequnit==null || this.frequnit.length()<=0)
	  		return null;
	  	else
	  		return this.frequnit;
	}
	
	/**
    * Ƶ�ʵ�λ
    * @return java.lang.String
    */
	public void setFrequnit(java.lang.String frequnit) {
	   this.frequnit = frequnit;
	}
	
	
    /**
    * �Ե�һ�������������ɵ�Ԥ����ά������
    * @return java.lang.Long
    */
	public java.lang.Long getPmcounter() {
	  		return this.pmcounter;
	}
	
	/**
    * �Ե�һ�������������ɵ�Ԥ����ά������
    * @return java.lang.Long
    */
	public void setPmcounter(java.lang.Long pmcounter) {
	   this.pmcounter = pmcounter;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getWorktype() {
		if(this.worktype==null || this.worktype.length()<=0)
	  		return null;
	  	else
	  		return this.worktype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setWorktype(java.lang.String worktype) {
	   this.worktype = worktype;
	}
	
	
    /**
    * ��׼��ҵ�ƻ����
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * ��׼��ҵ�ƻ����
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * �Ƿ�ʹ�ñ�׼��ҵ�ƻ�
    * @return java.lang.String
    */
	public java.lang.String getUsejpseq() {
		if(this.usejpseq==null || this.usejpseq.length()<=0)
	  		return null;
	  	else
	  		return this.usejpseq;
	}
	
	/**
    * �Ƿ�ʹ�ñ�׼��ҵ�ƻ�
    * @return java.lang.String
    */
	public void setUsejpseq(java.lang.String usejpseq) {
	   this.usejpseq = usejpseq;
	}
	
	
    /**
    * ��һ��������
    * @return java.util.Date
    */
	public java.util.Date getNextdate() {
	  		return this.nextdate;
	}
	
	/**
    * ��һ��������
    * @return java.util.Date
    */
	public void setNextdate(java.util.Date nextdate) {
	   this.nextdate = nextdate;
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
    * �Ƿ����ù�����
    * @return java.lang.String
    */
	public java.lang.String getAutowf() {
		if(this.autowf==null || this.autowf.length()<=0)
	  		return null;
	  	else
	  		return this.autowf;
	}
	
	/**
    * �Ƿ����ù�����
    * @return java.lang.String
    */
	public void setAutowf(java.lang.String autowf) {
	   this.autowf = autowf;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getWostatus() {
		if(this.wostatus==null || this.wostatus.length()<=0)
	  		return null;
	  	else
	  		return this.wostatus;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setWostatus(java.lang.String wostatus) {
	   this.wostatus = wostatus;
	}
	
	
    /**
    * ��Ҫͣ��
    * @return java.lang.String
    */
	public java.lang.String getEqdown() {
		if(this.eqdown==null || this.eqdown.length()<=0)
	  		return null;
	  	else
	  		return this.eqdown;
	}
	
	/**
    * ��Ҫͣ��
    * @return java.lang.String
    */
	public void setEqdown(java.lang.String eqdown) {
	   this.eqdown = eqdown;
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
    * ���Ӽ���
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * ���Ӽ���
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * ��Ƶ�ʵ�λΪ�Ƶ�Ƶ�ʣ�30�졢1��
    * @return java.lang.String
    */
	public java.lang.String getUsefrequency() {
		if(this.usefrequency==null || this.usefrequency.length()<=0)
	  		return null;
	  	else
	  		return this.usefrequency;
	}
	
	/**
    * ��Ƶ�ʵ�λΪ�Ƶ�Ƶ�ʣ�30�졢1��
    * @return java.lang.String
    */
	public void setUsefrequency(java.lang.String usefrequency) {
	   this.usefrequency = usefrequency;
	}
	
	
    /**
    * ����Ԥ����ά����Ҫ����ǰ����
    * @return java.lang.Long
    */
	public java.lang.Long getLeadtime() {
	  		return this.leadtime;
	}
	
	/**
    * ����Ԥ����ά����Ҫ����ǰ����
    * @return java.lang.Long
    */
	public void setLeadtime(java.lang.Long leadtime) {
	   this.leadtime = leadtime;
	}
	
	
    /**
    * �Ƿ������һ��������
    * @return java.lang.String
    */
	public java.lang.String getAdjnextdue() {
		if(this.adjnextdue==null || this.adjnextdue.length()<=0)
	  		return null;
	  	else
	  		return this.adjnextdue;
	}
	
	/**
    * �Ƿ������һ��������
    * @return java.lang.String
    */
	public void setAdjnextdue(java.lang.String adjnextdue) {
	   this.adjnextdue = adjnextdue;
	}
	
	
}