package br.com.zup.academy.mercado.livre.controller;

import java.time.LocalDateTime;

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

import br.com.zup.academy.mercado.livre.controller.form.CompraForm;
import br.com.zup.academy.mercado.livre.dominio.exception.CompraInvalidaException;
import br.com.zup.academy.mercado.livre.dominio.exception.ProdutoNaoEncontradoException;
import br.com.zup.academy.mercado.livre.dominio.modelo.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;
import br.com.zup.academy.mercado.livre.dominio.repository.CompraRepository;
import br.com.zup.academy.mercado.livre.dominio.repository.ProdutoRepository;
import br.com.zup.academy.mercado.livre.dominio.util.CriadorDeNotificacao;
import br.com.zup.academy.mercado.livre.dominio.util.Notificacao;
import br.com.zup.academy.mercado.livre.dominio.util.NotificarUsuario;

@RestController
@RequestMapping("/compras")
public class CompraController {

	private ProdutoRepository produtoRepository;
	private CompraRepository compraRepository;
	private NotificarUsuario notificador;

	public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository,
			NotificarUsuario notificador) {
		this.produtoRepository = produtoRepository;
		this.compraRepository = compraRepository;
		this.notificador = notificador;
	}

	@PostMapping("/{idProduto}")
	@Transactional
	public ResponseEntity<String> comprar(@PathVariable("idProduto") Long idProduto,
			@RequestBody @Valid CompraForm compraForm, @AuthenticationPrincipal Usuario usuario) {
		Produto produto = this.produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
		Compra compra = null;
		try {
			compra = compraForm.toCompra(produto, usuario);
		} catch (IllegalArgumentException e) {
			throw new CompraInvalidaException(e.getMessage());
		}
		this.compraRepository.save(compra);
		Notificacao notificacao = new CriadorDeNotificacao()
				.deUsuarioInteressado(usuario)
				.paraDestinatario(produto.getUsuario())
				.assunto("Seu produto foi vendido!")
				.corpo("Seu produto foi comprado no dia: " + LocalDateTime.now() + ", pelo usuário: "
						+ usuario.getUsername())
				.build();
		this.notificador.notificar(notificacao);
		return ResponseEntity.status(HttpStatus.FOUND).body(compra.getUrl());
				
	}
}