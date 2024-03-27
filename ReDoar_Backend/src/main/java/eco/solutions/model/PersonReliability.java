package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Table(name = "person_reliability")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PersonReliability implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_reliability_seq")
    @SequenceGenerator(name = "person_reliability_seq", sequenceName = "person_reliability_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "feedback_message")
    private final String feedbackMessage;

    @Column(name = "feedback_date")
    private final Date feedbackDate;

    @Column(name = "reliability_level")
    @Enumerated(EnumType.STRING)
    private ReliabilityLevel reliabilityLevel;

    @JoinColumn(name = "person_id", referencedColumnName = Person.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "person_id")
    private Long personId;

    @JoinColumn(name = "evaluator_id", referencedColumnName = Person.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Person evaluator;

    @Column(name = "evaluator_id")
    private Long evaluatorId;

    public PersonReliability(String feedbackMessage, ReliabilityLevel reliabilityLevel, Long personId, Long evaluatorId) throws BusinessRuleException {
        this.feedbackMessage = feedbackMessage;
        setReliabilityLevel(reliabilityLevel);
        setPersonId(personId);
        setEvaluatorId(evaluatorId);
        this.feedbackDate = new Date();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setReliabilityLevel(ReliabilityLevel reliabilityLevel) throws BusinessRuleException {
        if(reliabilityLevel == null){
            throw new BusinessRuleException("The person's reliability level can't be null!");
        }
        this.reliabilityLevel = reliabilityLevel;
    }

    public void setPersonId(Long personId) throws BusinessRuleException {
        if(personId == null){
            throw new BusinessRuleException("The person's reliability person can't be null!");
        }
        this.personId = personId;
    }

    public void setEvaluatorId(Long evaluatorId) throws BusinessRuleException {
        if(evaluatorId == null){
            throw new BusinessRuleException("The person's reliability evaluator can't be null!");
        }
        this.evaluatorId = evaluatorId;
    }
    //</editor-fold>
}
