package helios;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

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
    public static <T> List<ValidatorError<T>> safe(T value, Predicate<T> cond, ValidatorError<T> error) {
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
    public static <T> List<ValidatorError<T>> unsafe(T value, Predicate<T> cond, ValidatorError<T> error) {
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
