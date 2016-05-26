package helios;

/**
 * @since 0.1.0
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * @since 0.1.0
     */
    ValidatorResult<T> validate(T subject);

    /**
     * @since 0.1.0
     */
    public static <T> Validator<T> supplyDefault() {
        return (T payload) -> new ValidatorResult(payload, new ValidatorError[] {});
    }
}
