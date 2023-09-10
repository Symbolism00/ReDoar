package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.NumberUtils;
import eco.solutions.util.StringUtils;

import java.math.BigDecimal;

@Table(name = "address")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Address extends Audit{

    protected static final String PROPERTY_ID = "id";
    private static final String ENTITY_BUSINESS_RULE_EXCEPTION = "address'";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "door_number")
    private Integer doorNumber;

    @Column(name = "floor")
    private String floor;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @JoinColumn(name = "parish_id", referencedColumnName = Parish.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parish parish;

    @JoinColumn(name = "person_id", referencedColumnName = Person.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @JoinColumn(name = "organization_id", referencedColumnName = Organization.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    protected Address() {
        // for ORM
    }

    public Address(String street, Integer doorNumber, String floor, String zipCode, BigDecimal latitude, BigDecimal longitude, Parish parish) throws BusinessRuleException {
        setStreet(street);
        setDoorNumber(doorNumber);
        setFloor(floor);
        setZipCode(zipCode);
        setLatitude(latitude);
        setLongitude(longitude);
        setParish(parish);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setStreet(String street) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(street)){
            throw new BusinessRuleException("The address' street can't be null or empty!");
        }
        this.street = StringUtils.trim(street);
    }

    public void setDoorNumber(Integer doorNumber) throws BusinessRuleException {
        if(doorNumber == null || NumberUtils.isZeroOrNegative(doorNumber)){
            throw new BusinessRuleException("The address' door number can't be null or less/equal than zero!");
        }
        this.doorNumber = doorNumber;
    }

    public void setFloor(String floor) {
        this.floor = floor != null ? StringUtils.trim(floor) : null;
    }

    public void setZipCode(String zipCode) throws BusinessRuleException {
        String errorMessage = StringUtils.isValidZipCode(zipCode, ENTITY_BUSINESS_RULE_EXCEPTION);
        if(errorMessage != null){
            throw new BusinessRuleException(errorMessage);
        }
        this.zipCode = StringUtils.trim(zipCode);
    }

    public void setLatitude(BigDecimal latitude) throws BusinessRuleException {
        if(latitude == null){
            throw new BusinessRuleException("The address' latitude can't be null or empty!");
        }
        if(!NumberUtils.isValidLatitude(latitude)){
            throw new BusinessRuleException("The address' latitude " + latitude + " is not valid!");
        }
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) throws BusinessRuleException {
        if(longitude == null){
            throw new BusinessRuleException("The address' longitude can't be null or empty!");
        }
        if(!NumberUtils.isValidLongitude(longitude)){
            throw new BusinessRuleException("The address' longitude " + longitude + " is not valid!");
        }
        this.longitude = longitude;
    }

    public void setParish(Parish parish) throws BusinessRuleException {
        if(parish == null){
            throw new BusinessRuleException("The address' parish can't be null!");
        }
        this.parish = parish;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    //</editor-fold>
}
