package microservico.relacao.de.proposta.avaliacao;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.feignclient.AvaliacaoFinanceiraFeignClient;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@RestController
public class AvaliacaoFinanceiraController {
	private AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira;
	private AvaliacaoRepository avaliacaoRepository;
	private PropostaRepository propostaRepository;

	public AvaliacaoFinanceiraController(AvaliacaoFinanceiraFeignClient verificaAnaliseFinanceira,
			AvaliacaoRepository avaliacaoRepository, PropostaRepository propostaRepository) {
		this.verificaAnaliseFinanceira = verificaAnaliseFinanceira;
		this.avaliacaoRepository = avaliacaoRepository;
		this.propostaRepository = propostaRepository;
	}

	@PostMapping(value = "/resultadoDaAvaliacao")
	@Transactional
	public ResponseEntity<?> consultaResultadoFinanceiro(@RequestBody AvaliacaoRequest request) {
		Optional<Proposta> propostaOptional = propostaRepository.findById(request.getIdProposta());
		try {
			AvaliacaoResponse consultandoAnalise = verificaAnaliseFinanceira.consultandoAnalise(request);
			Avaliacao avaliacao = consultandoAnalise.toModel(propostaOptional.get());
			StatusDaProposta status = consultandoAnalise.getResultadoSolicitacao().statusDaSolicitacao();
			propostaOptional.get().setResultadoSolicitacao(status);
			avaliacaoRepository.save(avaliacao);
			return ResponseEntity.ok(consultandoAnalise);
		} catch (FeignException.UnprocessableEntity ex) {
			return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_JSON).body(ex.contentUTF8());
		}
	}
}