package microservico.relacao.de.proposta.carteira;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;
import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.cartao.CartaoRepository;
import microservico.relacao.de.proposta.excecao.RecursoNaoEncontradoExcecao;
import microservico.relacao.de.proposta.feignclient.cartao.CartaoFeignClient;
import microservico.relacao.de.proposta.feignclient.cartao.CarteiraFeignRequest;
import microservico.relacao.de.proposta.feignclient.cartao.CarteiraFeignResponse;

@RestController
@RequestMapping(value = "/api/carteiras")
public class NovaCarteiraController {

    private CartaoFeignClient cartaoFeignClient;
    private CartaoRepository cartaoRepository;
    private CarteiraRepository carteiraRepository;

    public NovaCarteiraController(CartaoFeignClient cartaoFeignClient, CartaoRepository cartaoRepository,
                                  CarteiraRepository carteiraRepository) {
        this.cartaoFeignClient = cartaoFeignClient;
        this.cartaoRepository = cartaoRepository;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<CarteiraFeignResponse> cadastraCarteira(@Valid @RequestBody CarteiraFeignRequest request,
                                              @PathVariable String idCartao) {

        Cartao cartao = cartaoRepository.findBynumero(idCartao)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));
        CarteiraFeignResponse consultaCarteira = cartaoFeignClient.consultaCarteira(cartao.getNumero(), request);

        Carteira carteira = request.toModel(cartao, consultaCarteira.getResultado(), consultaCarteira.getId());

        carteiraRepository.save(carteira);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(carteira.getId())
                .toUri();
        return ResponseEntity.created(uri).body(consultaCarteira);
    }
}
