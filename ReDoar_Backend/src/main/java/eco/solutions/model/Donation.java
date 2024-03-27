package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.NumberUtils;
import eco.solutions.util.StringUtils;

import java.util.List;

@Table(name = "donation")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Donation extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="donation_seq")
    @SequenceGenerator(name = "donation_seq", sequenceName = "donation_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "numberPersons")
    private int numberPersons;

    @JoinColumn(name = "donor_id", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User donor;

    @Column(name = "donor_id")
    private Long donorId;

    @JoinColumn(name = "receiver_id", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "donation_state")
    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    @JoinColumn(name = "pickup_location_id", referencedColumnName = PickupLocation.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PickupLocation pickupLocation;

    @Column(name = "pickup_location_id")
    private Long pickupLocationId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "donations")
    @JoinTable(
            name = "donation_category",
            joinColumns = { @JoinColumn(name = "donation_id", referencedColumnName = PROPERTY_ID) },
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = Category.PROPERTY_ID) }
    )
    private List<Category> categories;

    public Donation(String designation, String description, int numberPersons, Long donorId, Long receiverId) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setNumberPersons(numberPersons);
        setDonorId(donorId);
        setReceiverId(receiverId);
        this.donationState = DonationState.REQUESTED;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The donation's designation can't be null or empty!");
        }
        this.designation = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The donation's description can't be null or empty!");
        }
        this.description = StringUtils.trim(description);
    }

    public void setNumberPersons(int numberPersons) throws BusinessRuleException {
        if(NumberUtils.isZeroOrNegative(numberPersons)){
            throw new BusinessRuleException("The donation's number of persons can't be less/equal than zero!");
        }
        this.numberPersons = numberPersons;
    }

    public void setDonorId(Long donorId) throws BusinessRuleException {
        if(donorId == null){
            throw new BusinessRuleException("The donation's donor can't be null!");
        }
        this.donorId = donorId;
    }

    public void setReceiverId(Long receiverId) throws BusinessRuleException {
        if(receiverId == null){
            throw new BusinessRuleException("The donation's receiver can't be null!");
        }
        this.receiverId = receiverId;
    }

    public void setDonationState(DonationState donationState) throws BusinessRuleException {
        if(donationState == null){
            throw new BusinessRuleException("The donation's state can't be null!");
        }
        this.donationState = donationState;
    }
    //</editor-fold>
}
