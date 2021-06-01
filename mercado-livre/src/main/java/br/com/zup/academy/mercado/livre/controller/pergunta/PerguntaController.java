package br.com.zup.academy.mercado.livre.controller.pergunta;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.pergunta.form.PerguntaForm;
import br.com.zup.academy.mercado.livre.dominio.exception.produto.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.util.builder.CriadorDeNotificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificador;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.NotificarUsuario;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	private ProdutoRepository produtoRepository;
	private List<Notificador> notificadores;

	public PerguntaController(ProdutoRepository produtoRepository, List<Notificador> notificadores) {
		this.produtoRepository = produtoRepository;
		this.notificadores = notificadores;
	}

	@PostMapping("/{idProduto}")
	@Transactional
	public ResponseEntity<Object> cadastrarPergunta(@PathVariable("idProduto") Long id,
			@RequestBody @Valid PerguntaForm perguntaForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		Pergunta pergunta = perguntaForm.toPergunta(produto, usuario);
		this.produtoRepository.save(produto);
		Notificacao notificacao = new CriadorDeNotificacao().paraDestinatario(produto.getUsuario())
				.assunto("Você recebeu uma nova pergunta sobre o produto " + produto.getNome())
				.corpo(pergunta.getTitulo()).build();
		NotificarUsuario.notificar(notificadores, notificacao);
		return ResponseEntity.ok().build();
	}
}