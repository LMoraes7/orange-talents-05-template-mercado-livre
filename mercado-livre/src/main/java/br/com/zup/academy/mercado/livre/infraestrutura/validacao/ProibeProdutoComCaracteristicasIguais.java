package br.com.zup.academy.mercado.livre.infraestrutura.validacao;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.academy.mercado.livre.controller.form.ProdutoForm;

@Component
public class ProibeProdutoComCaracteristicasIguais implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProdutoForm form = (ProdutoForm) target;
		if (errors.hasErrors())
			return;
		Set<String> caracteristicasIguais = form.buscaCaracteristicasIguais();
		if (!caracteristicasIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null,
					"O produto não pode possuir características iguais: " + caracteristicasIguais);
		}
	}
}