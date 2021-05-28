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
import br.com.zup.academy.mercado.livre.dominio.util.CriadorDeNotificacao;
import br.com.zup.academy.mercado.livre.dominio.util.Notificacao;
import br.com.zup.academy.mercado.livre.dominio.util.NotificarUsuario;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	private ProdutoRepository produtoRepository;
	private NotificarUsuario notificador;

	public PerguntaController(ProdutoRepository produtoRepository, NotificarUsuario notificador) {
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
		
		String assunto = "Você recebeu uma nova pergunta sobre o produto "+produto.getNome();
		
		Notificacao notificacao = new CriadorDeNotificacao()
			.deUsuarioInteressado(pergunta.getUsuario())
			.paraDestinatario(produto.getUsuario())
			.assunto(assunto)
			.corpo(pergunta.getTitulo())
			.build();
		
		this.notificador.notificar(notificacao);
		return ResponseEntity.ok().build();
	}
}