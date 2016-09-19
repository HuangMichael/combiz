package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assetsdepreciationline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetscode;
     private java.lang.String assetslocation;
     private java.lang.String assetsmon;
     private java.lang.String assetsname;
     private java.lang.Double assetssum;
     private java.lang.String assetsyear;
     private java.lang.String hasparent;
     private java.lang.String status;
     
    /** default constructor */
    public Assetsdepreciationline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 编号
    * @return java.lang.String
    */
	public java.lang.String getAssetscode() {
		if(this.assetscode==null || this.assetscode.length()<=0)
	  		return null;
	  	else
	  		return this.assetscode;
	}
	
	/**
    * 编号
    * @return java.lang.String
    */
	public void setAssetscode(java.lang.String assetscode) {
	   this.assetscode = assetscode;
	}
	
	
    /**
    * 位置
    * @return java.lang.String
    */
	public java.lang.String getAssetslocation() {
		if(this.assetslocation==null || this.assetslocation.length()<=0)
	  		return null;
	  	else
	  		return this.assetslocation;
	}
	
	/**
    * 位置
    * @return java.lang.String
    */
	public void setAssetslocation(java.lang.String assetslocation) {
	   this.assetslocation = assetslocation;
	}
	
	
    /**
    * 月
    * @return java.lang.String
    */
	public java.lang.String getAssetsmon() {
		if(this.assetsmon==null || this.assetsmon.length()<=0)
	  		return null;
	  	else
	  		return this.assetsmon;
	}
	
	/**
    * 月
    * @return java.lang.String
    */
	public void setAssetsmon(java.lang.String assetsmon) {
	   this.assetsmon = assetsmon;
	}
	
	
    /**
    * 名称
    * @return java.lang.String
    */
	public java.lang.String getAssetsname() {
		if(this.assetsname==null || this.assetsname.length()<=0)
	  		return null;
	  	else
	  		return this.assetsname;
	}
	
	/**
    * 名称
    * @return java.lang.String
    */
	public void setAssetsname(java.lang.String assetsname) {
	   this.assetsname = assetsname;
	}
	
	
    /**
    * 净值
    * @return java.lang.Double
    */
	public java.lang.Double getAssetssum() {
	  		return this.assetssum;
	}
	
	/**
    * 净值
    * @return java.lang.Double
    */
	public void setAssetssum(java.lang.Double assetssum) {
	   this.assetssum = assetssum;
	}
	
	
    /**
    * 年
    * @return java.lang.String
    */
	public java.lang.String getAssetsyear() {
		if(this.assetsyear==null || this.assetsyear.length()<=0)
	  		return null;
	  	else
	  		return this.assetsyear;
	}
	
	/**
    * 年
    * @return java.lang.String
    */
	public void setAssetsyear(java.lang.String assetsyear) {
	   this.assetsyear = assetsyear;
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
	
	
}