package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {

    @Test
    public void shouldCreateDistrictTest() throws BusinessRuleException {
        District district = new District("Designation ", "Description ");
        assertEquals("Designation", district.getDesignation());
        assertEquals("Description", district.getDescription());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDistrictDesignationIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new District(str, "Description"));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailDistrictDescriptionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class, () -> new District("Designation", str));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }
}