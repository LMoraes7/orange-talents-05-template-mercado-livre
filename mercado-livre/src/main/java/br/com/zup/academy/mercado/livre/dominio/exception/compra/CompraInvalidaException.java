package br.com.zup.academy.mercado.livre.dominio.exception.compra;

import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;

public class CompraInvalidaException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public CompraInvalidaException(String msg) {
		super(msg);
	}
}
