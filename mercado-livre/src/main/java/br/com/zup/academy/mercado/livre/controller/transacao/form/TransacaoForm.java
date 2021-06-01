package br.com.zup.academy.mercado.livre.controller.transacao.form;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;
import br.com.zup.academy.mercado.livre.dominio.modelo.transacao.Transacao;

public interface TransacaoForm {

	Transacao toTransacao(Compra compra);
}
