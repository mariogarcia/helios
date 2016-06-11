package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.notBlank;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class NotBlankTest {

    @Test public void testNotBlank() {
        String name = " ";
        List<ValidatorError> errors = validate("name", name, notBlank());

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "blank");
    }
}
