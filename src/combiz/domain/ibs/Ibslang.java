package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibslang extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String langkey;
     private java.lang.String langpkg;
     private java.lang.String message;
     
    /** default constructor */
    public Ibslang(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 语言KEY
    * @return java.lang.String
    */
	public java.lang.String getLangkey() {
		if(this.langkey==null || this.langkey.length()<=0)
	  		return null;
	  	else
	  		return this.langkey;
	}
	
	/**
    * 语言KEY
    * @return java.lang.String
    */
	public void setLangkey(java.lang.String langkey) {
	   this.langkey = langkey;
	}
	
	
    /**
    * 语言包名
    * @return java.lang.String
    */
	public java.lang.String getLangpkg() {
		if(this.langpkg==null || this.langpkg.length()<=0)
	  		return null;
	  	else
	  		return this.langpkg;
	}
	
	/**
    * 语言包名
    * @return java.lang.String
    */
	public void setLangpkg(java.lang.String langpkg) {
	   this.langpkg = langpkg;
	}
	
	
    /**
    * 语言文本
    * @return java.lang.String
    */
	public java.lang.String getMessage() {
		if(this.message==null || this.message.length()<=0)
	  		return null;
	  	else
	  		return this.message;
	}
	
	/**
    * 语言文本
    * @return java.lang.String
    */
	public void setMessage(java.lang.String message) {
	   this.message = message;
	}
	
	
}