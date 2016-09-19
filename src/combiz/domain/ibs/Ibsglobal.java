package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsglobal extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String ibsvalue;
     private java.lang.String ibsvalue2;
     private java.lang.String ibsvalue3;
     private java.lang.String ibsvar;
     private java.lang.String memo;
     private java.lang.String vartype;
     
    /** default constructor */
    public Ibsglobal(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 值
    * @return java.lang.String
    */
	public java.lang.String getIbsvalue() {
		if(this.ibsvalue==null || this.ibsvalue.length()<=0)
	  		return null;
	  	else
	  		return this.ibsvalue;
	}
	
	/**
    * 值
    * @return java.lang.String
    */
	public void setIbsvalue(java.lang.String ibsvalue) {
	   this.ibsvalue = ibsvalue;
	}
	
	
    /**
    * 扩展值
    * @return java.lang.String
    */
	public java.lang.String getIbsvalue2() {
		if(this.ibsvalue2==null || this.ibsvalue2.length()<=0)
	  		return null;
	  	else
	  		return this.ibsvalue2;
	}
	
	/**
    * 扩展值
    * @return java.lang.String
    */
	public void setIbsvalue2(java.lang.String ibsvalue2) {
	   this.ibsvalue2 = ibsvalue2;
	}
	
	
    /**
    * 扩展值3
    * @return java.lang.String
    */
	public java.lang.String getIbsvalue3() {
		if(this.ibsvalue3==null || this.ibsvalue3.length()<=0)
	  		return null;
	  	else
	  		return this.ibsvalue3;
	}
	
	/**
    * 扩展值3
    * @return java.lang.String
    */
	public void setIbsvalue3(java.lang.String ibsvalue3) {
	   this.ibsvalue3 = ibsvalue3;
	}
	
	
    /**
    * 变量
    * @return java.lang.String
    */
	public java.lang.String getIbsvar() {
		if(this.ibsvar==null || this.ibsvar.length()<=0)
	  		return null;
	  	else
	  		return this.ibsvar;
	}
	
	/**
    * 变量
    * @return java.lang.String
    */
	public void setIbsvar(java.lang.String ibsvar) {
	   this.ibsvar = ibsvar;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * 系统、用户
    * @return java.lang.String
    */
	public java.lang.String getVartype() {
		if(this.vartype==null || this.vartype.length()<=0)
	  		return null;
	  	else
	  		return this.vartype;
	}
	
	/**
    * 系统、用户
    * @return java.lang.String
    */
	public void setVartype(java.lang.String vartype) {
	   this.vartype = vartype;
	}
	
	
}