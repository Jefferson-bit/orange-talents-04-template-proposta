package microservico.relacao.de.proposta.feignclient.avaliacao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import microservico.relacao.de.proposta.cartao.CartaoRepository;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.feignclient.cartao.CartaoFeignClient;
import microservico.relacao.de.proposta.feignclient.cartao.CartaoFeignResponse;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@Component
public class AgendaCartao {

    private static final Logger LOG = LoggerFactory.getLogger(AgendaCartao.class);

    private final PropostaRepository propostaRepository;
    private final CartaoFeignClient cartaoFeignClient;
    private final CartaoRepository cartaoRepository;

    public AgendaCartao(PropostaRepository propostaRepository, CartaoFeignClient cartaoFeignClient,
                        CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoFeignClient = cartaoFeignClient;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedRateString = "${time.scheduled}")
    public void buscaCartaoPorProposta() {
        List<Proposta> listaDePropostas = propostaRepository
                .buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta.ELEGIVEL);

        LOG.info("Scheduled started");

        listaDePropostas.forEach(proposta -> {
            CartaoFeignResponse consultaCartao = cartaoFeignClient.consultaCartao(proposta.getId());
            var cartao = consultaCartao.toModel(proposta);
            cartaoRepository.save(cartao);

            proposta.setCartao(cartao);
            LOG.info("Proposta {} agora tem o cartão {}", proposta.getId(),
                    consultaCartao.getId().substring(0, 4) + "-****-****-****");
            propostaRepository.save(proposta);
        });
    }
}
