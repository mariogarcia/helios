package helios.samples.validator;

import static org.junit.Assert.assertEquals;

import static helios.ValidatorError.error;
import static helios.ValidatorError.errors;

import java.util.List;
import org.junit.Test;

import helios.Validator;
import helios.ValidatorError;

public class LambdaTest {

    @Test public void testLambda() {
        // tag::testLambda[]
        Validator<String> validator = (String s) -> {
            if (s.equals("x")) {
                return errors(error(s, "not.x"));
            }
            return errors();
        };
        // end::testLambda[]

        List<ValidatorError> errors = validator.validate("x");
        assertEquals(errors.size() , 1);
        assertEquals(errors.get(0).key, "not.x");
    }
}
