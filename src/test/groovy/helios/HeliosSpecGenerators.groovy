package helios

import groovy.transform.CompileStatic

import spock.genesis.Gen
import spock.genesis.generators.Generator

import helios.HeliosSpecProperties as PROPERTY
import helios.HeliosSpecValidators as VALIDATOR

@CompileStatic
class HeliosSpecGenerators {

    static Generator<String> getEmailGenerator() {
        return Gen.string(3,9)
            .then(Gen.these('','@'))
            .then(Gen.string(3,9))
    }

    static Generator<PROPERTY.Loan> getLoanGenerator() {
        return Gen.type([name: Gen.string(0,20), amount: Gen.getDouble()] as Map, PROPERTY.Loan)
    }

    static Generator<Validator<PROPERTY.Loan>> anyOfLoan() {
        return Gen.any(VALIDATOR.loanByName(), VALIDATOR.loanByAmount(), null)
    }

    static Generator<List<Validator<PROPERTY.Loan>>> validatorList() {
        return Gen.list(anyOfLoan(), 4)
    }
}
