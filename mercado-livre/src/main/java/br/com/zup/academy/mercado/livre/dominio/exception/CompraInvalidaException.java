package br.com.zup.academy.mercado.livre.dominio.exception;

public class CompraInvalidaException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public CompraInvalidaException(String msg) {
		super(msg);
	}
}
