package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.matches;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class MatchesTest {

    @Test public void testMatches() {
        String word = "airport";
        List<ValidatorError> errors = validate("word", word, matches("nono.*"));
        List<ValidatorError> noErrors = validate("word", word, matches("air.*"));

        assertEquals(errors.get(0).key, "matches");
        assertEquals(noErrors.size(), 0);
    }
}
