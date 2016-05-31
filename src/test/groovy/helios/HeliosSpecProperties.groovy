package helios

import groovy.transform.ToString
import groovy.transform.CompileStatic

@CompileStatic
class HeliosSpecProperties {

    @ToString
    static class Loan {
        Long id
        String name
        Double amount
    }

    static void checkLoanResult(List<ValidatorError> result) {
        if (result.size() > 0) {
            assert result.any { ValidatorError<Loan> error ->
                error.key in ['required', 'min.notmet', 'string.min.notmet'] &&
                error.property.startsWith('loan.')
            }
        } else {
            assert result.size() == 0
        }
    }
}
