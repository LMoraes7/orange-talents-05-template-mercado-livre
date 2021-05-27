package br.com.zup.academy.mercado.livre.config.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@EnableWebSecurity
@Configuration
public class SegurancaConfigDev extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().permitAll()
		.and().csrf().disable();
	}
}