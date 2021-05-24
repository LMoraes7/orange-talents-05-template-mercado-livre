package br.com.zup.academy.mercado.livre.controller;


import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.form.NovoUsuarioForm;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.UsuarioRepository;

@Profile("dev")
@RestController
@RequestMapping("/login")
public class LoginControllerDev {

	private UsuarioRepository usuarioRepository;

	public LoginControllerDev(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping("/novo")
	@Transactional
	public ResponseEntity<Object> cadastrarNovoUsuario(@RequestBody @Valid NovoUsuarioForm usuarioForm) {
		Usuario usuario = usuarioForm.toUsuario();
		this.usuarioRepository.save(usuario);
		return ResponseEntity.ok().build();
	}
}