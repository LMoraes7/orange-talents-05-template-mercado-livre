package br.com.zup.academy.mercado.livre.controller.categoria.form;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao.ValorUnico;

public class CategoriaForm {

	@NotBlank
	@ValorUnico(entidade = Categoria.class, campo = "nome")
	private String nome;
	private Long categoriaMaeId;

	@JsonCreator
	public CategoriaForm(@NotBlank String nome, Long categoriaMaeId) {
		this.nome = nome;
		this.categoriaMaeId = categoriaMaeId;
	}
	
	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public Categoria toCategoria(Categoria categoriaMae) {
		return new Categoria(nome, categoriaMae);
	}
}