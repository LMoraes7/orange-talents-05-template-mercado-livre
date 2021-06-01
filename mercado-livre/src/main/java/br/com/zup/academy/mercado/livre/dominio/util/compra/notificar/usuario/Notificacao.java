package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario;

import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

public class Notificacao {

	private Usuario destinatario;
	private String assunto;
	private String corpo;

	public Notificacao(Usuario destinatario, String assunto, String corpo) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.corpo = corpo;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getCorpo() {
		return corpo;
	}
}