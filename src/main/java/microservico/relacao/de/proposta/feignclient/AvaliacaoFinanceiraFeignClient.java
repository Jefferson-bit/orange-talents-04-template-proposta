package microservico.relacao.de.proposta.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${cliente.avaliacao.financeira.url}", name = "consulta")
public interface AvaliacaoFinanceiraFeignClient {
	
	@PostMapping( value = "/api/solicitacao")
	AvaliacaoResponse consultandoAnalise(@RequestBody AvaliacaoRequest request);
}
