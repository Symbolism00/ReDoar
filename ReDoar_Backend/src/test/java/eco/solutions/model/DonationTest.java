package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DonationTest {

    private static User userA;
    private static User userB;

    @BeforeAll
    public static void setUp(){
        userA = new User();
        userB = new User();
    }

    @Test
    public void shouldCreateDonationTest() throws BusinessRuleException {
        Donation donation = new Donation("Designation ", "Description ", 1, userA, userB);
        assertEquals("Designation", donation.getDesignation());
        assertEquals("Description", donation.getDescription());
        assertEquals(1, donation.getNumberPersons());
        assertEquals(userA, donation.getDonor());
        assertEquals(userB, donation.getReceiver());
        assertEquals(DonationState.REQUESTED, donation.getDonationState());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationDesignationIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new Donation(str, "Description", 1, userA, userB));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationDescriptionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new Donation("Designation", str, 1, userA, userB));
    }

    @ParameterizedTest
    @MethodSource("giveZeroAndNegativeNumbers")
    public void shouldFailDonationNumberPersonsIsZeroOrNegativeTest(int number) {
        assertThrows(BusinessRuleException.class, () -> new Donation("Designation", "Description", number, userA, userB));
    }

    @Test
    public void shouldFailDonationDonorIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Donation("Designation", "Description", 1, null, userB));
    }

    @Test
    public void shouldFailDonationReceiverIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Donation("Designation", "Description", 1, userA, null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

    private static Stream<Integer> giveZeroAndNegativeNumbers(){
        return Stream.of(0, -1);
    }

}