package eco.solutions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonationStateTest {

    @Test
    public void shouldGetDonationStateByIdTest(){
        DonationState donationState = DonationState.getById(0);
        assertEquals(DonationState.CREATED, donationState);

        DonationState donationState2 = DonationState.getById(1);
        assertEquals(DonationState.REQUESTED, donationState2);

        DonationState donationState3 = DonationState.getById(2);
        assertEquals(DonationState.ACCEPTED, donationState3);

        DonationState donationState4 = DonationState.getById(3);
        assertEquals(DonationState.REJECTED, donationState4);

        DonationState donationState5 = DonationState.getById(4);
        assertEquals(DonationState.ONGOING, donationState5);

        DonationState donationState6 = DonationState.getById(5);
        assertEquals(DonationState.FINISHED, donationState6);

        DonationState donationState7 = DonationState.getById(6);
        assertEquals(DonationState.CANCELED, donationState7);

        DonationState donationState8 = DonationState.getById(7);
        assertEquals(DonationState.EXPIRED, donationState8);
    }

}