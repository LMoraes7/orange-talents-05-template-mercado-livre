package br.com.zup.academy.mercado.livre.controller.compra;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.compra.form.RankingVendedoresForm;

@RestController
@RequestMapping("/ranking-vendedores")
public class RankingVendedoresController {

	@PostMapping("/notificar")
	public ResponseEntity<String> notificar(@RequestBody @Valid RankingVendedoresForm form ) {
		return ResponseEntity.ok(form.toString());
	}
}
