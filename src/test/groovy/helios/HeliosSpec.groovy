package helios

import static helios.Helios.validate;
import static helios.Validators.min;
import static helios.Validators.required;

import spock.lang.Specification

import helios.HeliosSpecProperties as PROPERTY
import helios.HeliosSpecGenerators as GENERATOR
import helios.CommonProperties as PROPERTY_COMMON

class HeliosSpec extends Specification {

    void 'check Helios final class'() {
        given: 'an Helios class'
        Class<Helios> heliosClass = Helios

        expect: 'constructor to be one'
        PROPERTY_COMMON.classHasNoPublicConstructor heliosClass
    }

    void 'check composed validator from varargs'() {
        when: 'validating with several validators at once'
        List<ValidatorError<PROPERTY.Loan>> result =
            Helios.validate("loan", loanSample, [
                { validate("id", it.id, required(), min(0)) },
                { PROPERTY.Loan loan -> validate("name", loan.name, validators) },
                { PROPERTY.Loan loan -> validate("amount", loan.amount, required(), min(10), min(null)) }] as Validator[])

        then: 'result should reflect properties'
        PROPERTY.checkLoanResult result

        where: 'possible loan samples and validators are'
        loanSample << GENERATOR.getLoanGenerator().take(100)
        validators << GENERATOR.validatorList().take(100)
    }
}
