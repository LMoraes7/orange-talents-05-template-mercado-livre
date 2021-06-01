package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.sistema;

import org.springframework.http.ResponseEntity;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;

public interface SistemaExternoNotificador {

	ResponseEntity<String> processa(Compra compra);
}
