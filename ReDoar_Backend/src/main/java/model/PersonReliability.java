package model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Table(name = "person_reliability")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PersonReliability implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_reliability_seq")
    @SequenceGenerator(name = "person_reliability_seq", sequenceName = "person_reliability_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "feedback_message")
    private String feedbackMessage;

    @Column(name = "feedback_date")
    private Date feedbackDate;

    protected PersonReliability() {
        // for ORM
    }

    public PersonReliability(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
        this.feedbackDate = new Date();
    }
}
