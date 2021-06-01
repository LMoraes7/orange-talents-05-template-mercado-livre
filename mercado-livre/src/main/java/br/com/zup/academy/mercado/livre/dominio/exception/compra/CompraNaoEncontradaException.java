package br.com.zup.academy.mercado.livre.dominio.exception.compra;

import br.com.zup.academy.mercado.livre.dominio.exception.EntidadeNaoEncontradaException;

public class CompraNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public CompraNaoEncontradaException(String msg) {
		super(msg);
	}

	public CompraNaoEncontradaException(Long id) {
		this(String.format("Não existe compra cadastrada com id %d", id));
	}
}
