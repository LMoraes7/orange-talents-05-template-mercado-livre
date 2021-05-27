package br.com.zup.academy.mercado.livre.dominio.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("fake")
@Component
public class NotificadorEmailFake implements Notificador{

	@Override
	public void notificar(String emailDoPerguntador, String emailDoVendedor, String assunto, String corpo) {
		StringBuilder builder = new StringBuilder();
		builder.append("\nRemetente: "+ emailDoPerguntador);
		builder.append("\nDestinatário: "+ emailDoVendedor);
		builder.append("\nAssunto: "+ assunto);
		builder.append("\nCorpo: "+ corpo);
		
		String email = builder.toString();
		System.out.println("ENVIANDO EMAIL" + email);
	}
}