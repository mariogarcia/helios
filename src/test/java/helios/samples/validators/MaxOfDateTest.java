package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.maxOfDate;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.HOURS;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Date;

import org.junit.Test;

public class MaxOfDateTest {

    private static final Long AN_HOUR_AGO = now().minus(1, HOURS).toEpochMilli();

    @Test public void testMaxOfDate() {
        Date now = new Date();
        List<ValidatorError> errors = validate("date", now, maxOfDate(new Date(AN_HOUR_AGO)));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "date.max");
    }
}
