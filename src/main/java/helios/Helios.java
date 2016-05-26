package helios;

import java.util.List;
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

    /**
     * Validates a given object using a list of validators. Those
     * validators will be composed. The {@link ValidatorResult} will
     * contain errors if any of the validators fails.
     *
     * @param subject The value to validate
     * @param validators varargs of {@link Validator} suitable for the
     * type of the value to validate
     * @return a {@link ValidatorResult}
     * @since 0.1.0
     */
    public static final <T> ValidatorResult<T> validate(final T subject, final Validator<T>... validators) {
        return validate(subject, Arrays.asList(validators));
    }

    /**
     * Validates a given object using a list of validators. Those
     * validators will be composed. The {@link ValidatorResult} will
     * contain errors if any of the validators fails.
     *
     * @param subject The value to validate
     * @param validators list of {@link Validator} suitable for the
     * type of the value to validate
     * @return a {@link ValidatorResult}
     * @since 0.1.0
     */
    public static final <T> ValidatorResult<T> validate(final T subject, final List<Validator<T>> validators) {
        return Helios.compose(validators).orElseGet(Validator::supplyDefault).validate(subject);
    }

    /**
     * Validates a given value using a {@link Condition}.
     *
     * @param subject The value to validate
     * @param errorKey The errorKey to return in case the condition
     * fails
     * @param condition The condition used to validate the value
     * @return a {@link ValidatorResult}. It will have errors if the
     * condition returns false.
     * @since 0.1.0
     */
    public static final <T> ValidatorResult<T> validate(final T subject, String errorKey, final Condition<T> condition) {
        ValidatorError<T>[] errors = condition.apply(subject) ?
            new ValidatorError[] { } :
            new ValidatorError[] { new ValidatorError(subject, BLANK, errorKey) } ;

        return new ValidatorResult(subject, errors);
    }

    /**
     * This function composes all validators passed as argument and
     * returns an {@link Optional} value of the resulting {@link
     * Validator} instance (if any).
     * </br>
     * If the {@link Validator} instance is present, then the resulting
     * validator will succeed if all inner validators pass.
     *
     * @param validators a varargs containing possible validator
     * instances
     * @return an instance of {@link Optional} with a possible {@link Validator}
     * instance.
     * @since 0.1.0
     */
    public static final <T> Optional<Validator<T>> compose(final Validator<T>... validators) {
        return compose(Arrays.asList(validators));
    }

    /**
     * This function composes all validators passed as argument and
     * returns an {@link Optional} value of the resulting {@link
     * Validator} instance (if any).
     * </br>
     * If the {@link Validator} instance is present, then the resulting
     * validator will succeed if all inner validators pass.
     *
     * @param validators a {@link List} containing possible validator
     * instances
     * @return an instance of {@link Optional} with a possible {@link Validator}
     * instance.
     * @since 0.1.0
     */
    public static final <T> Optional<Validator<T>> compose(final List<Validator<T>> validators) {
        return filterNull(validators).map(Optional::get).reduce(Helios::compose);
    }

    private static <T> Stream<Optional<T>> filterNull(final List<T> elements) {
        return elements.stream().map(Optional::ofNullable).filter(Optional::isPresent);
    }

    /**
     * Composes safely two {@link Validator} instances. If any of the
     * validators is null it will be replaced by the {@link
     * Validator#supplyDefault} value.
     *
     * @param left possible left {@link Validator}
     * @param right possible right {@link Validator}
     * @return the combination of both.
     * @since 0.1.0
     */
    public static final <T> Validator<T> compose(final Validator<T> left, final Validator<T> right) {
        final Validator<T> foo = supplyDefaultIfNull(left);
        final Validator<T> bar = supplyDefaultIfNull(right);

        return (T subject) -> foo.validate(subject).merge(bar.validate(subject));
    }

    private static <T> Validator<T> supplyDefaultIfNull(final Validator<T> any) {
        return Optional.ofNullable(any).orElseGet(Validator::supplyDefault);
    }
}
