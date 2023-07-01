package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

@Table(name = "organization")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Organization extends Audit{

    protected static final String PROPERTY_ID = "id";
    private static final String ENTITY_BUSINESS_RULE_EXCEPTION = "organization's";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="organization_seq")
    @SequenceGenerator(name = "organization_seq", sequenceName = "organization_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "classification")
    private Double classification;

    protected Organization() {
        // for ORM
    }

    public Organization(String designation, String description, String taxNumber, String phoneNumber, String email) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setTaxNumber(taxNumber);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The organization's designation can't be null or empty!");
        }
        this.designation = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The organization's description can't be null or empty!");
        }
        this.description = StringUtils.trim(description);
    }

    public void setTaxNumber(String taxNumber) throws BusinessRuleException {
        String errorMessage = StringUtils.isValidTaxNumber(taxNumber, ENTITY_BUSINESS_RULE_EXCEPTION);
        if(errorMessage != null){
            throw new BusinessRuleException(errorMessage);
        }
        this.taxNumber = StringUtils.trim(taxNumber);
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
