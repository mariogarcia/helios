package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.required;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class RequiredTest {

    @Test public void testRequired() {
        Long id = null;
        List<ValidatorError> errors = validate("id", id, required());

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "required");
    }
}
