package eco.solutions.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@MappedSuperclass
public class Audit implements Serializable {

    @Column(name = "active")
    private boolean active;

    @Column(name = "ins_user")
    private User insUser;

    @Column(name = "ins_date")
    private Date insDate;

    @Column(name = "alt_user")
    private User altUser;

    @Column(name = "alt_date")
    private Date altDate;

    @Column(name = "version")
    private Long version;

    /**
     * Method that fills the insert entity audit
     * @param insUser the user that did the operation
     */
    public void insert(User insUser){
        insert(insUser, true);
    }

    /**
     * Method that sets the insert entity audit with the option to set the active property
     * @param insUser the user that did the operation
     * @param active property that indicates if the entity is active or not
     */
    public void insert(User insUser, boolean active){
        this.insUser = insUser;
        this.insDate = new Date();
        this.version = 1L;
        this.active = active;
    }

    /**
     * Method that sets the update entity audit
     * @param altUser the user that did the operation
     */
    public void update(User altUser){
        update(altUser, true);
    }

    /**
     * Method that sets the update entity audit with the option to set the active property
     * @param altUser the user that did the operation
     * @param active property that indicates if the entity is active or not
     */
    public void update(User altUser, boolean active){
        this.version++;
        this.altDate = new Date();
        this.altUser = altUser;
        this.active = active;
    }
}
