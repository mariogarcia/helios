package helios;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents the result of a given {@link Validator} execution
 *
 * @param <T> The type of the validated value
 * @since 0.1.0
 */
public class ValidatorResult<T> {

    /**
     * The validated value
     *
     * @since 0.1.0
     */
    public final T value;

    /**
     * The list of errors (if any) produced by the validation
     *
     * @since 0.1.0
     */
    public final List<ValidatorError<T>> errors;

    /**
     * Constructor to create an instance of {@link ValidatorResult}
     *
     * @param value the validated value
     * @param errors a varargs parameter to add {@link ValidatorError}
     * instances
     * @since 0.1.0
     */
    public ValidatorResult(final T value, final ValidatorError<T>... errors) {
        this.value = value;
        this.errors = Arrays.asList(errors);
    }

    /**
     * Returns whether the result has errors or not
     *
     * @return true if the result has errors, false otherwise
     * @since 0.1.0
     */
    public Boolean hasErrors() {
        return this.errors.iterator().hasNext();
    }

    /**
     * Returns whether the result didn't have any errors or had any
     *
     * @return true if the result has no errors, false otherwise
     * @since 0.1.0
     */
    public Boolean hasNoErrors() {
        return this.errors.isEmpty();
    }

    /**
     * When composing {@link Validator} instances, we would like to
     * add up all intermediate results.
     *
     * @param other the {@link ValidatorResult} we would like to merge
     * with
     * @return a {@link ValidatorResult} instance merging the current
     * instance with the result passed as parameter
     * @since 0.1.0
     */
    public ValidatorResult<T> merge(ValidatorResult<T> other) {
        Stream<ValidatorError<T>> stream = Stream.concat(other.errors.stream(), this.errors.stream());
        List<ValidatorError<T>> aggError = stream.collect(toList());

        return new ValidatorResult<T>(value, aggError.toArray(new ValidatorError[aggError.size()]));
    }
}
