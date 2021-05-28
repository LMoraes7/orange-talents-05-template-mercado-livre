package br.com.zup.academy.mercado.livre.dominio.modelo;

public enum Pagamento {
	
	PAYPAL {
		@Override
		public Long getId() {
			return id++;
		}

		@Override
		public String getUrl() {
			return "https://paypal/pagamentos/";
		}
	},
	PAGSEGURO {
		@Override
		public Long getId() {
			return id++;
		}

		@Override
		public String getUrl() {
			return "https://pagseguro/pagamentos/";
		}
	};
	
	private static Long id = 1L;
	
	public abstract Long getId();
	
	public abstract String getUrl();
}
