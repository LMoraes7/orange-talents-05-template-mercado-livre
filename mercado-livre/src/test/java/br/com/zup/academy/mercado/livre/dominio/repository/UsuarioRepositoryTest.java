package br.com.zup.academy.mercado.livre.dominio.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@Test
	void deveriaBuscarUmUsuarioPeloEmail() {
		Optional<Usuario> usuarioOptional = this.repository.findByEmail("diego@email.com");
		assertTrue(usuarioOptional.isPresent());
	}
	
	@Test
	void naoDeveriaBuscarUsuarioPeloEmail() {
		Optional<Usuario> usuarioOptional = this.repository.findByEmail("carlos@email.com");
		assertFalse(usuarioOptional.isPresent());
	}
}
