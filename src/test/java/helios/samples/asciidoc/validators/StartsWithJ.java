package helios.samples.asciidoc.validators;

import static helios.ValidatorError.error; // <1>
import static helios.ValidatorError.errors;

import java.util.List;

import helios.Validator;
import helios.ValidatorError;

public class StartsWithJ implements Validator<String> {  // <2>

    @Override
    public List<ValidatorError> validate(String word) {
        return word.startsWith("j") ?
            errors() :
            errors(error(word, "starts.with.j"));        // <3>
    }
}
