package pro.civitaspo.embulk.output.salesforce2.config.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = {NotBlankIfPresentValidator.class})
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface NotBlankIfPresent {
    String message() default "The value cannot be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
