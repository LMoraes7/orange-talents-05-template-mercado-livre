package br.com.zup.academy.mercado.livre.controller.form;

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

import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
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
	@Size(min = 3)
	@Valid
	private List<CaracteristicaForm> caracteristicas = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantEstoque() {
		return quantEstoque;
	}

	public void setQuantEstoque(Integer quantEstoque) {
		this.quantEstoque = quantEstoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public List<CaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicaForm> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Set<String> buscaCaracteristicasIguais() {
		Set<String> iguais = new HashSet<>();
		Set<String> auxiliar = new HashSet<>();
		for (CaracteristicaForm caracteristica : this.caracteristicas) {
			if(!auxiliar.add(caracteristica.getNome()))
				iguais.add(caracteristica.getNome());
		}
		return iguais;
	}

	public Produto toProduto(Categoria categoria, Usuario usuario) {
		return new Produto(nome, valor, quantEstoque, descricao, categoria, caracteristicas, usuario);
	}
}