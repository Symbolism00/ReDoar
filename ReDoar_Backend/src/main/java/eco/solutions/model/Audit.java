package eco.solutions.model;

import eco.solutions.exceptions.AuditException;
import jakarta.persistence.*;
import lf.sol.genericrepository.model.GenericEntity;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@MappedSuperclass
public class Audit implements GenericEntity {

    @Column(name = "active")
    private boolean active;

    @JoinColumn(name = "ins_user", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User insUser;

    @Column(name = "ins_user")
    private Long insUserId;

    @Column(name = "ins_date")
    private Date insDate;

    @JoinColumn(name = "alt_user", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User altUser;

    @Column(name = "alt_user")
    private Long altUserId;

    @Column(name = "alt_date")
    private Date altDate;

    @Column(name = "version")
    private Long version;

    /**
     * Method that fills the insert entity audit
     * @param insUserId the user identifier that did the operation
     * @throws AuditException if the entity was already inserted
     */
    public void insert(Long insUserId) throws AuditException {
        insert(insUserId, true);
    }

    /**
     * Method that sets the insert entity audit with the option to set the active property
     * @param insUserId the user identifier that did the operation
     * @param active property that indicates if the entity is active or not
     * @throws AuditException if the entity was already inserted
     */
    public void insert(Long insUserId, boolean active) throws AuditException {

        if(version != null){
            throw new AuditException("The entity was already inserted!");
        }

        this.insUserId = insUserId;
        this.insDate = new Date();
        this.version = 1L;
        this.active = active;
    }

    /**
     * Method that sets the update entity audit
     * @param altUserId the user identifier that did the operation
     * @throws AuditException if the entity was not already inserted
     */
    public void update(Long altUserId) throws AuditException {
        update(altUserId, true);
    }

    /**
     * Method that sets the update entity audit with the option to set the active property
     * @param altUserId the user identifier that did the operation
     * @param active property that indicates if the entity is active or not
     * @throws AuditException if the entity was not already inserted
     */
    public void update(Long altUserId, boolean active) throws AuditException {

        if(version == null){
            throw new AuditException("The entity was not already inserted!");
        }

        this.version++;
        this.altDate = new Date();
        this.altUserId = altUserId;
        this.active = active;
    }
}
