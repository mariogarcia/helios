package helios;

import java.util.List;
import java.util.ArrayList;

/**
 * Base abstraction to implement validators.
 *
 * == Overview
 *
 * `Validator` is a {@link FunctionalInterface} with a single method
 * {@link Validator#validate}. The good think about functional
 * interfaces is that you can implement them with lambda expressions
 * or use method references that comply with the interface method.
 *
 * == Lambda expression
 *
 * [source, java]
 * ----
 * include::src/test/java/helios/samples/validator/LambdaTest.java[tags=testLambda, indent=0]
 * ----
 *
 * == Method reference
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
}
