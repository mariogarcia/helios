package helios

import spock.genesis.Gen
import spock.genesis.generators.Generator

class ValidatorErrorSpecGenerators {

    static Generator<ValidatorError> getErrorGenerator() {
        def error = [
            value: Gen.any(1L, null, "", 23, new Date(), new File("/tmp")),
            property: Gen.any(null, 'amount', null, 'date'),
            key: Gen.any('error.required', 'error.custom')] as Map<String,?>

        return Gen.map(error)
    }
}
