package pro.civitaspo.embulk.output.salesforce2.config.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private boolean isCaseSensitive;
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        isCaseSensitive = annotation.caseSensitive();
        acceptedValues =
                Stream.of(annotation.enumClass().getEnumConstants())
                        .map(Enum::name)
                        .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (acceptedValues.stream()
                .anyMatch(
                        v ->
                                isCaseSensitive
                                        ? v.equals(value.toString())
                                        : v.equalsIgnoreCase(value.toString()))) {
            return true;
        }

        customizeErrorMessage(context);
        return false;
    }

    private void customizeErrorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        "must be any of values ["
                                + acceptedValues.stream()
                                        .map(v -> String.format("'%s'", v))
                                        .collect(Collectors.joining(", "))
                                + "]")
                .addConstraintViolation();
    }
}
