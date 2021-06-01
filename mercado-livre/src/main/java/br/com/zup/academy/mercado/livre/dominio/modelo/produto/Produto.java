package br.com.zup.academy.mercado.livre.dominio.modelo.produto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
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
import org.springframework.util.Assert;

import br.com.zup.academy.mercado.livre.controller.produto.form.CaracteristicaForm;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.Comentario;
import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Imagem> imagens = new HashSet<Imagem>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Comentario> comentarios = new HashSet<Comentario>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Pergunta> perguntas = new HashSet<Pergunta>();

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
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getMediaNotas() {
		OptionalDouble mediaOptional = this.comentarios.stream().mapToInt(comentario -> comentario.getNota()).average();
		if (mediaOptional.isPresent())
			return new BigDecimal(mediaOptional.getAsDouble()).setScale(2, RoundingMode.HALF_EVEN);
		return new BigDecimal("0.0");
	}

	public int getQuantNotas() {
		return this.comentarios.size();
	}

	public void associaImagens(Set<String> links) {
		links.stream().map(link -> new Imagem(link, this)).collect(Collectors.toSet());
	}

	public boolean pertenceAoUsuario(Usuario possivelUsuario) {
		return this.usuario.equals(possivelUsuario);
	}

	public void addComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	public void addPergunta(Pergunta pergunta) {
		this.perguntas.add(pergunta);
	}

	public void addImagem(Imagem imagem) {
		this.imagens.add(imagem);
	}

	public boolean abaterEstoque(Integer quantidade) {
		Assert.isTrue(quantidade > 0, "Quantidade para abater não pode ser menor ou igual do que 0");
		if(this.quantEstoque.compareTo(quantidade) < 0)
			return false;
		this.quantEstoque -= quantidade;
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}

	public Set<Imagem> getImagens() {
		return imagens;
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

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", quantEstoque=" + quantEstoque + "]";
	}
}