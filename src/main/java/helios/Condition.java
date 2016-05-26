package helios;

/**
 * Represents a simple condition. If a given value of type T passes
 * the condition then the condition {@link Condition#apply} will
 * return true.
 *
 * @param <T> The type of the value to check
 * @since 0.1.0
 */
@FunctionalInterface
public interface Condition<T> {

    /**
     * Evaluates a given value of type T and returns true if
     * the condition succeeds, false otherwise.
     *
     * @param subject The value to validate
     * @return true if it succeeded, false otherwise
     * @since 0.1.0
     */
    public Boolean apply(T subject);
}
