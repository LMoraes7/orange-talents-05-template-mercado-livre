package br.com.zup.academy.mercado.livre.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.academy.mercado.livre.dominio.exception.EntidadeNaoEncontradaException;
import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;

@RestControllerAdvice
public class ControllerHandler {

	private MessageSource messageSource;

	public ControllerHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrosDeFormularioDto> erroValidacaoHandler(MethodArgumentNotValidException e) {
		List<ErrosDeFormularioDto> errosDto = new ArrayList<ErrosDeFormularioDto>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(erro -> {
			String message = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			errosDto.add(new ErrosDeFormularioDto(erro.getField(), message));
		});
		return errosDto;
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ErroDto> ErroNaoEncontradoHandler(EntidadeNaoEncontradaException e) {
		ErroDto erroDto = new ErroDto(LocalDateTime.now(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroDto);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErroDto> ErroNegocioHandler(NegocioException e) {
		ErroDto erroDto = new ErroDto(LocalDateTime.now(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
	}
}