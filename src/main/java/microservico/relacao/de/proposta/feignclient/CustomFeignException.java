package microservico.relacao.de.proposta.feignclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class CustomFeignException implements ErrorDecoder {
	private static final Logger log = LoggerFactory.getLogger(CustomFeignException.class);
	
	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			log.error("Status code " + response.status() + ", methodKey = " + methodKey,
					", causa " + "Houve uma violação na restrição");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()));
		case 404:
			log.error("Status code " + response.status() + ", methodKey = " + methodKey,
			", causa: " + "O valor requerido não foi encontrado");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()));
		case 503:
			log.error("Status code " + response.status() + ", methodKey = " + methodKey,
					", causa " + "O servidor está indisponivel no momento");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()));
		case 504:
			log.error("Status code " + response.status() + ", methodKey = " + methodKey,
					", causa " + "O servidor não recebeu nenhuma resposta");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()));
		default:
			return FeignException.InternalServerError.errorStatus(methodKey, response);
		}
	}

}