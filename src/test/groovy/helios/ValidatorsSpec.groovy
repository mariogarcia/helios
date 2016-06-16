package helios

import static helios.Validators.positive
import static helios.Helios.validate

import spock.genesis.Gen
import spock.lang.Specification

import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsSpec extends Specification {

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<Validators> validatorsClass = Validators

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor validatorsClass
    }

    void 'check positive validator'() {
        expect: """
        the validator will add an error for zero or any number less
        than zero
        """
        (x <= 0 && errors == 1) || true

        and: """
        it won't add any error if the value is null
        """
        (x > 0 && errors == 0) || true

        where: "possible integer values are"
        x << Gen.getInteger().take(100)

        and: 'number of errors produced'
        errors = validate("x", x, positive()).size()
    }
}
