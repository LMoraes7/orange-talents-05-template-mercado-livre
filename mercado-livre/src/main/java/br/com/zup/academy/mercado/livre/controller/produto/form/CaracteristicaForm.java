package br.com.zup.academy.mercado.livre.controller.produto.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Caracteristica;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;

public class CaracteristicaForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Caracteristica toCaracteristica(Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}
}