package helios.samples.validator;

import static org.junit.Assert.assertEquals;

import static helios.ValidatorError.error;
import static helios.ValidatorError.errors;

import java.util.List;
import org.junit.Test;

import helios.Helios;
import helios.Validator;
import helios.ValidatorError;

public class MethodReferenceTest {

    @Test public void testMethodReference() {
        List<ValidatorError> errors =
            Helios.validate("name", "apache", this::doStuff);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "not.startswith.h");
    }

    List<ValidatorError> doStuff(String something) {
        return something.startsWith("h") ?
            errors() :
            errors(error(something, "not.startswith.h"));
    }
}
