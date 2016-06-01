package helios.samples.jdk;

import static helios.Helios.validate;

import static helios.Validators.min;
import static helios.Validators.required;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

import helios.ValidatorError;

public class SimplePropertiesTest {

    static class Loan {
        public Long id;
        public String name;
        public Double amount;

        public Loan(Long id, String name, Double amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }
    }

    public static List<ValidatorError> validateLoan(final Loan loanToValidate) {
        return validate("loan", loanToValidate,
            (Loan loan) -> validate("id", loan.id, required(), min(0)),
            (Loan loan) -> validate("name", loan.name, required()),
            (Loan loan) -> validate("amount", loan.amount, required()));
    }

    @Test
    public void testInvalidLoanId() {
        Loan loan = new Loan(1L, "car loan", 12000.0);

        assertEquals(validateLoan(loan).size(), 1);
    }

    @Test
    public void testInvalidName() {
        Loan loan = new Loan(null, null, 12d);

        assertEquals(validateLoan(loan).size(), 2);
    }

    public void testInvalidAmount() {
        Loan loan = new Loan(null, null, null);

        assertEquals(validateLoan(loan).size(), 3);
    }
}
