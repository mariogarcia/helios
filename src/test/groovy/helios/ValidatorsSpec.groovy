package helios

import spock.lang.Specification

import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsSpec extends Specification {

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<Validators> validatorsClass = Validators

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor validatorsClass
    }
}
