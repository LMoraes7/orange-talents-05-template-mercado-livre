package br.com.zup.academy.mercado.livre.dominio.modelo.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(String senha) {
		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(this.senha);
	}
}