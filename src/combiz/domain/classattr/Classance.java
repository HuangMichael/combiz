package combiz.domain.classattr;

import combiz.system.IBOBaseObject;

public class Classance extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ancestor;
     private java.lang.String classid;
     private java.lang.String classtype;
     
    /** default constructor */
    public Classance(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 祖先
    * @return java.lang.String
    */
	public java.lang.String getAncestor() {
		if(this.ancestor==null || this.ancestor.length()<=0)
	  		return null;
	  	else
	  		return this.ancestor;
	}
	
	/**
    * 祖先
    * @return java.lang.String
    */
	public void setAncestor(java.lang.String ancestor) {
	   this.ancestor = ancestor;
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
    * 类型
    * @return java.lang.String
    */
	public java.lang.String getClasstype() {
		if(this.classtype==null || this.classtype.length()<=0)
	  		return null;
	  	else
	  		return this.classtype;
	}
	
	/**
    * 类型
    * @return java.lang.String
    */
	public void setClasstype(java.lang.String classtype) {
	   this.classtype = classtype;
	}
	
	
}