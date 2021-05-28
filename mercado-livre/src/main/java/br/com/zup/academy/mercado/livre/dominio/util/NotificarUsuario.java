package br.com.zup.academy.mercado.livre.dominio.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificarUsuario {

	@Qualifier("fake")
	private Notificador notificador;

	public NotificarUsuario(Notificador notificador) {
		this.notificador = notificador;
	}

	public void notificar(Notificacao notificacao) {
		this.notificador.notificar(notificacao);
	}
}