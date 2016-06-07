package helios.samples.asciidoc;

import static helios.Helios.validate;
import static helios.Validators.matches;
import static helios.Validators.required;
import static helios.Validators.inRangeOfString;

import java.util.List;
import java.util.Arrays;

import helios.Validator;
import helios.ValidatorError;

public class Grouping {

    private List<Validator<String>> VALIDATORS =
        Arrays.asList(
            required(),
            inRangeOfString(0,4),
            matches("j.*"));

    public List<ValidatorError> validateName(String name) {
        return validate("name", name, VALIDATORS);
    }

    public List<ValidatorError> validateOtherName(String other) {
        return validate("other", other, VALIDATORS);
    }
}
