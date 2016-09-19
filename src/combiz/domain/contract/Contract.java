package combiz.domain.contract;

import combiz.system.IBOBaseObject;

public class Contract extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String cntnum;
     private java.lang.String description;
     private java.lang.String bidnum;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String cntversion;
     private java.lang.String deptnum;
     private java.lang.String cnttype;
     private java.lang.String potype;
     private java.lang.String cntclass;
     private java.lang.String owner;
     private java.lang.String owneronbf;
     private java.lang.String ownercontact;
     private java.lang.String ownerbank;
     private java.lang.String owneraccount;
     private java.lang.String ownerphone;
     private java.lang.String owneremail;
     private java.lang.String owneraddress;
     private java.lang.String vendor;
     private java.lang.String vendoronbf;
     private java.lang.String vendorcontact;
     private java.lang.String vendorphone;
     private java.lang.String vendoremail;
     private java.lang.String vendoraddress;
     private java.lang.String vendorbank;
     private java.lang.String vendoraccount;
     private java.lang.String parent;
     private java.lang.String haschild;
     private java.lang.String createby;
     private java.util.Date createdate;
     private java.util.Date cntstartdate;
     private java.util.Date cntenddate;
     private java.util.Date cmdstartdate;
     private java.util.Date cmdenddate;
     private java.lang.Double totalcost;
     private java.lang.String paymold;
     private java.util.Date sigdate;
     private java.lang.String cntdur;
     
    /** default constructor */
    public Contract(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ͬ���
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * ��ͬ���
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
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
    * �б���
    * @return java.lang.String
    */
	public java.lang.String getBidnum() {
		if(this.bidnum==null || this.bidnum.length()<=0)
	  		return null;
	  	else
	  		return this.bidnum;
	}
	
	/**
    * �б���
    * @return java.lang.String
    */
	public void setBidnum(java.lang.String bidnum) {
	   this.bidnum = bidnum;
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
    * ״̬����
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ״̬����
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * ��ͬ�汾
    * @return java.lang.String
    */
	public java.lang.String getCntversion() {
		if(this.cntversion==null || this.cntversion.length()<=0)
	  		return null;
	  	else
	  		return this.cntversion;
	}
	
	/**
    * ��ͬ�汾
    * @return java.lang.String
    */
	public void setCntversion(java.lang.String cntversion) {
	   this.cntversion = cntversion;
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
    * ��ͬ����
    * @return java.lang.String
    */
	public java.lang.String getCnttype() {
		if(this.cnttype==null || this.cnttype.length()<=0)
	  		return null;
	  	else
	  		return this.cnttype;
	}
	
	/**
    * ��ͬ����
    * @return java.lang.String
    */
	public void setCnttype(java.lang.String cnttype) {
	   this.cnttype = cnttype;
	}
	
	
    /**
    * �ɹ���ʽ
    * @return java.lang.String
    */
	public java.lang.String getPotype() {
		if(this.potype==null || this.potype.length()<=0)
	  		return null;
	  	else
	  		return this.potype;
	}
	
	/**
    * �ɹ���ʽ
    * @return java.lang.String
    */
	public void setPotype(java.lang.String potype) {
	   this.potype = potype;
	}
	
	
    /**
    * ��ͬ����
    * @return java.lang.String
    */
	public java.lang.String getCntclass() {
		if(this.cntclass==null || this.cntclass.length()<=0)
	  		return null;
	  	else
	  		return this.cntclass;
	}
	
	/**
    * ��ͬ����
    * @return java.lang.String
    */
	public void setCntclass(java.lang.String cntclass) {
	   this.cntclass = cntclass;
	}
	
	
    /**
    * �׷�
    * @return java.lang.String
    */
	public java.lang.String getOwner() {
		if(this.owner==null || this.owner.length()<=0)
	  		return null;
	  	else
	  		return this.owner;
	}
	
	/**
    * �׷�
    * @return java.lang.String
    */
	public void setOwner(java.lang.String owner) {
	   this.owner = owner;
	}
	
	
    /**
    * �׷�����
    * @return java.lang.String
    */
	public java.lang.String getOwneronbf() {
		if(this.owneronbf==null || this.owneronbf.length()<=0)
	  		return null;
	  	else
	  		return this.owneronbf;
	}
	
	/**
    * �׷�����
    * @return java.lang.String
    */
	public void setOwneronbf(java.lang.String owneronbf) {
	   this.owneronbf = owneronbf;
	}
	
	
    /**
    * �׷���ϵ��
    * @return java.lang.String
    */
	public java.lang.String getOwnercontact() {
		if(this.ownercontact==null || this.ownercontact.length()<=0)
	  		return null;
	  	else
	  		return this.ownercontact;
	}
	
	/**
    * �׷���ϵ��
    * @return java.lang.String
    */
	public void setOwnercontact(java.lang.String ownercontact) {
	   this.ownercontact = ownercontact;
	}
	
	
    /**
    * �׷���������
    * @return java.lang.String
    */
	public java.lang.String getOwnerbank() {
		if(this.ownerbank==null || this.ownerbank.length()<=0)
	  		return null;
	  	else
	  		return this.ownerbank;
	}
	
	/**
    * �׷���������
    * @return java.lang.String
    */
	public void setOwnerbank(java.lang.String ownerbank) {
	   this.ownerbank = ownerbank;
	}
	
	
    /**
    * �׷��ʺ�
    * @return java.lang.String
    */
	public java.lang.String getOwneraccount() {
		if(this.owneraccount==null || this.owneraccount.length()<=0)
	  		return null;
	  	else
	  		return this.owneraccount;
	}
	
	/**
    * �׷��ʺ�
    * @return java.lang.String
    */
	public void setOwneraccount(java.lang.String owneraccount) {
	   this.owneraccount = owneraccount;
	}
	
	
    /**
    * �׷���ϵ�绰
    * @return java.lang.String
    */
	public java.lang.String getOwnerphone() {
		if(this.ownerphone==null || this.ownerphone.length()<=0)
	  		return null;
	  	else
	  		return this.ownerphone;
	}
	
	/**
    * �׷���ϵ�绰
    * @return java.lang.String
    */
	public void setOwnerphone(java.lang.String ownerphone) {
	   this.ownerphone = ownerphone;
	}
	
	
    /**
    * �׷���ϵEMAIL
    * @return java.lang.String
    */
	public java.lang.String getOwneremail() {
		if(this.owneremail==null || this.owneremail.length()<=0)
	  		return null;
	  	else
	  		return this.owneremail;
	}
	
	/**
    * �׷���ϵEMAIL
    * @return java.lang.String
    */
	public void setOwneremail(java.lang.String owneremail) {
	   this.owneremail = owneremail;
	}
	
	
    /**
    * �׷���ϵ��ַ
    * @return java.lang.String
    */
	public java.lang.String getOwneraddress() {
		if(this.owneraddress==null || this.owneraddress.length()<=0)
	  		return null;
	  	else
	  		return this.owneraddress;
	}
	
	/**
    * �׷���ϵ��ַ
    * @return java.lang.String
    */
	public void setOwneraddress(java.lang.String owneraddress) {
	   this.owneraddress = owneraddress;
	}
	
	
    /**
    * �ҷ�
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * �ҷ�
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * �ҷ�����
    * @return java.lang.String
    */
	public java.lang.String getVendoronbf() {
		if(this.vendoronbf==null || this.vendoronbf.length()<=0)
	  		return null;
	  	else
	  		return this.vendoronbf;
	}
	
	/**
    * �ҷ�����
    * @return java.lang.String
    */
	public void setVendoronbf(java.lang.String vendoronbf) {
	   this.vendoronbf = vendoronbf;
	}
	
	
    /**
    * �ҷ���ϵ��
    * @return java.lang.String
    */
	public java.lang.String getVendorcontact() {
		if(this.vendorcontact==null || this.vendorcontact.length()<=0)
	  		return null;
	  	else
	  		return this.vendorcontact;
	}
	
	/**
    * �ҷ���ϵ��
    * @return java.lang.String
    */
	public void setVendorcontact(java.lang.String vendorcontact) {
	   this.vendorcontact = vendorcontact;
	}
	
	
    /**
    * �ҷ���ϵ�绰
    * @return java.lang.String
    */
	public java.lang.String getVendorphone() {
		if(this.vendorphone==null || this.vendorphone.length()<=0)
	  		return null;
	  	else
	  		return this.vendorphone;
	}
	
	/**
    * �ҷ���ϵ�绰
    * @return java.lang.String
    */
	public void setVendorphone(java.lang.String vendorphone) {
	   this.vendorphone = vendorphone;
	}
	
	
    /**
    * �ҷ���ϵEMAIL
    * @return java.lang.String
    */
	public java.lang.String getVendoremail() {
		if(this.vendoremail==null || this.vendoremail.length()<=0)
	  		return null;
	  	else
	  		return this.vendoremail;
	}
	
	/**
    * �ҷ���ϵEMAIL
    * @return java.lang.String
    */
	public void setVendoremail(java.lang.String vendoremail) {
	   this.vendoremail = vendoremail;
	}
	
	
    /**
    * �ҷ���ϵ��ַ
    * @return java.lang.String
    */
	public java.lang.String getVendoraddress() {
		if(this.vendoraddress==null || this.vendoraddress.length()<=0)
	  		return null;
	  	else
	  		return this.vendoraddress;
	}
	
	/**
    * �ҷ���ϵ��ַ
    * @return java.lang.String
    */
	public void setVendoraddress(java.lang.String vendoraddress) {
	   this.vendoraddress = vendoraddress;
	}
	
	
    /**
    * �ҷ���������
    * @return java.lang.String
    */
	public java.lang.String getVendorbank() {
		if(this.vendorbank==null || this.vendorbank.length()<=0)
	  		return null;
	  	else
	  		return this.vendorbank;
	}
	
	/**
    * �ҷ���������
    * @return java.lang.String
    */
	public void setVendorbank(java.lang.String vendorbank) {
	   this.vendorbank = vendorbank;
	}
	
	
    /**
    * �ҷ��ʺ�
    * @return java.lang.String
    */
	public java.lang.String getVendoraccount() {
		if(this.vendoraccount==null || this.vendoraccount.length()<=0)
	  		return null;
	  	else
	  		return this.vendoraccount;
	}
	
	/**
    * �ҷ��ʺ�
    * @return java.lang.String
    */
	public void setVendoraccount(java.lang.String vendoraccount) {
	   this.vendoraccount = vendoraccount;
	}
	
	
    /**
    * ������ͬ
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * ������ͬ
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * �б����ͬ
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * �б����ͬ
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getCreateby() {
		if(this.createby==null || this.createby.length()<=0)
	  		return null;
	  	else
	  		return this.createby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setCreateby(java.lang.String createby) {
	   this.createby = createby;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * ��ͬ��Ч�ڿ�ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getCntstartdate() {
	  		return this.cntstartdate;
	}
	
	/**
    * ��ͬ��Ч�ڿ�ʼʱ��
    * @return java.util.Date
    */
	public void setCntstartdate(java.util.Date cntstartdate) {
	   this.cntstartdate = cntstartdate;
	}
	
	
    /**
    * ��ͬ��Ч�ڽ���ʱ��
    * @return java.util.Date
    */
	public java.util.Date getCntenddate() {
	  		return this.cntenddate;
	}
	
	/**
    * ��ͬ��Ч�ڽ���ʱ��
    * @return java.util.Date
    */
	public void setCntenddate(java.util.Date cntenddate) {
	   this.cntenddate = cntenddate;
	}
	
	
    /**
    * Ҫ��ʼ����
    * @return java.util.Date
    */
	public java.util.Date getCmdstartdate() {
	  		return this.cmdstartdate;
	}
	
	/**
    * Ҫ��ʼ����
    * @return java.util.Date
    */
	public void setCmdstartdate(java.util.Date cmdstartdate) {
	   this.cmdstartdate = cmdstartdate;
	}
	
	
    /**
    * Ҫ���������
    * @return java.util.Date
    */
	public java.util.Date getCmdenddate() {
	  		return this.cmdenddate;
	}
	
	/**
    * Ҫ���������
    * @return java.util.Date
    */
	public void setCmdenddate(java.util.Date cmdenddate) {
	   this.cmdenddate = cmdenddate;
	}
	
	
    /**
    * ��ͬ�ܶ�
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * ��ͬ�ܶ�
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
	}
	
	
    /**
    * ���ʽ
    * @return java.lang.String
    */
	public java.lang.String getPaymold() {
		if(this.paymold==null || this.paymold.length()<=0)
	  		return null;
	  	else
	  		return this.paymold;
	}
	
	/**
    * ���ʽ
    * @return java.lang.String
    */
	public void setPaymold(java.lang.String paymold) {
	   this.paymold = paymold;
	}
	
	
    /**
    * ��ͬǩ������
    * @return java.util.Date
    */
	public java.util.Date getSigdate() {
	  		return this.sigdate;
	}
	
	/**
    * ��ͬǩ������
    * @return java.util.Date
    */
	public void setSigdate(java.util.Date sigdate) {
	   this.sigdate = sigdate;
	}
	
	
    /**
    * ��ͬ����
    * @return java.lang.String
    */
	public java.lang.String getCntdur() {
		if(this.cntdur==null || this.cntdur.length()<=0)
	  		return null;
	  	else
	  		return this.cntdur;
	}
	
	/**
    * ��ͬ����
    * @return java.lang.String
    */
	public void setCntdur(java.lang.String cntdur) {
	   this.cntdur = cntdur;
	}
	
	
}