package helios.samples.asciidoc;

import java.util.List;

import helios.Helios;
import helios.Validators;
import helios.ValidatorError;

public class SimpleProperty {

    public List<ValidatorError> validateName(String name) {
        return Helios.validate("name",                            // <1>
                               name,                              // <2>
                               Validators.required(),             // <3>
                               Validators.inRangeOfString(0,4));
    }
}
