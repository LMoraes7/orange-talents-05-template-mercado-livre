package br.com.zup.academy.mercado.livre.dominio.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.academy.mercado.livre.controller.form.CaracteristicaForm;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private BigDecimal valor;
	@Column(nullable = false)
	private Integer quantEstoque;
	@Column(columnDefinition = "VARCHAR(1000)", nullable = false)
	private String descricao;
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime instanteCadastro;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Imagem> imagens = new HashSet<Imagem>();

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantEstoque, String descricao, Categoria categoria,
			List<CaracteristicaForm> caracteristicas, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantEstoque = quantEstoque;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas = caracteristicas.stream()
				.map(caracteristicaForm -> caracteristicaForm.toCaracteristica(this)).collect(Collectors.toSet());
	}
	
	public void associaImagens(Set<String> links) {
		imagens = links.stream().map(link -> new Imagem(link, this)).collect(Collectors.toSet());
	}

	public boolean pertenceAoUsuario(Usuario possivelUsuario) {
		return this.usuario.equals(possivelUsuario);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}