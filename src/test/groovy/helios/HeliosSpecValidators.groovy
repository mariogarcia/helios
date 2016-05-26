package helios

import helios.HeliosSpecProperties as PROPERTY

class HeliosSpecValidators {

    static Validator<PROPERTY.Loan> loanByName() {
        return { PROPERTY.Loan loan ->
            return Helios.validate(loan, "error.loan.name") { it.name?.isEmpty() }
        }
    }

    static Validator<PROPERTY.Loan> loanByAmount() {
        return { PROPERTY.Loan loan ->
            return Helios.validate(loan, "error.loan.amount") { it.amount > 0 && it.amount < 1000 }
        }
    }
}
