package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ListCommandValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return ListCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (target != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "from", "required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "to", "required");
        }
    }
}
