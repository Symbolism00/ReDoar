package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.NumberUtils;
import util.StringUtils;

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

    protected DonationImage() {
        // for ORM
    }

    public DonationImage(String path, String fileName, String extension, double size) throws BusinessRuleException {
        setPath(path);
        setFileName(fileName);
        setExtension(extension);
        setSize(size);
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
    //</editor-fold>
}
