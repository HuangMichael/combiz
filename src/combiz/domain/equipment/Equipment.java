package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Equipment extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date lfdate;
     private java.lang.String grade;
     private java.util.Date rundate;
     private java.lang.String runnumber;
     private java.lang.String construct;
     private java.lang.String model;
     private java.lang.String lfnumber;
     private java.lang.String lfserial;
     private java.lang.Double stockcost;
     private java.lang.Double net_xn;
     private java.lang.String labornum;
     private java.lang.String largetype;
     private java.lang.String serial;
     private java.lang.String eqnum;
     private java.lang.String description;
     private java.lang.String location;
     private java.lang.String serialnum;
     private java.lang.String assetnum;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String vendor;
     private java.lang.String failurecode;
     private java.lang.String manufacturer;
     private java.lang.Double purprice;
     private java.util.Date installdate;
     private java.util.Date warrantyexpdate;
     private java.lang.Double totalcost;
     private java.lang.Double ytdcost;
     private java.lang.Double budgetcost;
     private java.lang.String classid;
     private java.lang.String isrunning;
     private java.lang.String itemnum;
     private java.lang.Double totdowntime;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.Long priority;
     private java.lang.Double invcost;
     private java.lang.String deptnum;
     private java.lang.String binnum;
     private java.lang.String parent;
     private java.lang.String calnum;
     private java.lang.String appertain;
     private java.lang.String cntnum;
     private java.lang.String currency;
     private java.lang.String project;
     private java.lang.Double rate;
     private java.lang.String lotnum;
     private java.lang.String shiftnum;
     private java.lang.Long planyears;
     
    /** default constructor */
    public Equipment(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getLfdate() {
	  		return this.lfdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setLfdate(java.util.Date lfdate) {
	   this.lfdate = lfdate;
	}
	
	
    /**
    * ���۵ȼ�
    * @return java.lang.String
    */
	public java.lang.String getGrade() {
		if(this.grade==null || this.grade.length()<=0)
	  		return null;
	  	else
	  		return this.grade;
	}
	
	/**
    * ���۵ȼ�
    * @return java.lang.String
    */
	public void setGrade(java.lang.String grade) {
	   this.grade = grade;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRundate() {
	  		return this.rundate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRundate(java.util.Date rundate) {
	   this.rundate = rundate;
	}
	
	
    /**
    * ���б��
    * @return java.lang.String
    */
	public java.lang.String getRunnumber() {
		if(this.runnumber==null || this.runnumber.length()<=0)
	  		return null;
	  	else
	  		return this.runnumber;
	}
	
	/**
    * ���б��
    * @return java.lang.String
    */
	public void setRunnumber(java.lang.String runnumber) {
	   this.runnumber = runnumber;
	}
	
	
    /**
    * ʩ����λ
    * @return java.lang.String
    */
	public java.lang.String getConstruct() {
		if(this.construct==null || this.construct.length()<=0)
	  		return null;
	  	else
	  		return this.construct;
	}
	
	/**
    * ʩ����λ
    * @return java.lang.String
    */
	public void setConstruct(java.lang.String construct) {
	   this.construct = construct;
	}
	
	
    /**
    * �ͺŹ��
    * @return java.lang.String
    */
	public java.lang.String getModel() {
		if(this.model==null || this.model.length()<=0)
	  		return null;
	  	else
	  		return this.model;
	}
	
	/**
    * �ͺŹ��
    * @return java.lang.String
    */
	public void setModel(java.lang.String model) {
	   this.model = model;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getLfnumber() {
		if(this.lfnumber==null || this.lfnumber.length()<=0)
	  		return null;
	  	else
	  		return this.lfnumber;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setLfnumber(java.lang.String lfnumber) {
	   this.lfnumber = lfnumber;
	}
	
	
    /**
    * ��ǲ���
    * @return java.lang.String
    */
	public java.lang.String getLfserial() {
		if(this.lfserial==null || this.lfserial.length()<=0)
	  		return null;
	  	else
	  		return this.lfserial;
	}
	
	/**
    * ��ǲ���
    * @return java.lang.String
    */
	public void setLfserial(java.lang.String lfserial) {
	   this.lfserial = lfserial;
	}
	
	
    /**
    * ���ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getStockcost() {
	  		return this.stockcost;
	}
	
	/**
    * ���ɱ�
    * @return java.lang.Double
    */
	public void setStockcost(java.lang.Double stockcost) {
	   this.stockcost = stockcost;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getNet_xn() {
	  		return this.net_xn;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setNet_xn(java.lang.Double net_xn) {
	   this.net_xn = net_xn;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * �豸��������
    * @return java.lang.String
    */
	public java.lang.String getLargetype() {
		if(this.largetype==null || this.largetype.length()<=0)
	  		return null;
	  	else
	  		return this.largetype;
	}
	
	/**
    * �豸��������
    * @return java.lang.String
    */
	public void setLargetype(java.lang.String largetype) {
	   this.largetype = largetype;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getSerial() {
		if(this.serial==null || this.serial.length()<=0)
	  		return null;
	  	else
	  		return this.serial;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setSerial(java.lang.String serial) {
	   this.serial = serial;
	}
	
	
    /**
    * �豸���
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸���
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
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
    * ���б��
    * @return java.lang.String
    */
	public java.lang.String getSerialnum() {
		if(this.serialnum==null || this.serialnum.length()<=0)
	  		return null;
	  	else
	  		return this.serialnum;
	}
	
	/**
    * ���б��
    * @return java.lang.String
    */
	public void setSerialnum(java.lang.String serialnum) {
	   this.serialnum = serialnum;
	}
	
	
    /**
    * �̶��ʲ����
    * @return java.lang.String
    */
	public java.lang.String getAssetnum() {
		if(this.assetnum==null || this.assetnum.length()<=0)
	  		return null;
	  	else
	  		return this.assetnum;
	}
	
	/**
    * �̶��ʲ����
    * @return java.lang.String
    */
	public void setAssetnum(java.lang.String assetnum) {
	   this.assetnum = assetnum;
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
    * ���ϴ���
    * @return java.lang.String
    */
	public java.lang.String getFailurecode() {
		if(this.failurecode==null || this.failurecode.length()<=0)
	  		return null;
	  	else
	  		return this.failurecode;
	}
	
	/**
    * ���ϴ���
    * @return java.lang.String
    */
	public void setFailurecode(java.lang.String failurecode) {
	   this.failurecode = failurecode;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * �ɹ��۸�
    * @return java.lang.Double
    */
	public java.lang.Double getPurprice() {
	  		return this.purprice;
	}
	
	/**
    * �ɹ��۸�
    * @return java.lang.Double
    */
	public void setPurprice(java.lang.Double purprice) {
	   this.purprice = purprice;
	}
	
	
    /**
    * ��װ����
    * @return java.util.Date
    */
	public java.util.Date getInstalldate() {
	  		return this.installdate;
	}
	
	/**
    * ��װ����
    * @return java.util.Date
    */
	public void setInstalldate(java.util.Date installdate) {
	   this.installdate = installdate;
	}
	
	
    /**
    * ����������
    * @return java.util.Date
    */
	public java.util.Date getWarrantyexpdate() {
	  		return this.warrantyexpdate;
	}
	
	/**
    * ����������
    * @return java.util.Date
    */
	public void setWarrantyexpdate(java.util.Date warrantyexpdate) {
	   this.warrantyexpdate = warrantyexpdate;
	}
	
	
    /**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
	}
	
	
    /**
    * ����Ԥ��
    * @return java.lang.Double
    */
	public java.lang.Double getYtdcost() {
	  		return this.ytdcost;
	}
	
	/**
    * ����Ԥ��
    * @return java.lang.Double
    */
	public void setYtdcost(java.lang.Double ytdcost) {
	   this.ytdcost = ytdcost;
	}
	
	
    /**
    * Ԥ��
    * @return java.lang.Double
    */
	public java.lang.Double getBudgetcost() {
	  		return this.budgetcost;
	}
	
	/**
    * Ԥ��
    * @return java.lang.Double
    */
	public void setBudgetcost(java.lang.Double budgetcost) {
	   this.budgetcost = budgetcost;
	}
	
	
    /**
    * �豸����
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * �豸����
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * �Ƿ�����
    * @return java.lang.String
    */
	public java.lang.String getIsrunning() {
		if(this.isrunning==null || this.isrunning.length()<=0)
	  		return null;
	  	else
	  		return this.isrunning;
	}
	
	/**
    * �Ƿ�����
    * @return java.lang.String
    */
	public void setIsrunning(java.lang.String isrunning) {
	   this.isrunning = isrunning;
	}
	
	
    /**
    * ���ʱ���
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ���ʱ���
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * �ܵ�ͣ��ʱ��
    * @return java.lang.Double
    */
	public java.lang.Double getTotdowntime() {
	  		return this.totdowntime;
	}
	
	/**
    * �ܵ�ͣ��ʱ��
    * @return java.lang.Double
    */
	public void setTotdowntime(java.lang.Double totdowntime) {
	   this.totdowntime = totdowntime;
	}
	
	
    /**
    * �޸�ʱ��
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * �޸�ʱ��
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * �޸���
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * �޸���
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * ���ȼ�
    * @return java.lang.Long
    */
	public java.lang.Long getPriority() {
	  		return this.priority;
	}
	
	/**
    * ���ȼ�
    * @return java.lang.Long
    */
	public void setPriority(java.lang.Long priority) {
	   this.priority = priority;
	}
	
	
    /**
    * ���ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getInvcost() {
	  		return this.invcost;
	}
	
	/**
    * ���ɱ�
    * @return java.lang.Double
    */
	public void setInvcost(java.lang.Double invcost) {
	   this.invcost = invcost;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * ��λ
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * ��λ
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * �����豸
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * �����豸
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
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
    * �Ƿ����豸
    * @return java.lang.String
    */
	public java.lang.String getAppertain() {
		if(this.appertain==null || this.appertain.length()<=0)
	  		return null;
	  	else
	  		return this.appertain;
	}
	
	/**
    * �Ƿ����豸
    * @return java.lang.String
    */
	public void setAppertain(java.lang.String appertain) {
	   this.appertain = appertain;
	}
	
	
    /**
    * ��ͬ����
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * ��ͬ����
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getCurrency() {
		if(this.currency==null || this.currency.length()<=0)
	  		return null;
	  	else
	  		return this.currency;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setCurrency(java.lang.String currency) {
	   this.currency = currency;
	}
	
	
    /**
    * ��Ŀ
    * @return java.lang.String
    */
	public java.lang.String getProject() {
		if(this.project==null || this.project.length()<=0)
	  		return null;
	  	else
	  		return this.project;
	}
	
	/**
    * ��Ŀ
    * @return java.lang.String
    */
	public void setProject(java.lang.String project) {
	   this.project = project;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
	}
	
	
    /**
    * ���κ�
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * ���κ�
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
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
    * Ԥ��ʹ������
    * @return java.lang.Long
    */
	public java.lang.Long getPlanyears() {
	  		return this.planyears;
	}
	
	/**
    * Ԥ��ʹ������
    * @return java.lang.Long
    */
	public void setPlanyears(java.lang.Long planyears) {
	   this.planyears = planyears;
	}
	
	
}