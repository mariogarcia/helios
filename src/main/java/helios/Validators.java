package helios;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.util.function.Function;
import java.util.function.Predicate;

public class Validators {

    private static final String BLANK = "";

    private Validators() { }

    public static <T> Validator<T> required() {
        return (T opt) -> unsafe(opt, x -> x == null, error(opt, "required"));
    }

    public static Validator<String> stringSize(final int n) {
        return (String opt) -> safe(opt, x -> x.length() >= n, error(opt, "string.min.notmet"));
    }

    public static <T extends Number> Validator<T> min(final T number) {
        return (T opt) -> safe(opt, x -> x.doubleValue() >= number.doubleValue(), error(opt, "min.notmet"));
    }

    private static <T> List<ValidatorError<T>> safe(T value, Predicate<T> cond, ValidatorError<T> error) {
        return Optional
            .ofNullable(value)
            .filter(cond)
            .map(next -> asList(error))
            .orElse(asList());
    }

    private static <T> List<ValidatorError<T>> unsafe(T value, Predicate<T> cond, ValidatorError<T> error) {
        return cond.test(value) ? asList() : asList(error);
    }

    private static <T> ValidatorError<T> error(T value, String errorKey) {
        return new ValidatorError<T>(value, BLANK, errorKey);
    }
}
