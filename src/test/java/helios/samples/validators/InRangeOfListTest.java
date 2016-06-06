package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inRangeOfList;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

public class InRangeOfListTest {

    @Test public void testInRangeList() {
        List<Integer> numbers = Arrays.asList(1,2,3);

        List<ValidatorError> minErrors = validate("min", numbers, inRangeOfList(4,5));
        List<ValidatorError> maxErrors = validate("max", numbers, inRangeOfList(0,2));

        assertEquals(minErrors.get(0).key, "list.min.notmet");
        assertEquals(maxErrors.get(0).key, "list.max.notmet");
    }
}
