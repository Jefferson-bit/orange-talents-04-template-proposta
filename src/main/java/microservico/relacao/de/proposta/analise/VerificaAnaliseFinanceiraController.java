package microservico.relacao.de.proposta.analise;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;

@RestController
public class VerificaAnaliseFinanceiraController {

	private VerificaAnaliseFinanceiraFeignClient verificaAnaliseFinanceira;
	public VerificaAnaliseFinanceiraController(VerificaAnaliseFinanceiraFeignClient verificaAnaliseFinanceira) {
		this.verificaAnaliseFinanceira = verificaAnaliseFinanceira;
	}

	@PostMapping(value = "/resultado")
	@Transactional
	public ResponseEntity<?> consultaResultadoFinanceiro(@RequestBody VerificaAnaliseFinanceiraRequest request) {

		try {
			VerificaAnaliseFinanceiraResponse consultandoAnalise = verificaAnaliseFinanceira
					.consultandoAnalise(request);
			return ResponseEntity.ok(consultandoAnalise);
		} catch (FeignException.UnprocessableEntity ex) {
			return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_JSON).body(ex.contentUTF8());
		}
	}
}
