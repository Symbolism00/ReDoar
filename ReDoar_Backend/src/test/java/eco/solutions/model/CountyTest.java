package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountyTest {

    private static District district;

    @BeforeAll
    public static void setUp(){
        district = new District();
    }

    @Test
    public void shouldCreateCountyTest() throws BusinessRuleException {
        County county = new County("Designation ", "Description ", district);
        assertEquals("Designation", county.getDesignation());
        assertEquals("Description", county.getDescription());
        assertEquals(district, county.getDistrict());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCountyDesignationIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new County(str, "Description", district));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCountyDescriptionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new County("Designation", str, district));
    }

    @Test
    public void shouldFailCountyDistrictIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new County("Designation", "Description", null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}