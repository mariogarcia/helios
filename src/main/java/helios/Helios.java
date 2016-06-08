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
 *
 * Set of functions to apply {@link Validator} instances.
 *
 * == Overview
 *
 * `Helios` tries to be as simple as possible. There is only one way
 * to validate a given value, using any of the `validate`
 * methods. These methods vary only in the way validators are
 * provided.
 *
 * == validate()
 *
 * - `validate(String, T, Validator&lt;T&gt;...}`: provides
 * validators as a varargs argument. Very handy when you would like to
 * add different types of validators
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/helios/HeliosVarargsTest.java[tags=test]
 * ----
 *
 * - `validate(String, T, List&lt;Validator&lt;T&gt;&gt;)`: provides
 * validators as a list. Normally used when you group a given set of
 * validators and want to reuse them as a unit.
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/helios/HeliosListTest.java[tags=test]
 * ----
 * <1> List of validators compatible with a value of type {@link Long}
 * <2> Apply that list to id (which is a value of type {@link Long})
 *
 * == Nesting validations
 *
 * The way `validate` methods were designed, they can be nested easily
 * to create a bigger validation unit.
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/helios/HeliosNestedTest.java[tags=test]
 * ----
 *
 * @since 0.1.0
 */
public class Helios {

    private Helios() { }

    /**
     * Validates a given value with the list of validators passed as
     * parameter (varargs).
     *
     * [source, java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/helios/HeliosVarargsTest.java[]
     * ----
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
     * [source, java]
     * .Example usage
     * ----
     * include::src/test/java/helios/samples/helios/HeliosListTest.java[]
     * ----
     * <1> List of {@link Validator} compatible with a value of type {@link Long}
     * <2> Apply that list to id (which is a value of type {@link Long})
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
