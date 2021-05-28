package br.com.zup.academy.mercado.livre.dominio.util;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class Notificacao {

	private Usuario usuarioInterassado;
	private Usuario usuarioDonoDoProduto;
	private String assunto;
	private String corpo;
	
	public Notificacao(Usuario usuarioInterassado) {
		this.usuarioInterassado = usuarioInterassado;
	}
	
	public void addDestinatario(Usuario remetente) {
		this.usuarioDonoDoProduto = remetente;
	}
	
	public void addAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public void addCorpo(String corpo) {
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