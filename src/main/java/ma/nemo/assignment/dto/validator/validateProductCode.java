package ma.nemo.assignment.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateProductCode {
    String message() default "Product code is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
