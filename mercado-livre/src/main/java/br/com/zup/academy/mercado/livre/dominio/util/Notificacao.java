package br.com.zup.academy.mercado.livre.dominio.util;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class Notificacao {

	private Usuario usuarioInterassado;
	private Usuario usuarioDonoDoProduto;
	private String assunto;
	private String corpo;

	public Notificacao(Usuario usuarioInterassado, Usuario usuarioDonoDoProduto, String assunto, String corpo) {
		this.usuarioInterassado = usuarioInterassado;
		this.usuarioDonoDoProduto = usuarioDonoDoProduto;
		this.assunto = assunto;
		this.corpo = corpo;
	}

	public Usuario getUsuarioInterassado() {
		return usuarioInterassado;
	}

	public Usuario getUsuarioDonoDoProduto() {
		return usuarioDonoDoProduto;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getCorpo() {
		return corpo;
	}
}