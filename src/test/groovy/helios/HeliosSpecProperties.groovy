package helios

class HeliosSpecProperties {

    static class Loan {
        String name
        Double amount
    }

    static void classHasNoPublicConstructor(Class<Helios> clazz) {
        assert clazz.constructors.size() == 0
        assert clazz.newInstance()
    }

    static void emailHasCharAt(String next) {
        def result = validateAt(next)

        if (result.hasErrors()) {
            assert result.errors.find().key == 'error.email.at'
            assert result.hasErrors()
        }

        if (result.hasNoErrors()) {
            assert result.errors.isEmpty()
        }
    }

    static ValidatorResult<String> validateAt(String next) {
        return Helios.validate(next, "error.email.at") { String email ->
            email.contains('@')
        }
    }

    static void emailHasDomain(String next) {
        def result = validateDomain(next)

        if (result.hasErrors()) {
            assert result.errors.find().key == 'error.email.domain'
        }
    }

    static ValidatorResult<String> validateDomain(String next) {
        return Helios.validate(next, "error.email.domain") { String email ->
            return email.contains('@') && email.substring(email.indexOf('@')).size() > 0
        }
    }

    static void checkComposition(Validator<Loan> validator, Loan loan) {
        ValidatorResult<Loan> result = validator.validate(loan)

        if (result.hasErrors()) {
            assert result.errors.any { ValidatorError<Loan> error ->
                error.key in ['error.loan.amount','error.loan.name']
            }
        }
    }

    static void checkVarArgsComposition(Optional<Validator<Loan>> validator, Loan loan) {
        if (validator.isPresent()) {
            ValidatorResult<Loan> result = validator.get().validate(loan)

            if (result.hasErrors()) {
                assert result.errors.any { ValidatorError<Loan> error ->
                    error.key in ['error.loan.amount','error.loan.name']
                }
            }
        }
    }

    static void checkLoanResult(ValidatorResult<Loan> result) {
        if (result.hasErrors()) {
            assert result.errors.any { ValidatorError<Loan> error ->
                error.key in ['error.loan.amount','error.loan.name']
            }
        }
    }
}
