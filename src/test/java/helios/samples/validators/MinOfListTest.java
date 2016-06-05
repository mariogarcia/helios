package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.minOfList;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

public class MinOfListTest {

    @Test public void testMinOfList() {
        List<Integer> numbers = Arrays.asList(1,2,3);
        List<ValidatorError> errors = validate("sample", numbers, minOfList(5));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "list.min.notmet");
    }
}
