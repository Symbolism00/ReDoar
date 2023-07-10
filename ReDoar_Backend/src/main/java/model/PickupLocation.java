package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.DateUtils;
import util.StringUtils;

import java.util.Date;
import java.util.List;

@Table(name = "pickup_location")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PickupLocation extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pickup_location_seq")
    @SequenceGenerator(name = "pickup_location_seq", sequenceName = "pickup_location_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "open_hour")
    private Date openHour;

    @Column(name = "close_hour")
    private Date closeHour;

    @JoinColumn(name = "address_id", referencedColumnName = Address.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "pickupLocations")
    private List<Organization> organizations;

    protected PickupLocation(){
        // for ORM
    }

    public PickupLocation(String code, String designation, String description, Date openHour, Date closeHour, Address address) throws BusinessRuleException {
        setCode(code);
        setDesignation(designation);
        setDescription(description);
        setOpenHour(openHour);
        setCloseHour(closeHour);
        setAddress(address);
        checkHours();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setCode(String code) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(code)){
            throw new BusinessRuleException("The pickup location's code can't be null or empty!");
        }
        this.code = StringUtils.trim(code);
    }

    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The pickup location's designation can't be null or empty!");
        }
        this.code = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The pickup location's description can't be null or empty!");
        }
        this.code = StringUtils.trim(description);
    }

    public void setOpenHour(Date openHour) throws BusinessRuleException {
        if(openHour == null){
            throw new BusinessRuleException("The pickup location's open hour can't be null!");
        }
        this.openHour = openHour;
    }

    public void setCloseHour(Date closeHour) throws BusinessRuleException {
        if(closeHour == null){
            throw new BusinessRuleException("The pickup location's close hour can't be null!");
        }
        this.closeHour = closeHour;
    }

    public void setAddress(Address address) throws BusinessRuleException {
        if(address == null){
            throw new BusinessRuleException("The pickup location's address can't be null!");
        }
        this.address = address;
    }
    //</editor-fold>

    /**
     * Method that checks if the open and close hour are well-defined
     * @throws BusinessRuleException if they are not well-defined
     */
    private void checkHours() throws BusinessRuleException {
        // if the open hour is after the close hour
        if(DateUtils.isAfter(openHour, closeHour, DateUtils.HH_MI)){
            throw new BusinessRuleException("The pickup location's open hour can't be after the close hour!");
        }
    }
}
