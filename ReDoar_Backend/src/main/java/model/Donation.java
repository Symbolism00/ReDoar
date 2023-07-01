package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.NumberUtils;
import util.StringUtils;

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

    protected Donation(){
        // for ORM
    }

    public Donation(String designation, String description, int numberPersons) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setNumberPersons(numberPersons);
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
    //</editor-fold>
}
