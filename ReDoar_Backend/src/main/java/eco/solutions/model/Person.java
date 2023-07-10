package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

import java.util.List;

@Table(name = "person")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Person extends Audit{

    protected static final String PROPERTY_ID = "id";
    private static final String ENTITY_BUSINESS_RULE_EXCEPTION = "person's";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "reliability_level")
    @Enumerated(EnumType.STRING)
    private ReliabilityLevel reliabilityLevel;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<PersonReliability> personReliabilities;

    protected Person(){
        // for ORM
    }

    public Person(String name, String phoneNumber, String email, ReliabilityLevel reliabilityLevel, User user) throws BusinessRuleException {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setReliabilityLevel(reliabilityLevel);
        setUser(user);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setName(String name) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(name)){
            throw new BusinessRuleException("The person's name can't be null or empty!");
        }
        this.name = StringUtils.trim(name);
    }

    public void setPhoneNumber(String phoneNumber) throws BusinessRuleException {
        String errorMessage = StringUtils.isValidPhoneNumber(phoneNumber, ENTITY_BUSINESS_RULE_EXCEPTION);
        if(errorMessage != null){
            throw new BusinessRuleException(errorMessage);
        }
        this.phoneNumber = StringUtils.trim(phoneNumber);
    }

    public void setEmail(String email) throws BusinessRuleException {
        String errorMessage = StringUtils.isValidEmail(email, ENTITY_BUSINESS_RULE_EXCEPTION);
        if(errorMessage != null){
            throw new BusinessRuleException(errorMessage);
        }
        this.email = StringUtils.trim(email);
    }

    public void setReliabilityLevel(ReliabilityLevel reliabilityLevel) throws BusinessRuleException {
        if(reliabilityLevel == null){
            throw new BusinessRuleException("The person's reliability level can't be null!");
        }
        this.reliabilityLevel = reliabilityLevel;
    }

    public void setUser(User user) throws BusinessRuleException {
        if(user == null){
            throw new BusinessRuleException("The person's user can't be null!");
        }
        this.user = user;
    }
    //</editor-fold>
}
