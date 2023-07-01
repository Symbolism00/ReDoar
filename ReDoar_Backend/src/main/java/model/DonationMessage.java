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

    protected DonationMessage(){
        // for ORM
    }

    public DonationMessage(String content) throws BusinessRuleException {
        setContent(content);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setContent(String content) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(content)){
            throw new BusinessRuleException("The message's content can't be null or empty!");
        }
        this.content = content;
    }
    //</editor-fold>
}
