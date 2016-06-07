package helios

import spock.lang.Specification
import helios.ValidatorErrorSpecGenerators as GENERATOR

class ValidatorErrorSpec extends Specification {

    void 'error property has never a point in the end'() {
        when: 'there is a new error'
        ValidatorError error =
            new ValidatorError(map.value, map.property, map.key, map.keyI18n)

        then: 'or there is no property'
        and: 'if there is any it should not end with .'
        assert !error.property || !error.property.endsWith('.')

        where: 'possible generated values are'
        map << GENERATOR.errorGenerator.take(100)
    }

    void 'error toString() should reflect always error key'() {
        when: 'there is a new error'
        ValidatorError error =
            new ValidatorError(map.value, map.property, map.key, map.keyI18n)

        then: 'it always should have the error key'
        assert "$error".contains("${error.key}")

        where: 'possible generated values are'
        map << GENERATOR.errorGenerator.take(100)
    }

    void 'error toString() should reflect always error property'() {
        when: 'there is a new error'
        ValidatorError error =
            new ValidatorError(map.value, map.property, map.key, map.keyI18n)

        then: 'it always should have the error property'
        assert "$error".contains("${error.property}")

        where: 'possible generated values are'
        map << GENERATOR.errorGenerator.take(100)
    }
}
