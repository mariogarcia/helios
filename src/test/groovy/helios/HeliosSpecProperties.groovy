package helios

import groovy.transform.ToString

class HeliosSpecProperties {

    static class Loan {
        Long id
        String name
        Double amount
    }

    static void classHasNoPublicConstructor(Class<Helios> clazz) {
        assert clazz.constructors.size() == 0
        assert clazz.newInstance()
    }

    static void checkLoanResult(List<ValidatorError<Loan>> result) {
        if (result.size() > 0) {
            assert result.any { ValidatorError<Loan> error ->
                error.key in ['required', 'min.notmet', 'string.min.notmet']
            }
        } else {
            assert result.size() == 0
        }
    }

}
