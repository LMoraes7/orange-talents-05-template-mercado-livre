package br.com.zup.academy.mercado.livre.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mercado.livre.dominio.exception.CategoriaNaoEncontradaException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.repository.CategoriaRepository;
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

	public Categoria toCategoria(CategoriaRepository categoriaRepository) {
		if(this.categoriaMaeId != null) {
			Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaMaeId);
			if(categoriaOptional.isPresent()) 
				return new Categoria(nome, categoriaOptional.get());
			throw new CategoriaNaoEncontradaException(categoriaMaeId);
		}
		return new Categoria(nome);
	}
}