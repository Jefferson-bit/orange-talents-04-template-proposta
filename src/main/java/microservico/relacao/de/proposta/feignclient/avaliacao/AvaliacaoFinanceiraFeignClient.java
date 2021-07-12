package microservico.relacao.de.proposta.feignclient.avaliacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${cliente.avaliacao.financeira.url}", name = "consulta")
public interface AvaliacaoFinanceiraFeignClient {
	
	@PostMapping( value = "/api/solicitacao")
	AvaliacaoFeignResponse consultandoAnalise(@RequestBody AvaliacaoFeignRequest request);
}
