package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsportlets extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Long colpos;
     private java.lang.String ctrlpage;
     private java.lang.String ispercent;
     private java.lang.String pagepath;
     private java.lang.Long plheight;
     private java.lang.String pltitle;
     private java.lang.Long plwidth;
     private java.lang.String portal;
     private java.lang.String portlet;
     private java.lang.String portlettype;
     private java.lang.String remark;
     private java.lang.Long rowpos;
     
    /** default constructor */
    public Ibsportlets(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 列坐标
    * @return java.lang.Long
    */
	public java.lang.Long getColpos() {
	  		return this.colpos;
	}
	
	/**
    * 列坐标
    * @return java.lang.Long
    */
	public void setColpos(java.lang.Long colpos) {
	   this.colpos = colpos;
	}
	
	
    /**
    * 管理页面
    * @return java.lang.String
    */
	public java.lang.String getCtrlpage() {
		if(this.ctrlpage==null || this.ctrlpage.length()<=0)
	  		return null;
	  	else
	  		return this.ctrlpage;
	}
	
	/**
    * 管理页面
    * @return java.lang.String
    */
	public void setCtrlpage(java.lang.String ctrlpage) {
	   this.ctrlpage = ctrlpage;
	}
	
	
    /**
    * 宽度是否按照百分比
    * @return java.lang.String
    */
	public java.lang.String getIspercent() {
		if(this.ispercent==null || this.ispercent.length()<=0)
	  		return null;
	  	else
	  		return this.ispercent;
	}
	
	/**
    * 宽度是否按照百分比
    * @return java.lang.String
    */
	public void setIspercent(java.lang.String ispercent) {
	   this.ispercent = ispercent;
	}
	
	
    /**
    * 页面相对地址：/portlets/xxx.xx
    * @return java.lang.String
    */
	public java.lang.String getPagepath() {
		if(this.pagepath==null || this.pagepath.length()<=0)
	  		return null;
	  	else
	  		return this.pagepath;
	}
	
	/**
    * 页面相对地址：/portlets/xxx.xx
    * @return java.lang.String
    */
	public void setPagepath(java.lang.String pagepath) {
	   this.pagepath = pagepath;
	}
	
	
    /**
    * 高度
    * @return java.lang.Long
    */
	public java.lang.Long getPlheight() {
	  		return this.plheight;
	}
	
	/**
    * 高度
    * @return java.lang.Long
    */
	public void setPlheight(java.lang.Long plheight) {
	   this.plheight = plheight;
	}
	
	
    /**
    * 标题
    * @return java.lang.String
    */
	public java.lang.String getPltitle() {
		if(this.pltitle==null || this.pltitle.length()<=0)
	  		return null;
	  	else
	  		return this.pltitle;
	}
	
	/**
    * 标题
    * @return java.lang.String
    */
	public void setPltitle(java.lang.String pltitle) {
	   this.pltitle = pltitle;
	}
	
	
    /**
    * 宽度
    * @return java.lang.Long
    */
	public java.lang.Long getPlwidth() {
	  		return this.plwidth;
	}
	
	/**
    * 宽度
    * @return java.lang.Long
    */
	public void setPlwidth(java.lang.Long plwidth) {
	   this.plwidth = plwidth;
	}
	
	
    /**
    * 门户名称
    * @return java.lang.String
    */
	public java.lang.String getPortal() {
		if(this.portal==null || this.portal.length()<=0)
	  		return null;
	  	else
	  		return this.portal;
	}
	
	/**
    * 门户名称
    * @return java.lang.String
    */
	public void setPortal(java.lang.String portal) {
	   this.portal = portal;
	}
	
	
    /**
    * 门户窗口
    * @return java.lang.String
    */
	public java.lang.String getPortlet() {
		if(this.portlet==null || this.portlet.length()<=0)
	  		return null;
	  	else
	  		return this.portlet;
	}
	
	/**
    * 门户窗口
    * @return java.lang.String
    */
	public void setPortlet(java.lang.String portlet) {
	   this.portlet = portlet;
	}
	
	
    /**
    * 门户窗口类别
    * @return java.lang.String
    */
	public java.lang.String getPortlettype() {
		if(this.portlettype==null || this.portlettype.length()<=0)
	  		return null;
	  	else
	  		return this.portlettype;
	}
	
	/**
    * 门户窗口类别
    * @return java.lang.String
    */
	public void setPortlettype(java.lang.String portlettype) {
	   this.portlettype = portlettype;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * 行坐标
    * @return java.lang.Long
    */
	public java.lang.Long getRowpos() {
	  		return this.rowpos;
	}
	
	/**
    * 行坐标
    * @return java.lang.Long
    */
	public void setRowpos(java.lang.Long rowpos) {
	   this.rowpos = rowpos;
	}
	
	
}