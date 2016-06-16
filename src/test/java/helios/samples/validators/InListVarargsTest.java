package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class InListVarargsTest {

    @Test public void testInList() {
        Integer value = 24;

        List<ValidatorError> errors =
            validate("value", value, inList(1, 2, 3));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "inList");
    }
}
