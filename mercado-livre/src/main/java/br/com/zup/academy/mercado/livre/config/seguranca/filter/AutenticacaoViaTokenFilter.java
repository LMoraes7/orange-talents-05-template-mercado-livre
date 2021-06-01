package br.com.zup.academy.mercado.livre.config.seguranca.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.academy.mercado.livre.config.seguranca.service.TokenService;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = this.recuperarToken(request);
		boolean tokenEhValido = this.tokenService.isTokenEhValido(token);
		if(tokenEhValido)
			this.autenticarCliente(token);
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = this.tokenService.getUsuarioId(token);
		Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	private String recuperarToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header == null || header.isEmpty() || !header.startsWith("Bearer "))
			return null;
		return header.substring(7, header.length());
	}



}
