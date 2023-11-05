package ma.nemo.assignment.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCodeValidator implements ConstraintValidator<ValidateProductCode, String> {
    private ValidationMessageBuilder validationMessageBuilder;

    @Autowired
    public ProductCodeValidator(ValidationMessageBuilder validationMessageBuilder) {
        this.validationMessageBuilder = validationMessageBuilder;
    }

    @Override
    public void initialize(ValidateProductCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String productCode, ConstraintValidatorContext context) {
        if (productCode == null) {
            validationMessageBuilder.addCustomErrorMessage(context, "Product code should not be null");
            return false;
        }
        if (productCode.isEmpty()) {
            validationMessageBuilder.addCustomErrorMessage(context, "Product code should not be empty");
            return false;
        }
        if (productCode.length() < 3) {
            validationMessageBuilder.addCustomErrorMessage(context, "Product code is too short");
            return false;
        }
        if (productCode.length() > 10) {
            validationMessageBuilder.addCustomErrorMessage(context, "Product code is too long");
            return false;
        }
        return true;
    }
}
