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
    * �ղ���
    * @return java.lang.String
    */
	public java.lang.String getFavoriteby() {
		if(this.favoriteby==null || this.favoriteby.length()<=0)
	  		return null;
	  	else
	  		return this.favoriteby;
	}
	
	/**
    * �ղ���
    * @return java.lang.String
    */
	public void setFavoriteby(java.lang.String favoriteby) {
	   this.favoriteby = favoriteby;
	}
	
	
    /**
    * �ղ�����
    * @return java.util.Date
    */
	public java.util.Date getFavoritedate() {
	  		return this.favoritedate;
	}
	
	/**
    * �ղ�����
    * @return java.util.Date
    */
	public void setFavoritedate(java.util.Date favoritedate) {
	   this.favoritedate = favoritedate;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
}