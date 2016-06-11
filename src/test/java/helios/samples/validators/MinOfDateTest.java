package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.minOfDate;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.HOURS;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Date;

import org.junit.Test;

public class MinOfDateTest {

    private static final Long IN_AN_HOUR = now().plus(1, HOURS).toEpochMilli();

    @Test public void testMinOfDate() {
        Date now = new Date();
        List<ValidatorError> errors = validate("date", now, minOfDate(new Date(IN_AN_HOUR)));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "date.min");
    }
}
