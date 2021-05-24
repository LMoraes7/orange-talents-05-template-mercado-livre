package br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.academy.mercado.livre.infraestrutura.validacao.ValorUnicoValidator;

@Target({FIELD, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValorUnicoValidator.class})
public @interface ValorUnico {

	String message() default "Valor informado já foi cadastrado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String campo();
	
	Class<?> entidade();
}
