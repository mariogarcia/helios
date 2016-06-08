package helios.samples.helios;

import static org.junit.Assert.assertEquals;

import static helios.Validators.matches;
import static helios.Validators.required;
import static helios.Validators.inRangeOfString;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

import helios.Helios;
import helios.Validator;
import helios.ValidatorError;

public class HeliosListTest {
    // tag::test[]
    @Test public void testValidatorList() {
        List<Validator<String>> validators = Arrays.asList(required(),matches("s.*")); // <1>
        List<ValidatorError> errorList = Helios.validate("word", "aloha", validators); // <2>

        assertEquals(errorList.get(0).key, "matches");
        assertEquals(errorList.get(0).property, "word");
        assertEquals(errorList.get(0).keyI18n, "word.matches");
    }
    // tag::test[]
}
