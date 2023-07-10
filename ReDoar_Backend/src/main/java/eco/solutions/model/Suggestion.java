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
    private boolean wasRead;

    @Column(name = "read_date")
    private Date readDate;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected Suggestion(){
        // for ORM
    }

    public Suggestion(String message, User user) throws BusinessRuleException {
        setMessage(message);
        this.wasRead = false;
        setUser(user);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMessage(String message) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(message)){
            throw new BusinessRuleException("The suggestion's message can't be null or empty!");
        }
        this.message = message;
    }

    public void setUser(User user) throws BusinessRuleException {
        if(user == null){
            throw new BusinessRuleException("The suggestion's user can't be null!");
        }
        this.user = user;
    }
    //</editor-fold>
}
