package microservico.relacao.de.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url =  "${cliente.analise.financeira.url}", name = "consulta")
public interface VerificaAnaliseFinanceiraFeignClient {
	
	@PostMapping( value = "/api/solicitacao")
	VerificaAnaliseFinanceiraResponse consultandoAnalise(VerificaAnaliseFinanceiraRequest request);
}
