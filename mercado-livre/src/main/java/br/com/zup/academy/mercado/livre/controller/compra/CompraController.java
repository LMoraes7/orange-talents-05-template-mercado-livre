package br.com.zup.academy.mercado.livre.controller.compra;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.compra.form.CompraForm;
import br.com.zup.academy.mercado.livre.dominio.exception.compra.CompraInvalidaException;
import br.com.zup.academy.mercado.livre.dominio.exception.produto.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.CompraRepository;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.util.builder.CriadorDeNotificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificador;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.NotificarUsuario;

@RestController
@RequestMapping("/compras")
public class CompraController {

	private ProdutoRepository produtoRepository;
	private CompraRepository compraRepository;
	private List<Notificador> notificadores;

	public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository,
			List<Notificador> notificadores) {
		this.produtoRepository = produtoRepository;
		this.compraRepository = compraRepository;
		this.notificadores = notificadores;
	}

	@PostMapping("/{idProduto}")
	@Transactional
	public ResponseEntity<String> comprar(@PathVariable("idProduto") Long idProduto,
			@RequestBody @Valid CompraForm compraForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
		Compra compra = processarCompra(compraForm, usuario, produto);
		Notificacao notificacao = new CriadorDeNotificacao().paraDestinatario(produto.getUsuario())
				.assunto("Seu produto foi vendido!").corpo("Seu produto foi comprado no dia: " + LocalDateTime.now()
						+ ", pelo usuário: " + usuario.getUsername())
				.build();
		NotificarUsuario.notificar(notificadores, notificacao);
		return ResponseEntity.status(HttpStatus.FOUND).body(compra.getUrl());
	}

	private Compra processarCompra(CompraForm compraForm, Usuario usuario, Produto produto) {
		Compra compra = null;
		try {
			compra = compraForm.toCompra(produto, usuario);
		} catch (IllegalArgumentException e) {
			throw new CompraInvalidaException(e.getMessage());
		}
		this.compraRepository.save(compra);
		return compra;
	}
}