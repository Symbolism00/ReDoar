package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.NumberUtils;

import java.io.Serializable;
import java.util.Date;

@Table(name = "organization_classification")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class OrganizationClassification implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="organization_classification_seq")
    @SequenceGenerator(name = "organization_classification_seq", sequenceName = "organization_classification_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "feedback_message")
    private String feedbackMessage;

    @Column(name = "classification")
    private int classification;

    @Column(name = "feedback_date")
    private Date feedbackDate;

    @JoinColumn(name = "user_id", referencedColumnName = User.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected OrganizationClassification(){
        // for ORM
    }

    public OrganizationClassification(String feedbackMessage, int classification, User user) throws BusinessRuleException {
        setClassification(classification);
        this.feedbackMessage = feedbackMessage;
        this.feedbackDate = new Date();
        setUser(user);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setClassification(int classification) throws BusinessRuleException {
        if(NumberUtils.isZeroOrNegative(classification)){
            throw new BusinessRuleException("The organization's classification can't be less than zero!");
        }
        this.classification = classification;
    }

    public void setUser(User user) throws BusinessRuleException {
        if(user == null){
            throw new BusinessRuleException("The organization's classification user can't be null!");
        }
        this.user = user;
    }
    //</editor-fold>
}
