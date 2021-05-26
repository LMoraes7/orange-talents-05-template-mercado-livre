package br.com.zup.academy.mercado.livre.infraestrutura.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao.ExisteId;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object>{

	private String campo;
	private Class<?> entidade;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteId constraintAnnotation) {
		campo = constraintAnnotation.campo();
		entidade = constraintAnnotation.entidade();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String jpql = "SELECT 1 FROM "+this.entidade.getName()+" x WHERE x."+this.campo+" = :value";
		List<?> lista = this.manager.createQuery(jpql)
			.setParameter("value", value)
			.getResultList();
		return !lista.isEmpty();
	}
}