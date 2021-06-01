package br.com.zup.academy.mercado.livre.controller.compra;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mercado.livre.controller.compra.form.NotaFiscalForm;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

	@PostMapping("/gerar")
	public ResponseEntity<String> gerarNF(@RequestBody @Valid NotaFiscalForm compraForm) {
		return ResponseEntity.ok(compraForm.toString());
	}
}
