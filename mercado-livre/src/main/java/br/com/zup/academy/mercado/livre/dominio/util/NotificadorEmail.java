package br.com.zup.academy.mercado.livre.dominio.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;

@Component
public class NotificadorEmail {

	@Qualifier("fake")
	private Notificador notificador;

	public NotificadorEmail(Notificador notificador) {
		this.notificador = notificador;
	}

	public void notificar(Pergunta pergunta) {
		String emailDoPerguntador = pergunta.getDonoDaPerguntaEmail();
		String emailDoVendedor = pergunta.getDonoDoProdutoEmail();
		String assunto = "Você recebeu uma nova pergunta sobre o produto "+pergunta.getProduto().getNome();
		String corpo = pergunta.getTitulo();
		this.notificador.notificar(emailDoPerguntador, emailDoVendedor, assunto, corpo);
	}
}
