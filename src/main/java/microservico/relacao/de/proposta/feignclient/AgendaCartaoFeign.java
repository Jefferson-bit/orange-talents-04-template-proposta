package microservico.relacao.de.proposta.feignclient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.cartao.CartaoRepository;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@Component
@RestController
@RequestMapping(value = "/listaProposta")
public class AgendaCartaoFeign {

	private static final Logger LOG = LoggerFactory.getLogger(AgendaCartaoFeign.class);

	private PropostaRepository propostaRepository;
	private CartaoFeignClient cartaoFeignClient;
	private CartaoRepository cartaoRepository;
	
	public AgendaCartaoFeign(PropostaRepository propostaRepository, CartaoFeignClient cartaoFeignClient,
			CartaoRepository cartaoRepository) {
		this.propostaRepository = propostaRepository;
		this.cartaoFeignClient = cartaoFeignClient;
		this.cartaoRepository = cartaoRepository;
	}
	
	@Scheduled(fixedRateString = "${time.scheduled}")
	public void buscaCartaoPorProposta() {
		List<Proposta> listaDePropostas = propostaRepository.buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta.ELEGIVEL);
			for (Proposta proposta : listaDePropostas) {
				
				CartaoFeignResponse consultaCartao = cartaoFeignClient.consultaCartao(proposta.getId());
				Cartao cartao = consultaCartao.toModel();
				cartaoRepository.save(cartao);
				
				proposta.setCartao(cartao);
				LOG.info("Proposta {} agora tem o cartão {}, cartao da entidade proposta {}", proposta.getId(),
						consultaCartao.getId().substring(0, 4) + "-****-****-****");
				propostaRepository.save(proposta);
			}
		
	}
}
