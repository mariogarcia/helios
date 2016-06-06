package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.max;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class MaxNumberTest {

    @Test public void testMaxNumber() {
        Integer numberToValidate = 10;
        List<ValidatorError> errors = validate("top", numberToValidate, max(5));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "max.notmet");
    }
}
