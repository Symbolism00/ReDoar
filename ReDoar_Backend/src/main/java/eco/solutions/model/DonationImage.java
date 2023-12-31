package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import eco.solutions.util.NumberUtils;
import eco.solutions.util.StringUtils;

import java.io.Serializable;

@Table(name = "donation_image")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DonationImage implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="donation_image_seq")
    @SequenceGenerator(name = "donation_image_seq", sequenceName = "donation_image_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "extension")
    private String extension;

    @Column(name = "size")
    private double size;

    @JoinColumn(name = "donation_id", referencedColumnName = Donation.PROPERTY_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Donation donation;
    protected DonationImage() {
        // for ORM
    }

    public DonationImage(String path, String fileName, String extension, double size, Donation donation) throws BusinessRuleException {
        setPath(path);
        setFileName(fileName);
        setExtension(extension);
        setSize(size);
        setDonation(donation);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setPath(String path) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(path)){
            throw new BusinessRuleException("The donation image's path can't be null or empty!");
        }
        this.path = StringUtils.trim(path);
    }

    public void setFileName(String fileName) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(fileName)){
            throw new BusinessRuleException("The donation image's file name can't be null or empty!");
        }
        this.fileName = StringUtils.trim(fileName);
    }

    public void setExtension(String extension) throws BusinessRuleException {
        if(StringUtils.isNullOrEmpty(extension)){
            throw new BusinessRuleException("The donation image's extension can't be null or empty!");
        }
        this.extension = StringUtils.trim(extension);
    }

    public void setSize(double size) throws BusinessRuleException {
        if(NumberUtils.isZeroOrNegative(size)){
            throw new BusinessRuleException("The donation image's size can't be less/equal than zero!");
        }
        this.size = size;
    }

    public void setDonation(Donation donation) throws BusinessRuleException {
        if(donation == null){
            throw new BusinessRuleException("The donation's image needs to be part of a donation!");
        }
        this.donation = donation;
    }
    //</editor-fold>
}
