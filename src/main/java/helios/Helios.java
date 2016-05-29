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

    private static final String BLANK = "";

    private Helios() { }

    public static final <T> List<ValidatorError<T>> validate(final String id, final T subject, final Validator<T>... validators) {
        return validate(id, subject, Arrays.asList(validators));
    }

    public static final <T> List<ValidatorError<T>> validate(final String id, final T subject, final List<Validator<T>> validators) {
        return validators
            .stream()
            .filter(v -> v != null)
            .map(v -> v.validate(subject))
            .flatMap(List::stream)
            .map(error -> error.copyWithProperty(id))
            .collect(toList());
    }
}
