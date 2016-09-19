package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Favorite extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String eqnum;
     private java.lang.String favoriteby;
     private java.util.Date favoritedate;
     private java.lang.String itemnum;
     
    /** default constructor */
    public Favorite(){}
    
   
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
    * 收藏人
    * @return java.lang.String
    */
	public java.lang.String getFavoriteby() {
		if(this.favoriteby==null || this.favoriteby.length()<=0)
	  		return null;
	  	else
	  		return this.favoriteby;
	}
	
	/**
    * 收藏人
    * @return java.lang.String
    */
	public void setFavoriteby(java.lang.String favoriteby) {
	   this.favoriteby = favoriteby;
	}
	
	
    /**
    * 收藏日期
    * @return java.util.Date
    */
	public java.util.Date getFavoritedate() {
	  		return this.favoritedate;
	}
	
	/**
    * 收藏日期
    * @return java.util.Date
    */
	public void setFavoritedate(java.util.Date favoritedate) {
	   this.favoritedate = favoritedate;
	}
	
	
    /**
    * 备件编号
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 备件编号
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
}