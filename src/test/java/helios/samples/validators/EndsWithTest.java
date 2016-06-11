package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.endsWith;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class EndsWithTest {

    @Test public void testEndsWith() {
        String name = "John";
        List<ValidatorError> errors = validate("name", name, endsWith("N"));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "endswith");
    }
}
