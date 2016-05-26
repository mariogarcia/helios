package helios;

/**
 * Represents a validator error. It gathers information related to the
 * error raised: value, error key, and a property.
 *
 * @param T the value type that raised the error
 * @since 0.1.0
 */
public class ValidatorError<T> {

    /**
     * Represents the value raising the error
     *
     * @since 0.1.0
     */
    public final T value;

    /**
     * The key that represents the error
     *
     * @since 0.1.0
     */
    public final String key;

    /**
     * The name of the property (if any) that was validated and raised
     * the error
     *
     * @since 0.1.0
     */
    public final String property;

    /**
     * @param value the value that raised the error
     * @param property the property the validator was validating (if
     * any)
     * @param key the key error. It can be used to translate the error
     * @since 0.1.0
     */
    public ValidatorError(final T value, final String property, final String key) {
        this.value = value;
        this.property = property;
        this.key = key;
    }
}
