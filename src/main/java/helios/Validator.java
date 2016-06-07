package helios;

import java.util.List;
import java.util.ArrayList;

import java.util.function.Predicate;

/**
 * Base abstraction to implement validators.
 *
 * == Overview
 *
 * `Validator` is a {@link FunctionalInterface} with a single method
 * {@link Validator#validate}.  A {@link Validator} can be implemented
 * with a class **implementing {@link Validator}**, using a **lambda
 * expression**, a **method reference**, or making use of **{@link
 * ValidatorsUtil#validator}**.
 *
 * Rules when implementing a {@link Validator}
 *
 * - The {@link Validator#validate} method should be defined in positive form.
 * - A given {@link Validator#validate} method returns a list of zero,
 * one or more {@link ValidatorError} instances.
 *
 * == Lambda expression
 *
 * The following validator uses a lambda expression that matches
 * {@link Validator#validate} method:
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/validator/LambdaTest.java[tags=testLambda, indent=0]
 * ----
 *
 * NOTE: Notice it has been defined in positive form, `if` is valid =>
 * no errors `else` => errors
 *
 * == Method reference
 *
 * The same way a lambda expression can be used as a validator when it
 * matches the {@link Validator#validate} method, so does a `method
 * reference`.
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/validator/MethodReferenceTest.java[]
 * ----
 *
 * == ValidatorsUtil#validator
 *
 * If your {@link Validator} can be expressed as a {@link
 * java.util.function.BiPredicate} function, then you can use {@link
 * helios.ValidatorsUtil#validator}.
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/validator/ValidatorsUtilTest.java[tags=validatorCreation,indent=0]
 * ----
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
     * @return a list of possible {@link ValidatorError} instances
     * @since 0.1.0
     */
    List<ValidatorError> validate(T subject);

    default Predicate<T> toPredicate() {
        return (T subject) -> Helios.validate("predicate", subject, this).size() == 0;
    }
}
