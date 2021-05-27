package br.com.zup.academy.mercado.livre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.form.CategoriaForm;
import br.com.zup.academy.mercado.livre.dominio.exception.CategoriaNaoEncontradaException;
import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid CategoriaForm categoriaForm) {
		try {
			Categoria categoria = categoriaForm.toCategoria(categoriaRepository);
			this.categoriaRepository.save(categoria);
			return ResponseEntity.ok().build();
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}