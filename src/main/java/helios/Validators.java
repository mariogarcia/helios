package helios;

import static helios.ValidatorsUtil.d;
import static helios.ValidatorsUtil.safe;
import static helios.ValidatorsUtil.validator;
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
     * Checks that a given value is present (is not null).
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/RequiredTest.java[]
     * ----
     *
     * @return a {@link Validator} to make sure a given value is
     * present
     * @since 0.1.0
     */
    public static <T> Validator<T> required() {
        return (T opt) -> unsafe(opt, x -> x == null, error(opt, "required"));
    }

    /**
     * Checks that a given {@link Number} is less than the number
     * passed as parameter.
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MinNumberTest.java[]
     * ----
     *
     * @param min to check against
     * @return a {@link Validator} instance
     * @since 0.1.0
     */
    public static <T extends Number> Validator<T> min(final T min) {
        return validator(min, (ref,y) -> d(y) < d(ref), "min.notmet");
    }

    /**
     * Checks that a given {@link List} has a minimum number of
     * elements
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MinOfListTest.java[]
     * ----
     *
     * @param min minimum number of elements the list should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<List<?>> minOfList(final int min) {
        return validator(min, (ref, l) -> l.size() < ref, "list.min.notmet");
    }

    /**
     * Checks that a given {@link String} has at least a given number
     * of characters.
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MinOfStringTest.java[]
     * ----
     *
     * @param min minimum number of characters that the string should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<String> minOfString(final int min) {
        return validator(min, (ref, st) -> st.length() < ref, "string.min.notmet");
    }
}
