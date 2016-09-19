package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsmenus extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String elementtype;
     private java.lang.String header;
     private java.lang.String image;
     private java.lang.String keyvalue;
     private java.lang.String menutype;
     private java.lang.String moduleapp;
     private java.lang.String parentmod;
     private java.lang.Long position;
     private java.lang.String tabdisplay;
     private java.lang.String url;
     private java.lang.String visible;
     
    // Constructors
    /** default constructor */
    public Ibsmenus(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getElementtype() {
	   return this.elementtype;
	}	    
	public void setElementtype(java.lang.String elementtype) {
	   this.elementtype = elementtype;
	}
	public java.lang.String getHeader() {
	   return this.header;
	}	    
	public void setHeader(java.lang.String header) {
	   this.header = header;
	}
	public java.lang.String getImage() {
	   return this.image;
	}	    
	public void setImage(java.lang.String image) {
	   this.image = image;
	}
	public java.lang.String getKeyvalue() {
	   return this.keyvalue;
	}	    
	public void setKeyvalue(java.lang.String keyvalue) {
	   this.keyvalue = keyvalue;
	}
	public java.lang.String getMenutype() {
	   return this.menutype;
	}	    
	public void setMenutype(java.lang.String menutype) {
	   this.menutype = menutype;
	}
	public java.lang.String getModuleapp() {
	   return this.moduleapp;
	}	    
	public void setModuleapp(java.lang.String moduleapp) {
	   this.moduleapp = moduleapp;
	}
	public java.lang.String getParentmod() {
	   return this.parentmod;
	}	    
	public void setParentmod(java.lang.String parentmod) {
	   this.parentmod = parentmod;
	}
	public java.lang.Long getPosition() {
	   return this.position;
	}	    
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	public java.lang.String getTabdisplay() {
	   return this.tabdisplay;
	}	    
	public void setTabdisplay(java.lang.String tabdisplay) {
	   this.tabdisplay = tabdisplay;
	}
	public java.lang.String getUrl() {
	   return this.url;
	}	    
	public void setUrl(java.lang.String url) {
	   this.url = url;
	}
	public java.lang.String getVisible() {
	   return this.visible;
	}	    
	public void setVisible(java.lang.String visible) {
	   this.visible = visible;
	}
}