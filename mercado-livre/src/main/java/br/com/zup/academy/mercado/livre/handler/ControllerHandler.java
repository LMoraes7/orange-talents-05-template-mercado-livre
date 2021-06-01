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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.academy.mercado.livre.dominio.exception.EntidadeNaoEncontradaException;
import br.com.zup.academy.mercado.livre.dominio.exception.NegocioException;
import br.com.zup.academy.mercado.livre.dominio.exception.usuario.UsuarioNaoTemPermissaoException;
import br.com.zup.academy.mercado.livre.handler.dto.ErroDto;
import br.com.zup.academy.mercado.livre.handler.dto.ErrosDeFormularioDto;

@RestControllerAdvice
public class ControllerHandler {

	private MessageSource messageSource;

	public ControllerHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrosDeFormularioDto>> erroValidacaoHandler(MethodArgumentNotValidException e) {
		List<ErrosDeFormularioDto> errosDto = new ArrayList<ErrosDeFormularioDto>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(erro -> {
			String message = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			errosDto.add(new ErrosDeFormularioDto(erro.getField(), message));
		});
		return ResponseEntity.badRequest().body(errosDto);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ErroDto> erroNaoEncontradoHandler(EntidadeNaoEncontradaException e) {
		ErroDto erroDto = new ErroDto(LocalDateTime.now(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroDto);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErroDto> erroNegocioHandler(NegocioException e) {
		ErroDto erroDto = new ErroDto(LocalDateTime.now(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
	}
	
	@ExceptionHandler(UsuarioNaoTemPermissaoException.class)
	public ResponseEntity<ErroDto> erroUsuarioNaoTemPermissaoHandler(UsuarioNaoTemPermissaoException e) {
		ErroDto erroDto = new ErroDto(LocalDateTime.now(), e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erroDto);
	}
}