package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

@Table(name = "donation_message")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DonationMessage extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="donation_message_seq")
    @SequenceGenerator(name = "donation_message_seq", sequenceName = "donation_message_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @Column(name = "donation_id")
    private Long donationId;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public DonationMessage(String content, Long donationId, Long userId) throws BusinessRuleException {
        setContent(content);
        setDonationId(donationId);
        setUserId(userId);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setContent(String content) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(content)){
            throw new BusinessRuleException("The message's content can't be null or empty!");
        }
        this.content = content;
    }

    public void setDonationId(Long donationId) throws BusinessRuleException {
        if(donationId == null){
            throw new BusinessRuleException("The message needs to be part of a donation!");
        }
        this.donationId = donationId;
    }

    public void setUserId(Long userId) throws BusinessRuleException {
        if(userId == null){
            throw new BusinessRuleException("The donation message's user can't be null!");
        }
        this.userId = userId;
    }
    //</editor-fold>
}
