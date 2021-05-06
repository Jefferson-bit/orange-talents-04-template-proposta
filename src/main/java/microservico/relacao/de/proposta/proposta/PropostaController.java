package microservico.relacao.de.proposta.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PropostaController {

	private PropostaRepository propostaRepository;

	public PropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}
	
	@PostMapping(value = "/propostas")
	public ResponseEntity<PropostaRequest> saveProposta(@Valid @RequestBody PropostaRequest request){
		Proposta proposta = request.toModel();
		propostaRepository.save(proposta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(request);
	}
}
