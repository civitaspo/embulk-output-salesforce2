package pro.civitaspo.embulk.output.salesforce2.config.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.ZoneId;

public class ZoneIdValidator implements ConstraintValidator<ValidZoneId, String> {

    @Override
    public boolean isValid(String zoneIdString, ConstraintValidatorContext context) {
        try {
            ZoneId.of(zoneIdString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
