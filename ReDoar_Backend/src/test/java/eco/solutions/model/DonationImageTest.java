package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DonationImageTest {

    @Test
    public void shouldCreateDonationImageTest() throws BusinessRuleException {
        DonationImage donationImage = new DonationImage("Path", "FileName", "Extension", 10.0, 1L);
        assertEquals("Path", donationImage.getPath());
        assertEquals("FileName", donationImage.getFileName());
        assertEquals("Extension", donationImage.getExtension());
        assertEquals(10.0, donationImage.getSize());
        assertEquals(1L, donationImage.getDonationId());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationImagePathIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () ->
                new DonationImage(str, "FileName", "Extension", 10.0, 1L));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationImageFileNameIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () ->
                new DonationImage("Path", str, "Extension", 10.0, 1L));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDonationImageExtensionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () ->
                new DonationImage("Path", "FileName", str, 10.0, 1L));
    }

    @ParameterizedTest
    @MethodSource("giveZeroAndNegativeNumbers")
    public void shouldFailDonationImageSizeIsZeroOrNegativeTest(double number) {
        assertThrows(BusinessRuleException.class, () ->
                new DonationImage("Path", "FileName", "Extension", number, 1L));
    }

    @Test
    public void shouldFailDonationImageDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () ->
                new DonationImage("Path", "FileName", "Extension", 10.0, null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

    private static Stream<Integer> giveZeroAndNegativeNumbers(){
        return Stream.of(0, -1);
    }

}