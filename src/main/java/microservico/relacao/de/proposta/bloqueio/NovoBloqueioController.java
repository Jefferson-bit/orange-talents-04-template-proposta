package microservico.relacao.de.proposta.bloqueio;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
//	   @RequestMapping(value = "/username", method = RequestMethod.GET)
//	    public String currentUserNameSimple(HttpServletRequest request, @AuthenticationPrincipal Jwt jwt ) {
//		   	Authentication prin = SecurityContextHolder.getContext().getAuthentication();
//		   	String claimAsString = jwt.getClaimAsString("email");
//		   	System.out.println("Email: " + claimAsString);
//		   	System.out.println("Testando: " + prin.getName() + "  " + prin.getAuthorities());
//		    String name = SecurityContextHolder.getContext().getAuthentication().getName();
//		   	System.out.println("principal: " + name);
//	        Principal principal = request.getUserPrincipal();
//	        return principal.getName();
//	    }
	
	
	@PostMapping(value = "/{idCartao}")
	@PreAuthorize("#email == #principal.email")
	public ResponseEntity<?> cadastraBloqueio(
			@PathVariable String idCartao,
			@Valid @RequestBody BloqueioFeignRequest request,
			HttpServletRequest servletRequest,
			@AuthenticationPrincipal Jwt principal) {
			
		try {
			Optional<Cartao> cartaoOptional = cartaoRepository.findBynumero(idCartao);
			Cartao cartao = cartaoOptional.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));
			
			BloqueioFeignResponse bloqueiaCartaoResponse = cartaoFeignClient.bloqueiaCartao(cartao.getNumero(),
					request);
			Bloqueio bloqueio = bloqueiaCartaoResponse.toModel(cartao, servletRequest);
			bloqueioRepository.save(bloqueio);

			return ResponseEntity.ok(bloqueiaCartaoResponse);
		} catch (FeignException.UnprocessableEntity ex) {
			return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_JSON).body(ex.contentUTF8());
		}

	}

}
