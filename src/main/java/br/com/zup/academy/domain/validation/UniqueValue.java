package br.com.zup.academy.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {

    String message() default "Value informed has already been registered. Please try another value.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String field();
    Class<?> entity();
}
