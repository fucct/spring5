package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator {


    @Override
    public boolean supports(final Class<?> clazz) {
        return ChangePwdCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
    }
}
