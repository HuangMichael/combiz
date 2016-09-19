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
    * 出厂日期
    * @return java.util.Date
    */
	public java.util.Date getLfdate() {
	  		return this.lfdate;
	}
	
	/**
    * 出厂日期
    * @return java.util.Date
    */
	public void setLfdate(java.util.Date lfdate) {
	   this.lfdate = lfdate;
	}
	
	
    /**
    * 评价等级
    * @return java.lang.String
    */
	public java.lang.String getGrade() {
		if(this.grade==null || this.grade.length()<=0)
	  		return null;
	  	else
	  		return this.grade;
	}
	
	/**
    * 评价等级
    * @return java.lang.String
    */
	public void setGrade(java.lang.String grade) {
	   this.grade = grade;
	}
	
	
    /**
    * 运行日期
    * @return java.util.Date
    */
	public java.util.Date getRundate() {
	  		return this.rundate;
	}
	
	/**
    * 运行日期
    * @return java.util.Date
    */
	public void setRundate(java.util.Date rundate) {
	   this.rundate = rundate;
	}
	
	
    /**
    * 运行编号
    * @return java.lang.String
    */
	public java.lang.String getRunnumber() {
		if(this.runnumber==null || this.runnumber.length()<=0)
	  		return null;
	  	else
	  		return this.runnumber;
	}
	
	/**
    * 运行编号
    * @return java.lang.String
    */
	public void setRunnumber(java.lang.String runnumber) {
	   this.runnumber = runnumber;
	}
	
	
    /**
    * 施工单位
    * @return java.lang.String
    */
	public java.lang.String getConstruct() {
		if(this.construct==null || this.construct.length()<=0)
	  		return null;
	  	else
	  		return this.construct;
	}
	
	/**
    * 施工单位
    * @return java.lang.String
    */
	public void setConstruct(java.lang.String construct) {
	   this.construct = construct;
	}
	
	
    /**
    * 型号规格
    * @return java.lang.String
    */
	public java.lang.String getModel() {
		if(this.model==null || this.model.length()<=0)
	  		return null;
	  	else
	  		return this.model;
	}
	
	/**
    * 型号规格
    * @return java.lang.String
    */
	public void setModel(java.lang.String model) {
	   this.model = model;
	}
	
	
    /**
    * 出厂编号
    * @return java.lang.String
    */
	public java.lang.String getLfnumber() {
		if(this.lfnumber==null || this.lfnumber.length()<=0)
	  		return null;
	  	else
	  		return this.lfnumber;
	}
	
	/**
    * 出厂编号
    * @return java.lang.String
    */
	public void setLfnumber(java.lang.String lfnumber) {
	   this.lfnumber = lfnumber;
	}
	
	
    /**
    * 派遣编号
    * @return java.lang.String
    */
	public java.lang.String getLfserial() {
		if(this.lfserial==null || this.lfserial.length()<=0)
	  		return null;
	  	else
	  		return this.lfserial;
	}
	
	/**
    * 派遣编号
    * @return java.lang.String
    */
	public void setLfserial(java.lang.String lfserial) {
	   this.lfserial = lfserial;
	}
	
	
    /**
    * 库存成本
    * @return java.lang.Double
    */
	public java.lang.Double getStockcost() {
	  		return this.stockcost;
	}
	
	/**
    * 库存成本
    * @return java.lang.Double
    */
	public void setStockcost(java.lang.Double stockcost) {
	   this.stockcost = stockcost;
	}
	
	
    /**
    * 净额
    * @return java.lang.Double
    */
	public java.lang.Double getNet_xn() {
	  		return this.net_xn;
	}
	
	/**
    * 净额
    * @return java.lang.Double
    */
	public void setNet_xn(java.lang.Double net_xn) {
	   this.net_xn = net_xn;
	}
	
	
    /**
    * 所属人
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * 所属人
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * 设备所属大类
    * @return java.lang.String
    */
	public java.lang.String getLargetype() {
		if(this.largetype==null || this.largetype.length()<=0)
	  		return null;
	  	else
	  		return this.largetype;
	}
	
	/**
    * 设备所属大类
    * @return java.lang.String
    */
	public void setLargetype(java.lang.String largetype) {
	   this.largetype = largetype;
	}
	
	
    /**
    * 序列
    * @return java.lang.String
    */
	public java.lang.String getSerial() {
		if(this.serial==null || this.serial.length()<=0)
	  		return null;
	  	else
	  		return this.serial;
	}
	
	/**
    * 序列
    * @return java.lang.String
    */
	public void setSerial(java.lang.String serial) {
	   this.serial = serial;
	}
	
	
    /**
    * 设备编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 设备编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
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
    * 序列编号
    * @return java.lang.String
    */
	public java.lang.String getSerialnum() {
		if(this.serialnum==null || this.serialnum.length()<=0)
	  		return null;
	  	else
	  		return this.serialnum;
	}
	
	/**
    * 序列编号
    * @return java.lang.String
    */
	public void setSerialnum(java.lang.String serialnum) {
	   this.serialnum = serialnum;
	}
	
	
    /**
    * 固定资产编号
    * @return java.lang.String
    */
	public java.lang.String getAssetnum() {
		if(this.assetnum==null || this.assetnum.length()<=0)
	  		return null;
	  	else
	  		return this.assetnum;
	}
	
	/**
    * 固定资产编号
    * @return java.lang.String
    */
	public void setAssetnum(java.lang.String assetnum) {
	   this.assetnum = assetnum;
	}
	
	
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
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
    * 故障代码
    * @return java.lang.String
    */
	public java.lang.String getFailurecode() {
		if(this.failurecode==null || this.failurecode.length()<=0)
	  		return null;
	  	else
	  		return this.failurecode;
	}
	
	/**
    * 故障代码
    * @return java.lang.String
    */
	public void setFailurecode(java.lang.String failurecode) {
	   this.failurecode = failurecode;
	}
	
	
    /**
    * 制造商
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * 制造商
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * 采购价格
    * @return java.lang.Double
    */
	public java.lang.Double getPurprice() {
	  		return this.purprice;
	}
	
	/**
    * 采购价格
    * @return java.lang.Double
    */
	public void setPurprice(java.lang.Double purprice) {
	   this.purprice = purprice;
	}
	
	
    /**
    * 安装日期
    * @return java.util.Date
    */
	public java.util.Date getInstalldate() {
	  		return this.installdate;
	}
	
	/**
    * 安装日期
    * @return java.util.Date
    */
	public void setInstalldate(java.util.Date installdate) {
	   this.installdate = installdate;
	}
	
	
    /**
    * 保修日期至
    * @return java.util.Date
    */
	public java.util.Date getWarrantyexpdate() {
	  		return this.warrantyexpdate;
	}
	
	/**
    * 保修日期至
    * @return java.util.Date
    */
	public void setWarrantyexpdate(java.util.Date warrantyexpdate) {
	   this.warrantyexpdate = warrantyexpdate;
	}
	
	
    /**
    * 总成本
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * 总成本
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
	}
	
	
    /**
    * 本年预算
    * @return java.lang.Double
    */
	public java.lang.Double getYtdcost() {
	  		return this.ytdcost;
	}
	
	/**
    * 本年预算
    * @return java.lang.Double
    */
	public void setYtdcost(java.lang.Double ytdcost) {
	   this.ytdcost = ytdcost;
	}
	
	
    /**
    * 预算
    * @return java.lang.Double
    */
	public java.lang.Double getBudgetcost() {
	  		return this.budgetcost;
	}
	
	/**
    * 预算
    * @return java.lang.Double
    */
	public void setBudgetcost(java.lang.Double budgetcost) {
	   this.budgetcost = budgetcost;
	}
	
	
    /**
    * 设备分类
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 设备分类
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * 是否运行
    * @return java.lang.String
    */
	public java.lang.String getIsrunning() {
		if(this.isrunning==null || this.isrunning.length()<=0)
	  		return null;
	  	else
	  		return this.isrunning;
	}
	
	/**
    * 是否运行
    * @return java.lang.String
    */
	public void setIsrunning(java.lang.String isrunning) {
	   this.isrunning = isrunning;
	}
	
	
    /**
    * 物资编码
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 物资编码
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * 总的停机时间
    * @return java.lang.Double
    */
	public java.lang.Double getTotdowntime() {
	  		return this.totdowntime;
	}
	
	/**
    * 总的停机时间
    * @return java.lang.Double
    */
	public void setTotdowntime(java.lang.Double totdowntime) {
	   this.totdowntime = totdowntime;
	}
	
	
    /**
    * 修改时间
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 修改时间
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * 修改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 修改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
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
    * 库存成本
    * @return java.lang.Double
    */
	public java.lang.Double getInvcost() {
	  		return this.invcost;
	}
	
	/**
    * 库存成本
    * @return java.lang.Double
    */
	public void setInvcost(java.lang.Double invcost) {
	   this.invcost = invcost;
	}
	
	
    /**
    * 所属部门
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * 所属部门
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * 货位
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * 货位
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * 父级设备
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父级设备
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
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
    * 是否附属设备
    * @return java.lang.String
    */
	public java.lang.String getAppertain() {
		if(this.appertain==null || this.appertain.length()<=0)
	  		return null;
	  	else
	  		return this.appertain;
	}
	
	/**
    * 是否附属设备
    * @return java.lang.String
    */
	public void setAppertain(java.lang.String appertain) {
	   this.appertain = appertain;
	}
	
	
    /**
    * 合同单号
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * 合同单号
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * 币种
    * @return java.lang.String
    */
	public java.lang.String getCurrency() {
		if(this.currency==null || this.currency.length()<=0)
	  		return null;
	  	else
	  		return this.currency;
	}
	
	/**
    * 币种
    * @return java.lang.String
    */
	public void setCurrency(java.lang.String currency) {
	   this.currency = currency;
	}
	
	
    /**
    * 项目
    * @return java.lang.String
    */
	public java.lang.String getProject() {
		if(this.project==null || this.project.length()<=0)
	  		return null;
	  	else
	  		return this.project;
	}
	
	/**
    * 项目
    * @return java.lang.String
    */
	public void setProject(java.lang.String project) {
	   this.project = project;
	}
	
	
    /**
    * 汇率
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * 汇率
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
	}
	
	
    /**
    * 批次号
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * 批次号
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
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
    * 预计使用年限
    * @return java.lang.Long
    */
	public java.lang.Long getPlanyears() {
	  		return this.planyears;
	}
	
	/**
    * 预计使用年限
    * @return java.lang.Long
    */
	public void setPlanyears(java.lang.Long planyears) {
	   this.planyears = planyears;
	}
	
	
}