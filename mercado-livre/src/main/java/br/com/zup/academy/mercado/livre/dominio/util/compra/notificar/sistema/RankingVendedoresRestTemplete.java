package br.com.zup.academy.mercado.livre.dominio.util.compra.notificar.sistema;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;

@Component
public class RankingVendedoresRestTemplete implements SistemaExternoNotificador {

	@Override
	public ResponseEntity<String> processa(Compra compra) {
		System.out.println("RankingVendedoresRestTemplete");
		Map<String, Long> request = Map.of("idCompra", compra.getId(), "idVendedor",
				compra.getProduto().getUsuario().getId());
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity("http://localhost:8080/ranking-vendedores/notificar", request, String.class);
	}
}
