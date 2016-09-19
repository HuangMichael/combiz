package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assetscard extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetclasses;
     private java.lang.String assetcode;
     private java.lang.String assetname;
     private java.lang.String assetstatus;
     private java.lang.String assetsubject;
     private java.lang.String changemethod;
     private java.lang.Double cost;
     private java.util.Date daterecorded;
     private java.lang.String depreciationmethod;
     private java.lang.String depreciationsjt;
     private java.lang.String eqnum;
     private java.lang.String location;
     private java.lang.Double net_xn;
     private java.lang.String parent;
     private java.lang.String responsible;
     private java.lang.String status;
     private java.lang.String summary;
     private java.util.Date usedate;
     private java.lang.String usedepartment;
     private java.lang.String users;
     
    /** default constructor */
    public Assetscard(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ʲ����
    * @return java.lang.String
    */
	public java.lang.String getAssetclasses() {
		if(this.assetclasses==null || this.assetclasses.length()<=0)
	  		return null;
	  	else
	  		return this.assetclasses;
	}
	
	/**
    * �ʲ����
    * @return java.lang.String
    */
	public void setAssetclasses(java.lang.String assetclasses) {
	   this.assetclasses = assetclasses;
	}
	
	
    /**
    * �ʲ����
    * @return java.lang.String
    */
	public java.lang.String getAssetcode() {
		if(this.assetcode==null || this.assetcode.length()<=0)
	  		return null;
	  	else
	  		return this.assetcode;
	}
	
	/**
    * �ʲ����
    * @return java.lang.String
    */
	public void setAssetcode(java.lang.String assetcode) {
	   this.assetcode = assetcode;
	}
	
	
    /**
    * �ʲ�����
    * @return java.lang.String
    */
	public java.lang.String getAssetname() {
		if(this.assetname==null || this.assetname.length()<=0)
	  		return null;
	  	else
	  		return this.assetname;
	}
	
	/**
    * �ʲ�����
    * @return java.lang.String
    */
	public void setAssetname(java.lang.String assetname) {
	   this.assetname = assetname;
	}
	
	
    /**
    * �ʲ�״̬
    * @return java.lang.String
    */
	public java.lang.String getAssetstatus() {
		if(this.assetstatus==null || this.assetstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assetstatus;
	}
	
	/**
    * �ʲ�״̬
    * @return java.lang.String
    */
	public void setAssetstatus(java.lang.String assetstatus) {
	   this.assetstatus = assetstatus;
	}
	
	
    /**
    * �ʲ���Ŀ
    * @return java.lang.String
    */
	public java.lang.String getAssetsubject() {
		if(this.assetsubject==null || this.assetsubject.length()<=0)
	  		return null;
	  	else
	  		return this.assetsubject;
	}
	
	/**
    * �ʲ���Ŀ
    * @return java.lang.String
    */
	public void setAssetsubject(java.lang.String assetsubject) {
	   this.assetsubject = assetsubject;
	}
	
	
    /**
    * ������ʽ
    * @return java.lang.String
    */
	public java.lang.String getChangemethod() {
		if(this.changemethod==null || this.changemethod.length()<=0)
	  		return null;
	  	else
	  		return this.changemethod;
	}
	
	/**
    * ������ʽ
    * @return java.lang.String
    */
	public void setChangemethod(java.lang.String changemethod) {
	   this.changemethod = changemethod;
	}
	
	
    /**
    * ԭֵ
    * @return java.lang.Double
    */
	public java.lang.Double getCost() {
	  		return this.cost;
	}
	
	/**
    * ԭֵ
    * @return java.lang.Double
    */
	public void setCost(java.lang.Double cost) {
	   this.cost = cost;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getDaterecorded() {
	  		return this.daterecorded;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setDaterecorded(java.util.Date daterecorded) {
	   this.daterecorded = daterecorded;
	}
	
	
    /**
    * �۾ɷ�ʽ
    * @return java.lang.String
    */
	public java.lang.String getDepreciationmethod() {
		if(this.depreciationmethod==null || this.depreciationmethod.length()<=0)
	  		return null;
	  	else
	  		return this.depreciationmethod;
	}
	
	/**
    * �۾ɷ�ʽ
    * @return java.lang.String
    */
	public void setDepreciationmethod(java.lang.String depreciationmethod) {
	   this.depreciationmethod = depreciationmethod;
	}
	
	
    /**
    * �۾ɿ�Ŀ
    * @return java.lang.String
    */
	public java.lang.String getDepreciationsjt() {
		if(this.depreciationsjt==null || this.depreciationsjt.length()<=0)
	  		return null;
	  	else
	  		return this.depreciationsjt;
	}
	
	/**
    * �۾ɿ�Ŀ
    * @return java.lang.String
    */
	public void setDepreciationsjt(java.lang.String depreciationsjt) {
	   this.depreciationsjt = depreciationsjt;
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
    * ��ŵص�
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * ��ŵص�
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ���⾻��
    * @return java.lang.Double
    */
	public java.lang.Double getNet_xn() {
	  		return this.net_xn;
	}
	
	/**
    * ���⾻��
    * @return java.lang.Double
    */
	public void setNet_xn(java.lang.Double net_xn) {
	   this.net_xn = net_xn;
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
    * ������
    * @return java.lang.String
    */
	public java.lang.String getResponsible() {
		if(this.responsible==null || this.responsible.length()<=0)
	  		return null;
	  	else
	  		return this.responsible;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setResponsible(java.lang.String responsible) {
	   this.responsible = responsible;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ժҪ
    * @return java.lang.String
    */
	public java.lang.String getSummary() {
		if(this.summary==null || this.summary.length()<=0)
	  		return null;
	  	else
	  		return this.summary;
	}
	
	/**
    * ժҪ
    * @return java.lang.String
    */
	public void setSummary(java.lang.String summary) {
	   this.summary = summary;
	}
	
	
    /**
    * ʹ������
    * @return java.util.Date
    */
	public java.util.Date getUsedate() {
	  		return this.usedate;
	}
	
	/**
    * ʹ������
    * @return java.util.Date
    */
	public void setUsedate(java.util.Date usedate) {
	   this.usedate = usedate;
	}
	
	
    /**
    * ʹ�ò���
    * @return java.lang.String
    */
	public java.lang.String getUsedepartment() {
		if(this.usedepartment==null || this.usedepartment.length()<=0)
	  		return null;
	  	else
	  		return this.usedepartment;
	}
	
	/**
    * ʹ�ò���
    * @return java.lang.String
    */
	public void setUsedepartment(java.lang.String usedepartment) {
	   this.usedepartment = usedepartment;
	}
	
	
    /**
    * ʹ����
    * @return java.lang.String
    */
	public java.lang.String getUsers() {
		if(this.users==null || this.users.length()<=0)
	  		return null;
	  	else
	  		return this.users;
	}
	
	/**
    * ʹ����
    * @return java.lang.String
    */
	public void setUsers(java.lang.String users) {
	   this.users = users;
	}
	
	
}