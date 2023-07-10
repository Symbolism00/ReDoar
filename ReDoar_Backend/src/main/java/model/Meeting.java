package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.DateUtils;

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

    @Column(name = "has_occured")
    private boolean hasOccured;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @JoinColumn(name = "address_id", referencedColumnName = Address.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    protected Meeting(){
        // for ORM
    }

    public Meeting(Date meetingDatetime, Donation donation, Address address) throws BusinessRuleException {
        setMeetingDatetime(meetingDatetime);
        setDonation(donation);
        setAddress(address);
        this.hasOccured = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMeetingDatetime(Date meetingDatetime) throws BusinessRuleException {
        if(meetingDatetime == null || !DateUtils.isAfterCurrentDate(meetingDatetime, DateUtils.YYYY_MM_DD_HH_MI)){
            throw new BusinessRuleException("The meeting's date time can't be null or before/equal the current date!");
        }
        this.meetingDatetime = meetingDatetime;
    }

    public void setDonation(Donation donation) throws BusinessRuleException {
        if(donation == null){
            throw new BusinessRuleException("The meeting's donation can't be null!");
        }
        this.donation = donation;
    }

    public void setAddress(Address address) throws BusinessRuleException {
        if(address == null){
            throw new BusinessRuleException("The meeting's address can't be null!");
        }
        this.address = address;
    }
    //</editor-fold>
}
