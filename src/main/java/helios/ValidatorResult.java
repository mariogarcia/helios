package helios;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * @since 0.1.0
 */
public class ValidatorResult<T> {

    public final T value;
    public final List<ValidatorError<T>> errors;

    /**
     * @since 0.1.0
     */
    public ValidatorResult(final T value, final ValidatorError<T>... errors) {
        this.value = value;
        this.errors = Arrays.asList(errors);
    }

    /**
     * @since 0.1.0
     */
    public Boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    /**
     * @since 0.1.0
     */
    public Boolean hasNoErrors() {
        return !hasErrors();
    }

    /**
     * @since 0.1.0
     */
    public ValidatorResult<T> merge(ValidatorResult<T> other) {
        List<ValidatorError<T>> errors = new ArrayList<>();

        errors.addAll(this.errors);
        errors.addAll(other.errors);

        return new ValidatorResult<T>(value, errors.toArray(new ValidatorError[errors.size()]));
    }
}
