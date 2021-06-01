package br.com.zup.academy.mercado.livre.controller.produto.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.academy.mercado.livre.controller.comentario.dto.ComentarioDto;
import br.com.zup.academy.mercado.livre.controller.login.dto.UsuarioDto;
import br.com.zup.academy.mercado.livre.controller.pergunta.dto.PerguntaDto;
import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;

public class ProdutoDetalhadoDto {

	private String nome;
	private BigDecimal valor;
	private String descricao;
	private BigDecimal mediaNotas;
	private Integer quantNotas;
	private UsuarioDto usuario;
	private List<CaracteristicaDto> caracteristicas = new ArrayList<>();
	private List<ImagemDto> imagens = new ArrayList<>();
	private List<ComentarioDto> comentarios = new ArrayList<>();
	private List<PerguntaDto> perguntas = new ArrayList<>();

	public ProdutoDetalhadoDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.mediaNotas = produto.getMediaNotas();
		this.quantNotas = produto.getQuantNotas();
		this.usuario = new UsuarioDto(produto.getUsuario());
		this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaDto::new).collect(Collectors.toList());
		this.comentarios = produto.getComentarios().stream().map(ComentarioDto::new).collect(Collectors.toList());
		this.imagens = produto.getImagens().stream().map(ImagemDto::new).collect(Collectors.toList());
		this.perguntas = produto.getPerguntas().stream().map(PerguntaDto::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getMediaNotas() {
		return mediaNotas;
	}

	public Integer getQuantNotas() {
		return quantNotas;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public List<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public List<ImagemDto> getImagens() {
		return imagens;
	}

	public List<ComentarioDto> getComentarios() {
		return comentarios;
	}

	public List<PerguntaDto> getPerguntas() {
		return perguntas;
	}
}
