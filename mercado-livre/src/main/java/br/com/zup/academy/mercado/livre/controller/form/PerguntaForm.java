package br.com.zup.academy.mercado.livre.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.academy.mercado.livre.dominio.modelo.Pergunta;
import br.com.zup.academy.mercado.livre.dominio.modelo.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.Usuario;

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
