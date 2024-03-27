package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DonationMessageTest {

    @Test
    public void shouldCreateDonationMessageTest() throws BusinessRuleException {
        DonationMessage donationMessage = new DonationMessage("Content", 1L, 1L);
        assertEquals("Content", donationMessage.getContent());
        assertEquals(1L, donationMessage.getDonationId());
        assertEquals(1L, donationMessage.getUserId());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationMessageContentIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage(str, 1L, 1L));
    }

    @Test
    public void shouldFailDonationMessageDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage("Content", null, 1L));
    }

    @Test
    public void shouldFailDonationMessageContentIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new DonationMessage("Content", 1L, null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}