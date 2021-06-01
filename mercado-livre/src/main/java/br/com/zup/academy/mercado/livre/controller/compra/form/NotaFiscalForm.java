package br.com.zup.academy.mercado.livre.controller.compra.form;

import javax.validation.constraints.NotNull;

public class NotaFiscalForm {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idUsuario;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Nota Fiscal [idCompra=" + idCompra + ", idUsuario=" + idUsuario + "]";
	}
}
