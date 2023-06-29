package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.StringUtils;

@Table(name = "category")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", initialValue = 100, allocationSize = 1)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    protected Category(){
        // for ORM
    }

    public Category(String code, String designation, String description) throws BusinessRuleException {
        setCode(code);
        setDesignation(designation);
        setDescription(description);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setCode(String code) throws BusinessRuleException{
        if(StringUtils.isNullOrEmpty(code)){
            throw new BusinessRuleException("The category's code can't be null or empty!");
        }
        this.code = StringUtils.trim(code);
    }

    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The category's designation can't be null or empty!");
        }
        this.designation = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The category's description can't be null or empty!");
        }
        this.description = StringUtils.trim(description);
    }
    //</editor-fold>
}
