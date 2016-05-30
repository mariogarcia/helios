package helios;

import java.util.List;
import java.util.ArrayList;

/**
 * Abstraction to implement validators.
 *
 * @param <T> The type of the value the validator will check
 * @since 0.1.0
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates a given object and returns a result that might
     * contain errors.
     *
     * @param subject the value validated
     * @return a list of possible {@link ValidatorError} instances
     * @since 0.1.0
     */
    List<ValidatorError<T>> validate(T subject);
}
