package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

@Table(name = "parish")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Parish extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="parish_seq")
    @SequenceGenerator(name = "parish_seq", sequenceName = "parish_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    protected Parish() {
        // for ORM
    }

    public Parish(String designation, String description) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The parish's designation can't be null or empty!");
        }
        this.designation = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The parish's description can't be null or empty!");
        }
        this.description = StringUtils.trim(description);
    }
    //</editor-fold>
}
