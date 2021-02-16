package edu.asoldatov.library.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordsValidator implements ConstraintValidator<ConfirmPasswords, Object> {
    private String passwordField;
    private String confirmPasswordField;


    @Override
    public void initialize(ConfirmPasswords constraintAnnotation) {
        passwordField = constraintAnnotation.password();
        confirmPasswordField = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);

        String password = (String) beanWrapper.getPropertyValue(passwordField);

        String confirmPassword = (String) beanWrapper.getPropertyValue(confirmPasswordField);

        return password != null && password.equals(confirmPassword);
    }
}
