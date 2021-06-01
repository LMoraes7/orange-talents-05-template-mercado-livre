package br.com.zup.academy.mercado.livre.controller.comentario.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mercado.livre.dominio.modelo.Comentario;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;
import br.com.zup.academy.mercado.livre.dominio.modelo.usuario.Usuario;

public class ComentarioForm {

	@NotNull
	@Min(1)
	@Max(5)
	private Byte nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Length(max = 500)
	private String descricao;

	@JsonCreator
	public ComentarioForm(@NotNull @Min(1) @Max(5) Byte nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Comentario toComentario(Produto produto, Usuario usuario) {
		return new Comentario(nota, titulo, descricao, produto, usuario);
	}
}