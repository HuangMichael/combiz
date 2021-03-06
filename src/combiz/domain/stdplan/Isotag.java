package combiz.domain.stdplan;



/**
 * Isotag generated by MyEclipse - Hibernate Tools
 */

public class Isotag  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String isotagid;
     private String description;
     private String location;
     private String eqnum;
     private Long aplyseq;
     private String state;


    // Constructors

    /** default constructor */
    public Isotag() {
    }

	/** minimal constructor */
    public Isotag(String isotagid, Long aplyseq) {
        this.isotagid = isotagid;
        this.aplyseq = aplyseq;
    }
    
    /** full constructor */
    public Isotag(String isotagid, String description, String location, String eqnum, Long aplyseq, String state) {
        this.isotagid = isotagid;
        this.description = description;
        this.location = location;
        this.eqnum = eqnum;
        this.aplyseq = aplyseq;
        this.state = state;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsotagid() {
        return this.isotagid;
    }
    
    public void setIsotagid(String isotagid) {
        this.isotagid = isotagid;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getEqnum() {
        return this.eqnum;
    }
    
    public void setEqnum(String eqnum) {
        this.eqnum = eqnum;
    }

    public Long getAplyseq() {
        return this.aplyseq;
    }
    
    public void setAplyseq(Long aplyseq) {
        this.aplyseq = aplyseq;
    }

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
   








}