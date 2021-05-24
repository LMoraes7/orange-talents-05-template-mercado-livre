package br.com.zup.academy.mercado.livre.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginForm {

	@NotNull
	@Email
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;

	@JsonCreator
	public LoginForm(@NotNull @Email String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken toDadosLogin() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
}