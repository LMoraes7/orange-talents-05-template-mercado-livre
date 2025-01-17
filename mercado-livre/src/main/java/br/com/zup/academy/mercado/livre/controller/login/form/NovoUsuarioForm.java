package br.com.zup.academy.mercado.livre.controller.login.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.SenhaLimpa;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.anotacao.ValorUnico;

public class NovoUsuarioForm {

	@NotNull
	@Email
	@ValorUnico(entidade = Usuario.class, campo = "email")
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;

	@JsonCreator
	public NovoUsuarioForm(@NotNull @Email String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toUsuario() {
		return new Usuario(this.email, new SenhaLimpa(this.senha));
	}
}