package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountyTest {

    @Test
    public void shouldCreateCountyTest() throws BusinessRuleException {
        County county = new County("Designation ", "Description ", 1L);
        assertEquals("Designation", county.getDesignation());
        assertEquals("Description", county.getDescription());
        assertEquals(1L, county.getDistrictId());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCountyDesignationIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new County(str, "Description", 1L));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCountyDescriptionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new County("Designation", str, 1L));
    }

    @Test
    public void shouldFailCountyDistrictIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new County("Designation", "Description", null));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}