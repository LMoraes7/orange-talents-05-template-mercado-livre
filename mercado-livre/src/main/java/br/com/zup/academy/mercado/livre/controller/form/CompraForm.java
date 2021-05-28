package br.com.zup.academy.mercado.livre.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.academy.mercado.livre.dominio.modelo.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.Pagamento;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class CompraForm {

	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	private Pagamento pagamento;

	public Integer getQuantCompra() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Compra toCompra(Produto produto, Usuario usuario) {
		return new Compra(this.quantidade, produto, pagamento, usuario);
	}
}
