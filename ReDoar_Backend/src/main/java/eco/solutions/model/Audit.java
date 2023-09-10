package eco.solutions.model;

import eco.solutions.exceptions.AuditException;
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
     * @throws AuditException if the entity was already inserted
     */
    public void insert(User insUser) throws AuditException {
        insert(insUser, true);
    }

    /**
     * Method that sets the insert entity audit with the option to set the active property
     * @param insUser the user that did the operation
     * @param active property that indicates if the entity is active or not
     * @throws AuditException if the entity was already inserted
     */
    public void insert(User insUser, boolean active) throws AuditException {

        if(version != null){
            throw new AuditException("The entity was already inserted!");
        }

        this.insUser = insUser;
        this.insDate = new Date();
        this.version = 1L;
        this.active = active;
    }

    /**
     * Method that sets the update entity audit
     * @param altUser the user that did the operation
     * @throws AuditException if the entity was not already inserted
     */
    public void update(User altUser) throws AuditException {
        update(altUser, true);
    }

    /**
     * Method that sets the update entity audit with the option to set the active property
     * @param altUser the user that did the operation
     * @param active property that indicates if the entity is active or not
     * @throws AuditException if the entity was not already inserted
     */
    public void update(User altUser, boolean active) throws AuditException {

        if(version == null){
            throw new AuditException("The entity was not already inserted!");
        }

        this.version++;
        this.altDate = new Date();
        this.altUser = altUser;
        this.active = active;
    }
}
