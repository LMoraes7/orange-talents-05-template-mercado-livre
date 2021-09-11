package br.com.zup.academy.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidateEntityValidator.class})
public @interface ValidateEntity {

    String message() default "Entered value does not reference a record. Please enter another value.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String field();
    Class<?> entity();
}
