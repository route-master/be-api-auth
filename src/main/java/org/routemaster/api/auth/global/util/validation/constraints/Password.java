package org.routemaster.api.auth.global.util.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.routemaster.api.auth.global.util.validation.validator.PasswordConstraintValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Password {

    String message() default "{org.routemaster.api.auth.global.util.validation.constraints.Password.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
