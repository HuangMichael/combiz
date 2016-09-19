package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invreport extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classid;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String modelnum;
     private java.util.Date thisdate;
     private java.lang.Double invuseqty;
     private java.lang.Double invusecost;
     private java.lang.String warehouse;
     private java.lang.Double curbal;
     private java.lang.Double linecost;
     private java.lang.Double invrecqty;
     private java.lang.Double invrecvcost;
     private java.lang.Double lastmonqty;
     private java.lang.Double lastmoncost;
     private java.lang.Long orderdesc;
     private java.lang.Double avgcost;
     
    /** default constructor */
    public Invreport(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ���ʱ������
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * ���ʱ������
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * �����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �����Ŀ
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ���
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * ���
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * ״̬����
    * @return java.util.Date
    */
	public java.util.Date getThisdate() {
	  		return this.thisdate;
	}
	
	/**
    * ״̬����
    * @return java.util.Date
    */
	public void setThisdate(java.util.Date thisdate) {
	   this.thisdate = thisdate;
	}
	
	
    /**
    * ����ʹ������
    * @return java.lang.Double
    */
	public java.lang.Double getInvuseqty() {
	  		return this.invuseqty;
	}
	
	/**
    * ����ʹ������
    * @return java.lang.Double
    */
	public void setInvuseqty(java.lang.Double invuseqty) {
	   this.invuseqty = invuseqty;
	}
	
	
    /**
    * ����ʹ�óɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getInvusecost() {
	  		return this.invusecost;
	}
	
	/**
    * ����ʹ�óɱ�
    * @return java.lang.Double
    */
	public void setInvusecost(java.lang.Double invusecost) {
	   this.invusecost = invusecost;
	}
	
	
    /**
    * �ֿ�
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ֿ�
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
    /**
    * ��ǰ����
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * ��ǰ����
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
	}
	
	
    /**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * �ܳɱ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ������������
    * @return java.lang.Double
    */
	public java.lang.Double getInvrecqty() {
	  		return this.invrecqty;
	}
	
	/**
    * ������������
    * @return java.lang.Double
    */
	public void setInvrecqty(java.lang.Double invrecqty) {
	   this.invrecqty = invrecqty;
	}
	
	
    /**
    * ���������ܽ��
    * @return java.lang.Double
    */
	public java.lang.Double getInvrecvcost() {
	  		return this.invrecvcost;
	}
	
	/**
    * ���������ܽ��
    * @return java.lang.Double
    */
	public void setInvrecvcost(java.lang.Double invrecvcost) {
	   this.invrecvcost = invrecvcost;
	}
	
	
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getLastmonqty() {
	  		return this.lastmonqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setLastmonqty(java.lang.Double lastmonqty) {
	   this.lastmonqty = lastmonqty;
	}
	
	
    /**
    * �����ܽ��
    * @return java.lang.Double
    */
	public java.lang.Double getLastmoncost() {
	  		return this.lastmoncost;
	}
	
	/**
    * �����ܽ��
    * @return java.lang.Double
    */
	public void setLastmoncost(java.lang.Double lastmoncost) {
	   this.lastmoncost = lastmoncost;
	}
	
	
    /**
    * �����
    * @return java.lang.Long
    */
	public java.lang.Long getOrderdesc() {
	  		return this.orderdesc;
	}
	
	/**
    * �����
    * @return java.lang.Long
    */
	public void setOrderdesc(java.lang.Long orderdesc) {
	   this.orderdesc = orderdesc;
	}
	
	
    /**
    * ƽ���۸�
    * @return java.lang.Double
    */
	public java.lang.Double getAvgcost() {
	  		return this.avgcost;
	}
	
	/**
    * ƽ���۸�
    * @return java.lang.Double
    */
	public void setAvgcost(java.lang.Double avgcost) {
	   this.avgcost = avgcost;
	}
	
	
}