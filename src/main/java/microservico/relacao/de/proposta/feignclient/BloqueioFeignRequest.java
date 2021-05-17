package microservico.relacao.de.proposta.feignclient;

import javax.validation.constraints.NotBlank;

public class BloqueioFeignRequest {
	
	@NotBlank(message = "Sistema responsavel não pode ser nulo")
	private String sistemaResponsavel;
	public BloqueioFeignRequest() {
	}
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}
