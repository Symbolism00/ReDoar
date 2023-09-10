package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DonationMessageTest {

    private static Donation donation;
    private static User user;

    @BeforeAll
    public static void setUp(){
        donation = new Donation();
        user = new User();
    }

    @Test
    public void shouldCreateDonationMessageTest() throws BusinessRuleException {
        DonationMessage donationMessage = new DonationMessage("Content", donation, user);
        assertEquals("Content", donationMessage.getContent());
        assertEquals(donation, donationMessage.getDonation());
        assertEquals(user, donationMessage.getUser());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationMessageContentIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage(str, donation, user));
    }

    @Test
    public void shouldFailDonationMessageDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage("Content", null, user));
    }

    @Test
    public void shouldFailDonationMessageContentIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage("Content", donation, null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}