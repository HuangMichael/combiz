package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Eqsparepart extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double culbar;
     private java.lang.String description;
     private java.lang.String eqnum;
     private java.lang.String itemnum;
     private java.lang.Double quantity;
     
    /** default constructor */
    public Eqsparepart(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 库存数量
    * @return java.lang.Double
    */
	public java.lang.Double getCulbar() {
	  		return this.culbar;
	}
	
	/**
    * 库存数量
    * @return java.lang.Double
    */
	public void setCulbar(java.lang.Double culbar) {
	   this.culbar = culbar;
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
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 库存编码
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存编码
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * 数量
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * 数量
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
}