package br.com.zup.academy.mercado.livre.controller;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.config.seguranca.service.TokenService;
import br.com.zup.academy.mercado.livre.controller.dto.TokenDto;
import br.com.zup.academy.mercado.livre.controller.form.LoginForm;
import br.com.zup.academy.mercado.livre.controller.form.NovoUsuarioForm;
import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.UsuarioRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

	private UsuarioRepository usuarioRepository;
	private AuthenticationManager auth;
	private TokenService tokenService;

	public LoginController(UsuarioRepository usuarioRepository, AuthenticationManager auth, 
			TokenService tokenService) {
		this.usuarioRepository = usuarioRepository;
		this.auth = auth;
		this.tokenService = tokenService;
	}
	
	@PostMapping
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm loginForm) {
		Authentication dadosLogin = loginForm.toDadosLogin();
		try {
			Authentication authenticate = this.auth.authenticate(dadosLogin);
			String token = this.tokenService.gerarToken(authenticate);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			throw new NegocioException("Dados de login são inválidos");
		}
	}

	@PostMapping("/novo")
	@Transactional
	public ResponseEntity<Object> cadastrarNovoUsuario(@RequestBody @Valid NovoUsuarioForm usuarioForm) {
		Usuario usuario = usuarioForm.toUsuario();
		this.usuarioRepository.save(usuario);
		return ResponseEntity.ok().build();
	}
}