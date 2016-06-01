package helios;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * All reused constant values used through the library should be
 * declared over here.
 *
 * @since 0.1.0
 */
public class Constants {

    private Constants() { }

    /**
     * Blank space
     *
     * @since 0.1.0
     */
    public static final String BLANK = "";

    /**
     * Point character
     *
     * @since 0.1.0
     */
    public static final String POINT = ".";

    /**
     * Checks if a given value is not null.
     *
     * @param subject the object to check
     * @return true if the subject is not null false otherwise
     * @since 0.1.0
     */
    public static final <T> Boolean FN_IS_NOT_NULL(T subject) {
        return Optional
            .ofNullable(subject)
            .isPresent();
    }

    /**
     * Checks if a given value is not a blank character ""
     *
     * @param subject the value to check
     * @return true if the subject is not blank false otherwise
     * @since 0.1.0
     */
    public static final <T> Boolean FN_IS_NOT_BLANK(T subject) {
        return Optional
            .ofNullable(subject)
            .map(Object::toString)
            .map(String::isEmpty)
            .map(isEmpty -> !isEmpty)
            .orElse(false);
    }
}
