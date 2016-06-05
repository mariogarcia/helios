package helios.samples;

import static helios.Helios.validate;

import static helios.Validators.min;
import static helios.Validators.minOfString;
import static helios.Validators.required;
import static helios.ValidatorsUtil.validator;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Arrays;
import org.junit.Test;

import helios.ValidatorError;
import helios.Validator;

public class HeliosTest {

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

    // tag::usevarargs[]
    public static List<ValidatorError> validateOnlyId(final Loan loanToValidate) {
        return validate("loan.id", loanToValidate.id, required(), min(0));
    }
    // end::usevarargs[]

    // tag::uselists[]
    public static List<ValidatorError> validateOnlyName(final Loan loanToValidate) {
        final List<Validator<String>> GROUP = Arrays.asList(required(), minOfString(4));

        return validate("loan.name", loanToValidate.name, GROUP);
    }
    // end::uselists[]

    // tag::uselambda[]
    public static List<ValidatorError> validateOnTheFly(final Loan loanToValidate) {
        Validator<String> custom = (String st) -> {
            return !st.equals("Helios") ?
                Arrays.asList(ValidatorError.error(st, "is.not.helios")) :
                Arrays.asList();
        };

        return validate("loan.name", loanToValidate.name, required(), custom);
    }
    // end::uselambda[]

    // tag::validateBeforeSave[]
    public static List<ValidatorError> validateBeforeSave(final Loan loanToValidate) {
        return validate("loan", loanToValidate,
            (Loan loan) -> validate("name", loan.name, required(), minOfString(4)),
            (Loan loan) -> validate("amount", loan.amount, required()));
    }
    // end::validateBeforeSave[]

    // tag::validateBeforeUpdate[]
    public static List<ValidatorError> validateBeforeUpdate(final Loan loanToValidate) {
        final List<Validator<Long>>   ID_VALIDATOR   = Arrays.asList(required(), min(0L)); // <1>
        final List<Validator<String>> NAME_VALIDATOR = Arrays.asList(required(), minOfString(4));

        return validate("loan", loanToValidate,
            (Loan loan) -> validate("id", loan.id, ID_VALIDATOR), // <2>
            (Loan loan) -> validate("name", loan.name, NAME_VALIDATOR),
            (Loan loan) -> validate("amount", loan.amount, required()));
    }
    // end::validateBeforeUpdate[]
}
