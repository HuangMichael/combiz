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
    * ���
    * @return java.lang.String
    */
	public java.lang.String getAssetscode() {
		if(this.assetscode==null || this.assetscode.length()<=0)
	  		return null;
	  	else
	  		return this.assetscode;
	}
	
	/**
    * ���
    * @return java.lang.String
    */
	public void setAssetscode(java.lang.String assetscode) {
	   this.assetscode = assetscode;
	}
	
	
    /**
    * λ��
    * @return java.lang.String
    */
	public java.lang.String getAssetslocation() {
		if(this.assetslocation==null || this.assetslocation.length()<=0)
	  		return null;
	  	else
	  		return this.assetslocation;
	}
	
	/**
    * λ��
    * @return java.lang.String
    */
	public void setAssetslocation(java.lang.String assetslocation) {
	   this.assetslocation = assetslocation;
	}
	
	
    /**
    * ��
    * @return java.lang.String
    */
	public java.lang.String getAssetsmon() {
		if(this.assetsmon==null || this.assetsmon.length()<=0)
	  		return null;
	  	else
	  		return this.assetsmon;
	}
	
	/**
    * ��
    * @return java.lang.String
    */
	public void setAssetsmon(java.lang.String assetsmon) {
	   this.assetsmon = assetsmon;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getAssetsname() {
		if(this.assetsname==null || this.assetsname.length()<=0)
	  		return null;
	  	else
	  		return this.assetsname;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setAssetsname(java.lang.String assetsname) {
	   this.assetsname = assetsname;
	}
	
	
    /**
    * ��ֵ
    * @return java.lang.Double
    */
	public java.lang.Double getAssetssum() {
	  		return this.assetssum;
	}
	
	/**
    * ��ֵ
    * @return java.lang.Double
    */
	public void setAssetssum(java.lang.Double assetssum) {
	   this.assetssum = assetssum;
	}
	
	
    /**
    * ��
    * @return java.lang.String
    */
	public java.lang.String getAssetsyear() {
		if(this.assetsyear==null || this.assetsyear.length()<=0)
	  		return null;
	  	else
	  		return this.assetsyear;
	}
	
	/**
    * ��
    * @return java.lang.String
    */
	public void setAssetsyear(java.lang.String assetsyear) {
	   this.assetsyear = assetsyear;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getHasparent() {
		if(this.hasparent==null || this.hasparent.length()<=0)
	  		return null;
	  	else
	  		return this.hasparent;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setHasparent(java.lang.String hasparent) {
	   this.hasparent = hasparent;
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
	
	
}