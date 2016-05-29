package helios

import static helios.Helios.validate;
import static helios.Validators.min;
import static helios.Validators.required;

import spock.lang.Specification

import helios.HeliosSpecProperties as PROPERTY
import helios.HeliosSpecValidators as VALIDATOR
import helios.HeliosSpecGenerators as GENERATOR

class HeliosSpec extends Specification {

    void 'check Helios final class'() {
        given: 'an Helios class'
        Class<Helios> heliosClass = Helios

        expect: 'constructor to be one'
        PROPERTY.classHasNoPublicConstructor heliosClass
    }

    void 'check Validators final class'() {
        given: 'an Validators class'
        Class<Validators> validatorsClass = Validators

        expect: 'constructor to be one'
        PROPERTY.classHasNoPublicConstructor validatorsClass
    }

    void 'check composed validator from varargs'() {
        when: 'validating with several validators at once'
        List<ValidatorError<PROPERTY.Loan>> result =
            Helios.validate("loan", loanSample, [
                { PROPERTY.Loan loan -> validate("name", loan.name, stringValidators) },
                { PROPERTY.Loan loan -> validate("amount", loan.amount, required(), min(10)) }] as Validator[])

        then: 'result should reflect properties'
        PROPERTY.checkLoanResult result

        where: 'possible loan samples and stringValidators are'
        loanSample << GENERATOR.getLoanGenerator().take(100)
        stringValidators << GENERATOR.validatorList().take(100)
    }
}
