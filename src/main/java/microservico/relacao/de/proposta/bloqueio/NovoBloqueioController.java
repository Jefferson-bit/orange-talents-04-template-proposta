package microservico.relacao.de.proposta.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> cadastraBloqueio(
								@PathVariable String idCartao,
								@Valid @RequestBody BloqueioFeignRequest request,
								HttpServletRequest servletRequest) {
		try {
			Optional<Cartao> cartaoOptional = cartaoRepository.findBynumero(idCartao);
			Cartao cartao = cartaoOptional.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));
			
			BloqueioFeignResponse bloqueiaCartaoResponse = cartaoFeignClient.bloqueiaCartao(cartao.getNumero(), request);
			Bloqueio bloqueio = bloqueiaCartaoResponse.toModel(cartao, servletRequest);
			bloqueioRepository.save(bloqueio);
			
			return ResponseEntity.ok(bloqueiaCartaoResponse);
		} catch (FeignException.UnprocessableEntity ex) {
			return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_JSON).body(ex.contentUTF8());
		} catch (FeignException.BadRequest ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
