package br.com.zup.academy.mercado.livre.controller.dto;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;

public class PerguntaDto {

	private String titulo;

	public PerguntaDto(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
}