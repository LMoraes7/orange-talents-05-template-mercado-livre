package br.com.zup.academy.mercado.livre.controller.dto;

import br.com.zup.academy.mercado.livre.dominio.modelo.Caracteristica;

public class CaracteristicaDto {

	private String nome;
	private String descricao;

	public CaracteristicaDto(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}