package br.com.zup.academy.mercado.livre.dominio.exception.transacao;

import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;

public class TransacaoInvalidaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public TransacaoInvalidaException(String msg) {
		super(msg);
	}
}
