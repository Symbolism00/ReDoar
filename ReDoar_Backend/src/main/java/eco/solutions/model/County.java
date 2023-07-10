package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.StringUtils;

@Table(name = "county")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class County extends Audit{

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="county_seq")
    @SequenceGenerator(name = "county_seq", sequenceName = "county_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "district_id", referencedColumnName = District.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    protected County() {
        // for ORM
    }

    public County(String designation, String description, District district) throws BusinessRuleException {
        setDesignation(designation);
        setDescription(description);
        setDistrict(district);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setDesignation(String designation) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(designation)){
            throw new BusinessRuleException("The county's designation can't be null or empty!");
        }
        this.designation = StringUtils.trim(designation);
    }

    public void setDescription(String description) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(description)){
            throw new BusinessRuleException("The county's description can't be null or empty!");
        }
        this.description = StringUtils.trim(description);
    }

    public void setDistrict(District district) throws BusinessRuleException {
        if(district == null){
            throw new BusinessRuleException("The county's district can't be null!");
        }
        this.district = district;
    }
    //</editor-fold>
}
