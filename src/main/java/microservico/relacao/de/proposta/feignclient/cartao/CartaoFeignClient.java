package microservico.relacao.de.proposta.feignclient.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${cliente.cartao.url}", name = "cartoes")
public interface CartaoFeignClient {

	@GetMapping(value = "/api/cartoes")
	CartaoFeignResponse consultaCartao(@RequestParam(name = "idProposta")Long id);
	
	@PostMapping(value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	BloqueioFeignResponse bloqueiaCartao(@PathVariable String id, @RequestBody BloqueioFeignRequest request);
	
	@PostMapping(value = "/api/cartoes/{idCartao}/avisos", consumes = "application/json")
	AvisoFeignResponse consultaAviso(@PathVariable String idCartao, @RequestBody AvisoFeignRequest request);
	
	@PostMapping(value = "/api/cartoes/{idCartao}/carteiras", consumes = "application/json")
	CarteiraFeignResponse consultaCarteira(@PathVariable String idCartao, @RequestBody CarteiraFeignRequest request);
	
}
