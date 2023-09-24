package pro.civitaspo.embulk.output.salesforce2.config.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ZoneIdValidator.class)
public @interface ValidZoneId {
    String message() default "Invalid ZoneId";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
