package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario;

import org.springframework.stereotype.Component;

@Component
public class NotificadorEmailFake implements Notificador{

	@Override
	public void notificar(Notificacao notificacao) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nRemetente: mercadolivre@email.com.br");
		builder.append("\nDestinatário: "+ notificacao.getDestinatario().getUsername());
		builder.append("\nAssunto: "+ notificacao.getAssunto());
		builder.append("\nCorpo: "+ notificacao.getCorpo());
		
		String email = builder.toString();
		System.out.println("ENVIANDO EMAIL" + email);
	}
}