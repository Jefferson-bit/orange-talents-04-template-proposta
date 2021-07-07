package microservico.relacao.de.proposta.excecao;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import feign.FeignException;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorDeExcecao {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidadorDeError> validatoError(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ValidadorDeError validatorError = new ValidadorDeError(LocalDateTime.now(), 
		HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), "Error Validator", request.getRequestURI());
		for (FieldError e  : ex.getBindingResult().getFieldErrors()) {
			String defaultMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			validatorError.addError(e.getField(), defaultMessage);
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validatorError);
	}	
	
	@ExceptionHandler(RecursoNaoEncontradoExcecao.class)
	public ResponseEntity<ErrorPadrao> recursoNaoEncontrado(RecursoNaoEncontradoExcecao ex,
			HttpServletRequest request) {
		ErrorPadrao validatorError = new ErrorPadrao(LocalDateTime.now(), 
		HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Not Found", request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(validatorError);
	}
}
