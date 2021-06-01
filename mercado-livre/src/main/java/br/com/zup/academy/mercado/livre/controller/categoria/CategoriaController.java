package br.com.zup.academy.mercado.livre.controller.categoria;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.categoria.form.CategoriaForm;
import br.com.zup.academy.mercado.livre.dominio.exception.categoria.CategoriaInvalidaException;
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
		Long categoriaMaeId = categoriaForm.getCategoriaMaeId();
		Categoria categoria = null;
		if (categoriaMaeId == null) {
			categoria = categoriaForm.toCategoria(null);
		} else {
			Categoria categoriaMae = this.categoriaRepository.findById(categoriaMaeId)
					.orElseThrow(() -> new CategoriaInvalidaException(categoriaMaeId));
			categoria = categoriaForm.toCategoria(categoriaMae);
		}
		this.categoriaRepository.save(categoria);
		return ResponseEntity.ok().build();
	}
}