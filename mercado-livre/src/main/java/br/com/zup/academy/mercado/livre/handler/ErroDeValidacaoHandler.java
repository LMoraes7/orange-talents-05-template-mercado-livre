package br.com.zup.academy.mercado.livre.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	private MessageSource messageSource;

	public ErroDeValidacaoHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrosDeFormularioDto> handler(MethodArgumentNotValidException e) {
		List<ErrosDeFormularioDto> errosDto = new ArrayList<ErrosDeFormularioDto>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(erro -> {
			String message = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			errosDto.add(new ErrosDeFormularioDto(erro.getField(), message));
		});
		return errosDto;
	}
}