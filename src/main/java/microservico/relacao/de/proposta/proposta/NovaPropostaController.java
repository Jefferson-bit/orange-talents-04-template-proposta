package microservico.relacao.de.proposta.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import microservico.relacao.de.proposta.validacao.CampoUnico;

@RestController
public class NovaPropostaController{

	@Autowired
	private CampoUnico campoUnico;
	private PropostaRepository propostaRepository;	
	public NovaPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(campoUnico);
	}
	
	@PostMapping(value = "/propostas")
	@Transactional
	public ResponseEntity<NovaPropostaRequest> salvandoProposta(@Valid @RequestBody NovaPropostaRequest request) {
		Proposta proposta = request.toModel();
		propostaRepository.save(proposta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(request);
	}

}
