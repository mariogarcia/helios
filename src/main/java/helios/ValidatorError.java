package helios;

import static helios.ValidatorsUtil.BLANK;
import static helios.ValidatorsUtil.POINT;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * The key that represents the error for the current property.
     *
     * @since 0.1.0
     */
    public final String keyI18n;

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
     * @param keyI18n the key error aware of the current property path
     * @since 0.1.0
     */
    public ValidatorError(final Object value,
                          final String property,
                          final String key,
                          final String keyI18n) {
        this.value = value;
        this.property = property;
        this.key = key;
        this.keyI18n = keyI18n;
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
        String prop = Optional
            .ofNullable(property)
            .filter(ValidatorsUtil::isNotBlank)
            .orElse(newProperty);

        String path = Arrays.asList(newProperty, property,key)
            .stream()
            .filter(ValidatorsUtil::isNotNull)
            .filter(ValidatorsUtil::isNotBlank)
            .collect(Collectors.joining(POINT));

        return new ValidatorError(value, prop, key, path);
    }

    /**
     * Creates a new instance of a {@link ValidatorError} for a given
     * value an identified with a given error key.
     *
     * @param value the value that produced the error
     * @param errorKey the error id
     * @return an instance of {@link ValidatorError}
     * @since 0.1.0
     */
    public static ValidatorError error(final Object value, final String errorKey) {
        return new ValidatorError(value, BLANK, errorKey, BLANK);
    }

    /**
     * This is a utility method to easily build a {@link
     * ValidatorError} list.
     *
     * @param errors elements of type {@link ValidatorError}
     * @return a {@link List} of elements of type {@link
     * ValidatorError}
     */
    public static List<ValidatorError> errors(final ValidatorError... errors) {
        return Arrays.asList(errors);
    }

    /**
     * Shows the most relevant information of the error when
     * it's printed out
     *
     * @since 0.1.0
     */
    @Override
    public String toString() {
        return new StringBuilder("ValidatorError(")
            .append(property)
            .append(",")
            .append(key)
            .append(",")
            .append(keyI18n)
            .append(")")
            .toString();
    }
}
