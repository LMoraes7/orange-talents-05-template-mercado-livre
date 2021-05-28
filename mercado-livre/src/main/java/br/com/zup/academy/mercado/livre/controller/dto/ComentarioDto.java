package br.com.zup.academy.mercado.livre.controller.dto;

import br.com.zup.academy.mercado.livre.dominio.modelo.Comentario;

public class ComentarioDto {

	private Byte nota;
	private String titulo;
	private String descricao;

	public ComentarioDto(Comentario comentario) {
		this.nota = comentario.getNota();
		this.titulo = comentario.getTitulo();
		this.descricao = comentario.getDescricao();
	}

	public Byte getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}