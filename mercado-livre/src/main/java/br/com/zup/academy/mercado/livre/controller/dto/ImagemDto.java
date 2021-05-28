package br.com.zup.academy.mercado.livre.controller.dto;

import br.com.zup.academy.mercado.livre.dominio.modelo.Imagem;

public class ImagemDto {

	private String link;
	
	public ImagemDto(Imagem imagem) {
		this.link = imagem.getLink();
	}
	
	public String getLink() {
		return link;
	}
}
