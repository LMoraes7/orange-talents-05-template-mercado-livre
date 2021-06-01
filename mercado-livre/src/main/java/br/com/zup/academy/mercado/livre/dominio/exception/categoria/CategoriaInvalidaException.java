package br.com.zup.academy.mercado.livre.dominio.exception.categoria;

import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;

public class CategoriaInvalidaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public CategoriaInvalidaException(String msg) {
		super(msg);
	}
	
	public CategoriaInvalidaException(Long id) {
		this(String.format("Não existe categoria cadastrada para o id %d", id));
	}
}
