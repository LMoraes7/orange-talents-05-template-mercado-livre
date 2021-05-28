package br.com.zup.academy.mercado.livre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.dto.ProdutoDetalhadoDto;
import br.com.zup.academy.mercado.livre.controller.form.ImagemForm;
import br.com.zup.academy.mercado.livre.controller.form.ProdutoForm;
import br.com.zup.academy.mercado.livre.dominio.exception.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.exception.UsuarioNaoTemPermissaoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.util.UploaderFake;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.ProibeProdutoComCaracteristicasIguaisValidator;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private EntityManager manager;
	private ProdutoRepository produtoRepository;
	private UploaderFake uploaderFake;

	public ProdutoController(EntityManager manager, ProdutoRepository produtoRepository, UploaderFake uploaderFake) {
		this.manager = manager;
		this.produtoRepository = produtoRepository;
		this.uploaderFake = uploaderFake;
	}

	@InitBinder(value = "produtoForm")
	public void init(WebDataBinder binder) {
		binder.addValidators(new ProibeProdutoComCaracteristicasIguaisValidator());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalhadoDto> listarPorId(@PathVariable("id") Long id) {
		Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		ProdutoDetalhadoDto produtoDetalhadoDto = new ProdutoDetalhadoDto(produto);
		return ResponseEntity.ok(produtoDetalhadoDto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			@AuthenticationPrincipal Usuario usuario) {
		Categoria categoria = this.manager.find(Categoria.class, produtoForm.getCategoriaId());
		Produto produto = produtoForm.toProduto(categoria, usuario);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{idProduto}/imagens")
	@Transactional
	public ResponseEntity<Object> adicionarImagens(@PathVariable("idProduto") Long id, @Valid ImagemForm imagemForm,
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		Set<String> links = uploaderFake.envia(imagemForm.getImagens());
		if (!produto.pertenceAoUsuario(usuario))
			throw new UsuarioNaoTemPermissaoException();
		produto.associaImagens(links);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}