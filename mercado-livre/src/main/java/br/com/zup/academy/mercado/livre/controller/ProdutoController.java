package br.com.zup.academy.mercado.livre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.form.ProdutoForm;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.CategoriaRepository;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.ProibeProdutoComCaracteristicasIguais;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new ProibeProdutoComCaracteristicasIguais());
	}

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			@AuthenticationPrincipal Usuario usuario) {
		Categoria categoria = categoriaRepository.findById(produtoForm.getCategoriaId()).get();
		Produto produto = produtoForm.toProduto(categoria, usuario);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}
