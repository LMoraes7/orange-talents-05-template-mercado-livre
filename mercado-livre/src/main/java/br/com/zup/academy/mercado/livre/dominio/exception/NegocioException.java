package br.com.zup.academy.mercado.livre.dominio.exception;

public abstract class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msg) {
		super(msg);
	}
}
