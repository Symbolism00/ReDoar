package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

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

    protected Person(){
        // for ORM
    }

    public Person(String name, String phoneNumber, String email) throws BusinessRuleException {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
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
    //</editor-fold>
}
