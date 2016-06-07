package helios.samples.asciidoc.validators;

import static helios.Validators.matches;
import static helios.Validators.required;

import static helios.ValidatorsUtil.compose;

import java.util.List;
import java.util.Arrays;

import helios.Validator;

public class AsPredicate {

    private final List<String> WORDS = Arrays.asList( // <1>
        "something",
        "aloha",
        "somewhere",
        "some");

    public Long countWordsStartingWithS() {
        Validator<String> byCustom = compose(required(), matches("[Ss].*"));

        return countWords(WORDS, byCustom); // <2>
    }

    public Long countWords(final List<String> wordList, final Validator<String> filter) {
        return wordList
            .stream()
            .filter(filter.toPredicate())  // <3>
            .count();
    }
}
