package helios

import helios.HeliosSpecProperties as PROPERTY

class HeliosSpecValidators {

    static Validator<PROPERTY.Loan> loanByName() {
        return { PROPERTY.Loan loan ->
            if (!loan.name) {
                return [new ValidatorError<PROPERTY.Loan>(loan.name, "", "required")]
            }
            return []
        }
    }

    static Validator<PROPERTY.Loan> loanByAmount() {
        return { PROPERTY.Loan loan ->
            return loan.amount > 0 && loan.amount < 1000 ?
                [new ValidatorError<PROPERTY.Loan>(loan.amount, "", "error.loan.amount")] :
                []
        }
    }
}
