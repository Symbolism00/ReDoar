package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void shouldCreateAddressTest() throws BusinessRuleException {
        Address address = new Address("Street ", 1, "Floor ", "4444-444 ",
                BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L);
        assertEquals("Street", address.getStreet());
        assertEquals(1, address.getDoorNumber());
        assertEquals("Floor", address.getFloor());
        assertEquals("4444-444", address.getZipCode());
        assertEquals(BigDecimal.valueOf(41.123456), address.getLatitude());
        assertEquals(BigDecimal.valueOf(-8.123456), address.getLongitude());
        assertEquals(1L, address.getParishId());
    }

    @Test
    public void shouldCreateAddressLatitudeMaxValueTest() throws BusinessRuleException {
        Address address = new Address("Street", 1, "Floor", "4444-444",
                BigDecimal.valueOf(90.0), BigDecimal.valueOf(-8.123456), 1L);
        assertEquals(BigDecimal.valueOf(90.0), address.getLatitude());
    }

    @Test
    public void shouldCreateAddressLatitudeMinValueTest() throws BusinessRuleException {
        Address address = new Address("Street", 1, "Floor", "4444-444",
                BigDecimal.valueOf(-90.0), BigDecimal.valueOf(-8.123456), 1L);
        assertEquals(BigDecimal.valueOf(-90.0), address.getLatitude());
    }

    @Test
    public void shouldCreateAddressLongitudeMaxValueTest() throws BusinessRuleException {
        Address address = new Address("Street", 1, "Floor", "4444-444",
                BigDecimal.valueOf(41.123456), BigDecimal.valueOf(180.0), 1L);
        assertEquals(BigDecimal.valueOf(180.0), address.getLongitude());
    }

    @Test
    public void shouldCreateAddressLongitudeMinValueTest() throws BusinessRuleException {
        Address address = new Address("Street", 1, "Floor", "4444-444",
                BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-180.0), 1L);
        assertEquals(BigDecimal.valueOf(-180.0), address.getLongitude());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailAddressStreetIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class,
                () -> new Address(str, 1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressDoorNumberIsZeroTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 0, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressDoorNumberIsLessThanZeroTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", -1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailAddressZipCodeIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", str,
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressZipCodeIsInvalidTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressLatitudeIsNullTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        null, BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressLatitudeIsHigherTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(90.1), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressLatitudeIsLowerTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(-90.1), BigDecimal.valueOf(-8.123456), 1L));
    }

    @Test
    public void shouldFailAddressLongitudeIsNullTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), null, 1L));
    }

    @Test
    public void shouldFailAddressLongitudeIsHigherTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(180.1), 1L));
    }

    @Test
    public void shouldFailAddressLongitudeIsLowerTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-180.1), 1L));
    }

    @Test
    public void shouldFailAddressParishIsNullTest() {
        assertThrows(BusinessRuleException.class,
                () -> new Address("Street", 1, "Floor", "4444-444",
                        BigDecimal.valueOf(41.123456), BigDecimal.valueOf(-8.123456), null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}