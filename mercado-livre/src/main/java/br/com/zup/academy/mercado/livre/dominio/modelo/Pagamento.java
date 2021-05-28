package br.com.zup.academy.mercado.livre.dominio.modelo;

public enum Pagamento {
	
	PAYPAL {
		@Override
		public String getUrl(String url) {
			return "paypal.com?buyerId="+url+"&redirectUrl=https://urlqualquer.com";
		}
	},
	PAGSEGURO { 
		@Override
		public String getUrl(String url) {
			return "pagseguro.com?buyerId="+url+"&redirectUrl=https://urlqualquer.com";
		}
	};
	
	public abstract String getUrl(String url);
}
