package edu.asoldatov.library.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateAuthorValidator implements ConstraintValidator<UpdateAuthor, Object> {
    private String firstNameField;
    private String lastNameField;
    private String patronymicField;
    private String yearOfBirthField;
    private String biographyField;

    @Override
    public void initialize(UpdateAuthor constraintAnnotation) {
        firstNameField = constraintAnnotation.first();
        lastNameField = constraintAnnotation.second();
        patronymicField = constraintAnnotation.third();
        yearOfBirthField = constraintAnnotation.forth();
        biographyField = constraintAnnotation.fifth();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);
        String firstName = (String) beanWrapper.getPropertyValue(firstNameField);
        String lastName = (String) beanWrapper.getPropertyValue(lastNameField);
        String patronymic = (String) beanWrapper.getPropertyValue(patronymicField);
        Integer yearOfBirth = (Integer) beanWrapper.getPropertyValue(yearOfBirthField);
        String biography = (String) beanWrapper.getPropertyValue(biographyField);
        boolean hasFirstName = firstName != null;
        boolean hasLastName = lastName != null;
        boolean hasPatronymic = patronymic != null;
        boolean hasYearOfBirth = yearOfBirth != null;
        boolean hasBiography = biography != null;
        return hasFirstName || hasLastName || hasPatronymic || hasYearOfBirth || hasBiography;
    }
}
