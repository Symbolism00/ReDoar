package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

import java.util.Date;

@Table(name = "suggestion")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Suggestion extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="suggestion_seq")
    @SequenceGenerator(name = "suggestion_seq", sequenceName = "suggestion_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "was_read")
    private final boolean wasRead;

    @Column(name = "read_date")
    private Date readDate;

    @JoinColumn(name = "person_id", referencedColumnName = Person.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "person_id")
    private Long personId;

    public Suggestion(String message, Long personId) throws BusinessRuleException {
        setMessage(message);
        this.wasRead = false;
        setPersonId(personId);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMessage(String message) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(message)){
            throw new BusinessRuleException("The suggestion's message can't be null or empty!");
        }
        this.message = message;
    }

    public void setPersonId(Long personId) throws BusinessRuleException {
        if(personId == null){
            throw new BusinessRuleException("The suggestion's person can't be null!");
        }
        this.personId = personId;
    }
    //</editor-fold>
}
