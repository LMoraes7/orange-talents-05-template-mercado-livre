package br.com.zup.academy.mercado.livre.dominio.modelo.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pagamento;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.StatusTransacao;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.Transacao;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Column(nullable = false)
	private String codigo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Pagamento pagamento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;

	@Column(nullable = false)
	private boolean aprovada;

	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Transacao> transacoes = new HashSet<Transacao>();

	@Deprecated
	public Compra() {
	}

	public Compra(Integer quantidade, Produto produto, Pagamento pagamento, Usuario comprador) {
		if (produto.abaterEstoque(quantidade))
			throw new IllegalArgumentException(
					"Quantidade de estoque é insuficiente para a quantidade de compra desejada");
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
		this.pagamento = pagamento;
		this.usuario = comprador;
		this.aprovada = false;
		this.status = Status.AGUARDANDO_PAGAMENTO;
	}

	@PrePersist
	private void gerarCodigo() {
		this.codigo = UUID.randomUUID().toString();
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public String getUrl() {
		return this.pagamento.getUrl(this.id.toString());
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public Usuario getComprador() {
		return usuario;
	}

	public boolean isAprovada() {
		return aprovada;
	}

	public void addTransacao(Transacao transacao) {
		if (this.isAprovada())
			throw new IllegalArgumentException("Compra informada já foi aprovada");
		if (this.jaExisteTransacao(transacao))
			throw new IllegalArgumentException("Já existe essa transação cadastrada para a compra informada");
		if (transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
			this.aprovada = true;
			this.status = Status.APROVADA;
		}
		this.transacoes.add(transacao);
	}

	private boolean jaExisteTransacao(Transacao possivelTransacao) {
		for (Transacao transacao : this.transacoes) {
			if (transacao.equals(possivelTransacao))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", valorTotal=" + valorTotal
				+ ", pagamento=" + pagamento + ", status=" + status + ", comprador=" + usuario + "]";
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
		Compra other = (Compra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}