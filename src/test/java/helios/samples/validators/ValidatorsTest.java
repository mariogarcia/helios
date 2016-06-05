package helios.samples.jdk.validators;

import static org.junit.Assert.assertEquals;

import static helios.Helios.validate;
import static helios.Validators.min;
import static helios.Validators.required;
import static helios.Validators.minOfString;

import helios.ValidatorError;

import java.util.List;
import org.junit.Test;

public class ValidatorsTest {

    static class Product {
        final Long id;
        final String desc;
        final Double amount;

        public Product(final Long id, final String desc, final Double amount) {
            this.id = id;
            this.desc = desc;
            this.amount = amount;
        }
    }

    @Test public void testData() {
        Product product = new Product(1L, "Bike 600cc", 100d);
        List<ValidatorError> errors = validate("product", product,
            (Product p) -> validate("id", p.id, required()),
            (Product p) -> validate("desc", p.desc, required(), minOfString(4)),
            (Product p) -> validate("amount", p.amount, required(), min(1000d)));

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).key, "min.notmet");
    }
}
