package br.com.zup.academy.mercado.livre.handler;

import java.time.LocalDateTime;

public class ErroDto {

	private LocalDateTime data;
	private String message;

	public ErroDto(LocalDateTime data, String message) {
		this.data = data;
		this.message = message;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
}