package helios

import spock.lang.Specification

import helios.HeliosSpecProperties as PROPERTY
import helios.HeliosSpecValidators as VALIDATOR
import helios.Utils as GENERATOR

class HeliosSpec extends Specification {

    void 'check final class'() {
        given: 'an Helios class'
        Class<Helios> heliosClass = Helios.class

        expect: 'constructor to be one'
        PROPERTY.classHasNoPublicConstructor heliosClass
    }

    void 'validate a simple email'() {
        expect: 'properties are followed'
        PROPERTY.emailHasCharAt email
        PROPERTY.emailHasDomain email

        where: 'possible emails are'
        email << GENERATOR.emailGenerator.take(100)
    }

    void 'composing two validators'() {
        when: 'composing two validators'
        Validator<PROPERTY.Loan> validator = Helios.compose(left, right)

        then: 'both properties should hold'
        PROPERTY.checkComposition validator, loan

        where: 'possible values are'
        loan  << GENERATOR.loanGenerator.take(100)
        left  << GENERATOR.anyOfLoan().take(100)
        right << GENERATOR.anyOfLoan().take(100)
    }

    void 'composing list of validators'() {
        when: 'composing two validators'
        Optional<Validator<PROPERTY.Loan>> validator = Helios.compose(validators as Validator[])

        then: 'both properties should hold'
        PROPERTY.checkVarArgsComposition validator, loan

        where: 'possible values are'
        loan        << GENERATOR.loanGenerator.take(100)
        validators  << GENERATOR.validatorList().take(100)
    }

    void 'validating composing validators'() {
        when: 'validating with several validators at once'
        ValidatorResult<PROPERTY.Loan> result = Helios.validate(loan, validators as Validator[])

        then: 'both properties should hold'
        result

        where: 'possible values are'
        loan        << GENERATOR.loanGenerator.take(100)
        validators  << GENERATOR.validatorList().take(100)
    }
}
