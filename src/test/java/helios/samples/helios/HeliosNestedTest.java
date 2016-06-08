package helios.samples.helios;

import static org.junit.Assert.assertEquals;

import static helios.Helios.validate;
import static helios.Validators.inRange;
import static helios.Validators.required;
import static helios.Validators.inRangeOfString;

import java.util.List;
import org.junit.Test;

import helios.ValidatorError;

public class HeliosNestedTest {

    static class Person {

        public final String name;
        public final Integer age;
        public final String city;

        public Person(String name, Integer age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }
    }

    // tag::test[]
    @Test public void testPerson() {
        Person john = new Person("John", 35, "Paris");
        List<ValidatorError> errors = validate("person", john,
            (Person p) -> validate("name", p.name, required()),
            (Person p) -> validate("age", p.age, required(), inRange(0,100)),
            (Person p) -> validate("city", p.city, inRangeOfString(4,4))); // this will fail


        assertEquals(errors.get(0).key, "string.max.notmet");
        assertEquals(errors.get(0).property, "city");
        assertEquals(errors.get(0).keyI18n, "person.city.string.max.notmet");
    }
    // end::test[]
}
