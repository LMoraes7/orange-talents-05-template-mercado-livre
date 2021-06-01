package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.sistema;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;

@Component
public class NotaFiscalRestTemplete implements SistemaExternoNotificador {

	@Override
	public ResponseEntity<String> processa(Compra compra) {
		System.out.println("NotaFiscalRestTemplete");
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Long> request = Map.of("idCompra", compra.getId(), "idUsuario", compra.getComprador().getId());
		return restTemplate.postForEntity("http://localhost:8080/notas-fiscais/gerar", request, String.class);
	}

}
