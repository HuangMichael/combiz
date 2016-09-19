package combiz.domain.tool;

import combiz.system.IBOBaseObject;

public class Toolspec extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String classattr;
     private java.lang.String classid;
     private java.lang.String ismustbe;
     private java.lang.Double numvalue;
     private java.lang.String remark;
     private java.lang.String strvalue;
     private java.lang.String toolnum;
     private java.lang.String unitid;
     
    /** default constructor */
    public Toolspec(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 技术参数属性
    * @return java.lang.String
    */
	public java.lang.String getClassattr() {
		if(this.classattr==null || this.classattr.length()<=0)
	  		return null;
	  	else
	  		return this.classattr;
	}
	
	/**
    * 技术参数属性
    * @return java.lang.String
    */
	public void setClassattr(java.lang.String classattr) {
	   this.classattr = classattr;
	}
	
	
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
    * 必须有值
    * @return java.lang.String
    */
	public java.lang.String getIsmustbe() {
		if(this.ismustbe==null || this.ismustbe.length()<=0)
	  		return null;
	  	else
	  		return this.ismustbe;
	}
	
	/**
    * 必须有值
    * @return java.lang.String
    */
	public void setIsmustbe(java.lang.String ismustbe) {
	   this.ismustbe = ismustbe;
	}
	
	
    /**
    * 数值
    * @return java.lang.Double
    */
	public java.lang.Double getNumvalue() {
	  		return this.numvalue;
	}
	
	/**
    * 数值
    * @return java.lang.Double
    */
	public void setNumvalue(java.lang.Double numvalue) {
	   this.numvalue = numvalue;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * 字符串值
    * @return java.lang.String
    */
	public java.lang.String getStrvalue() {
		if(this.strvalue==null || this.strvalue.length()<=0)
	  		return null;
	  	else
	  		return this.strvalue;
	}
	
	/**
    * 字符串值
    * @return java.lang.String
    */
	public void setStrvalue(java.lang.String strvalue) {
	   this.strvalue = strvalue;
	}
	
	
    /**
    * 工具编号
    * @return java.lang.String
    */
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}
	
	/**
    * 工具编号
    * @return java.lang.String
    */
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
	
	
    /**
    * 计量单位
    * @return java.lang.String
    */
	public java.lang.String getUnitid() {
		if(this.unitid==null || this.unitid.length()<=0)
	  		return null;
	  	else
	  		return this.unitid;
	}
	
	/**
    * 计量单位
    * @return java.lang.String
    */
	public void setUnitid(java.lang.String unitid) {
	   this.unitid = unitid;
	}
	
	
}