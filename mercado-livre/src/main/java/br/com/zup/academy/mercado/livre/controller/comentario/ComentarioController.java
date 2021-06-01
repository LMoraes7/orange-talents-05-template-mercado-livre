package br.com.zup.academy.mercado.livre.controller.comentario;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.comentario.form.ComentarioForm;
import br.com.zup.academy.mercado.livre.dominio.exception.produto.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	private ProdutoRepository produtoRepository;

	public ComentarioController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@PostMapping("/{idProduto}")
	@Transactional
	public ResponseEntity<Object> cadastrarComentario(@PathVariable("idProduto") Long id,
			@RequestBody @Valid ComentarioForm comentarioForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		comentarioForm.toComentario(produto, usuario);
		this.produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}