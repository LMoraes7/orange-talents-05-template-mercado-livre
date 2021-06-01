package br.com.zup.academy.mercado.livre.controller.produto.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao.ExisteId;

public class ProdutoForm {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	private Integer quantEstoque;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@ExisteId(entidade = Categoria.class, campo = "id")
	private Long categoriaId;
	@NotNull
	@Size(min = 3)
	@Valid
	private List<CaracteristicaForm> caracteristicas = new ArrayList<>();

	@JsonCreator
	public ProdutoForm(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantEstoque, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Long categoriaId, @NotNull @Size(min = 3) @Valid List<CaracteristicaForm> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantEstoque = quantEstoque;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas = caracteristicas;
	}
	
	public Long getCategoriaId() {
		return categoriaId;
	}

	public Set<String> buscaCaracteristicasIguais() {
		Set<String> iguais = new HashSet<>();
		Set<String> auxiliar = new HashSet<>();
		for (CaracteristicaForm caracteristica : this.caracteristicas) {
			if (!auxiliar.add(caracteristica.getNome()))
				iguais.add(caracteristica.getNome());
		}
		return iguais;
	}

	public Produto toProduto(Categoria categoria, Usuario usuario) {
		return new Produto(nome, valor, quantEstoque, descricao, categoria, caracteristicas, usuario);
	}
}