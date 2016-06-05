package helios.samples.validator;

import static org.junit.Assert.assertEquals;

import java.util.function.BiPredicate;

import java.util.List;
import org.junit.Test;

import helios.Helios;
import helios.Validator;
import helios.ValidatorError;
import helios.ValidatorsUtil;

public class ValidatorsUtilTest {

    @Test public void testValidatorShortcut() {
        List<ValidatorError> errors =
            Helios.validate("symbol", "y",  customValidator());

        assertEquals(errors.get(0).key, "isnot.x");
    }

    // tag::validatorCreation[]
    public Validator<String> customValidator() {
        BiPredicate<String,String> equalsX = (x,y) -> !x.equals(y);

        return ValidatorsUtil.validator("x", equalsX, "isnot.x");
    }
    // end::validatorCreation[]
}
