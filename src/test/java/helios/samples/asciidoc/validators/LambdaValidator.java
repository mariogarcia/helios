package helios.samples.asciidoc.validators;

import static helios.ValidatorError.error;
import static helios.ValidatorError.errors;
import static helios.Validators.required;

import helios.Validator;
import helios.Helios;

public class LambdaValidator {

    public Validator<String> getCustomValidator() { // <1>
        return (String word) -> {
            return word.startsWith("j") ?
                errors() :
                errors(error(word, "starts.with.j"));
        };
    }

    public Validator<String> getRequiredStringValidator() { // <2>
        return (String word) -> Helios.validate("word", word, required());
    }
}
