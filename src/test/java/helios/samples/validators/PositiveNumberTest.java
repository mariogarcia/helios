package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.positive;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class PositiveNumberTest {

    @Test public void testPositiveNumber() {
        Integer numberToValidate = -1;
        List<ValidatorError> errors = validate("number", numberToValidate, positive());

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "positive.notmet");
    }
}
