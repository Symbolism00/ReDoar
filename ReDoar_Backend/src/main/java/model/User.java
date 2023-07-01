package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

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

    protected User(){
        // for ORM
    }

    public User(String username, String password) throws BusinessRuleException {
        setUsername(username);
        setPassword(password);
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
    //</editor-fold>
}
