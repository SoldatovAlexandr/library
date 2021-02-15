package edu.asoldatov.library.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UpdateAuthorValidator.class)
@Documented
public @interface UpdateAuthor {
    String message() default "PARAMS_NOT_SET";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    String third();

    String forth();

    String fifth();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UpdateAuthor[] value();
    }
}
