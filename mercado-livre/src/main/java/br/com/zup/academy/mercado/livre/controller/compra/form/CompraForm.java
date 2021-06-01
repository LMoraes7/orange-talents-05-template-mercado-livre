package br.com.zup.academy.mercado.livre.controller.compra.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pagamento;
import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

public class CompraForm {

	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank
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
