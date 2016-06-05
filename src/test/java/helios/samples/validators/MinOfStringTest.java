package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.minOfString;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class MinOfStringTest {

    @Test public void testMinOfList() {
        String name = "John";
        List<ValidatorError> errors = validate("name", name, minOfString(5));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "string.min.notmet");
    }
}
