package br.com.zup.academy.mercado.livre.dominio.modelo;

import br.com.zup.academy.mercado.livre.dominio.util.GeradorDeStringGateway;

public enum Pagamento {
	
	PAYPAL {
		@Override
		public Long getId() {
			return id++;
		}

		@Override
		public String getUrl() {
			return GeradorDeStringGateway.gerarUrl("https://paypal/pagamentos/");
		}
	},
	PAGSEGURO {
		@Override
		public Long getId() {
			return id++;
		}

		@Override
		public String getUrl() {
			return GeradorDeStringGateway.gerarUrl("https://pagseguro/pagamentos/");
		}
	};
	
	private static Long id = 1L;
	
	public abstract Long getId();
	
	public abstract String getUrl();
}
