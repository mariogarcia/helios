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

    static void checkLoanResult(final List<ValidatorError> result) {
        if (!result.isEmpty()) {
            assert result.any { ValidatorError error ->
                error.key in ['required', 'min.notmet', 'string.min.notmet'] &&
                error.property.startsWith('loan.')
            }
        }
    }

    static void checkErrorPropertyField(final List<ValidatorError> result) {
        if (!result.isEmpty()) {
            assert result.any { ValidatorError error ->
                error.property in ["loan.amount", "loan"]
            }
        }
    }
}
