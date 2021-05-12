package microservico.relacao.de.proposta.cartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@RestController
@RequestMapping(value = "/consultaCartao")
public class CartaoController {

	private static final Logger LOG = LoggerFactory.getLogger(CartaoController.class);

	private PropostaRepository propostaRepository;
	private CartaoFeignClient cartaoFeignClient;

	public CartaoController(PropostaRepository propostaRepository, CartaoFeignClient cartaoFeignClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoFeignClient = cartaoFeignClient;
	}

	@Scheduled(fixedRate = 300022)
	public void buscaCartaoPorProposta() {
		List<Proposta> listaDePropostas = propostaRepository
				.buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta.ELEGIVEL);
		for (Proposta proposta : listaDePropostas) {
			try {
				CartaoResponse consultaCartao = cartaoFeignClient.consultaCartao(proposta.getId());
				proposta.setNumeroDoCartao(consultaCartao.getId());
				LOG.info("Proposta {} agora tem o cartão {}", proposta.getId(),
						consultaCartao.getId().substring(0, 4) + "-****-****-****");
				propostaRepository.save(proposta);
			} catch (FeignException.BadRequest | FeignException.FeignServerException ex) {
				LOG.warn("Não foi possível atrelar um número do cartão para a proposta {}" + "Tente mais tarde",
						proposta.getId());
			}
		}
	}
}
