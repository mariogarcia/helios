package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.maxOfString;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class MaxOfStringTest {

    @Test public void testMaxOfString() {
        String name = "John";
        List<ValidatorError> errors = validate("name", name, maxOfString(3));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "string.max.notmet");
    }
}
