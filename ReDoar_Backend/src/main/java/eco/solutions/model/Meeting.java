package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.DateUtils;

import java.util.Date;

@Table(name = "meeting")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Meeting extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="meeting_seq")
    @SequenceGenerator(name = "meeting_seq", sequenceName = "meeting_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "meeting_datetime")
    private Date meetingDatetime;

    @Column(name = "has_occurred")
    private final boolean hasOccurred;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @Column(name = "donation_id")
    private Long donationId;

    @JoinColumn(name = "address_id", referencedColumnName = Address.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "address_id")
    private Long addressId;

    public Meeting(Date meetingDatetime, Long donationId, Long addressId) throws BusinessRuleException {
        setMeetingDatetime(meetingDatetime);
        setDonationId(donationId);
        setAddressId(addressId);
        this.hasOccurred = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMeetingDatetime(Date meetingDatetime) throws BusinessRuleException {
        if(meetingDatetime == null || !DateUtils.isAfterCurrentDate(meetingDatetime, DateUtils.YYYY_MM_DD_HH_MI)){
            throw new BusinessRuleException("The meeting's date time can't be null or before/equal the current date!");
        }
        this.meetingDatetime = meetingDatetime;
    }

    public void setDonationId(Long donationId) throws BusinessRuleException {
        if(donationId == null){
            throw new BusinessRuleException("The meeting's donation can't be null!");
        }
        this.donationId = donationId;
    }

    public void setAddressId(Long addressId) throws BusinessRuleException {
        if(addressId == null){
            throw new BusinessRuleException("The meeting's address can't be null!");
        }
        this.addressId = addressId;
    }
    //</editor-fold>
}
