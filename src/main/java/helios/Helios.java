package helios;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.function.Supplier;

/**
 * Set of functions to execute or compose {@link Validator} instances
 *
 * @since 0.1.0
 */
public class Helios {

    private Helios() { }

    /**
     * Validates a given value with the list of validators passed as
     * parameter (varargs).
     *
     * @param id the alias to identify the value to validate
     * @param subject the object to validate
     * @param validators a varargs param of {@link Validator}
     * instances
     * @return a list of possible {@link ValidatorError} instances
     * @since 0.1.0
     */
    @SafeVarargs
    public static final <T> List<ValidatorError> validate(final String id, final T subject, final Validator<T>... validators) {
        return validate(id, subject, Arrays.asList(validators));
    }

    /**
     * Validates a given value with the list of validators passed as
     * parameter.
     *
     * @param id the alias to identify the value to validate
     * @param subject the object to validate
     * @param validators a list of {@link Validator}
     * instances
     * @return a list of possible {@link ValidatorError} instances
     * @since 0.1.0
     */
    public static final <T> List<ValidatorError> validate(final String id, final T subject, final List<Validator<T>> validators) {
        return validators
            .stream()
            .filter(v -> v != null)
            .map(v -> v.validate(subject))
            .flatMap(List::stream)
            .map(error -> error.copyWithProperty(id))
            .collect(toList());
    }
}
