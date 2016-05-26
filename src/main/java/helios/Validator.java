package helios;

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
     * @return an instance of type {@link ValidatorResult} containing
     * the result of the validation
     * @since 0.1.0
     */
    ValidatorResult<T> validate(T subject);

    /**
     * This function simply returns an instance of a {@link Validator}
     * that always return a successful result.
     *
     * @return an instance of a validator that always returns a non
     * error result
     * @since 0.1.0
     */
    public static <T> Validator<T> supplyDefault() {
        return (T payload) -> new ValidatorResult(payload, new ValidatorError[] {});
    }
}
