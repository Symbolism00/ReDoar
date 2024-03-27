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

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<PersonReliability> personReliabilities;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JoinTable(
            name = "person_category",
            joinColumns = { @JoinColumn(name = "person_id", referencedColumnName = PROPERTY_ID) },
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = Category.PROPERTY_ID) }
    )
    private List<Category> categories;

    public Person(String name, String phoneNumber, String email, ReliabilityLevel reliabilityLevel, Long userId) throws BusinessRuleException {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setReliabilityLevel(reliabilityLevel);
        setUserId(userId);
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

    public void setUserId(Long userId) throws BusinessRuleException {
        if(userId == null){
            throw new BusinessRuleException("The person's user can't be null!");
        }
        this.userId = userId;
    }
    //</editor-fold>
}
