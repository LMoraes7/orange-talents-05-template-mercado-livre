package br.com.zup.academy.mercado.livre.config.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zup.academy.mercado.livre.config.seguranca.filter.AutenticacaoViaTokenFilter;
import br.com.zup.academy.mercado.livre.config.seguranca.service.AutenticacaoService;
import br.com.zup.academy.mercado.livre.config.seguranca.service.TokenService;
import br.com.zup.academy.mercado.livre.dominio.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

	private AutenticacaoService autenticacaoService;
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public SegurancaConfig(AutenticacaoService autenticacaoService, TokenService tokenService,
			UsuarioRepository usuarioRepository) {
		this.autenticacaoService = autenticacaoService;
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login/**").permitAll()
				.antMatchers(HttpMethod.POST, "/notas-fiscais/**").permitAll()
				.antMatchers(HttpMethod.POST, "/ranking-vendedores/**").permitAll().anyRequest().authenticated().and()
				.cors().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(this.tokenService, this.usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}