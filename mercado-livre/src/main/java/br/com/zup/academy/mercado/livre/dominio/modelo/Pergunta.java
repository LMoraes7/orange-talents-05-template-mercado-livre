package br.com.zup.academy.mercado.livre.dominio.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Deprecated
	public Pergunta() {
	}

	public Pergunta(String titulo, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
		produto.addPergunta(this);
	}
	
	public String getDonoDaPerguntaEmail() {
		return usuario.getUsername();
	}
	
	public String getDonoDoProdutoEmail() {
		return produto.getUsuario().getUsername();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}