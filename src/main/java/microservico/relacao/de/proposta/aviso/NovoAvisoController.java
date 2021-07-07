package microservico.relacao.de.proposta.aviso;

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
import microservico.relacao.de.proposta.feignclient.cartao.AvisoFeignRequest;
import microservico.relacao.de.proposta.feignclient.cartao.AvisoFeignResponse;
import microservico.relacao.de.proposta.feignclient.cartao.CartaoFeignClient;

@RestController
@RequestMapping(value = "/api/avisos")
public class NovoAvisoController {

    private CartaoFeignClient cartaoFeignClient;
    private AvisoRepository avisoRepository;
    private CartaoRepository cartaoRepository;

    public NovoAvisoController(CartaoFeignClient cartaoFeignClient, AvisoRepository avisoRepository,
                               CartaoRepository cartaoRepository) {
        this.cartaoFeignClient = cartaoFeignClient;
        this.avisoRepository = avisoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<AvisoFeignResponse> cadastraAvisoViagem(@PathVariable String idCartao,
                                                 @Valid @RequestBody AvisoFeignRequest request,
                                                 HttpServletRequest servletRequest) {

        Cartao cartao = cartaoRepository.findBynumero(idCartao)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));
        AvisoFeignResponse consultaAviso = cartaoFeignClient.consultaAviso(cartao.getNumero(), request);

        avisoRepository.save(request.toModel(cartao, servletRequest));
        return ResponseEntity.ok(consultaAviso);
    }
}
