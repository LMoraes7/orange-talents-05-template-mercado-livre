package br.com.zup.academy.mercado.livre.dominio.exception.usuario;

import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;

public class UsuarioInvalidoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException() {
		super("Dados de login inválidos");
	}
}
