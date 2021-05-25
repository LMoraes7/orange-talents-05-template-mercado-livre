package br.com.zup.academy.mercado.livre.dominio.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CategoriaNaoEncontradaException(Long id) {
		this(String.format("Não existe categoria cadastrada para o id %d", id));
	}
}
