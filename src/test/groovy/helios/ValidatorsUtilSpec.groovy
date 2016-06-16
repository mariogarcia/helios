package helios

import static helios.ValidatorsUtil.compose
import static helios.Helios.validate
import static helios.Validators.min
import static helios.Validators.max
import static helios.Validators.required

import spock.genesis.Gen
import spock.genesis.generators.Generator

import spock.lang.Specification

import helios.Validator
import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsUtilSpec extends Specification {

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
        anything << Gen.integer.then(Gen.string).take(100)
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
        validators << Gen.list(validatorsWithNull, 2).take(100)

        and: "values to make validation fail"
        toValidate << Gen.integer(-100,0).take(100)
    }

    private Generator getValidatorsWithNull() {
        return Gen.once(null).then(Gen.any(min(0), max(-101)))
    }
}
