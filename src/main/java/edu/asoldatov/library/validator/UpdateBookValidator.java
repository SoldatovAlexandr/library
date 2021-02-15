package edu.asoldatov.library.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateBookValidator implements ConstraintValidator<UpdateBook, Object> {
    private String nameField;
    private String yearOfPublishingField;
    private String genreField;

    @Override
    public void initialize(UpdateBook constraintAnnotation) {
        nameField = constraintAnnotation.first();
        yearOfPublishingField = constraintAnnotation.second();
        genreField = constraintAnnotation.third();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);
        String name = (String) beanWrapper.getPropertyValue(nameField);
        Integer yearOfPublishing = (Integer) beanWrapper.getPropertyValue(yearOfPublishingField);
        Long genre = (Long) beanWrapper.getPropertyValue(genreField);
        boolean hasName = name != null;
        boolean hasYearOfPublishing = yearOfPublishing != null;
        boolean hasGenre = genre != null;
        return hasName || hasYearOfPublishing || hasGenre;
    }
}
