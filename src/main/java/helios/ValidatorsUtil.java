package helios;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static helios.ValidatorError.error;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.BiPredicate;

/**
 * This class contains utility functions used when implementing {@link
 * Validator} instances in the {@link Validators} class.
 *
 * @since 0.1.0
 */
public class ValidatorsUtil {

    private ValidatorsUtil() { }

    /**
     * Shortcut to get the double value from a given {@link
     * Number}. It's normally used in formulas to avoid unecessary
     * verbosity.
     *
     * @param n the number to convert to double
     * @return the double value of the number passed as argument
     */
    public static double d(final Number n) {
        return n.doubleValue();
    }

    /**
     * Creates a new {@link Validator} from a given reference value
     * and a formula expression given as a {@link BiPredicate}.
     *
     * @param ref the reference value the checked value will be
     * validated against
     * @param pred the validation expression it is a {@link
     * BiPredicate} meaning it's receiving as the left hand side
     * operand the reference and in the right hand side the value to
     * validate
     * @return a {@link Validator}
     * @since 0.1.0
     */
    public static <T,S> Validator<S> validator(final T ref, final BiPredicate<T,S> pred, final String errorKey) {
        return (S val) -> safe(val, safeCurry(ref, pred), error(val, errorKey));
    }

    /**
     * Executes safely a given {@link Predicate} with a possible null
     * value. If the condition returns true, that means the condition
     * matches the error case, therefore it will return a {@link List}
     * with the {@link ValidatorError} passed as last parameter.
     *
     * @param value The possible null value
     * @param cond error case condition. If should return true if it
     * matched the error case, false otherwise.
     * @param error the error to return in case the predicate matches
     * the error condition
     * @return a list of {@link ValidatorError} elements.
     * @since 0.1.0
     */
    public static <T> List<ValidatorError> safe(T value, Predicate<T> cond, ValidatorError error) {
        return Optional
            .ofNullable(value)
            .filter(cond)
            .map(next -> asList(error))
            .orElse(emptyList());
    }

    /**
     * Executes a given {@link Predicate} with a possible null
     * value. If the condition returns true, that means the condition
     * matches the error case, therefore it will return a {@link List}
     * with the {@link ValidatorError} passed as last parameter.
     *
     * @param value unsafe value
     * @param cond error case condition. If should return true if it
     * matched the error case, false otherwise.
     * @param error the error to return in case the predicate matches
     * the error condition
     * @return a list of {@link ValidatorError} elements.
     * @since 0.1.0
     */
    public static <T> List<ValidatorError> unsafe(T value, Predicate<T> cond, ValidatorError error) {
        return cond.test(value) ? asList(error) : emptyList();
    }

    /**
     * Given a {@link BiPredicate} function, this method applies
     * safely the first param of the predicate and returns the partial
     * applied function. If the first param is null then this method
     * will return a {@link Predicate} that always returns false, if
     * the param is not null it will return a {@link Predicate} that
     * represents the previous {@link BiPredicate} with the first
     * parameter set.
     *
     * @param param the first parameter of the {@link BiPredicate}
     * @param source an instance of {@link BiPredicate}
     * @return an instance of {@link Predicate}.
     * @since 0.1.0
     */
    public static <A,B> Predicate<B> safeCurry(final A param, final BiPredicate<A,B> source) {
        Function<A, Predicate<B>> getSafePred = (A a) -> (B b) -> source.test(a,b);
        Supplier<Predicate<B>> getDefaultPred = () -> (B b) -> false;

        return Optional
            .ofNullable(param)
            .map(getSafePred)
            .orElseGet(getDefaultPred);
    }
}
