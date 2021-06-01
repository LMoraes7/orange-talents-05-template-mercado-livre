package br.com.zup.academy.mercado.livre.controller.pergunta.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

public class PerguntaForm {

	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}
	
	public Pergunta toPergunta(Produto produto, Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}
}
