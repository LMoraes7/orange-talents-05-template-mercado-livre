package br.com.zup.academy.mercado.livre.config.seguranca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

	@Value(value = "${mercado.livre.jwt.secret}")
	private String chave;
	@Value(value = "${mercado.livre.jwt.expiration}")
	private String expiration;
	
	public String gerarToken(Authentication authenticate) {
		Usuario usuario = (Usuario) authenticate.getPrincipal();
		Date agora = new Date();
		Date expiracao = new Date(Long.parseLong(this.expiration) + agora.getTime());
		return Jwts.builder()
				.setIssuer("API Mercado Livre")
				.setIssuedAt(agora)
				.setSubject(usuario.getId().toString())
				.setExpiration(expiracao)
				.signWith(SignatureAlgorithm.HS256, this.chave)
				.compact();
	}

	public boolean isTokenEhValido(String token) {
		try {
			this.parserToken(token);
			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			return false;
		}
	}
	
	public Long getUsuarioId(String token) {
		Jws<Claims> jws = this.parserToken(token);
		String usuarioId = jws.getBody().getSubject();
		return Long.valueOf(usuarioId);
	}

	private Jws<Claims> parserToken(String token) {
		return Jwts.parser().setSigningKey(this.chave).parseClaimsJws(token);
	}	
}