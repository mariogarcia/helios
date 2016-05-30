package helios

import spock.lang.Specification

import helios.CommonProperties as PROPERTY_COMMON

class ValidatorsUtilSpec extends Specification {

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<ValidatorsUtil> validatorsClass = ValidatorsUtil

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor validatorsClass
    }
}
