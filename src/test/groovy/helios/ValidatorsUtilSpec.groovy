package helios

import static helios.Helios.validate
import static helios.Validators.min
import static helios.Validators.max
import static helios.Validators.required
import static helios.ValidatorsUtil.compose
import static helios.ValidatorsUtil.safeCurry

import java.util.function.BiPredicate

import spock.genesis.Gen
import spock.genesis.generators.Generator
import spock.lang.Specification

import helios.Validator
import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsUtilSpec extends Specification {

    static final Integer LIMIT = 100

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<ValidatorsUtil> validatorsClass = ValidatorsUtil

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor validatorsClass
    }

    void 'check validator composition with no validators'() {
        expect: "composition could be called over no validators"
        and: "it will return an empty validator"
        and: "which always return no errors"
        validate("anything", anything, compose()).size() == 0

        where: "values could be anything"
        anything << Gen.integer.then(Gen.string).take(LIMIT)
    }

    void 'check validator with possible null validators'() {
        given: 'a set of validators that will make the validation to fail'
        Validator[] failingValidators = validators

        expect: """
        a composition on null and valid validators to behave
        as if there were no null values
        """
        validate("anything",
                 toValidate,
                 compose(failingValidators)).isEmpty() == validators.findAll().isEmpty()

        where: "validator list with possible null values"
        validators << Gen.list(validatorsWithNull, 2).take(LIMIT)

        and: "values to make validation fail"
        toValidate << Gen.integer(-10, -1).take(LIMIT)
    }

    void 'check safe bi-predicate currying'() {
        given: 'a bi-predicate'
        BiPredicate<Integer,Integer> eq = { Integer a, Integer b -> a == b }

        expect: 'to be success ONLY if both param and nCase exist'
        and: 'both numbers are equal'
        safeCurry(param, eq).test(nCase) == (param != null)

        where: 'possible params are'
        param << Gen.once(null).then(Gen.integer).take(LIMIT)

        and: 'making nCase to be same as param'
        nCase = param
    }

    private Generator getValidatorsWithNull() {
        return Gen.once(null).then(Gen.any(min(0), max(-101)))
    }
}
