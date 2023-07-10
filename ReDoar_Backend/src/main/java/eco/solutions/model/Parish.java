package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

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

    @JoinColumn(name = "county_id", referencedColumnName = County.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private County county;

    protected Parish() {
        // for ORM
    }

    public Parish(String designation, String description, County county) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setCounty(county);
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

    public void setCounty(County county) throws BusinessRuleException {
        if(county == null){
            throw new BusinessRuleException("The parish's county can't be null!");
        }
        this.county = county;
    }
    //</editor-fold>
}
