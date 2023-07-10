package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

import java.util.List;

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

    @Column(name = "organization_state")
    @Enumerated(EnumType.STRING)
    private OrganizationState organizationState;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<OrganizationClassification> organizationClassifications;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
    @JoinTable(
            name = "organization_pickup_location",
            joinColumns = { @JoinColumn(name = "organization_id", referencedColumnName = PROPERTY_ID) },
            inverseJoinColumns = { @JoinColumn(name = "pickup_location_id", referencedColumnName = PickupLocation.PROPERTY_ID) }
    )
    private List<PickupLocation> pickupLocations;

    protected Organization() {
        // for ORM
    }

    public Organization(String designation, String description, String taxNumber, String phoneNumber, String email, OrganizationState organizationState) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setTaxNumber(taxNumber);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setOrganizationState(organizationState);
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

    public void setOrganizationState(OrganizationState organizationState) throws BusinessRuleException {
        if(organizationState == null){
            throw new BusinessRuleException("The organization's state can't be null!");
        }
        this.organizationState = organizationState;
    }
    //</editor-fold>
}
