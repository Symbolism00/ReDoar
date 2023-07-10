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
    private String feedbackMessage;

    @Column(name = "feedback_date")
    private Date feedbackDate;

    @Column(name = "reliability_level")
    @Enumerated(EnumType.STRING)
    private ReliabilityLevel reliabilityLevel;

    @JoinColumn(name = "person_id", referencedColumnName = Person.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected PersonReliability() {
        // for ORM
    }

    public PersonReliability(String feedbackMessage, ReliabilityLevel reliabilityLevel, Person person, User user) throws BusinessRuleException {
        this.feedbackMessage = feedbackMessage;
        setReliabilityLevel(reliabilityLevel);
        setPerson(person);
        setUser(user);
        this.feedbackDate = new Date();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setReliabilityLevel(ReliabilityLevel reliabilityLevel) throws BusinessRuleException {
        if(reliabilityLevel == null){
            throw new BusinessRuleException("The person's reliability level can't be null!");
        }
        this.reliabilityLevel = reliabilityLevel;
    }

    public void setPerson(Person person) throws BusinessRuleException {
        if(person == null){
            throw new BusinessRuleException("The person's reliability can't be null!");
        }
        this.person = person;
    }

    public void setUser(User user) throws BusinessRuleException {
        if(user == null){
            throw new BusinessRuleException("The person's reliability user can't be null!");
        }
        this.user = user;
    }
    //</editor-fold>
}
