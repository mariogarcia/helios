package helios;

/**
 * @since 0.1.0
 */
public class ValidatorError<T> {
    public final T payload;
    public final String key;
    public final String property;

    /**
     * @since 0.1.0
     */
    public ValidatorError(final T payload, final String property, final String key) {
        this.payload = payload;
        this.property = property;
        this.key = key;
    }
}
