package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

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

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected DonationMessage(){
        // for ORM
    }

    public DonationMessage(String content, Donation donation, User user) throws BusinessRuleException {
        setContent(content);
        setDonation(donation);
        setUser(user);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setContent(String content) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(content)){
            throw new BusinessRuleException("The message's content can't be null or empty!");
        }
        this.content = content;
    }

    public void setDonation(Donation donation) throws BusinessRuleException {
        if(donation == null){
            throw new BusinessRuleException("The message needs to be part of a donation!");
        }
        this.donation = donation;
    }

    public void setUser(User user) throws BusinessRuleException {
        if(user == null){
            throw new BusinessRuleException("The donation message's user can't be null!");
        }
        this.user = user;
    }
    //</editor-fold>
}
