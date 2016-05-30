package helios;

import static helios.ValidatorsUtil.safe;
import static helios.ValidatorsUtil.unsafe;
import static helios.ValidatorsUtil.safeCurry;
import static helios.ValidatorError.error;

import java.util.List;
import java.util.Optional;

import java.util.function.Predicate;
import java.util.function.BiPredicate;

/**
 * All validators implemented in Helios can be found here
 *
 * @since 0.1.0
 */
public class Validators {

    private Validators() { }

    /**
     * Validator to make sure a given value is present (is not null). If
     * the value is not present, then a {@link ValidatorError} will be
     * returned.
     *
     * @return a {@link Validator} to make sure a given value is
     * present
     * @since 0.1.0
     */
    public static <T> Validator<T> required() {
        return (T opt) -> unsafe(opt, x -> x == null, error(opt, "required"));
    }

    /**
     * Validator that checks whether a given {@link String} has less
     * characters than the number passed as parameter.
     *
     * @param n min number of characters that the string should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<String> stringSize(final int n) {
        return (String opt) -> safe(opt, x -> x.length() < n, error(opt, "string.min.notmet"));
    }

    /**
     * Validator that checks whether a given {@link Number} is less
     * than the number passed as parameter.
     *
     * @param number to check against
     * @return a {@link Validator} instance
     * @since 0.1.0
     */
    public static <T extends Number> Validator<T> min(final T number) {
        final BiPredicate<T,T> pred = (x,y) -> x.doubleValue() < y.doubleValue();
        final Predicate<T> safePred = safeCurry(number, pred);

        return (T opt) -> safe(opt, safePred, error(opt, "min.notmet"));
    }
}
