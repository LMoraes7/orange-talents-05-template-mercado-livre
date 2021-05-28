package br.com.zup.academy.mercado.livre.controller.dto;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

public class UsuarioDto {
	
	private String email;

	public UsuarioDto(Usuario usuario) {
		this.email = usuario.getUsername();
	}
	
	public String getEmail() {
		return email;
	}
}
