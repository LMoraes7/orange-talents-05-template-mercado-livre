package br.com.zup.academy.mercado.livre.dominio.modelo;

public enum Pagamento {
	
	PAYPAL {
		@Override
		public String getUrl(String id) {
			return "paypal.com?buyerId="+id+"&redirectUrl=https://localhost:8080/transacoes/retorno-paypal/realiza-transacao/";
		}
	},
	PAGSEGURO { 
		@Override
		public String getUrl(String id) {
			return "pagseguro.com?buyerId="+id+"&redirectUrl=https://localhost:8080/transacoes/retorno-pagseguro/realiza-transacao/";
		}
	};
	
	public abstract String getUrl(String url);
}
