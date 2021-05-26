package br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.academy.mercado.livre.infraestrutura.validacao.ExisteIdValidator;

@Target({FIELD, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
public @interface ExisteId {

	String message() default "Entidade refereciada não existe no cadastrado";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String campo();
	
	Class<?> entidade();
}
