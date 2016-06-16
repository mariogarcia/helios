package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

public class InListTest {

    @Test public void testInList() {
        String value = "Dublin";
        List<String> cities = Arrays.asList("Paris", "London", "Berlin", "Oslo");

        List<ValidatorError> errors =
            validate("value", value, inList(cities));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "inList");
    }
}
