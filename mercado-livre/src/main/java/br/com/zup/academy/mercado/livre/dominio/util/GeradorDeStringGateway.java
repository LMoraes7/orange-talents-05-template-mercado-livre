package br.com.zup.academy.mercado.livre.dominio.util;

import java.util.UUID;

public class GeradorDeStringGateway {

	public static String gerarUrl(String string) {
		return string.concat("-"+UUID.randomUUID().toString());
	}
}
