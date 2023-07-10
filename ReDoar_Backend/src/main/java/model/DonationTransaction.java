package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Table(name = "donation_transaction")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DonationTransaction implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="donation_transaction_seq")
    @SequenceGenerator(name = "donation_transaction_seq", sequenceName = "donation_transaction_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @Column(name = "donation_state")
    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    protected DonationTransaction() {
        // for ORM
    }

    public DonationTransaction(Donation donation, DonationState donationState) throws BusinessRuleException {
        setDonation(donation);
        setDonationState(donationState);
        this.transactionDate = new Date();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDonation(Donation donation) throws BusinessRuleException {
        if(donation == null){
            throw new BusinessRuleException("The donation's transaction needs to be part of a donation!");
        }
        this.donation = donation;
    }

    public void setDonationState(DonationState donationState) throws BusinessRuleException {
        if(donationState == null){
            throw new BusinessRuleException("The donation's state needs to be part of a donation!");
        }
        this.donationState = donationState;
    }
    //</editor-fold>
}
