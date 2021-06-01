package br.com.zup.academy.mercado.livre.dominio.modelo.transacao;

public enum StatusCompraPagSeguro {

	SUCESSO, 
	ERRO;

	public StatusTransacao getStatusPadrao() {
		if(this.equals(StatusCompraPagSeguro.SUCESSO))
			return StatusTransacao.SUCESSO;
		return StatusTransacao.FALHA;
	}
}
