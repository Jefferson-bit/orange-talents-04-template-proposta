package microservico.relacao.de.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${cliente.cartao.url}", name = "cartoes")
public interface CartaoFeignClient {

	@GetMapping(value = "/api/cartoes")
	CartaoResponse consultaCartao(@RequestParam(name = "idProposta")Long id);
}
