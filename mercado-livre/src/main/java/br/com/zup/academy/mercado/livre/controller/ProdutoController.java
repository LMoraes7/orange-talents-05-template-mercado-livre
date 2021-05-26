package br.com.zup.academy.mercado.livre.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.academy.mercado.livre.controller.form.ImagemForm;
import br.com.zup.academy.mercado.livre.controller.form.ProdutoForm;
import br.com.zup.academy.mercado.livre.dominio.modelo.Categoria;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.CategoriaRepository;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.service.UploaderFake;
import br.com.zup.academy.mercado.livre.infraestrutura.validacao.ProibeProdutoComCaracteristicasIguaisValidator;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;
	private UploaderFake uploaderFake;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			UploaderFake uploaderFake) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.uploaderFake = uploaderFake;
	}

	@InitBinder(value = "produtoForm")
	public void init(WebDataBinder binder) {
		binder.addValidators(new ProibeProdutoComCaracteristicasIguaisValidator());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			@AuthenticationPrincipal Usuario usuario) {
		Categoria categoria = categoriaRepository.findById(produtoForm.getCategoriaId()).get();
		Produto produto = produtoForm.toProduto(categoria, usuario);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{idProduto}/imagens")
	@Transactional
	public ResponseEntity<Object> adicionaImagens(@PathVariable("idProduto") Long id, @Valid ImagemForm imagemForm,
			@AuthenticationPrincipal Usuario usuario) {
		Set<String> links = uploaderFake.envia(imagemForm.getImagens());
//		O certo aqui seria fazer uma verificação para ver se o ID que estava sendo passado era válido ou não(caso não fosse válido, retornaria 404),
//			porém eu ainda não implementei uma boa camada de Exceptions e nem de RestControllerAdvice. 
//		Até o final desse desafio, vou ajeitar esses detalhes. 
//		Estou focado em aprender os processos das novas funcionalidades que estão sendo ensinadas nos desafios.
		Produto produto = this.produtoRepository.findById(id).get();
		if(!produto.pertenceAoUsuario(usuario)) 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem autorização para editar esse produto");
		produto.associaImagens(links);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}