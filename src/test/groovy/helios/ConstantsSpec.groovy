package helios

import spock.lang.Specification

import helios.CommonProperties as PROPERTY_COMMON

class ConstantsSpec extends Specification {

    void 'check Constants final class'() {
        given: 'an Constants class'
        Class<Constants> constantsClass = Constants

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor constantsClass
    }
}
