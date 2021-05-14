package microservico.relacao.de.proposta.feignclient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import feign.FeignException;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@Component
public class AgendaCartaoFeign {

	private static final Logger LOG = LoggerFactory.getLogger(AgendaCartaoFeign.class);

	private PropostaRepository propostaRepository;
	private CartaoFeignClient cartaoFeignClient;

	public AgendaCartaoFeign(PropostaRepository propostaRepository, CartaoFeignClient cartaoFeignClient) {
		this.propostaRepository = propostaRepository;
		this.cartaoFeignClient = cartaoFeignClient;
	}

	@Scheduled(fixedRate = 300022)
	public void buscaCartaoPorProposta() {

		List<Proposta> listaDePropostas = propostaRepository
				.buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta.ELEGIVEL);
		try {
			for (Proposta proposta : listaDePropostas) {
				LOG.info("Chego o numero");
				CartaoFeignResponse consultaCartao = cartaoFeignClient.consultaCartao(proposta.getId());
				proposta.setNumeroDoCartao(consultaCartao.getId());
				LOG.info("Proposta {} agora tem o cartão {}, cartao da entidade proposta {}", proposta.getId(),
						consultaCartao.getId().substring(0, 4) + "-****-****-****");
				propostaRepository.save(proposta);
			}
		} catch (FeignException.BadRequest | FeignException.FeignServerException ex) {
			LOG.warn("Não foi possível atrelar um número de cartão a proposta, tente mais tarde");
		}
	}
}
