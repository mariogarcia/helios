package helios.samples.helios;

import static org.junit.Assert.assertEquals;

import static helios.Validators.matches;
import static helios.Validators.required;
import static helios.Validators.inRangeOfString;

import java.util.List;
import org.junit.Test;

import helios.Helios;
import helios.ValidatorError;

public class HeliosVarargsTest {
    // tag::test[]
    @Test public void testVarargs() {
        List<ValidatorError> errors = Helios.validate("word", "john", required(), inRangeOfString(0,1));

        assertEquals(errors.get(0).key, "string.max.notmet");
        assertEquals(errors.get(0).property, "word");
        assertEquals(errors.get(0).keyI18n, "word.string.max.notmet");
    }
    // end::test[]
}
