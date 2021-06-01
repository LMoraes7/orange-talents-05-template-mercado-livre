package br.com.zup.academy.mercado.livre.controller.transacao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.transacao.form.TransacaoPagSeguroForm;
import br.com.zup.academy.mercado.livre.controller.transacao.form.TransacaoPayPalForm;
import br.com.zup.academy.mercado.livre.dominio.exception.transacao.TransacaoInvalidaException;
import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.StatusTransacao;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.Transacao;
import br.com.zup.academy.mercado.livre.dominio.repository.CompraRepository;
import br.com.zup.academy.mercado.livre.dominio.util.builder.CriadorDeNotificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.sistema.SistemaExternoNotificador;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificacao;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.Notificador;
import br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.usuario.NotificarUsuario;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private List<SistemaExternoNotificador> notificadoresSistemasExternos;
	private List<Notificador> notificadores;
	private CompraRepository compraRepository;

	public TransacaoController(List<SistemaExternoNotificador> notificadoresSistemasExternos,
			List<Notificador> notificadores, CompraRepository compraRepository) {
		this.notificadoresSistemasExternos = notificadoresSistemasExternos;
		this.notificadores = notificadores;
		this.compraRepository = compraRepository;
	}

	@PostMapping("/retorno-paypal/realiza-transacao/")
	@Transactional
	public ResponseEntity<StatusTransacao> retornoPaypal(@RequestBody @Valid TransacaoPayPalForm transacaoForm) {
		Compra compra = this.compraRepository.findById(transacaoForm.getIdCompra()).get();
		Transacao transacao = transacaoForm.toTransacao(compra);
		return processarTransacao(compra, transacao);
	}

	@PostMapping("/retorno-pagseguro/realiza-transacao/")
	@Transactional
	public ResponseEntity<StatusTransacao> retornoPagSeguro(@RequestBody @Valid TransacaoPagSeguroForm transacaoForm) {
		Compra compra = this.compraRepository.findById(transacaoForm.getIdCompra()).get();
		Transacao transacao = transacaoForm.toTransacao(compra);
		return processarTransacao(compra, transacao);
	}

	private ResponseEntity<StatusTransacao> processarTransacao(Compra compra, Transacao transacao) {
		adicionarTransacaoNaCompra(compra, transacao);
		Notificacao notificacao = null;
		if (compra.isAprovada()) {
			notificarSistemasExternos(compra);
			notificacao = new CriadorDeNotificacao().paraDestinatario(compra.getComprador()).assunto("Compra APROVADA")
					.corpo("Sua compra foi aprovada. Ela deve chegar dentro de alguns dias.").build();
		} else {
			notificacao = new CriadorDeNotificacao().paraDestinatario(compra.getComprador()).assunto("Compra REPROVADA")
					.corpo("Sua compra foi reprovada. " + "Tente realizar novamente a compra no link: "
							+ "http://localhost:8080/compras/" + compra.getProduto().getId())
					.build();
		}
		NotificarUsuario.notificar(notificadores, notificacao);
		return ResponseEntity.ok(transacao.getStatus());
	}

	private void notificarSistemasExternos(Compra compra) {
		this.notificadoresSistemasExternos.forEach(sistema -> {
			ResponseEntity<String> resposta = sistema.processa(compra);
			System.out.println(resposta);
		});
	}

	private void adicionarTransacaoNaCompra(Compra compra, Transacao transacao) {
		try {
			compra.addTransacao(transacao);
		} catch (IllegalArgumentException e) {
			throw new TransacaoInvalidaException(e.getMessage());
		}
	}
}