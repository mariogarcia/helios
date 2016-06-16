package helios.samples.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import static helios.Validators.required;
import static helios.Validators.notBlank;
import static helios.Validators.endsWith;
import static helios.Validators.startsWith;
import static helios.Validators.minOfString;

import static helios.ValidatorsUtil.compose;

import helios.Validator;
import helios.ValidatorsUtil;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

public class ToPredicateTest {

    @Test public void testToPredicate() {
        List<String> cities = Arrays.asList("Madrid", "Montreal", " ", "New York", null, "Dublin");
        Validator<String> filter = compose(required(),
                                           notBlank(),
                                           minOfString(5),
                                           startsWith("D"),
                                           endsWith("n"));

        Optional<String> result = cities
            .stream()
            .filter(filter.toPredicate())
            .findFirst();

        assertTrue(result.isPresent());
        assertEquals(result.get(), "Dublin");
    }
}
