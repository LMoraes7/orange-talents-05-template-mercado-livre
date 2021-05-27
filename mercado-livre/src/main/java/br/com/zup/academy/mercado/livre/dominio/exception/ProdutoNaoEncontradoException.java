package br.com.zup.academy.mercado.livre.dominio.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProdutoNaoEncontradoException(Long id) {
		this(String.format("Não existe produto cadastrado com id %d", id));
	}
}
