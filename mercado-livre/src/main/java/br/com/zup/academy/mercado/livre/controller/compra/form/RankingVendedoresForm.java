package br.com.zup.academy.mercado.livre.controller.compra.form;

import javax.validation.constraints.NotNull;

public class RankingVendedoresForm {

	@NotNull
	public Long idCompra;
	@NotNull
	public Long idVendedor;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	@Override
	public String toString() {
		return "Ranking Vendedores [idCompra=" + idCompra + ", idVendedor=" + idVendedor + "]";
	}
}
