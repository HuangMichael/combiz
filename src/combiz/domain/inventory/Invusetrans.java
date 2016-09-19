package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invusetrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double actualcost;
     private java.util.Date actualdate;
     private java.lang.String binnum;
     private java.lang.Double conversion;
     private java.lang.Double curbal;
     private java.lang.String description;
     private java.lang.String enterby;
     private java.lang.String eqnum;
     private java.lang.Double exchangerate;
     private java.lang.Long invrectransid;
     private java.lang.Long issueid;
     private java.lang.String issuetolabor;
     private java.lang.String issuetype;
     private java.lang.String itemnum;
     private java.lang.Double linecost;
     private java.lang.String location;
     private java.lang.String lotnum;
     private java.lang.String matreqnum;
     private java.lang.String memo;
     private java.lang.String packnum;
     private java.lang.Double physcnt;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.Double quantity;
     private java.lang.Double reqqty;
     private java.lang.String roteqnum;
     private java.util.Date transdate;
     private java.lang.Double unitcost;
     private java.lang.String warehouse;
     private java.lang.String wonum;
     private java.lang.String issuedeptnum;
     private java.lang.String state;
     private java.util.Date requestdate;
     private java.util.Date usedate;
     private java.lang.String isprint;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Invusetrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ʵ�ʵ���
    * @return java.lang.Double
    */
	public java.lang.Double getActualcost() {
	  		return this.actualcost;
	}
	
	/**
    * ʵ�ʵ���
    * @return java.lang.Double
    */
	public void setActualcost(java.lang.Double actualcost) {
	   this.actualcost = actualcost;
	}
	
	
    /**
    * ʵ������
    * @return java.util.Date
    */
	public java.util.Date getActualdate() {
	  		return this.actualdate;
	}
	
	/**
    * ʵ������
    * @return java.util.Date
    */
	public void setActualdate(java.util.Date actualdate) {
	   this.actualdate = actualdate;
	}
	
	
    /**
    * �����
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * �����
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public java.lang.Double getConversion() {
	  		return this.conversion;
	}
	
	/**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
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
    * �����Ŀ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * �����Ŀ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ¼����
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * ¼����
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
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
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
	}
	
	
    /**
    * ������
    * @return java.lang.Long
    */
	public java.lang.Long getInvrectransid() {
	  		return this.invrectransid;
	}
	
	/**
    * ������
    * @return java.lang.Long
    */
	public void setInvrectransid(java.lang.Long invrectransid) {
	   this.invrectransid = invrectransid;
	}
	
	
    /**
    * �������
    * @return java.lang.Long
    */
	public java.lang.Long getIssueid() {
	  		return this.issueid;
	}
	
	/**
    * �������
    * @return java.lang.Long
    */
	public void setIssueid(java.lang.Long issueid) {
	   this.issueid = issueid;
	}
	
	
    /**
    * ����Ա��
    * @return java.lang.String
    */
	public java.lang.String getIssuetolabor() {
		if(this.issuetolabor==null || this.issuetolabor.length()<=0)
	  		return null;
	  	else
	  		return this.issuetolabor;
	}
	
	/**
    * ����Ա��
    * @return java.lang.String
    */
	public void setIssuetolabor(java.lang.String issuetolabor) {
	   this.issuetolabor = issuetolabor;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getIssuetype() {
		if(this.issuetype==null || this.issuetype.length()<=0)
	  		return null;
	  	else
	  		return this.issuetype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setIssuetype(java.lang.String issuetype) {
	   this.issuetype = issuetype;
	}
	
	
    /**
    * ���ʱ���
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ���ʱ���
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * �����ܼ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * �����ܼ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ���α��
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * ���α��
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
	}
	
	
    /**
    * �������뵥���
    * @return java.lang.String
    */
	public java.lang.String getMatreqnum() {
		if(this.matreqnum==null || this.matreqnum.length()<=0)
	  		return null;
	  	else
	  		return this.matreqnum;
	}
	
	/**
    * �������뵥���
    * @return java.lang.String
    */
	public void setMatreqnum(java.lang.String matreqnum) {
	   this.matreqnum = matreqnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * װ�䵥���
    * @return java.lang.String
    */
	public java.lang.String getPacknum() {
		if(this.packnum==null || this.packnum.length()<=0)
	  		return null;
	  	else
	  		return this.packnum;
	}
	
	/**
    * װ�䵥���
    * @return java.lang.String
    */
	public void setPacknum(java.lang.String packnum) {
	   this.packnum = packnum;
	}
	
	
    /**
    * ʵ���̵�����
    * @return java.lang.Double
    */
	public java.lang.Double getPhyscnt() {
	  		return this.physcnt;
	}
	
	/**
    * ʵ���̵�����
    * @return java.lang.Double
    */
	public void setPhyscnt(java.lang.Double physcnt) {
	   this.physcnt = physcnt;
	}
	
	
    /**
    * �ɹ�����
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * �ɹ�����
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * �ɹ���
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * �ɹ���
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getReqqty() {
	  		return this.reqqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setReqqty(java.lang.Double reqqty) {
	   this.reqqty = reqqty;
	}
	
	
    /**
    * ��ת�豸���
    * @return java.lang.String
    */
	public java.lang.String getRoteqnum() {
		if(this.roteqnum==null || this.roteqnum.length()<=0)
	  		return null;
	  	else
	  		return this.roteqnum;
	}
	
	/**
    * ��ת�豸���
    * @return java.lang.String
    */
	public void setRoteqnum(java.lang.String roteqnum) {
	   this.roteqnum = roteqnum;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
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
    * ����
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
    /**
    * ����������
    * @return java.lang.String
    */
	public java.lang.String getIssuedeptnum() {
		if(this.issuedeptnum==null || this.issuedeptnum.length()<=0)
	  		return null;
	  	else
	  		return this.issuedeptnum;
	}
	
	/**
    * ����������
    * @return java.lang.String
    */
	public void setIssuedeptnum(java.lang.String issuedeptnum) {
	   this.issuedeptnum = issuedeptnum;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getState() {
		if(this.state==null || this.state.length()<=0)
	  		return null;
	  	else
	  		return this.state;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setState(java.lang.String state) {
	   this.state = state;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
    /**
    * �黹����
    * @return java.util.Date
    */
	public java.util.Date getUsedate() {
	  		return this.usedate;
	}
	
	/**
    * �黹����
    * @return java.util.Date
    */
	public void setUsedate(java.util.Date usedate) {
	   this.usedate = usedate;
	}
	
	
    /**
    * �Ƿ��Ѿ���ӡ
    * @return java.lang.String
    */
	public java.lang.String getIsprint() {
		if(this.isprint==null || this.isprint.length()<=0)
	  		return null;
	  	else
	  		return this.isprint;
	}
	
	/**
    * �Ƿ��Ѿ���ӡ
    * @return java.lang.String
    */
	public void setIsprint(java.lang.String isprint) {
	   this.isprint = isprint;
	}
	
	
    /**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * Ԥ���
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * Ԥ���
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
}