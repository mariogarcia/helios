package helios.samples.asciidoc;

import static helios.Helios.validate;
import static helios.Validators.inRange;
import static helios.Validators.inRangeOfString;
import static helios.Validators.required;

import java.util.List;
import java.util.Arrays;

import helios.Validator;
import helios.ValidatorError;

public class Nested {

    static class Product {
        public final String desc;
        public final Double amount;
        public final Integer items;

        public Product(String desc, Double amount, Integer items) {
            this.desc = desc;
            this.amount = amount;
            this.items = items;
        }
    }

    // tag::nested[]
    public List<ValidatorError> validateProduct(final Product product) {
        return validate("product", product,
            (Product p) -> validate("desc", p.desc, getDescValidators()),         // <1>
            (Product p) -> validate("items", p.items, getItemsValidators()),      // <2>
            (Product p) -> validate("amount", p.amount, getAmountValidators()));  // <3>
    }
    // end::nested[]

    private List<Validator<String>> getDescValidators() {
        return Arrays.asList(required(), inRangeOfString(5,100));
    }

    private List<Validator<Integer>> getItemsValidators() {
        return Arrays.asList(required(), inRange(1,100));
    }

    private List<Validator<Double>> getAmountValidators() {
        return Arrays.asList(required(), inRange(10d,1000d));
    }
}
