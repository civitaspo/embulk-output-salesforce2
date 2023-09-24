package pro.civitaspo.embulk.output.salesforce2.config.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;

public class NotBlankIfPresentValidator
        implements ConstraintValidator<NotBlankIfPresent, Optional<String>> {

    @Override
    public void initialize(NotBlankIfPresent constraintAnnotation) {}

    @Override
    public boolean isValid(Optional<String> value, ConstraintValidatorContext context) {
        if (!value.isPresent()) return true;
        String strValue = value.get();
        return strValue != null && !strValue.trim().isEmpty();
    }
}
