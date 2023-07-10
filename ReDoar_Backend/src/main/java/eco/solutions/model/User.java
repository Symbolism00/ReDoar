package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

import java.util.List;

@Table(name = "user")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends Audit {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JoinTable(
            name = "user_category",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = PROPERTY_ID) },
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = Category.PROPERTY_ID) }
    )
    private List<Category> categories;

    protected User(){
        // for ORM
    }

    public User(String username, String password, UserRole userRole) throws BusinessRuleException {
        setUsername(username);
        setPassword(password);
        setUserRole(userRole);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setUsername(String username) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(username)){
            throw new BusinessRuleException("The user's username can't be null or empty!");
        }
        this.username = StringUtils.trim(username);
    }

    public void setPassword(String password) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(password)){
            throw new BusinessRuleException("The user's password can't be null or empty!");
        }
        this.password = StringUtils.trim(password);
    }

    public void setUserRole(UserRole userRole) throws BusinessRuleException {
        if(userRole == null){
            throw new BusinessRuleException("The user's role can't be null!");
        }
        this.userRole = userRole;
    }
    //</editor-fold>
}
