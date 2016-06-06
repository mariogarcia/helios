package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inRangeOfString;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class InRangeOfStringTest {

    @Test public void testInRangeOfString() {
        String name = "airport";

        List<ValidatorError> minErrors = validate("strings", name, inRangeOfString(8,10));
        List<ValidatorError> maxErrors = validate("strings", name, inRangeOfString(0,3));

        assertEquals(minErrors.get(0).key, "string.min.notmet");
        assertEquals(maxErrors.get(0).key, "string.max.notmet");
    }
}
