package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.startsWith;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class StartsWithTest {

    @Test public void testStartsWith() {
        String name = "John";
        List<ValidatorError> errors = validate("name", name, startsWith("X"));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "startswith");
    }
}
