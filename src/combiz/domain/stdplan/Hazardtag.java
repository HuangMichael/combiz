package combiz.domain.stdplan;



/**
 * Hazard generated by MyEclipse - Hibernate Tools
 */

public class Hazardtag  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String hazardid;
     private String isotagid;
	public String getHazardid()
	{
		return hazardid;
	}
	public void setHazardid(String hazardid)
	{
		this.hazardid = hazardid;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getIsotagid()
	{
		return isotagid;
	}
	public void setIsotagid(String isotagid)
	{
		this.isotagid = isotagid;
	}
}