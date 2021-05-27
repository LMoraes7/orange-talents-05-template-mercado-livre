package br.com.zup.academy.mercado.livre.dominio.exception;

public class UsuarioNaoTemPermissaoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoTemPermissaoException() {
		super("Usuário não possui permissão para manipular essa informação");
	}
}
