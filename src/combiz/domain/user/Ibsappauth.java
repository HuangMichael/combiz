package combiz.domain.user;



/**
 * Ibsappauth generated by MyEclipse - Hibernate Tools
 */

public class Ibsappauth  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String grpname;
     private String app;
     private String keyvalue;


    // Constructors

    /** default constructor */
    public Ibsappauth() {
    }

    
    /** full constructor */
    public Ibsappauth(String grpname, String app, String keyvalue) {
        this.grpname = grpname;
        this.app = app;
        this.keyvalue = keyvalue;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getGrpname() {
        return this.grpname;
    }
    
    public void setGrpname(String grpname) {
        this.grpname = grpname;
    }

    public String getApp() {
        return this.app;
    }
    
    public void setApp(String app) {
        this.app = app;
    }

    public String getKeyvalue() {
        return this.keyvalue;
    }
    
    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }
   

}