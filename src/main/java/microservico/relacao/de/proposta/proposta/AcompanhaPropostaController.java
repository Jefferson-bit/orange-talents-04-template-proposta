package microservico.relacao.de.proposta.proposta;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import microservico.relacao.de.proposta.excecao.RecursoNaoEncontradoExcecao;

@RestController
public class AcompanhaPropostaController {

	private PropostaRepository propostaRepository;

	public AcompanhaPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}
	
	@GetMapping(value = "/acompanhaProposta/{id}")
	public ResponseEntity<PropostaResponse> acompanhaProposta(@PathVariable Long id){
		Optional<Proposta> propostaOptional = propostaRepository.findById(id);
		Proposta proposta = propostaOptional.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Not Found: " + id));
		return ResponseEntity.ok(new PropostaResponse(proposta));
	}
}
