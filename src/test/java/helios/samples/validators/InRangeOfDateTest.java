package helios.samples.validators;

import static helios.Helios.validate;
import static helios.Validators.inRangeOfDate;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

import static org.junit.Assert.assertEquals;

import helios.ValidatorError;

import java.util.List;
import java.util.Date;

import org.junit.Test;

public class InRangeOfDateTest {

    private static final Long IN_AN_HOUR = now().plus(1, HOURS).toEpochMilli();
    private static final Long AN_HOUR_AGO = now().minus(1, HOURS).toEpochMilli();

    @Test public void testInRangeOfDate() {
        Date yesterday = new Date(now().minus(1, DAYS).toEpochMilli());

        List<ValidatorError> errors = validate("date", yesterday,
                                               inRangeOfDate(new Date(AN_HOUR_AGO),
                                                             new Date(IN_AN_HOUR)));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "date.min");
    }
}
