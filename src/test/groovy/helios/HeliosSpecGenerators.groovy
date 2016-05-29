package helios

import static helios.Validators.required
import static helios.Validators.stringSize

import groovy.transform.CompileStatic

import spock.genesis.Gen
import spock.genesis.generators.Generator

import helios.HeliosSpecProperties as PROPERTY

@CompileStatic
class HeliosSpecGenerators {

    static Generator<String> getEmailGenerator() {
        return Gen.string(3,9).then(Gen.these('','@')).then(Gen.string(3,9))
    }

    static Generator<PROPERTY.Loan> getLoanGenerator() {
        return Gen.type([name: Gen.any(null, '', 'somethingelse'), amount: Gen.getDouble()] as Map, PROPERTY.Loan)
    }

    static Generator<Validator<PROPERTY.Loan>> stringValidators() {
        return Gen.any(required(), stringSize(10), null)
    }

    static Generator<List<Validator<PROPERTY.Loan>>> validatorList() {
        return Gen.list(stringValidators(), 2)
    }
}
