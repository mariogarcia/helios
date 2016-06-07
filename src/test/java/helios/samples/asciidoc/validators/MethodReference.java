package helios.samples.asciidoc.validators;

import static helios.Validators.required;
import static helios.Validators.inRangeOfString;

import static helios.ValidatorError.error;
import static helios.ValidatorError.errors;

import java.util.List;

import helios.Helios;
import helios.Validator;
import helios.ValidatorError;

public class MethodReference {

    public List<ValidatorError> isSmallWord(final String word) {
        return Helios.validate("word", word, this::stringValidator);
    }

    public List<ValidatorError> stringValidator(String word) {
        return word != null && word.length() > 0 ?
            errors() :
            errors(error(word, "string.required"));
    }
}
