package helios;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import java.util.function.Function;
import java.util.function.Predicate;

public class Validators {

    private static final String BLANK = "";

    private Validators() { }

    public static <T> Validator<T> required() {
        return (T opt) -> evaluate(opt, (x) -> {System.out.println(x); return x != null;}, error(opt, "required"));
    }

    public static Validator<String> stringSize(final int n) {
        return (String opt) -> evaluate(opt, x -> x.length() >= n, error(opt, "string.min.notmet"));
    }

    public static <T extends Number> Validator<T> min(final Number n) {
        return (T opt) -> evaluate(opt, x -> x.doubleValue() >= n.doubleValue(), error(opt, "min.notmet"));
    }

    private static <T> List<ValidatorError<T>> evaluate(T value, Predicate<T> cond, ValidatorError<T> error) {
        return Optional
            .ofNullable(value)
            .filter(cond)
            .map(next -> Arrays.asList(error))
            .orElse(Arrays.asList());
    }

    private static <T> ValidatorError<T> error(T value, String errorKey) {
        return new ValidatorError<T>(value, BLANK, errorKey);
    }
}
