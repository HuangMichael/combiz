package combiz.domain.failure;



/**
 * Failurecause generated by MyEclipse - Hibernate Tools
 */

public class Failurecause  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String failureproblem;
     private String failurecause;
     private String description;


    // Constructors

    /** default constructor */
    public Failurecause() {
    }

	/** minimal constructor */
    public Failurecause(String failureproblem, String failurecause) {
        this.failureproblem = failureproblem;
        this.failurecause = failurecause;
    }
    
    /** full constructor */
    public Failurecause(String failureproblem, String failurecause, String description) {
        this.failureproblem = failureproblem;
        this.failurecause = failurecause;
        this.description = description;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getFailureproblem() {
        return this.failureproblem;
    }
    
    public void setFailureproblem(String failureproblem) {
        this.failureproblem = failureproblem;
    }

    public String getFailurecause() {
        return this.failurecause;
    }
    
    public void setFailurecause(String failurecause) {
        this.failurecause = failurecause;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   








}