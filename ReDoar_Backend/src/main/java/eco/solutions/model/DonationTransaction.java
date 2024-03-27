package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
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
    private final Date transactionDate;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @Column(name = "donation_id")
    private Long donationId;

    @Column(name = "donation_state")
    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    public DonationTransaction(Long donationId, DonationState donationState) throws BusinessRuleException {
        setDonationId(donationId);
        setDonationState(donationState);
        this.transactionDate = new Date();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDonationId(Long donationId) throws BusinessRuleException {
        if(donationId == null){
            throw new BusinessRuleException("The donation's transaction needs to be part of a donation!");
        }
        this.donationId = donationId;
    }

    public void setDonationState(DonationState donationState) throws BusinessRuleException {
        if(donationState == null){
            throw new BusinessRuleException("The donation's state needs to be part of a donation!");
        }
        this.donationState = donationState;
    }
    //</editor-fold>
}
