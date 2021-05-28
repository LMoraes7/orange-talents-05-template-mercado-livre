package br.com.zup.academy.mercado.livre.dominio.modelo;

import java.math.BigDecimal;
import java.util.UUID;

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
import javax.persistence.PrePersist;

import org.springframework.util.Assert;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false)
	private BigDecimal valorTotal;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Pagamento pagamento;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Usuario comprador;
	@Column(nullable = false)
	private String codigo;

	public Compra(Integer quantidade, Produto produto, Pagamento pagamento, Usuario comprador) {
		Assert.isTrue(produto.abaterEstoque(quantidade),
				"Quantidade de estoque é insuficiente para a quantidade de compra desejada");
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
		this.pagamento = pagamento;
		this.comprador = comprador;
		this.status = Status.INICIADA;
	}
	
	@PrePersist
	private void gerarCogido() {
		this.codigo = UUID.randomUUID().toString();
	}
	
	public String getUrl() {
		return this.pagamento.getUrl(this.codigo);
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void attStatusDaCompra(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", valorTotal=" + valorTotal
				+ ", pagamento=" + pagamento + ", status=" + status + ", comprador=" + comprador + "]";
	}
}
