package br.com.zup.academy.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {

    String message() default "Valor informado já foi cadastrado. Por favor tent outro valor.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String field();
    Class<?> entity();
}
