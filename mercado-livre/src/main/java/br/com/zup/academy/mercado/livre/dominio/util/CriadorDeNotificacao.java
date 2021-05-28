package br.com.zup.academy.mercado.livre.dominio.util;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class CriadorDeNotificacao {

	private Usuario usuarioInteressado;
	private Usuario usuarioDestinatario;
	private String assunto;
	private String corpo;

	public CriadorDeNotificacao() {
	}
	
	public CriadorDeNotificacao deUsuarioInteressado(Usuario usuarioInteressado) {
		this.usuarioInteressado = usuarioInteressado;
		return this;
	}
	
	public CriadorDeNotificacao paraDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
		return this;
	}
	
	public CriadorDeNotificacao assunto(String assunto) {
		this.assunto = assunto;
		return this;
	}
	
	public CriadorDeNotificacao corpo(String corpo) {
		this.corpo = corpo;
		return this;
	}
	
	public Notificacao build() {
		if(usuarioDestinatario == null || assunto == null || corpo == null) {
			throw new IllegalArgumentException("Existem argumentos que não preenchidos");
		}
		return new Notificacao(usuarioInteressado, usuarioDestinatario, assunto, corpo);
	}
}