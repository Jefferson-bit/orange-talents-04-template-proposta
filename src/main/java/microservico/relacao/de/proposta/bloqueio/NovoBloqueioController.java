package microservico.relacao.de.proposta.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;
import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.cartao.CartaoRepository;
import microservico.relacao.de.proposta.excecao.RecursoNaoEncontradoExcecao;
import microservico.relacao.de.proposta.feignclient.cartao.BloqueioFeignRequest;
import microservico.relacao.de.proposta.feignclient.cartao.BloqueioFeignResponse;
import microservico.relacao.de.proposta.feignclient.cartao.CartaoFeignClient;

@RestController
@RequestMapping(value = "/api/bloqueios")
public class NovoBloqueioController {


    private BloqueioRepository bloqueioRepository;
    private CartaoRepository cartaoRepository;
    private CartaoFeignClient cartaoFeignClient;

    public NovoBloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository,
                                  CartaoFeignClient cartaoFeignClient) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
        this.cartaoFeignClient = cartaoFeignClient;
    }

    @PostMapping(value = "/{idCartao}")
    @Transactional
    public ResponseEntity<BloqueioFeignResponse> cadastraBloqueio(
            @PathVariable String idCartao,
            @Valid @RequestBody BloqueioFeignRequest request,
            HttpServletRequest servletRequest) {

        Cartao cartao = cartaoRepository.findBynumero(idCartao)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));

        BloqueioFeignResponse bloqueiaCartaoResponse = cartaoFeignClient.bloqueiaCartao(cartao.getNumero(),
                request);

        bloqueioRepository.save(bloqueiaCartaoResponse.toModel(cartao, servletRequest));

        return ResponseEntity.ok(bloqueiaCartaoResponse);

    }

}
