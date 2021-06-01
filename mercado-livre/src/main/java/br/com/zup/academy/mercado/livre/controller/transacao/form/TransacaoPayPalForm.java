package br.com.zup.academy.mercado.livre.controller.transacao.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.StatusTransacao;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.Transacao;

public class TransacaoPayPalForm implements TransacaoForm{

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idTransacao;

	@NotNull
	@Min(0)
	@Max(1)
	private int status;
	
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

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public Transacao toTransacao(Compra compra) {
		if(status == 0)
			return new Transacao(StatusTransacao.FALHA, idTransacao, compra);
		return new Transacao(StatusTransacao.SUCESSO, idTransacao, compra);
	}
}
