package br.com.zup.academy.mercado.livre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.form.PerguntaForm;
import br.com.zup.academy.mercado.livre.dominio.exception.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.util.NotificadorEmail;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	private ProdutoRepository produtoRepository;
	private NotificadorEmail notificador;

	public PerguntaController(ProdutoRepository produtoRepository, NotificadorEmail notificador) {
		this.produtoRepository = produtoRepository;
		this.notificador = notificador;
	}

	@PostMapping("/{idProduto}")
	@Transactional
	public ResponseEntity<Object> cadastrarPergunta(@PathVariable("idProduto") Long id,
			@RequestBody @Valid PerguntaForm perguntaForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		Pergunta pergunta = perguntaForm.toPergunta(produto, usuario);
		this.produtoRepository.save(produto);
		this.notificador.notificar(pergunta);
		return ResponseEntity.ok().build();
	}

}