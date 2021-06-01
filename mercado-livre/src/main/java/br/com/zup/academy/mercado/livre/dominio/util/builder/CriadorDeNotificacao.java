package br.com.zup.academy.mercado.livre.dominio.util.builder;

import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificacao;

public class CriadorDeNotificacao {

	private Usuario usuarioDestinatario;
	private String assunto;
	private String corpo;

	public CriadorDeNotificacao() {
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
		return new Notificacao(usuarioDestinatario, assunto, corpo);
	}
}