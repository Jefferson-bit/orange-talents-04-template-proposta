package microservico.relacao.de.proposta.feignclient;

import javax.validation.constraints.NotBlank;

import microservico.relacao.de.proposta.bloqueio.Bloqueio;
import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.enums.StatusDoBloqueio;

public class BloqueioFeignRequest {
	
	@NotBlank(message = "Sistema responsavel não pode ser nulo")
	private String sistemaResponsavel;
	private StatusDoBloqueio resultado;
	
	public BloqueioFeignRequest() {
	}
	
	public Bloqueio toModel(Cartao cartao) {
		return new Bloqueio(sistemaResponsavel, resultado, cartao);
	}
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
