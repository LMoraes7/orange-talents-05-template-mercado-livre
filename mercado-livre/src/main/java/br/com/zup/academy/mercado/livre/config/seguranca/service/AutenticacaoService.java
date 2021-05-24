package br.com.zup.academy.mercado.livre.config.seguranca.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.academy.mercado.livre.dominio.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Dados Inválidos"));
		
	}

}
