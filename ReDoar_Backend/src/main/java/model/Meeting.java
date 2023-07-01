package model;

import exceptions.BusinessRuleException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.DateUtils;

import java.util.Date;

@Table(name = "meeting")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Meeting extends Audit{

    protected static final String PROPERTY_ID = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="meeting_seq")
    @SequenceGenerator(name = "meeting_seq", sequenceName = "meeting_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "meeting_datetime")
    private Date meetingDatetime;

    @Column(name = "has_occured")
    private boolean hasOccured;

    protected Meeting(){
        // for ORM
    }

    public Meeting(Date meetingDatetime) throws BusinessRuleException {
        setMeetingDatetime(meetingDatetime);
        this.hasOccured = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setMeetingDatetime(Date meetingDatetime) throws BusinessRuleException {
        if(meetingDatetime == null || !DateUtils.isAfterCurrentDate(meetingDatetime, DateUtils.YYYY_MM_DD_HH_MI)){
            throw new BusinessRuleException("The meeting's date time can't be null or before/equal the current date!");
        }
        this.meetingDatetime = meetingDatetime;
    }
    //</editor-fold>
}
