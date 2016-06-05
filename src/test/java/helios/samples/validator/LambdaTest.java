package helios.samples.validator;

import static org.junit.Assert.assertEquals;

import static helios.ValidatorError.error;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

import helios.Validator;
import helios.ValidatorError;

public class LambdaTest {

    @Test public void testLambda() {
        // tag::testLambda[]
        Validator<String> validator = (String s) -> {
            if (s.equals("x")) {
                return Arrays.asList(error(s, "not.x"));
            }
            return Arrays.asList();
        };
        // end::testLambda[]

        List<ValidatorError> errors = validator.validate("x");
        assertEquals(errors.size() , 1);
        assertEquals(errors.get(0).key, "not.x");
    }
}
