package br.com.zup.academy.mercado.livre.dominio.util;

public interface Notificador {

	void notificar(String emailDoPerguntador, String emailDoVendedor, String assunto, String corpo);
}
