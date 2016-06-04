package helios

import static helios.Validators.required
import static helios.Validators.minOfString

import groovy.transform.CompileStatic

import spock.genesis.Gen
import spock.genesis.generators.Generator

import helios.HeliosSpecProperties as PROPERTY

@CompileStatic
class HeliosSpecGenerators {

    static Generator<PROPERTY.Loan> getLoanGenerator() {
        def loan = [
            id: Gen.any(1L, null),
            name: Gen.any(null, '', 'somethingelse'),
            amount: Gen.any(null, 0).then(Gen.getDouble())] as Map<String,?>

        return Gen.type(loan, PROPERTY.Loan)
    }

    static Generator<Validator<PROPERTY.Loan>> stringValidators() {
        return Gen.any(required(), minOfString(10), null)
    }

    static Generator<List<Validator<PROPERTY.Loan>>> validatorList() {
        return Gen.list(stringValidators(), 2)
    }
}
