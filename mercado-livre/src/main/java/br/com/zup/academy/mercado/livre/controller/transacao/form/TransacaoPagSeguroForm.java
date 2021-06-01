package br.com.zup.academy.mercado.livre.controller.transacao.form;

import javax.validation.constraints.NotNull;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.StatusCompraPagSeguro;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.Transacao;

public class TransacaoPagSeguroForm implements TransacaoForm{

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idTransacao;
	@NotNull
	private StatusCompraPagSeguro status;
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}

	public StatusCompraPagSeguro getStatus() {
		return status;
	}

	public void setStatus(StatusCompraPagSeguro status) {
		this.status = status;
	}
	
	@Override
	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status.getStatusPadrao(), idTransacao, compra);
	}
}