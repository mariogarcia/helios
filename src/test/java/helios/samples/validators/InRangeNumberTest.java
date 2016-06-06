package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inRange;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class InRangeNumberTest {

    @Test public void testInRangeNumber() {
        Integer minFailingValue = 10;
        Integer maxFailingValue = 20;

        List<ValidatorError> minErrors = validate("min", minFailingValue, inRange(11,20));
        List<ValidatorError> maxErrors = validate("max", maxFailingValue, inRange(1,19));

        assertEquals(minErrors.get(0).key, "min.notmet");
        assertEquals(maxErrors.get(0).key, "max.notmet");
    }
}
