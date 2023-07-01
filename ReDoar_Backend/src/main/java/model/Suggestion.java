package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

import java.util.Date;

@Table(name = "suggestion")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Suggestion extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="suggestion_seq")
    @SequenceGenerator(name = "suggestion_seq", sequenceName = "suggestion_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "was_read")
    private boolean wasRead;

    @Column(name = "read_date")
    private Date readDate;

    protected Suggestion(){
        // for ORM
    }

    public Suggestion(String message) throws BusinessRuleException {
        setMessage(message);
        this.wasRead = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMessage(String message) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(message)){
            throw new BusinessRuleException("The suggestion's message can't be null or empty!");
        }
        this.message = message;
    }
    //</editor-fold>
}
