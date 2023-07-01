package model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Table(name = "donation_transaction")
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DonationTransaction implements Serializable {

    protected static final String PROPERTY_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="donation_transaction_seq")
    @SequenceGenerator(name = "donation_transaction_seq", sequenceName = "donation_transaction_seq", initialValue = 100, allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    protected DonationTransaction() {
        // for ORM
    }
}
