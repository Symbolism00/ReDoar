package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void shouldCreateCategoryTest() throws BusinessRuleException {
        Category category = new Category("Code ", "Designation ", "Description ");
        assertEquals("Code", category.getCode());
        assertEquals("Designation", category.getDesignation());
        assertEquals("Description", category.getDescription());
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCategoryDesignationIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class,
                () -> new Category("Code", str, "Description"));
    }

    @ParameterizedTest
    @MethodSource("giveNullAndEmptyStrings")
    public void shouldFailCategoryDescriptionIsNullOrEmptyTest(String str) {
        assertThrows(BusinessRuleException.class,
                () -> new Category("Code", "Designation", str));
    }

    private static Stream<String> giveNullAndEmptyStrings(){
        return Stream.of("", null, " ");
    }

}