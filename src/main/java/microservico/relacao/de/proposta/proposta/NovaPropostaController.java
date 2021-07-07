package microservico.relacao.de.proposta.proposta;

import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignRequest;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignResponse;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFinanceiraFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	private PropostaRepository propostaRepository;
	private AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira;

	public NovaPropostaController(PropostaRepository propostaRepository,
			AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira) {
		this.propostaRepository = propostaRepository;
		this.verificaAnaliseFinanceira = verificaAnaliseFinanceira;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraProposta(@Valid @RequestBody NovaPropostaRequest request){
		Proposta proposta = request.toModel();

			proposta = propostaRepository.save(proposta);
			AvaliacaoFeignRequest requestAv = new AvaliacaoFeignRequest(proposta.getDocumento(), proposta.getNome(),
					proposta.getId());
			AvaliacaoFeignResponse consultandoAnalise = verificaAnaliseFinanceira.consultandoAnalise(requestAv);
			StatusDaProposta status = consultandoAnalise.status();
			proposta.setResultadoSolicitacao(status);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId())
				.toUri();
		return ResponseEntity.created(uri).body(request);
	}
}
