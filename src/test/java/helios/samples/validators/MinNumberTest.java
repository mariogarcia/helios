package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.min;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class MinNumberTest {

    @Test public void testMinNumber() {
        Integer numberToValidate = 4;
        List<ValidatorError> errors = validate("top", numberToValidate, min(5));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "min.notmet");
    }
}
