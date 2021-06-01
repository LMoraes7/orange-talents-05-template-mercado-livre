package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario;

import java.util.List;

public class NotificarUsuario {

	public static void notificar(List<Notificador> notificadores, Notificacao notificacao) {
		for (Notificador notificador : notificadores) {
			notificador.notificar(notificacao);
		}
	}
}
