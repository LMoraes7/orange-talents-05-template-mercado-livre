package br.com.zup.academy.mercado.livre.dominio.util;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class CriadorDeNotificacao {

	private Notificacao notificacao;
	
	public CriadorDeNotificacao() {
	}
	
	public CriadorDeNotificacao deUsuarioInteressado(Usuario usuarioInteressado) {
		this.notificacao = new Notificacao(usuarioInteressado);
		return this;
	}
	
	public CriadorDeNotificacao paraDestinatario(Usuario usuarioDestinatario) {
		this.notificacao.addDestinatario(usuarioDestinatario);
		return this;
	}
	
	public CriadorDeNotificacao assunto(String assunto) {
		this.notificacao.addAssunto(assunto);
		return this;
	}
	
	public CriadorDeNotificacao corpo(String corpo) {
		this.notificacao.addCorpo(corpo);
		return this;
	}
	
	public Notificacao build() {
		return this.notificacao;
	}
}
