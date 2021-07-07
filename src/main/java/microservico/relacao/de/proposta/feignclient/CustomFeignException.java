package microservico.relacao.de.proposta.feignclient;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomFeignException implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Houve uma violação na requisição");
            case 404:
                return new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Recurso Não encontrado");
            case 422:
                return new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY, "Houve uma violação na regra de negócio");
            case 504:
                return new ResponseStatusException(
                        HttpStatus.GATEWAY_TIMEOUT, "Sem resposta do servidor upstream");
            case 503:
                return new ResponseStatusException(
                        HttpStatus.SERVICE_UNAVAILABLE, "O servidor está indisponível no momento");
            default:
                return new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado");
        }
    }
}
