package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assettree extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetcode;
     private java.lang.String assetname;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String eqnum;
     private java.lang.String hasparent;
     private java.lang.Long isasset;
     
    /** default constructor */
    public Assettree(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 设备编号
    * @return java.lang.String
    */
	public java.lang.String getAssetcode() {
		if(this.assetcode==null || this.assetcode.length()<=0)
	  		return null;
	  	else
	  		return this.assetcode;
	}
	
	/**
    * 设备编号
    * @return java.lang.String
    */
	public void setAssetcode(java.lang.String assetcode) {
	   this.assetcode = assetcode;
	}
	
	
    /**
    * 名称
    * @return java.lang.String
    */
	public java.lang.String getAssetname() {
		if(this.assetname==null || this.assetname.length()<=0)
	  		return null;
	  	else
	  		return this.assetname;
	}
	
	/**
    * 名称
    * @return java.lang.String
    */
	public void setAssetname(java.lang.String assetname) {
	   this.assetname = assetname;
	}
	
	
    /**
    * 人员
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 人员
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 日期
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 日期
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * 编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 父级
    * @return java.lang.String
    */
	public java.lang.String getHasparent() {
		if(this.hasparent==null || this.hasparent.length()<=0)
	  		return null;
	  	else
	  		return this.hasparent;
	}
	
	/**
    * 父级
    * @return java.lang.String
    */
	public void setHasparent(java.lang.String hasparent) {
	   this.hasparent = hasparent;
	}
	
	
    /**
    * 是否
    * @return java.lang.Long
    */
	public java.lang.Long getIsasset() {
	  		return this.isasset;
	}
	
	/**
    * 是否
    * @return java.lang.Long
    */
	public void setIsasset(java.lang.Long isasset) {
	   this.isasset = isasset;
	}
	
	
}