package microservico.relacao.de.proposta.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;
import io.micrometer.core.annotation.Counted;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.feignclient.AvaliacaoFinanceiraFeignClient;
import microservico.relacao.de.proposta.feignclient.AvaliacaoFeignRequest;
import microservico.relacao.de.proposta.feignclient.AvaliacaoFeignResponse;
import microservico.relacao.de.proposta.validacao.CampoUnico;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	@Autowired
	private CampoUnico campoUnico;
	private PropostaRepository propostaRepository;
	private AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira;

	public NovaPropostaController(PropostaRepository propostaRepository,
			AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira) {
		this.propostaRepository = propostaRepository;
		this.verificaAnaliseFinanceira = verificaAnaliseFinanceira;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(campoUnico);
	}

	
	@Counted

	public void counted() {
		
		
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> salvandoProposta(@Valid @RequestBody NovaPropostaRequest request) {
		Proposta proposta = request.toModel();
		try {
			proposta = propostaRepository.save(proposta);
			AvaliacaoFeignRequest requestAv = new AvaliacaoFeignRequest(proposta.getDocumento(), proposta.getNome(),
					proposta.getId());
			AvaliacaoFeignResponse consultandoAnalise = verificaAnaliseFinanceira.consultandoAnalise(requestAv);
			StatusDaProposta status = consultandoAnalise.status();
			proposta.setResultadoSolicitacao(status);
		
		} catch (FeignException.UnprocessableEntity ex) {
			return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_JSON).body(ex.contentUTF8());
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId())
				.toUri();
		return ResponseEntity.created(uri).body(request);
	}

}
