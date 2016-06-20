package helios;

import static helios.ValidatorsUtil.d;
import static helios.ValidatorsUtil.safe;
import static helios.ValidatorsUtil.validator;
import static helios.ValidatorsUtil.unsafe;
import static helios.ValidatorsUtil.compose;
import static helios.ValidatorsUtil.safeCurry;
import static helios.ValidatorsUtil.BLANK;
import static helios.ValidatorError.error;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import java.util.function.Predicate;
import java.util.function.BiPredicate;

/**
 * All basic validators implemented in Helios can be found
 * here. Validators normally are used in {@link Helios#validate}
 * methods.
 *
 * NOTE: Check {@link Helios} to see how to use validators
 *
 * @since 0.1.0
 * @see Helios#validate
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
        return (T opt) -> unsafe(opt, ValidatorsUtil::isNotNull, error(opt, "required"));
    }

    /**
     * Checks that a given {@link Number} number should be greater or
     * equals than the number passed as parameter.
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
        return validator(min, (ref,y) -> d(y) >= d(ref), "min.notmet");
    }

    /**
     * Checks that a given {@link Number} is a positive number
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/PositiveNumberTest.java[]
     * ----
     *
     * @return a {@link Validator} instance
     * @since 0.1.0
     */
    public static<T extends Number> Validator<T> positive() {
        return validator(0, (zero, y) -> zero < d(y), "positive.notmet");
    }

    /**
     * Checks that a given {@link List} size is greater or equals than
     * the number passed as parameter.
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
    public static Validator<List<?>> minOfList(int min) {
        return validator(min, (ref, l) -> l.size() >= ref, "list.min.notmet");
    }

    /**
     * Checks that a given {@link List} size is less or equals than
     * the number passed as parameter.
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MaxOfListTest.java[]
     * ----
     *
     * @param max maximum number of elements the list should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<List<?>> maxOfList(int max) {
        return validator(max, (ref, l) -> l.size() <= ref, "list.max.notmet");
    }

    /**
     * Checks that a given {@link List} size is within a specific
     * range given by two numbers passed as parameters.
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InRangeOfListTest.java[]
     * ----
     *
     * @param min minimum number of elements the list should have
     * @param max maximum number of elements the list should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<List<?>> inRangeOfList(int min, int max) {
        return compose(minOfList(min), maxOfList(max));
    }

    /**
     * Checks that a given {@link String} length is greater equals a
     * given number of characters.
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
    public static Validator<String> minOfString(int min) {
        return validator(min, (ref, st) -> st.length() >= ref, "string.min.notmet");
    }

    /**
     * Checks that a given {@link String} length is less equals a
     * given number
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MaxOfStringTest.java[]
     * ----
     *
     * @param max maximum number of characters that the string should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<String> maxOfString(int max) {
        return validator(max, (ref, st) -> st.length() <= ref, "string.max.notmet");
    }

    /**
     * Checks that a given {@link String} length is within a specific
     * range given by two numbers passed as parameters.
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InRangeOfStringTest.java[]
     * ----
     *
     * IMPORTANT: `InRangeOfString` validator is composed by
     * `minOfString` and `maxOfString` validators.
     *
     * @param min minimum number of characters the string should have
     * @param max maximum number of characters the string should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    // tag::inRangeOfString[]
    public static Validator<String> inRangeOfString(int min, int max) {
        return compose(minOfString(min), maxOfString(max));
    }
    // end::inRangeOfString[]

    /**
     * Checks that a given {@link Number} is less equals than the
     * number passed as parameter
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MaxNumberTest.java[]
     * ----
     *
     * @param max minimum number of characters that the string should have
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static <T extends Number> Validator<T> max(final T max) {
        return validator(max, (ref,y) -> d(y) <= d(ref), "max.notmet");
    }

    /**
     * Checks that a given {@link Number} is within a given boundaries
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InRangeNumberTest.java[]
     * ----
     *
     * IMPORTANT: `InRange` validator is composed by `min` and `max`
     * validators.
     *
     * @param min minimum number of characters that the string should have
     * @param max maximum number of characters that the string should have
     * @return a {@link Validator}
     * @see Validators#min
     * @see Validators#max
     * @since 0.1.0
     */
    public static <T extends Number> Validator<T> inRange(final T min, final T max) {
        return compose(min(min), max(max));
    }

    /**
     * Checks that a given {@link String} matches a specific pattern
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MatchesTest.java[]
     * ----
     *
     * @param pattern the pattern the string should match
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static Validator<String> matches(final String pattern) {
        return validator(pattern, Pattern::matches, "matches");
    }

    /**
     * Checks that a given {@link String} starts with a given string
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/StartsWithTest.java[]
     * ----
     *
     * @param word the string of characters to match against
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<String> startsWith(final String word) {
        return validator(word, (ref, param) -> param.startsWith(ref), "startswith");
    }

    /**
     * Checks that a given {@link String} ends with a given string
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/EndsWithTest.java[]
     * ----
     *
     * @param word the string to check against
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<String> endsWith(final String word) {
        return validator(word, (ref, param) -> param.endsWith(ref), "endswith");
    }

    /**
     * Checks that a given {@link String} is not blank
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/NotBlankTest.java[]
     * ----
     *
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<String> notBlank() {
        return validator(BLANK, (ref, param) -> !param.trim().equals(ref), "blank");
    }

    /**
     * Cheks that a given {@link Date} is after the reference date
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MinOfDateTest.java[]
     * ----
     *
     * @param ref the reference date
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<Date> minOfDate(final Date ref) {
        return validator(ref, (min, val) -> val.after(ref), "date.min");
    }

    /**
     * Cheks that a given {@link Date} is before the reference date
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/MaxOfDateTest.java[]
     * ----
     *
     * @param ref the reference date
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<Date> maxOfDate(final Date ref) {
        return validator(ref, (min, val) -> val.before(ref), "date.max");
    }

    /**
     * Cheks that a given {@link Date} is between the min and max dates
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InRangeOfDateTest.java[]
     * ----
     *
     * @param min the minimum date
     * @param max the maximum date
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static Validator<Date> inRangeOfDate(final Date min, final Date max) {
        return compose(minOfDate(min), maxOfDate(max));
    }

    /**
     * Cheks that a given value is contained in the elements passed as
     * parameter
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InListVarargsTest.java[]
     * ----
     *
     * @param elements the elements to match against
     * @return a {@link Validator}
     * @since 0.1.1
     */
    @SafeVarargs
    public static <T> Validator<T> inList(final T... elements) {
        return validator(elements, (ref, value) -> asList(ref).contains(value), "inList");
    }

    /**
     * Cheks that a given value is contained in the list passed as
     * parameter
     *
     * [source,java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/validators/InListTest.java[]
     * ----
     *
     * @param elements the elements to match against
     * @return a {@link Validator}
     * @since 0.1.1
     */
    public static <T> Validator<T> inList(final List<T> elements) {
        return validator(elements, (ref, value) -> ref.contains(value), "inList");
    }
}
