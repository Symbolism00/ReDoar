package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonationTransactionTest {

    private static Donation donation;

    @BeforeAll
    public static void setUp(){
        donation = new Donation();
    }

    @Test
    public void shouldCreateDonationTransactionTest() throws BusinessRuleException {
        DonationTransaction donationTransaction = new DonationTransaction(donation, DonationState.CANCELED);
        assertEquals(donation, donationTransaction.getDonation());
        assertEquals(DonationState.CANCELED, donationTransaction.getDonationState());
        assertNotNull(donationTransaction.getTransactionDate());
    }

    @Test
    public void shouldFailDonationTransactionDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationTransaction(null, DonationState.ACCEPTED));
    }

    @Test
    public void shouldFailDonationTransactionDonationStateIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationTransaction(donation, null));
    }

}
