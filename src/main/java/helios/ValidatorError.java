package helios;

import static helios.Constants.BLANK;

/**
 * Represents a validator error. It gathers information related to the
 * error raised: value, error key, and a property.
 *
 * @since 0.1.0
 */
public class ValidatorError {

    /**
     * Represents the value raising the error
     *
     * @since 0.1.0
     */
    public final Object value;

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
    public ValidatorError(final Object value, final String property, final String key) {
        this.value = value;
        this.property = property;
        this.key = key;
    }

    /**
     * A {@link ValidatorError} is an immutable object. It can only be
     * created once.  The only reason we could think of mutating a
     * given {@link ValidatorError} is to complete the property path.
     *
     * This method has been created for that purpose. It respects the
     * idea of having an immutable object. That's why it creates a new
     * {@link ValidatorError} with the same values that the current
     * one but with different property value.
     *
     * @param newProperty the property of the new instance
     * @return a new {@link ValidatorError} with same value and key
     * but with different property
     * @since 0.1.0
     */
    public ValidatorError copyWithProperty(final String newProperty) {
        return new ValidatorError(value, "" + newProperty + "." + property, key);
    }

    /**
     * Creates a new instance of a {@link ValidatorError} for a given
     * value an identified with a given error key.
     *
     * @param value the value that produced the error
     * @param errorKey the error id
     * @return an instance of {@link ValidatorError}
     */
    public static <T> ValidatorError error(Object value, String errorKey) {
        return new ValidatorError(value, BLANK, errorKey);
    }
}
