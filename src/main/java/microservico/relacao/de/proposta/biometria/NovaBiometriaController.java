package microservico.relacao.de.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.cartao.CartaoRepository;
import microservico.relacao.de.proposta.excecao.RecursoNaoEncontradoExcecao;
import microservico.relacao.de.proposta.utils.validacao.ValidaBase64;

@RestController
@RequestMapping(value = "/api/biometrias")
public class NovaBiometriaController {

	private CartaoRepository cartaoRepository;
	private BiometriaRepository biometriaRepository;
	
	public NovaBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
	}

	@PostMapping(value = "/{idCartao}")
	public ResponseEntity<?> cadastraBiometria(@Valid @RequestBody BiometriaRequest request,
			@PathVariable Long idCartao) {
		Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);
		Cartao cartao = cartaoOptional.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not found: " + idCartao));
		Biometria biometria = request.toModel(cartao);	
		if (ValidaBase64.isValidBase64(request.getFingerPrint())) {
			return ResponseEntity.badRequest().body("Biometria invália");
		}
		biometriaRepository.save(biometria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(biometria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(request);

	}
}
