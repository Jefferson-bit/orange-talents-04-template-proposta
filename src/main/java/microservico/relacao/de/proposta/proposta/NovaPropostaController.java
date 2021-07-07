package microservico.relacao.de.proposta.proposta;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;
import microservico.relacao.de.proposta.enums.StatusDaProposta;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignRequest;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignResponse;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFinanceiraFeignClient;

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
	public ResponseEntity<?> cadastraProposta(@Valid @RequestBody NovaPropostaRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
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
