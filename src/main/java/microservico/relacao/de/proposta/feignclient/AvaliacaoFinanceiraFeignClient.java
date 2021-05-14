package microservico.relacao.de.proposta.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import microservico.relacao.de.proposta.avaliacao.AvaliacaoRequest;
import microservico.relacao.de.proposta.avaliacao.AvaliacaoResponse;

@FeignClient(url = "${cliente.avaliacao.financeira.url}", name = "consulta")
public interface AvaliacaoFinanceiraFeignClient {
	
	@PostMapping( value = "/api/solicitacao")
	AvaliacaoResponse consultandoAnalise(@RequestBody AvaliacaoRequest request);
}
